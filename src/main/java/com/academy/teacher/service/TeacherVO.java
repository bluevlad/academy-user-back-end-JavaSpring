package com.academy.teacher.service;

import com.academy.common.CommonVO;

/**
 * TeacherVO.java
 * 교수 관련 Value Object
 */
public class TeacherVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 카테고리 코드 */
    private String searchCategoryCode;

    /** 과목 코드 */
    private String searchSubjectCode;

    /** 사용자 ID (교수) */
    private String searchUserId;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 상단 메뉴 코드 */
    private String topMenu;

    /** 직렬 코드 */
    private String searchSeriesCode;

    /** 학습 형태 코드 */
    private String searchLearningCd;

    /** 강의 유형 */
    private String lecTypeChoice;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 검색어 */
    private String searchText;

    /** 검색 종류 */
    private String searchKind;

    // Getters and Setters
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

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSearchLearningCd() {
        return searchLearningCd;
    }

    public void setSearchLearningCd(String searchLearningCd) {
        this.searchLearningCd = searchLearningCd;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getBoardMngSeq() {
        return boardMngSeq;
    }

    public void setBoardMngSeq(String boardMngSeq) {
        this.boardMngSeq = boardMngSeq;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }
}
