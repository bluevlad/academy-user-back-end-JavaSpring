package com.academy.mypage.service;

import com.academy.common.CommonVO;

/**
 * MypageVO.java
 * 마이페이지 관련 Value Object
 */
public class MypageVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사용자 ID */
    private String userId;

    /** 사용자명 */
    private String userNm;

    /** 전화번호 */
    private String telNo;

    /** 휴대폰번호 */
    private String phoneNo;

    /** 이메일 */
    private String email;

    /** 주소1 */
    private String address1;

    /** 주소2 */
    private String address2;

    /** 우편번호 */
    private String zipCode;

    /** 카테고리 코드 */
    private String categoryCode;

    /** 가입 채널 */
    private String joinChannel;

    /** 직업 */
    private String job;

    /** SMS 수신 동의 */
    private String isokSms;

    /** 이메일 수신 동의 */
    private String isokEmail;

    /** 비밀번호 */
    private String userPwd;

    /** 주문번호 */
    private String orderNo;

    /** 패키지번호 */
    private String packageNo;

    /** 강좌번호 */
    private String lectureNo;

    /** 강좌코드 */
    private String lecCode;

    /** 아이템ID */
    private String itemId;

    /** 관리번호 */
    private String mgntNo;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getJoinChannel() {
        return joinChannel;
    }

    public void setJoinChannel(String joinChannel) {
        this.joinChannel = joinChannel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIsokSms() {
        return isokSms;
    }

    public void setIsokSms(String isokSms) {
        this.isokSms = isokSms;
    }

    public String getIsokEmail() {
        return isokEmail;
    }

    public void setIsokEmail(String isokEmail) {
        this.isokEmail = isokEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMgntNo() {
        return mgntNo;
    }

    public void setMgntNo(String mgntNo) {
        this.mgntNo = mgntNo;
    }
}
