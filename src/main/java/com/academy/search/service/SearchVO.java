package com.academy.search.service;

import com.academy.common.CommonVO;

/**
 * SearchVO.java
 * 검색 관련 Value Object
 */
public class SearchVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 통합 검색어 */
    private String unitedSarchText;

    /** 탑메뉴 */
    private String topMenu;

    /** 탑메뉴타입 */
    private String topMenuType;

    /** 게시판관리SEQ */
    private String boardMngSeq;

    // Getters and Setters
    public String getUnitedSarchText() {
        return unitedSarchText;
    }

    public void setUnitedSarchText(String unitedSarchText) {
        this.unitedSarchText = unitedSarchText;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getBoardMngSeq() {
        return boardMngSeq;
    }

    public void setBoardMngSeq(String boardMngSeq) {
        this.boardMngSeq = boardMngSeq;
    }
}
