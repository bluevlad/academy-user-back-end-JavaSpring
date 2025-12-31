package com.academy.login.service;

import com.academy.common.CommonVO;

/**
 * LoginVO.java
 * 로그인/회원 관련 Value Object
 */
public class LoginVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사용자 비밀번호 */
    private String userPwd;

    /** 사용자 이름 */
    private String userNm;

    /** 사용자 이메일 */
    private String userEmail;

    /** 사용자 전화번호 */
    private String userPhone;

    /** 사용자 역할 */
    private String userRole;

    /** 생년월일 */
    private String birthday;

    /** 카테고리 코드 */
    private String categoryCode;

    /** 사용여부 */
    private String isuse;

    /** 사용자 포인트 */
    private String userPoint;

    /** 가입 채널 */
    private String joinChannel;

    /** 사용자 IP */
    private String userIp;

    /** 검색 타입 (아이디/비밀번호 찾기) */
    private String searchtype;

    /** 검색 아이디 */
    private String searchid;

    /** 이름 (검색용) */
    private String name;

    // Getters and Setters
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getJoinChannel() {
        return joinChannel;
    }

    public void setJoinChannel(String joinChannel) {
        this.joinChannel = joinChannel;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSearchid() {
        return searchid;
    }

    public void setSearchid(String searchid) {
        this.searchid = searchid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
