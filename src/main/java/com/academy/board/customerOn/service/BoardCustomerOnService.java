package com.academy.board.customerOn.service;

import com.academy.mapper.BoardCustomerOnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardCustomerOnService.java
 * 고객센터 온라인 게시판 서비스
 */
@Service
public class BoardCustomerOnService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardCustomerOnMapper boardCustomerOnMapper;

    @Autowired
    public BoardCustomerOnService(BoardCustomerOnMapper boardCustomerOnMapper) {
        this.boardCustomerOnMapper = boardCustomerOnMapper;
    }

    /**
     * 쿠폰 유효성 검사
     */
    public List<HashMap<String, Object>> isUseCouponCheck(HashMap<String, String> params) {
        return boardCustomerOnMapper.isUseCouponCheck(params);
    }

    /**
     * 쿠폰 사용 횟수 조회
     */
    public List<HashMap<String, Object>> getCouponCount(HashMap<String, String> params) {
        return boardCustomerOnMapper.getCouponCount(params);
    }

    /**
     * 쿠폰 만료일 조회
     */
    public String getCouponEndDate(HashMap<String, String> params) {
        return boardCustomerOnMapper.getCouponEndDate(params);
    }

    /**
     * 쿠폰 정보 조회
     */
    public HashMap<String, Object> getCouponMap(HashMap<String, String> params) {
        return boardCustomerOnMapper.getCouponMap(params);
    }

    /**
     * 내 쿠폰 등록
     */
    public void insertMyCoupon(HashMap<String, Object> params) {
        boardCustomerOnMapper.insertMyCoupon(params);
    }

    /**
     * 내 쿠폰 등록 (파라미터 String)
     */
    public void insertMyCoupon2(HashMap<String, String> params) {
        boardCustomerOnMapper.insertMyCoupon2(params);
    }

    /**
     * MSSQL 쿠폰 업데이트
     */
    public void updateMssqlCoupon(HashMap<String, Object> params) {
        boardCustomerOnMapper.updateMssqlCoupon(params);
    }

    /**
     * 제휴 쿠폰 사용 여부 검사
     */
    public List<HashMap<String, Object>> coopIsUseCouponCheck(HashMap<String, String> params) {
        return boardCustomerOnMapper.coopIsUseCouponCheck(params);
    }

    /**
     * 제휴 주문 등록
     */
    public void coopInsertOrder(HashMap<String, String> params) {
        boardCustomerOnMapper.coopInsertOrder(params);
    }

    /**
     * 제휴 쿠폰 마스터 업데이트
     */
    public void coopCouponMstUpdate(HashMap<String, String> params) {
        boardCustomerOnMapper.coopCouponMstUpdate(params);
    }

    /**
     * 이벤트 댓글 목록 조회
     */
    public List<HashMap<String, Object>> getEventReplyList(HashMap<String, String> params) {
        return boardCustomerOnMapper.getEventReplyList(params);
    }

    /**
     * 이벤트 댓글 수 조회
     */
    public int getEventReplyCount(HashMap<String, String> params) {
        return boardCustomerOnMapper.getEventReplyCount(params);
    }
}
