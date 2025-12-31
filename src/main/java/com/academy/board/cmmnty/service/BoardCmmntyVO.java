package com.academy.board.cmmnty.service;

import com.academy.common.CommonVO;

/**
 * BoardCmmntyVO.java
 * 커뮤니티 게시판 Value Object
 */
public class BoardCmmntyVO extends CommonVO {

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

    /** 커뮤니티 구분 코드 */
    private String cmmntyGubn;

    /** 커뮤니티 구분 검색 */
    private String schCmmntyGubn;

    /** 게시판 타입 */
    private String boardType;

    /** INCTYPE */
    private String incType;

    /** 공개 여부 */
    private String openYn;

    /** 작성자명 */
    private String createName;

    /** MOCK 코드 */
    private String mockCode;

    /** 연간 패키지 검색 */
    private String searchYearPackage;

    /** 추천 수 */
    private int votingYCnt;

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

    public String getCmmntyGubn() {
        return cmmntyGubn;
    }

    public void setCmmntyGubn(String cmmntyGubn) {
        this.cmmntyGubn = cmmntyGubn;
    }

    public String getSchCmmntyGubn() {
        return schCmmntyGubn;
    }

    public void setSchCmmntyGubn(String schCmmntyGubn) {
        this.schCmmntyGubn = schCmmntyGubn;
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

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getMockCode() {
        return mockCode;
    }

    public void setMockCode(String mockCode) {
        this.mockCode = mockCode;
    }

    public String getSearchYearPackage() {
        return searchYearPackage;
    }

    public void setSearchYearPackage(String searchYearPackage) {
        this.searchYearPackage = searchYearPackage;
    }

    public int getVotingYCnt() {
        return votingYCnt;
    }

    public void setVotingYCnt(int votingYCnt) {
        this.votingYCnt = votingYCnt;
    }
}
