package com.academy.index.service;

import com.academy.common.CommonVO;

/**
 * IndexVO.java
 * 메인/인덱스 페이지 Value Object
 */
public class IndexVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 과목 코드 */
    private String subjectCd;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 게시판 SEQ */
    private String boardSeq;

    /** 메뉴 타입 (O:온라인, F:오프라인) */
    private String topMenuType;

    /** 메뉴 코드 */
    private String topMenu;

    /** 메뉴명 */
    private String topMenuName;

    /** 화면 구분 (M:메인, S:서브, H:홈) */
    private String screenGubun;

    /** 배너 번호 */
    private String bannerNo;

    /** 도서 유형 (NEW:신간, RECOMMEND:추천) */
    private String bookType;

    /** 서브 강의 유형 (DAN:단과, MUN:문제풀이, REC:추천, NEW:신규) */
    private String subLecType;

    /** 검색 직렬 코드 */
    private String searchSeriesCode;

    /** 팝업 SEQ */
    private String seq;

    /** 배너 SEQ */
    private String bannerSeq;

    /** 검색어 */
    private String searchText;

    /** 조회 개수 */
    private int cnt;

    // Getters and Setters
    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getBoardMngSeq() {
        return boardMngSeq;
    }

    public void setBoardMngSeq(String boardMngSeq) {
        this.boardMngSeq = boardMngSeq;
    }

    public String getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(String boardSeq) {
        this.boardSeq = boardSeq;
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

    public String getTopMenuName() {
        return topMenuName;
    }

    public void setTopMenuName(String topMenuName) {
        this.topMenuName = topMenuName;
    }

    public String getScreenGubun() {
        return screenGubun;
    }

    public void setScreenGubun(String screenGubun) {
        this.screenGubun = screenGubun;
    }

    public String getBannerNo() {
        return bannerNo;
    }

    public void setBannerNo(String bannerNo) {
        this.bannerNo = bannerNo;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getSubLecType() {
        return subLecType;
    }

    public void setSubLecType(String subLecType) {
        this.subLecType = subLecType;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getBannerSeq() {
        return bannerSeq;
    }

    public void setBannerSeq(String bannerSeq) {
        this.bannerSeq = bannerSeq;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
