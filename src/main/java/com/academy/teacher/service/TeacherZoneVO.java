package com.academy.teacher.service;

import com.academy.common.CommonVO;

/**
 * TeacherZoneVO.java
 * 교수 관리 페이지(Teacher Zone) Value Object
 */
public class TeacherZoneVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 강의 코드 */
    private String lecCode;

    /** 강의 유형 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 강의 타입 (D: 단과, N/J/P: 종합반, Y: 프리패스) */
    private String lecType;

    /** 메뉴 타입 */
    private String menuType;

    /** 좌측 메뉴 타입 */
    private String lMenuType;

    /** 학습 형태 코드 */
    private String learningCd;

    /** 검색 시작일 */
    private String searchStartDate;

    /** 검색 종료일 */
    private String searchEndDate;

    /** 조회 시작일 */
    private String searchViewStartDate;

    /** 조회 종료일 */
    private String searchViewEndDate;

    /** 카테고리 검색 */
    private String searchCategory;

    /** 학습 형태 검색 */
    private String searchLearningCd;

    /** 강의 타입 검색 */
    private String searchLecType;

    /** 과목 검색 */
    private String searchSubject;

    /** 과목 코드 검색 */
    private String searchSubjectCd;

    /** 검색어 */
    private String searchText;

    /** 사용자 검색 */
    private String searchUser;

    /** 마스터 강의 코드 */
    private String mstLecCode;

    /** 로그 카운트 */
    private String logCnt;

    // Getters and Setters
    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getLecType() {
        return lecType;
    }

    public void setLecType(String lecType) {
        this.lecType = lecType;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getlMenuType() {
        return lMenuType;
    }

    public void setlMenuType(String lMenuType) {
        this.lMenuType = lMenuType;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getSearchViewStartDate() {
        return searchViewStartDate;
    }

    public void setSearchViewStartDate(String searchViewStartDate) {
        this.searchViewStartDate = searchViewStartDate;
    }

    public String getSearchViewEndDate() {
        return searchViewEndDate;
    }

    public void setSearchViewEndDate(String searchViewEndDate) {
        this.searchViewEndDate = searchViewEndDate;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchLearningCd() {
        return searchLearningCd;
    }

    public void setSearchLearningCd(String searchLearningCd) {
        this.searchLearningCd = searchLearningCd;
    }

    public String getSearchLecType() {
        return searchLecType;
    }

    public void setSearchLecType(String searchLecType) {
        this.searchLecType = searchLecType;
    }

    public String getSearchSubject() {
        return searchSubject;
    }

    public void setSearchSubject(String searchSubject) {
        this.searchSubject = searchSubject;
    }

    public String getSearchSubjectCd() {
        return searchSubjectCd;
    }

    public void setSearchSubjectCd(String searchSubjectCd) {
        this.searchSubjectCd = searchSubjectCd;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }

    public String getMstLecCode() {
        return mstLecCode;
    }

    public void setMstLecCode(String mstLecCode) {
        this.mstLecCode = mstLecCode;
    }

    public String getLogCnt() {
        return logCnt;
    }

    public void setLogCnt(String logCnt) {
        this.logCnt = logCnt;
    }
}
