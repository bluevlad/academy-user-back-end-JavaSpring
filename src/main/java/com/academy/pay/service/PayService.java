package com.academy.pay.service;

import com.academy.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * PayService.java
 * 결제 관련 서비스 클래스
 */
@Service
public class PayService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PayMapper payMapper;

    @Autowired
    public PayService(PayMapper payMapper) {
        this.payMapper = payMapper;
    }

    /**
     * 전체 주문 결제 정보 조회 (온라인 + 오프라인 + 모의고사)
     */
    public List<HashMap<String, Object>> payOrderAlllist(HashMap<String, String> params) {
        return payMapper.payOrderAlllist(params);
    }

    /**
     * 전체 주문 결제 건수 조회
     */
    public int orderAlllistCount(HashMap<String, String> params) {
        return payMapper.orderAlllistCount(params);
    }

    /**
     * 온라인 주문 결제 정보 조회
     */
    public List<HashMap<String, Object>> payOrderOnlist(HashMap<String, String> params) {
        return payMapper.payOrderOnlist(params);
    }

    /**
     * 오프라인 주문 결제 정보 조회
     */
    public List<HashMap<String, Object>> payOrderOfflist(HashMap<String, String> params) {
        return payMapper.payOrderOfflist(params);
    }

    /**
     * 모의고사 주문 결제 정보 조회
     */
    public List<HashMap<String, Object>> payTOrderList(HashMap<String, String> params) {
        return payMapper.payTOrderList(params);
    }

    /**
     * 온라인 강좌 주문 상세 조회
     */
    public List<HashMap<String, Object>> orderDetailLectureOnlist(HashMap<String, String> params) {
        return payMapper.orderDetailLectureOnlist(params);
    }

    /**
     * 온라인 도서 주문 상세 조회
     */
    public List<HashMap<String, Object>> orderDetailBookOnlist(HashMap<String, String> params) {
        return payMapper.orderDetailBookOnlist(params);
    }

    /**
     * 오프라인 강좌 주문 상세 조회
     */
    public List<HashMap<String, Object>> orderDetailLectureOfflist(HashMap<String, String> params) {
        return payMapper.orderDetailLectureOfflist(params);
    }

    /**
     * 사물함 주문 상세 조회
     */
    public List<HashMap<String, Object>> orderDetailBoxlist(HashMap<String, String> params) {
        return payMapper.orderDetailBoxlist(params);
    }

    /**
     * 독서실 주문 상세 조회
     */
    public List<HashMap<String, Object>> orderDetailRoomlist(HashMap<String, String> params) {
        return payMapper.orderDetailRoomlist(params);
    }

    /**
     * 모의고사 주문 상세 조회
     */
    public List<HashMap<String, Object>> tOrderDetailList(HashMap<String, String> params) {
        return payMapper.tOrderDetailList(params);
    }

    /**
     * 온라인 주문 정보 조회
     */
    public HashMap<String, Object> getOrdersOn(HashMap<String, String> params) {
        return payMapper.getOrdersOn(params);
    }

    /**
     * 온라인 결제 정보 조회
     */
    public HashMap<String, Object> getApprovalsOn(HashMap<String, String> params) {
        return payMapper.getApprovalsOn(params);
    }

    /**
     * 오프라인 주문 정보 조회
     */
    public HashMap<String, Object> getOrdersOff(HashMap<String, String> params) {
        return payMapper.getOrdersOff(params);
    }

    /**
     * 오프라인 결제 정보 조회
     */
    public HashMap<String, Object> getApprovalsOff(HashMap<String, String> params) {
        return payMapper.getApprovalsOff(params);
    }

    /**
     * 모의고사 주문 정보 조회
     */
    public HashMap<String, Object> getTOrders(HashMap<String, String> params) {
        return payMapper.getTOrders(params);
    }

    /**
     * 모의고사 결제 정보 조회
     */
    public HashMap<String, Object> getTApprovals(HashMap<String, String> params) {
        return payMapper.getTApprovals(params);
    }

    /**
     * 배송 정보 조회
     */
    public HashMap<String, Object> getDelivers(HashMap<String, String> params) {
        return payMapper.getDelivers(params);
    }

    /**
     * 도서 배송 목록 조회
     */
    public List<HashMap<String, Object>> bookDeliveryOnlist(HashMap<String, String> params) {
        return payMapper.bookDeliveryOnlist(params);
    }

    /**
     * 배송 정보 수정
     */
    @Transactional
    public void updateDeliverInfo(HashMap<String, String> params) {
        payMapper.updateDeliverInfo(params);
    }

    /**
     * 사용자 취소 가능 여부 조회
     */
    public String getUserCancelYN(HashMap<String, String> params) {
        return payMapper.getUserCancelYN(params);
    }

}
