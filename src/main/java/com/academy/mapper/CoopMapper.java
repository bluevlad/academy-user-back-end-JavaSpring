package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * CoopMapper.java
 * 제휴사 게시판 Mapper Interface
 */
@Mapper
public interface CoopMapper {

    /**
     * 제휴사 공지 목록 조회 (추천/상단고정)
     */
    List<HashMap<String, Object>> getCoopNoticeList(HashMap<String, Object> params);

    /**
     * 제휴사 게시판 목록 조회
     */
    List<HashMap<String, Object>> getCoopboardList(HashMap<String, Object> params);

    /**
     * 제휴사 게시판 목록 수 조회
     */
    int getCoopboardListCount(HashMap<String, Object> params);

    /**
     * 제휴사 게시판 상세 조회
     */
    HashMap<String, Object> coopboardView(HashMap<String, Object> params);

    /**
     * 제휴사 코드 목록 조회
     */
    List<HashMap<String, Object>> getCoopCodeList(HashMap<String, Object> params);

    /**
     * 조회수 증가
     */
    void clickCoopboard(HashMap<String, Object> params);

    /**
     * 쿠폰 사용 가능 여부 확인
     */
    List<HashMap<String, Object>> isUseCouponCheck(HashMap<String, Object> params);
}
