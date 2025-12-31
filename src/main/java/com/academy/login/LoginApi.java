package com.academy.login;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.JwtUtil;
import com.academy.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * LoginApi.java
 * 로그인/회원 관련 REST API Controller
 */
@Tag(name = "Login", description = "로그인/회원 관리 API")
@RestController
@RequestMapping("/api/login")
public class LoginApi extends CORSFilter {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginApi(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 로그인
     */
    @Operation(summary = "로그인", description = "사용자 로그인을 처리하고 JWT 토큰을 발급합니다.")
    @PostMapping("/login")
    public JSONObject login(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = CommonUtil.isNull(params.get("USER_ID"), "");
        String userPwd = CommonUtil.isNull(params.get("userPwd"), "");

        if (userId.isEmpty() || userPwd.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "아이디와 비밀번호를 입력해주세요.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("userPwd", userPwd);

        HashMap<String, Object> loginInfo = loginService.login(params);

        if (loginInfo == null) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "존재하지 않는 사용자입니다.");
            return new JSONObject(jsonObject);
        }

        String dbPassword = (String) loginInfo.get("USER_PWD");
        String tempPassword = (String) loginInfo.get("TEMP_USER_PWD");

        if (dbPassword == null || !dbPassword.equals(tempPassword)) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "비밀번호가 일치하지 않습니다.");
            return new JSONObject(jsonObject);
        }

        // JWT 토큰 생성
        String userRole = (String) loginInfo.get("USER_ROLE");
        String token = jwtUtil.generateToken(userId, userRole);

        // 세션에 사용자 정보 저장
        HttpSession session = request.getSession();
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("USER_ID", userId);
        userInfo.put("USER_NM", (String) loginInfo.get("USER_NM"));
        userInfo.put("USER_ROLE", userRole);
        session.setAttribute("userInfo", userInfo);

        // 로그인 로그 저장
        params.put("USER_IP", CommonUtil.getClientIP(request));
        loginService.loginLogInsert(params);

        jsonObject.put("retMsg", "OK");
        jsonObject.put("token", token);
        jsonObject.put("userId", userId);
        jsonObject.put("userName", loginInfo.get("USER_NM"));
        jsonObject.put("userRole", userRole);
        jsonObject.put("message", "로그인에 성공했습니다.");

        return new JSONObject(jsonObject);
    }

    /**
     * 로그아웃
     */
    @Operation(summary = "로그아웃", description = "사용자 로그아웃을 처리합니다.")
    @PostMapping("/logout")
    public JSONObject logout(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("retMsg", "OK");
        jsonObject.put("message", "로그아웃되었습니다.");

        return new JSONObject(jsonObject);
    }

    /**
     * 회원 ID 중복 체크
     */
    @Operation(summary = "회원 ID 중복 체크", description = "회원가입 시 ID 중복 여부를 확인합니다.")
    @GetMapping("/checkId")
    public JSONObject checkId(
            @Parameter(description = "사용자 ID", required = true) @RequestParam String userId) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("USER_ID", userId);

        int count = loginService.memberIdCheck(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        if (count > 0) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("available", false);
            jsonObject.put("message", "이미 사용 중인 아이디입니다.");
        } else {
            jsonObject.put("retMsg", "OK");
            jsonObject.put("available", true);
            jsonObject.put("message", "사용 가능한 아이디입니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 카테고리 목록 조회
     */
    @Operation(summary = "카테고리 목록 조회", description = "회원가입에 사용되는 카테고리 목록을 조회합니다.")
    @GetMapping("/getCategory")
    public JSONObject getCategory() throws Exception {

        HashMap<String, String> params = new HashMap<>();
        List<HashMap<String, Object>> resultList = loginService.getCategory(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("categoryList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/register")
    public JSONObject register(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 필수 항목 체크
        String userId = CommonUtil.isNull(params.get("USER_ID"), "");
        String userPwd = CommonUtil.isNull(params.get("USER_PWD"), "");
        String userNm = CommonUtil.isNull(params.get("USER_NM"), "");

        if (userId.isEmpty() || userPwd.isEmpty() || userNm.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "필수 항목을 입력해주세요.");
            return new JSONObject(jsonObject);
        }

        // ID 중복 체크
        int count = loginService.memberIdCheck(params);
        if (count > 0) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이미 사용 중인 아이디입니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("USER_ROLE", "USER");
            params.put("ISUSE", "Y");
            params.put("USER_POINT", "0");
            params.put("JOIN_CHANNEL", "WEB");

            loginService.userInsertProcess(params);

            // 카테고리 등록
            if (params.get("CATEGORY_CODE") != null && !params.get("CATEGORY_CODE").isEmpty()) {
                loginService.userCategoryInsert(params);
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "회원가입이 완료되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "회원가입에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 아이디 찾기
     */
    @Operation(summary = "아이디 찾기", description = "이름, 생년월일, 이메일로 아이디를 찾습니다.")
    @PostMapping("/findId")
    public JSONObject findId(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        params.put("SEARCHTYPE", "INFO");

        HashMap<String, Object> result = loginService.getSearchId(params);

        if (result == null || result.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "일치하는 회원 정보가 없습니다.");
        } else {
            String userId = (String) result.get("USER_ID");
            // ID 마스킹 처리
            String maskedId = userId.substring(0, 3) + "***";
            jsonObject.put("retMsg", "OK");
            jsonObject.put("userId", maskedId);
            jsonObject.put("message", "아이디를 찾았습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 비밀번호 찾기
     */
    @Operation(summary = "비밀번호 찾기", description = "아이디와 이름으로 비밀번호를 찾습니다.")
    @PostMapping("/findPwd")
    public JSONObject findPwd(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        params.put("SEARCHTYPE", "INFO");
        params.put("SEARCHID", params.get("USER_ID"));
        params.put("NAME", params.get("USER_NM"));

        HashMap<String, Object> result = loginService.getSearchPW(params);

        if (result == null || result.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "일치하는 회원 정보가 없습니다.");
        } else {
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "임시 비밀번호가 이메일로 발송되었습니다.");
        }

        return new JSONObject(jsonObject);
    }

}
