package com.academy.board.library.service;

import com.academy.common.CommonVO;

/**
 * BoardLibraryVO.java
 * 자료실 게시판 Value Object
 */
public class BoardLibraryVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 게시물 SEQ */
    private String boardSeq;

    /** 부모 게시물 SEQ */
    private String parentBoardSeq;

    /** 게시물 제목 */
    private String subject;

    /** 게시물 내용 */
    private String content;

    /** 검색어 */
    private String searchText;

    /** 검색 종류 */
    private String searchKind;

    /** 게시판 타입 */
    private String boardType;

    /** INCTYPE */
    private String incType;

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

    public String getParentBoardSeq() {
        return parentBoardSeq;
    }

    public void setParentBoardSeq(String parentBoardSeq) {
        this.parentBoardSeq = parentBoardSeq;
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

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getIncType() {
        return incType;
    }

    public void setIncType(String incType) {
        this.incType = incType;
    }
}
