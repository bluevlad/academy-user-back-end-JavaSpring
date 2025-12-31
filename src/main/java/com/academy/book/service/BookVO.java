package com.academy.book.service;

import com.academy.common.CommonVO;

/**
 * BookVO.java
 * 도서 관련 Value Object
 */
public class BookVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 리소스 ID */
    private String rscId;

    /** SEQ */
    private String seq;

    /** 카테고리 코드 */
    private String topMenu;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 검색 폼 (학습형태) */
    private String searchForm;

    /** 검색 종류 (카테고리) */
    private String searchKind;

    /** 검색 과목코드#강사ID */
    private String searchStjCode;

    /** 검색 직렬 코드 */
    private String searchSeriesCode;

    /** 검색 과목 코드 */
    private String searchSubjectCode;

    /** 검색 강사 코드 */
    private String searchPrfCode;

    /** 검색 유형 (1:도서명, 2:저자명, 3:출판사) */
    private String searchType;

    /** 검색어 */
    private String searchText;

    /** 메인 여부 */
    private String isMain;

    /** 도서 플래그 */
    private String bookFlag;

    /** 강사 ID */
    private String searchUserId;

    /** 수강생교재 표시 여부 */
    private String studentBookDispYn;

    /** 강좌코드 */
    private String lecCode;

    /** 장바구니 수량 */
    private String bookCount;

    /** KIND_TYPE */
    private String kindType;

    /** RSC_TYPE */
    private String rscType;

    // Getters and Setters
    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }

    public String getSearchStjCode() {
        return searchStjCode;
    }

    public void setSearchStjCode(String searchStjCode) {
        this.searchStjCode = searchStjCode;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setSearchSubjectCode(String searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public String getSearchPrfCode() {
        return searchPrfCode;
    }

    public void setSearchPrfCode(String searchPrfCode) {
        this.searchPrfCode = searchPrfCode;
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

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getBookFlag() {
        return bookFlag;
    }

    public void setBookFlag(String bookFlag) {
        this.bookFlag = bookFlag;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    public String getStudentBookDispYn() {
        return studentBookDispYn;
    }

    public void setStudentBookDispYn(String studentBookDispYn) {
        this.studentBookDispYn = studentBookDispYn;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getBookCount() {
        return bookCount;
    }

    public void setBookCount(String bookCount) {
        this.bookCount = bookCount;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }

    public String getRscType() {
        return rscType;
    }

    public void setRscType(String rscType) {
        this.rscType = rscType;
    }
}
