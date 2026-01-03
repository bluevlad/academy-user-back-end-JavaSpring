package com.academy.mypage.mylecture.service;

import com.academy.common.CommonVO;

public class MyLectureVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String mockCode;
    private String lecCode;
    private String itemId;
    private String identyId;
    private String searchYear;
    private String searchMonth;
    private String pageCmd;
    private String lectureNo;
    private String orderNo;
    private String playYn;

    // Getters and Setters
    public String getMockCode() {
        return mockCode;
    }

    public void setMockCode(String mockCode) {
        this.mockCode = mockCode;
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

    public String getIdentyId() {
        return identyId;
    }

    public void setIdentyId(String identyId) {
        this.identyId = identyId;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchMonth() {
        return searchMonth;
    }

    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }

    public String getPageCmd() {
        return pageCmd;
    }

    public void setPageCmd(String pageCmd) {
        this.pageCmd = pageCmd;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPlayYn() {
        return playYn;
    }

    public void setPlayYn(String playYn) {
        this.playYn = playYn;
    }
}
