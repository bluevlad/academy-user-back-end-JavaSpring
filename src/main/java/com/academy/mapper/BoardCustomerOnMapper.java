package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardCustomerOnMapper.java
 * 고객센터 온라인 게시판 Mapper Interface
 */
@Mapper
public interface BoardCustomerOnMapper {

    /**
     * 쿠폰 유효성 검사
     */
    List<HashMap<String, Object>> isUseCouponCheck(HashMap<String, String> params);

    /**
     * 쿠폰 사용 횟수 조회
     */
    List<HashMap<String, Object>> getCouponCount(HashMap<String, String> params);

    /**
     * 쿠폰 만료일 조회
     */
    String getCouponEndDate(HashMap<String, String> params);

    /**
     * 쿠폰 정보 조회
     */
    HashMap<String, Object> getCouponMap(HashMap<String, String> params);

    /**
     * 내 쿠폰 등록
     */
    void insertMyCoupon(HashMap<String, Object> params);

    /**
     * 내 쿠폰 등록 (파라미터 String)
     */
    void insertMyCoupon2(HashMap<String, String> params);

    /**
     * MSSQL 쿠폰 업데이트
     */
    void updateMssqlCoupon(HashMap<String, Object> params);

    /**
     * 제휴 쿠폰 사용 여부 검사
     */
    List<HashMap<String, Object>> coopIsUseCouponCheck(HashMap<String, String> params);

    /**
     * 제휴 주문 등록
     */
    void coopInsertOrder(HashMap<String, String> params);

    /**
     * 제휴 쿠폰 마스터 업데이트
     */
    void coopCouponMstUpdate(HashMap<String, String> params);

    /**
     * 이벤트 댓글 목록 조회
     */
    List<HashMap<String, Object>> getEventReplyList(HashMap<String, String> params);

    /**
     * 이벤트 댓글 수 조회
     */
    int getEventReplyCount(HashMap<String, String> params);
}
