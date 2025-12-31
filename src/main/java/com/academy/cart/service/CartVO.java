package com.academy.cart.service;

import com.academy.common.CommonVO;

/**
 * CartVO.java
 * 장바구니 관련 Value Object
 */
public class CartVO extends CommonVO {

    private static final long serialVersionUID = 1L;

    /** SEQ */
    private String seq;

    /** 사용자 ID */
    private String userId;

    /** 강좌 코드 */
    private String lecCode;

    /** 종류 타입 (D:단과, J:종합, N:선택, P:패키지, Y:연회원패키지) */
    private String kindType;

    /** 동영상 타입 */
    private String movieType;

    /** 리소스 ID */
    private String rscId;

    /** 리소스 타입 */
    private String rscType;

    /** 도서 수량 */
    private String bookCount;

    /** 마킹 주문번호 */
    private String marking;

    // Getters and Setters
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getRscType() {
        return rscType;
    }

    public void setRscType(String rscType) {
        this.rscType = rscType;
    }

    public String getBookCount() {
        return bookCount;
    }

    public void setBookCount(String bookCount) {
        this.bookCount = bookCount;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }
}
