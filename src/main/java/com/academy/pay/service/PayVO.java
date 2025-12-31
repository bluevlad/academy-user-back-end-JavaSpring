package com.academy.pay.service;

import com.academy.common.CommonVO;

/**
 * PayVO.java
 * 결제 관련 Value Object
 */
public class PayVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** 주문번호 */
    private String orderNo;

    /** 사용자 ID */
    private String userId;

    /** 관리번호 */
    private String mgntNo;

    /** 상태코드 */
    private String statusCode;

    /** 결제유형 */
    private String pType;

    /** 동영상/PMP 유형 */
    private String wmvPmp;

    /** 검색 시작일 */
    private String searchSDate;

    /** 검색 종료일 */
    private String searchEDate;

    /** 온/오프라인 구분 */
    private String ofType;

    /** 모바일 여부 */
    private String mobileFlag;

    /** 시작 번호 */
    private int startNo;

    /** 종료 번호 */
    private int endNo;

    // Getters and Setters
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMgntNo() {
        return mgntNo;
    }

    public void setMgntNo(String mgntNo) {
        this.mgntNo = mgntNo;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getWmvPmp() {
        return wmvPmp;
    }

    public void setWmvPmp(String wmvPmp) {
        this.wmvPmp = wmvPmp;
    }

    public String getSearchSDate() {
        return searchSDate;
    }

    public void setSearchSDate(String searchSDate) {
        this.searchSDate = searchSDate;
    }

    public String getSearchEDate() {
        return searchEDate;
    }

    public void setSearchEDate(String searchEDate) {
        this.searchEDate = searchEDate;
    }

    public String getOfType() {
        return ofType;
    }

    public void setOfType(String ofType) {
        this.ofType = ofType;
    }

    public String getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(String mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    public int getStartNo() {
        return startNo;
    }

    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }

    public int getEndNo() {
        return endNo;
    }

    public void setEndNo(int endNo) {
        this.endNo = endNo;
    }
}
