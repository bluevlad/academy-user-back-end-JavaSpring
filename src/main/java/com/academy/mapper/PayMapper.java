package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * PayMapper.java
 * 결제 관련 Mapper 인터페이스
 */
@Mapper
public interface PayMapper {

    /**
     * 전체 주문 결제 정보 조회 (온라인 + 오프라인 + 모의고사)
     */
    List<HashMap<String, Object>> payOrderAlllist(HashMap<String, String> params);

    /**
     * 전체 주문 결제 건수 조회
     */
    int orderAlllistCount(HashMap<String, String> params);

    /**
     * 온라인 주문 결제 정보 조회
     */
    List<HashMap<String, Object>> payOrderOnlist(HashMap<String, String> params);

    /**
     * 오프라인 주문 결제 정보 조회
     */
    List<HashMap<String, Object>> payOrderOfflist(HashMap<String, String> params);

    /**
     * 모의고사 주문 결제 정보 조회
     */
    List<HashMap<String, Object>> payTOrderList(HashMap<String, String> params);

    /**
     * 온라인 강좌 주문 상세 조회
     */
    List<HashMap<String, Object>> orderDetailLectureOnlist(HashMap<String, String> params);

    /**
     * 온라인 도서 주문 상세 조회
     */
    List<HashMap<String, Object>> orderDetailBookOnlist(HashMap<String, String> params);

    /**
     * 오프라인 강좌 주문 상세 조회
     */
    List<HashMap<String, Object>> orderDetailLectureOfflist(HashMap<String, String> params);

    /**
     * 사물함 주문 상세 조회
     */
    List<HashMap<String, Object>> orderDetailBoxlist(HashMap<String, String> params);

    /**
     * 독서실 주문 상세 조회
     */
    List<HashMap<String, Object>> orderDetailRoomlist(HashMap<String, String> params);

    /**
     * 모의고사 주문 상세 조회
     */
    List<HashMap<String, Object>> tOrderDetailList(HashMap<String, String> params);

    /**
     * 온라인 주문 정보 조회
     */
    HashMap<String, Object> getOrdersOn(HashMap<String, String> params);

    /**
     * 온라인 결제 정보 조회
     */
    HashMap<String, Object> getApprovalsOn(HashMap<String, String> params);

    /**
     * 오프라인 주문 정보 조회
     */
    HashMap<String, Object> getOrdersOff(HashMap<String, String> params);

    /**
     * 오프라인 결제 정보 조회
     */
    HashMap<String, Object> getApprovalsOff(HashMap<String, String> params);

    /**
     * 모의고사 주문 정보 조회
     */
    HashMap<String, Object> getTOrders(HashMap<String, String> params);

    /**
     * 모의고사 결제 정보 조회
     */
    HashMap<String, Object> getTApprovals(HashMap<String, String> params);

    /**
     * 배송 정보 조회
     */
    HashMap<String, Object> getDelivers(HashMap<String, String> params);

    /**
     * 도서 배송 목록 조회
     */
    List<HashMap<String, Object>> bookDeliveryOnlist(HashMap<String, String> params);

    /**
     * 배송 정보 수정
     */
    void updateDeliverInfo(HashMap<String, String> params);

    /**
     * 사용자 취소 가능 여부 조회
     */
    String getUserCancelYN(HashMap<String, String> params);

}
