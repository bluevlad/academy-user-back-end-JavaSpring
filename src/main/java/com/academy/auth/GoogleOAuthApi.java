package com.academy.auth;

import com.academy.common.CORSFilter;
import com.academy.common.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * GoogleOAuthApi.java
 * Google OAuth 관리자 로그인 REST API Controller
 */
@Tag(name = "Auth", description = "Google OAuth 관리자 인증 API")
@RestController
@RequestMapping("/api/auth")
public class GoogleOAuthApi extends CORSFilter {

    private static final Logger log = LoggerFactory.getLogger(GoogleOAuthApi.class);

    private final JwtUtil jwtUtil;
    private final GoogleIdTokenVerifier verifier;
    private final Set<String> superAdminEmails;

    @Autowired
    public GoogleOAuthApi(
            JwtUtil jwtUtil,
            @Value("${google.client.id:}") String googleClientId,
            @Value("${app.super-admin-emails:}") String superAdminEmailsCsv) {
        this.jwtUtil = jwtUtil;

        // Google ID Token Verifier 초기화
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        // 슈퍼 관리자 이메일 목록 파싱
        this.superAdminEmails = new HashSet<>();
        if (superAdminEmailsCsv != null && !superAdminEmailsCsv.isBlank()) {
            for (String email : superAdminEmailsCsv.split(",")) {
                String trimmed = email.trim().toLowerCase();
                if (!trimmed.isEmpty()) {
                    this.superAdminEmails.add(trimmed);
                }
            }
        }

        log.info("GoogleOAuthApi initialized - clientId={}, superAdminEmails={}",
                googleClientId != null && !googleClientId.isBlank() ? "SET" : "NOT SET",
                this.superAdminEmails.size());
    }

    /**
     * Google ID Token 검증 및 관리자 JWT 발급
     */
    @Operation(summary = "Google OAuth 관리자 로그인",
            description = "Google ID Token을 검증하고, 허용된 관리자 이메일이면 JWT 토큰을 발급합니다.")
    @PostMapping("/google/verify")
    public JSONObject verifyGoogleToken(@RequestBody Map<String, String> request) {

        HashMap<String, Object> jsonObject = new HashMap<>();
        String idTokenString = request.get("idToken");

        if (idTokenString == null || idTokenString.isBlank()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "ID Token이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // Google ID Token 검증
            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "유효하지 않은 Google 토큰입니다.");
                return new JSONObject(jsonObject);
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");

            log.info("Google OAuth login attempt - email={}", email);

            // 슈퍼 관리자 이메일 체크
            if (!superAdminEmails.contains(email.toLowerCase())) {
                log.warn("Google OAuth denied - email={} is not a super admin", email);
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "관리자 권한이 없는 계정입니다.");
                return new JSONObject(jsonObject);
            }

            // JWT 토큰 생성 (ADMIN 역할)
            String token = jwtUtil.generateToken(email, "ADMIN");

            log.info("Google OAuth admin login success - email={}", email);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("token", token);
            jsonObject.put("userId", email);
            jsonObject.put("userName", name != null ? name : email);
            jsonObject.put("userRole", "ADMIN");
            jsonObject.put("picture", picture);
            jsonObject.put("message", "관리자 로그인에 성공했습니다.");

        } catch (Exception e) {
            log.error("Google OAuth verification failed", e);
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "Google 인증 처리 중 오류가 발생했습니다.");
        }

        return new JSONObject(jsonObject);
    }
}
