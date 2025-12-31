package com.academy.rent.service;

import com.academy.common.CommonVO;

/**
 * RentVO.java
 * 사물함/독서실 대여 관련 Value Object
 */
public class RentVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 사물함 코드 */
    private String boxCd;

    /** 사물함 번호 */
    private String boxNum;

    /** 독서실 코드 */
    private String roomCd;

    /** 독서실 번호 */
    private String roomNum;

    /** 카테고리 코드 */
    private String topMenu;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String topMenuType;

    /** 좌측 메뉴 유형 */
    private String leftMenuLType;

    /** 명령 (box/room) */
    private String cmd;

    /** 강좌 코드 */
    private String lecCode;

    /** 리소스 ID */
    private String rscId;

    /** 종류 타입 (S: 사물함, T: 독서실) */
    private String kindType;

    // Getters and Setters
    public String getBoxCd() {
        return boxCd;
    }

    public void setBoxCd(String boxCd) {
        this.boxCd = boxCd;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public String getRoomCd() {
        return roomCd;
    }

    public void setRoomCd(String roomCd) {
        this.roomCd = roomCd;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
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

    public String getLeftMenuLType() {
        return leftMenuLType;
    }

    public void setLeftMenuLType(String leftMenuLType) {
        this.leftMenuLType = leftMenuLType;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }
}
