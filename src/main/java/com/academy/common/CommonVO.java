package com.academy.common;

import java.io.Serializable;

/**
 * CommonVO.java
 * 공통 Value Object 클래스
 * 모든 VO는 이 클래스를 상속받아 사용
 */
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 현재 페이지 번호 */
    private int currentPage = 1;

    /** 페이지당 출력할 데이터 건수 */
    private int pageRow = 10;

    /** 페이지 사이즈 */
    private int pageSize = 10;

    /** 시작 인덱스 */
    private int firstIndex = 0;

    /** 마지막 인덱스 */
    private int lastIndex = 0;

    /** 페이지당 레코드 수 */
    private int recordCountPerPage = 10;

    /** 전체 레코드 수 */
    private int totalRecordCount = 0;

    /** 검색 키워드 */
    private String searchKeyword;

    /** 검색 타입 */
    private String searchType;

    /** 등록자 ID */
    private String regId;

    /** 수정자 ID */
    private String updId;

    /** 사용자 ID */
    private String userId;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
