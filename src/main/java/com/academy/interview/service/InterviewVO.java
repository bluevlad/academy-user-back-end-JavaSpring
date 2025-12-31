package com.academy.interview.service;

import com.academy.common.CommonVO;

/**
 * InterviewVO.java
 * 상담/문의 게시판 관련 Value Object
 */
public class InterviewVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 게시판 관리 SEQ */
    private String boardMngSeq;

    /** 게시판 SEQ */
    private String boardSeq;

    /** 카테고리 코드 */
    private String topMenu;

    /** 공개 여부 */
    private String openYn;

    /** 제목 */
    private String subject;

    /** 내용 */
    private String content;

    /** 상단 고정 여부 */
    private String noticeTopYn;

    /** 검색어 */
    private String searchText;

    /** 검색 종류 */
    private String searchKind;

    /** 작성자명 */
    private String createName;

    /** 시험 유형 */
    private String examType;

    /** 시험 지역 */
    private String examArea;

    /** 시험 카테고리 */
    private String examCate;

    /** 시험 과목 */
    private String examSub;

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

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
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

    public String getNoticeTopYn() {
        return noticeTopYn;
    }

    public void setNoticeTopYn(String noticeTopYn) {
        this.noticeTopYn = noticeTopYn;
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamArea() {
        return examArea;
    }

    public void setExamArea(String examArea) {
        this.examArea = examArea;
    }

    public String getExamCate() {
        return examCate;
    }

    public void setExamCate(String examCate) {
        this.examCate = examCate;
    }

    public String getExamSub() {
        return examSub;
    }

    public void setExamSub(String examSub) {
        this.examSub = examSub;
    }
}
