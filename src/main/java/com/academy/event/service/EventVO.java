package com.academy.event.service;

import com.academy.common.CommonVO;

/**
 * EventVO.java
 * 이벤트 관련 Value Object
 */
public class EventVO extends CommonVO {

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

    /** 온/오프라인 구분 */
    private String onoffDiv;

    /** 이벤트 타입 */
    private String eventType;

    /** 검색 유형 */
    private String searchKind;

    /** 검색어 */
    private String searchText;

    /** 진행중 목록 여부 */
    private String searchingList;

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

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
}
