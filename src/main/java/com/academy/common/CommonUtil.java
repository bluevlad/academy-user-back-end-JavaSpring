package com.academy.common;

import jakarta.servlet.http.HttpServletRequest;

/**
 * CommonUtil.java
 * 공통 유틸리티 클래스
 */
public class CommonUtil {

    /**
     * null 값을 빈 문자열로 변환
     */
    public static String nullToBlank(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * null 값을 기본값으로 변환
     */
    public static String isNull(String str, String defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        return str.trim();
    }

    /**
     * null 값을 빈 문자열로 변환
     */
    public static String isNull(String str) {
        return isNull(str, "");
    }

    /**
     * 클라이언트 IP 주소 조회
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 현재 타임스탬프 조회 (yyyy-MM-dd HH:mm:ss)
     */
    public static String getCurrentTimestamp() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }

    /**
     * 문자열이 숫자인지 확인
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 문자열을 정수로 변환 (기본값 0)
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    /**
     * 문자열을 정수로 변환 (기본값 지정)
     */
    public static int parseInt(String str, int defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
