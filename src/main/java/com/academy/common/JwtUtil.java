package com.academy.common;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret:MySecretKeyForJwtTokenMySecretKeyForJwtToken}")
    private String secretKey;

    @Value("${jwt.expiration:3600000}")
    private long expirationTime;

    private static final String CLAIM_USER_ROLE = "userRole";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * JWT 토큰 생성 (기존 호환성 유지)
     */
    public String generateToken(String username) {
        return generateToken(username, null);
    }

    /**
     * JWT 토큰 생성 (userRole 포함)
     */
    public String generateToken(String username, String userRole) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime));

        if (userRole != null && !userRole.isEmpty()) {
            builder.claim(CLAIM_USER_ROLE, userRole);
        }

        return builder.signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT 토큰에서 사용자명 추출
     */
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * JWT 토큰에서 userRole 추출
     */
    public String extractUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.get(CLAIM_USER_ROLE, String.class);
    }

    /**
     * JWT 토큰에서 Claims 추출
     */
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 토큰 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * JWT 토큰 검증 및 USER 권한 확인
     */
    public boolean validateUserToken(String token) {
        try {
            if (!validateToken(token)) {
                return false;
            }
            String userRole = extractUserRole(token);
            return "USER".equals(userRole) || "ADMIN".equals(userRole);
        } catch (Exception e) {
            return false;
        }
    }
}
