package com.academy.lecture.yearpackage.service;

import com.academy.mapper.YearPackageLectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * YearPackageLectureService.java
 * 연회원 패키지 강의 서비스
 */
@Service
public class YearPackageLectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final YearPackageLectureMapper yearPackageLectureMapper;

    @Autowired
    public YearPackageLectureService(YearPackageLectureMapper yearPackageLectureMapper) {
        this.yearPackageLectureMapper = yearPackageLectureMapper;
    }

    /**
     * 연회원 패키지 강의 목록 조회
     */
    public List<HashMap<String, Object>> yearPackageLectureOnList(HashMap<String, Object> params) {
        return yearPackageLectureMapper.yearPackageLectureOnList(params);
    }

    /**
     * 연회원 패키지 강의 목록 건수 조회
     */
    public int yearPackageLectureOnListCount(HashMap<String, Object> params) {
        return yearPackageLectureMapper.yearPackageLectureOnListCount(params);
    }

    /**
     * 연회원 패키지 강의 상세 조회 (필수 강좌)
     */
    public List<HashMap<String, Object>> yearPackageLectureOnDetail1(HashMap<String, Object> params) {
        return yearPackageLectureMapper.yearPackageLectureOnDetail1(params);
    }

    /**
     * 연회원 패키지 강의 상세 조회 (선택 강좌)
     */
    public List<HashMap<String, Object>> yearPackageLectureOnDetail2(HashMap<String, Object> params) {
        return yearPackageLectureMapper.yearPackageLectureOnDetail2(params);
    }

    /**
     * 장바구니 연회원 패키지 건수 조회
     */
    public int getYearPCartCnt(HashMap<String, Object> params) {
        return yearPackageLectureMapper.getYearPCartCnt(params);
    }

    /**
     * 장바구니 연회원 종합반 건수 조회
     */
    public int getYearJCartCnt(HashMap<String, Object> params) {
        return yearPackageLectureMapper.getYearJCartCnt(params);
    }
}
