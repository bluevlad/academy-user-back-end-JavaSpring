package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CouponMapper {

    /**
     * 내 쿠폰 목록 조회
     */
    List<HashMap<String, Object>> getMyCoupon(Map<String, Object> params);

    /**
     * 쿠폰 상세 조회
     */
    HashMap<String, Object> getCouponDetail(Map<String, Object> params);

    /**
     * 쿠폰 사용
     */
    int useCoupon(Map<String, Object> params);

    /**
     * 적립금 현황 조회
     */
    List<HashMap<String, Object>> getMileageStatus(Map<String, Object> params);

    /**
     * 현재 적립금 조회
     */
    int getNowPoint(Map<String, Object> params);

    /**
     * 적립금 내역 조회
     */
    List<HashMap<String, Object>> getMileageList(Map<String, Object> params);

    /**
     * 적립금 내역 카운트
     */
    int getMileageListCount(Map<String, Object> params);

    /**
     * 적립금 적립
     */
    int insertMileage(Map<String, Object> params);

    /**
     * 적립금 사용
     */
    int useMileage(Map<String, Object> params);
}
