package com.academy.survey.service;

import com.academy.common.CommonVO;

/**
 * SurveyVO.java
 * 설문조사 Value Object
 */
public class SurveyVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 설문 ID */
    private String surveyId;

    /** 설문 제목 */
    private String surveyTitle;

    /** 설문 설명 */
    private String surveyDesc;

    /** 설문 시작일 */
    private String surveySdat;

    /** 설문 종료일 */
    private String surveyEdat;

    /** 설문 세트 ID */
    private String setId;

    /** 사용 여부 */
    private String isUse;

    /** 설문 대상 */
    private String surveyTarget;

    /** 질문 ID */
    private String queId;

    /** 질문 순서 */
    private String qseq;

    /** 질문 유형 (S:단일, M:다중, T:텍스트, D:드롭다운) */
    private String queType;

    /** 사용자 응답 */
    private String userAnsw;

    /** 검색 유형 */
    private String searchType;

    /** 검색어 */
    private String searchText;

    /** 위치 정보 */
    private String position;

    // Getters and Setters
    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public String getSurveySdat() {
        return surveySdat;
    }

    public void setSurveySdat(String surveySdat) {
        this.surveySdat = surveySdat;
    }

    public String getSurveyEdat() {
        return surveyEdat;
    }

    public void setSurveyEdat(String surveyEdat) {
        this.surveyEdat = surveyEdat;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getSurveyTarget() {
        return surveyTarget;
    }

    public void setSurveyTarget(String surveyTarget) {
        this.surveyTarget = surveyTarget;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

    public String getQseq() {
        return qseq;
    }

    public void setQseq(String qseq) {
        this.qseq = qseq;
    }

    public String getQueType() {
        return queType;
    }

    public void setQueType(String queType) {
        this.queType = queType;
    }

    public String getUserAnsw() {
        return userAnsw;
    }

    public void setUserAnsw(String userAnsw) {
        this.userAnsw = userAnsw;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
