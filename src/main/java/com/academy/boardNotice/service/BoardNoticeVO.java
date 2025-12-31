package com.academy.boardNotice.service;

import com.academy.common.CommonVO;

/**
 * BoardNoticeVO.java
 * 공지사항 관련 Value Object
 */
public class BoardNoticeVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 게시글 SEQ */
    private String boardSeq;

    /** 카테고리 코드 */
    private String categoryCode;

    /** 제목 */
    private String subject;

    /** 내용 */
    private String content;

    /** 공개 여부 */
    private String openYn;

    /** 상단 고정 여부 */
    private String noticeTopYn;

    /** 조회수 */
    private int hits;

    /** 검색 유형 */
    private String searchKind;

    /** 검색어 */
    private String searchText;

    /** 디바이스 타입 */
    private String diviceType;

    /** 탑메뉴 */
    private String topMenu;

    /** 탑메뉴타입 */
    private String topMenuType;

    // Getters and Setters
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getNoticeTopYn() {
        return noticeTopYn;
    }

    public void setNoticeTopYn(String noticeTopYn) {
        this.noticeTopYn = noticeTopYn;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
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

    public String getDiviceType() {
        return diviceType;
    }

    public void setDiviceType(String diviceType) {
        this.diviceType = diviceType;
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
}
