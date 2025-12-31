package com.academy.lecture.service;

import com.academy.common.CommonVO;

/**
 * CategoryVO.java
 * 강의 카테고리 관련 Value Object
 */
public class CategoryVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 카테고리 코드 */
    private String searchCategoryCode;

    /** 직렬 코드 */
    private String searchSeriesCode;

    /** 과목 코드 */
    private String searchSubjectCode;

    /** 검색할 카테고리 코드 */
    private String searchCat;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 상단 메뉴 코드 */
    private String topMenu;

    /** 왼쪽 메뉴 타입 */
    private String leftMenuLType;

    /** 링크 구분 */
    private String link;

    // Getters and Setters
    public String getSearchCategoryCode() {
        return searchCategoryCode;
    }

    public void setSearchCategoryCode(String searchCategoryCode) {
        this.searchCategoryCode = searchCategoryCode;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setSearchSubjectCode(String searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public String getSearchCat() {
        return searchCat;
    }

    public void setSearchCat(String searchCat) {
        this.searchCat = searchCat;
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

    public String getLeftMenuLType() {
        return leftMenuLType;
    }

    public void setLeftMenuLType(String leftMenuLType) {
        this.leftMenuLType = leftMenuLType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
