package com.academy.lecture.reply.service;

import com.academy.common.CommonVO;

/**
 * LectureReplyVO.java
 * 강의 수강후기 Value Object
 */
public class LectureReplyVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 강의코드 */
    private String leccode;

    /** 후기 SEQ */
    private String seq;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 평점 */
    private String choicePoint;

    /** 과목코드 */
    private String subjectSjtCd;

    /** 강사ID */
    private String subjectTeacher;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 학습형태 코드 */
    private String learningCd;

    /** 강의유형 */
    private String lecTypeChoice;

    /** 탑메뉴 */
    private String topMenu;

    /** 탑메뉴명 */
    private String topMenuName;

    /** 탑메뉴타입 */
    private String topMenuType;

    /** 삭제할 강의코드 */
    private String deleteLeccode;

    /** 삭제할 SEQ */
    private String deleteSeq;

    /** 검색유형 */
    private String searchKind;

    /** 검색어 */
    private String searchText;

    /** 검색과목 */
    private String searchSubject;

    /** 검색강사 */
    private String searchTeacher;

    /** 검색사용자ID */
    private String searchUserId;

    /** 검색사용자명 */
    private String searchUserNm;

    /** 검색과목명 */
    private String searchSubjectNm;

    /** 서브메인 여부 */
    private String isSubMain;

    // Getters and Setters
    public String getLeccode() {
        return leccode;
    }

    public void setLeccode(String leccode) {
        this.leccode = leccode;
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

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
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

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getDeleteLeccode() {
        return deleteLeccode;
    }

    public void setDeleteLeccode(String deleteLeccode) {
        this.deleteLeccode = deleteLeccode;
    }

    public String getDeleteSeq() {
        return deleteSeq;
    }

    public void setDeleteSeq(String deleteSeq) {
        this.deleteSeq = deleteSeq;
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

    public String getSearchSubject() {
        return searchSubject;
    }

    public void setSearchSubject(String searchSubject) {
        this.searchSubject = searchSubject;
    }

    public String getSearchTeacher() {
        return searchTeacher;
    }

    public void setSearchTeacher(String searchTeacher) {
        this.searchTeacher = searchTeacher;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    public String getSearchUserNm() {
        return searchUserNm;
    }

    public void setSearchUserNm(String searchUserNm) {
        this.searchUserNm = searchUserNm;
    }

    public String getSearchSubjectNm() {
        return searchSubjectNm;
    }

    public void setSearchSubjectNm(String searchSubjectNm) {
        this.searchSubjectNm = searchSubjectNm;
    }

    public String getIsSubMain() {
        return isSubMain;
    }

    public void setIsSubMain(String isSubMain) {
        this.isSubMain = isSubMain;
    }
}
