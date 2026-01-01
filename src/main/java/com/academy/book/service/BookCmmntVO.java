package com.academy.book.service;

import com.academy.common.CommonVO;

/**
 * BookCmmntVO.java
 * 도서 후기(댓글) Value Object
 */
public class BookCmmntVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 리소스 ID */
    private String rscId;

    /** 후기 SEQ */
    private String seq;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 평점 */
    private String choicePoint;

    /** 삭제할 리소스 ID */
    private String deleteRscId;

    /** 삭제할 SEQ */
    private String deleteSeq;

    /** 검색 과목 */
    private String searchSubject;

    /** 검색 교수 */
    private String searchTeac;

    /** 검색 과목 코드 */
    private String searchSubjectCode;

    /** 검색 강좌 코드 */
    private String searchLecCode;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChoicePoint() {
        return choicePoint;
    }

    public void setChoicePoint(String choicePoint) {
        this.choicePoint = choicePoint;
    }

    public String getDeleteRscId() {
        return deleteRscId;
    }

    public void setDeleteRscId(String deleteRscId) {
        this.deleteRscId = deleteRscId;
    }

    public String getDeleteSeq() {
        return deleteSeq;
    }

    public void setDeleteSeq(String deleteSeq) {
        this.deleteSeq = deleteSeq;
    }

    public String getSearchSubject() {
        return searchSubject;
    }

    public void setSearchSubject(String searchSubject) {
        this.searchSubject = searchSubject;
    }

    public String getSearchTeac() {
        return searchTeac;
    }

    public void setSearchTeac(String searchTeac) {
        this.searchTeac = searchTeac;
    }

    public String getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setSearchSubjectCode(String searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public String getSearchLecCode() {
        return searchLecCode;
    }

    public void setSearchLecCode(String searchLecCode) {
        this.searchLecCode = searchLecCode;
    }
}
