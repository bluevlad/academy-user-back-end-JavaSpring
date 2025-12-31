package com.academy.openLecture.service;

import com.academy.common.CommonVO;

/**
 * OpenLectureVO.java
 * 공개강의 관련 Value Object
 */
public class OpenLectureVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** SEQ */
    private String seq;

    /** 공개강의 코드 */
    private String openLecCode;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 공개분류 */
    private String openBunru;

    /** 공개강의 제목 */
    private String openTitle;

    /** 과목 코드 */
    private String openSjtCd;

    /** 강사 ID */
    private String openTeacher;

    /** 메모 */
    private String openMemo;

    /** 설명 */
    private String openDesc;

    /** 고화질 동영상 경로 */
    private String openHiMoviePath;

    /** 일반 동영상 경로 */
    private String openNomalMoviePath;

    /** 파일 */
    private String openFile;

    /** 사용여부 */
    private String openIsUse;

    /** 비밀번호 */
    private String openPassword;

    /** 포인트 */
    private String openPoint;

    /** 조회수 */
    private String openHit;

    /** 검색 분류 */
    private String searchBunru;

    /** 검색 유형 */
    private String searchType;

    /** 검색어 */
    private String searchText;

    // Getters and Setters
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getOpenLecCode() {
        return openLecCode;
    }

    public void setOpenLecCode(String openLecCode) {
        this.openLecCode = openLecCode;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getOpenBunru() {
        return openBunru;
    }

    public void setOpenBunru(String openBunru) {
        this.openBunru = openBunru;
    }

    public String getOpenTitle() {
        return openTitle;
    }

    public void setOpenTitle(String openTitle) {
        this.openTitle = openTitle;
    }

    public String getOpenSjtCd() {
        return openSjtCd;
    }

    public void setOpenSjtCd(String openSjtCd) {
        this.openSjtCd = openSjtCd;
    }

    public String getOpenTeacher() {
        return openTeacher;
    }

    public void setOpenTeacher(String openTeacher) {
        this.openTeacher = openTeacher;
    }

    public String getOpenMemo() {
        return openMemo;
    }

    public void setOpenMemo(String openMemo) {
        this.openMemo = openMemo;
    }

    public String getOpenDesc() {
        return openDesc;
    }

    public void setOpenDesc(String openDesc) {
        this.openDesc = openDesc;
    }

    public String getOpenHiMoviePath() {
        return openHiMoviePath;
    }

    public void setOpenHiMoviePath(String openHiMoviePath) {
        this.openHiMoviePath = openHiMoviePath;
    }

    public String getOpenNomalMoviePath() {
        return openNomalMoviePath;
    }

    public void setOpenNomalMoviePath(String openNomalMoviePath) {
        this.openNomalMoviePath = openNomalMoviePath;
    }

    public String getOpenFile() {
        return openFile;
    }

    public void setOpenFile(String openFile) {
        this.openFile = openFile;
    }

    public String getOpenIsUse() {
        return openIsUse;
    }

    public void setOpenIsUse(String openIsUse) {
        this.openIsUse = openIsUse;
    }

    public String getOpenPassword() {
        return openPassword;
    }

    public void setOpenPassword(String openPassword) {
        this.openPassword = openPassword;
    }

    public String getOpenPoint() {
        return openPoint;
    }

    public void setOpenPoint(String openPoint) {
        this.openPoint = openPoint;
    }

    public String getOpenHit() {
        return openHit;
    }

    public void setOpenHit(String openHit) {
        this.openHit = openHit;
    }

    public String getSearchBunru() {
        return searchBunru;
    }

    public void setSearchBunru(String searchBunru) {
        this.searchBunru = searchBunru;
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
}
