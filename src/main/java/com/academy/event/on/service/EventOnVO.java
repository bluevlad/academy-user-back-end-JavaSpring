package com.academy.event.on.service;

import com.academy.common.CommonVO;

/**
 * EventOnVO.java
 * 온라인 동영상 이벤트 Value Object
 */
public class EventOnVO extends CommonVO {

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

    /** 강좌 코드 */
    private String lecCode;

    /** 댓글 내용 */
    private String commentText;

    /** 댓글 SEQ */
    private String seq;

    /** 조회수 증가 여부 */
    private String incType;

    /** 출석 체크 일자 */
    private String attendanceDate;

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

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getIncType() {
        return incType;
    }

    public void setIncType(String incType) {
        this.incType = incType;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}
