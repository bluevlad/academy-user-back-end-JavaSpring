package com.academy.mypage.coupon.service;

import com.academy.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CouponMapper couponMapper;

    @Autowired
    public CouponService(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    public List<HashMap<String, Object>> getMyCoupon(Map<String, Object> params) {
        return couponMapper.getMyCoupon(params);
    }

    public HashMap<String, Object> getCouponDetail(Map<String, Object> params) {
        return couponMapper.getCouponDetail(params);
    }

    public int useCoupon(Map<String, Object> params) {
        return couponMapper.useCoupon(params);
    }

    public List<HashMap<String, Object>> getMileageStatus(Map<String, Object> params) {
        return couponMapper.getMileageStatus(params);
    }

    public int getNowPoint(Map<String, Object> params) {
        return couponMapper.getNowPoint(params);
    }

    public List<HashMap<String, Object>> getMileageList(Map<String, Object> params) {
        return couponMapper.getMileageList(params);
    }

    public int getMileageListCount(Map<String, Object> params) {
        return couponMapper.getMileageListCount(params);
    }

    public int insertMileage(Map<String, Object> params) {
        return couponMapper.insertMileage(params);
    }

    public int useMileage(Map<String, Object> params) {
        return couponMapper.useMileage(params);
    }
}
