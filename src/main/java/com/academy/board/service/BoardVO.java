package com.academy.board.service;

import com.academy.common.CommonVO;

/**
 * BoardVO.java
 * 게시판 관련 Value Object
 */
public class BoardVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 게시물 SEQ */
    private String boardSeq;

    /** 게시물 제목 */
    private String boardTitle;

    /** 게시물 내용 */
    private String boardContent;

    /** 검색어 */
    private String searchText;

    /** 검색 종류 */
    private String searchKind;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 상단 메뉴 코드 */
    private String topMenu;

    /** 작성자 */
    private String regNm;

    /** 조회수 */
    private int hits;

    /** 첨부파일 SEQ */
    private String fileSeq;

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

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
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

    public String getRegNm() {
        return regNm;
    }

    public void setRegNm(String regNm) {
        this.regNm = regNm;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }
}
