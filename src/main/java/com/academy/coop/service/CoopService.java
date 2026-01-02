package com.academy.coop.service;

import com.academy.mapper.CoopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * CoopService.java
 * 제휴사 게시판 서비스
 */
@Service
public class CoopService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CoopMapper coopMapper;

    @Autowired
    public CoopService(CoopMapper coopMapper) {
        this.coopMapper = coopMapper;
    }

    /**
     * 제휴사 공지 목록 조회 (추천/상단고정)
     */
    public List<HashMap<String, Object>> getCoopNoticeList(HashMap<String, Object> params) {
        return coopMapper.getCoopNoticeList(params);
    }

    /**
     * 제휴사 게시판 목록 조회
     */
    public List<HashMap<String, Object>> getCoopboardList(HashMap<String, Object> params) {
        return coopMapper.getCoopboardList(params);
    }

    /**
     * 제휴사 게시판 목록 수 조회
     */
    public int getCoopboardListCount(HashMap<String, Object> params) {
        return coopMapper.getCoopboardListCount(params);
    }

    /**
     * 제휴사 게시판 상세 조회
     */
    public HashMap<String, Object> coopboardView(HashMap<String, Object> params) {
        return coopMapper.coopboardView(params);
    }

    /**
     * 제휴사 코드 목록 조회
     */
    public List<HashMap<String, Object>> getCoopCodeList(HashMap<String, Object> params) {
        return coopMapper.getCoopCodeList(params);
    }

    /**
     * 조회수 증가
     */
    public void clickCoopboard(HashMap<String, Object> params) {
        coopMapper.clickCoopboard(params);
    }

    /**
     * 쿠폰 사용 가능 여부 확인
     */
    public List<HashMap<String, Object>> isUseCouponCheck(HashMap<String, Object> params) {
        return coopMapper.isUseCouponCheck(params);
    }
}
