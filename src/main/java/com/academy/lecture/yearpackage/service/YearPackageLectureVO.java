package com.academy.lecture.yearpackage.service;

import com.academy.common.CommonVO;

/**
 * YearPackageLectureVO.java
 * 연회원 패키지 강의 Value Object
 */
public class YearPackageLectureVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 강의코드 */
    private String leccode;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 학습형태 코드 */
    private String learningCd;

    /** 강의유형 선택 */
    private String lecTypeChoice;

    /** 과목코드 */
    private String subjectSjtCd;

    /** 할인율 */
    private String discount;

    /** 검색유형 */
    private String searchType;

    /** 검색어 */
    private String searchText;

    /** 카테고리 검색 코드 */
    private String searchCategoryCode;

    /** 과목 검색 코드 */
    private String searchSubjectCode;

    /** 탑메뉴 */
    private String topMenu;

    /** 탑메뉴명 */
    private String topMenuName;

    /** 탑메뉴타입 */
    private String topMenuType;

    /** 왼쪽메뉴타입 */
    private String leftMenuLType;

    /** 강좌종류 */
    private String lecKType;

    /** 주문번호 */
    private String orderNo;

    /** 추가일수 */
    private String day;

    /** 무료여부 */
    private String free;

    /** 이벤트번호 */
    private String eventNo;

    /** 텍스트 */
    private String txt;

    // Getters and Setters
    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchCategoryCode() {
        return searchCategoryCode;
    }

    public void setSearchCategoryCode(String searchCategoryCode) {
        this.searchCategoryCode = searchCategoryCode;
    }

    public String getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setSearchSubjectCode(String searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getTopMenuName() {
        return topMenuName;
    }

    public void setTopMenuName(String topMenuName) {
        this.topMenuName = topMenuName;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getLeftMenuLType() {
        return leftMenuLType;
    }

    public void setLeftMenuLType(String leftMenuLType) {
        this.leftMenuLType = leftMenuLType;
    }

    public String getLecKType() {
        return lecKType;
    }

    public void setLecKType(String lecKType) {
        this.lecKType = lecKType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getEventNo() {
        return eventNo;
    }

    public void setEventNo(String eventNo) {
        this.eventNo = eventNo;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
