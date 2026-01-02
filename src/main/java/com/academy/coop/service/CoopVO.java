package com.academy.coop.service;

import com.academy.common.CommonVO;

/**
 * CoopVO.java
 * 제휴사 게시판 Value Object
 */
public class CoopVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 제휴 게시판 SEQ */
    private String coopBoardSeq;

    /** 공개 여부 */
    private String openYn;

    /** 작성자명 */
    private String createName;

    /** 제목 */
    private String subject;

    /** 내용 */
    private String content;

    /** 파일 경로 */
    private String filePath;

    /** 파일명 */
    private String fileName;

    /** 실제 파일명 */
    private String realFileName;

    /** 썸네일 파일 경로 */
    private String thumbnailFilePath;

    /** 썸네일 파일명 */
    private String thumbnailFileName;

    /** 썸네일 실제 파일명 */
    private String thumbnailFileRealName;

    /** 상단 고정 여부 */
    private String noticeTopYn;

    /** 추천 여부 */
    private String recommend;

    /** 제휴사 유형 */
    private String coopType;

    /** 제휴사 지역 */
    private String coopArea;

    /** 제휴사 카테고리 */
    private String coopCate;

    /** 검색어 */
    private String searchText;

    /** 검색 종류 */
    private String searchKind;

    /** 검색 키워드 */
    private String searchKeyword;

    /** 조회 개수 */
    private int cnt;

    /** 쿠폰 코드 */
    private String couponCode;

    /** 쿠폰 타입 */
    private String couponType;

    /** IDX */
    private String idx;

    /** CCode */
    private String cCode;

    // Getters and Setters
    public String getCoopBoardSeq() {
        return coopBoardSeq;
    }

    public void setCoopBoardSeq(String coopBoardSeq) {
        this.coopBoardSeq = coopBoardSeq;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    public void setThumbnailFilePath(String thumbnailFilePath) {
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public String getThumbnailFileName() {
        return thumbnailFileName;
    }

    public void setThumbnailFileName(String thumbnailFileName) {
        this.thumbnailFileName = thumbnailFileName;
    }

    public String getThumbnailFileRealName() {
        return thumbnailFileRealName;
    }

    public void setThumbnailFileRealName(String thumbnailFileRealName) {
        this.thumbnailFileRealName = thumbnailFileRealName;
    }

    public String getNoticeTopYn() {
        return noticeTopYn;
    }

    public void setNoticeTopYn(String noticeTopYn) {
        this.noticeTopYn = noticeTopYn;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getCoopType() {
        return coopType;
    }

    public void setCoopType(String coopType) {
        this.coopType = coopType;
    }

    public String getCoopArea() {
        return coopArea;
    }

    public void setCoopArea(String coopArea) {
        this.coopArea = coopArea;
    }

    public String getCoopCate() {
        return coopCate;
    }

    public void setCoopCate(String coopCate) {
        this.coopCate = coopCate;
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

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }
}
