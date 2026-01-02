package com.academy.event.off.service;

import com.academy.common.CommonVO;

/**
 * EventOffVO.java
 * 오프라인 이벤트(설명회) Value Object
 */
public class EventOffVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 이벤트 번호 */
    private String eventNo;

    /** 제목 */
    private String title;

    /** 시작일 */
    private String startDate;

    /** 시작시간 */
    private String startTime;

    /** 종료일 */
    private String endDate;

    /** 종료시간 */
    private String endTime;

    /** 공개 여부 */
    private String openYn;

    /** 검색 유형 */
    private String searchKind;

    /** 검색어 */
    private String searchText;

    /** 진행중 목록 여부 */
    private String searchingList;

    /** 옵션1 SEQ (참여 선택항목) */
    private String option1Seq;

    /** 이름 */
    private String userName;

    /** 전화번호 */
    private String phoneNo;

    /** 이메일 */
    private String email;

    /** 장소 */
    private String location;

    /** SMS 수신 동의 */
    private String smsAgree;

    /** 조회수 증가 여부 */
    private String incType;

    // Getters and Setters
    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchingList() {
        return searchingList;
    }

    public void setSearchingList(String searchingList) {
        this.searchingList = searchingList;
    }

    public String getOption1Seq() {
        return option1Seq;
    }

    public void setOption1Seq(String option1Seq) {
        this.option1Seq = option1Seq;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSmsAgree() {
        return smsAgree;
    }

    public void setSmsAgree(String smsAgree) {
        this.smsAgree = smsAgree;
    }

    public String getIncType() {
        return incType;
    }

    public void setIncType(String incType) {
        this.incType = incType;
    }
}
