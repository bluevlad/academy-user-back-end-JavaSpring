package com.academy.lecture.service;

import com.academy.common.CommonVO;

/**
 * LectureVO.java
 * 강의 관련 Value Object
 */
public class LectureVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 강의코드 */
    private String leccode;

    /** 브릿지 강의코드 */
    private String bridgeLeccode;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 학습형태 코드 */
    private String learningCd;

    /** 강의유형 선택 */
    private String lecTypeChoice;

    /** 과목코드 */
    private String subjectSjtCd;

    /** 강좌명 */
    private String subjectTitle;

    /** 강좌설명 */
    private String subjectDesc;

    /** 강좌메모 */
    private String subjectMemo;

    /** 강사ID */
    private String subjectTeacher;

    /** 가격 */
    private String subjectPrice;

    /** 할인율 */
    private String subjectDiscount;

    /** 수강기간 */
    private String subjectPeriod;

    /** 동영상구분 */
    private String movieType;

    /** 검색어 */
    private String searchText;

    /** 검색유형 */
    private String searchType;

    /** 검색시리즈코드 */
    private String searchSeriesCode;

    /** 검색년월 */
    private String searchYearMonth;

    /** 무료탭 */
    private String freeTab;

    /** 탑메뉴 */
    private String topMenu;

    /** 사용자ID */
    private String userId;

    /** 교재ID */
    private String rscId;

    /** 교재타입 */
    private String rscType;

    /** 교재수량 */
    private String bookCount;

    /** MST강의코드 */
    private String mstLeccode;

    /** 장바구니 종류 */
    private String kindType;

    // Getters and Setters
    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
    }

    public String getBridgeLeccode() {
        return bridgeLeccode;
    }

    public void setBridgeLeccode(String bridgeLeccode) {
        this.bridgeLeccode = bridgeLeccode;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectMemo() {
        return subjectMemo;
    }

    public void setSubjectMemo(String subjectMemo) {
        this.subjectMemo = subjectMemo;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(String subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public String getSubjectPeriod() {
        return subjectPeriod;
    }

    public void setSubjectPeriod(String subjectPeriod) {
        this.subjectPeriod = subjectPeriod;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSearchYearMonth() {
        return searchYearMonth;
    }

    public void setSearchYearMonth(String searchYearMonth) {
        this.searchYearMonth = searchYearMonth;
    }

    public String getFreeTab() {
        return freeTab;
    }

    public void setFreeTab(String freeTab) {
        this.freeTab = freeTab;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getRscType() {
        return rscType;
    }

    public void setRscType(String rscType) {
        this.rscType = rscType;
    }

    public String getBookCount() {
        return bookCount;
    }

    public void setBookCount(String bookCount) {
        this.bookCount = bookCount;
    }

    public String getMstLeccode() {
        return mstLeccode;
    }

    public void setMstLeccode(String mstLeccode) {
        this.mstLeccode = mstLeccode;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }
}
