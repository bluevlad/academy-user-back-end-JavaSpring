package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * YearPackageLectureMapper.java
 * 연회원 패키지 강의 Mapper Interface
 */
@Mapper
public interface YearPackageLectureMapper {

    /**
     * 연회원 패키지 강의 목록 조회
     */
    List<HashMap<String, Object>> yearPackageLectureOnList(HashMap<String, Object> params);

    /**
     * 연회원 패키지 강의 목록 건수 조회
     */
    int yearPackageLectureOnListCount(HashMap<String, Object> params);

    /**
     * 연회원 패키지 강의 상세 조회 (필수 강좌)
     */
    List<HashMap<String, Object>> yearPackageLectureOnDetail1(HashMap<String, Object> params);

    /**
     * 연회원 패키지 강의 상세 조회 (선택 강좌)
     */
    List<HashMap<String, Object>> yearPackageLectureOnDetail2(HashMap<String, Object> params);

    /**
     * 장바구니 연회원 패키지 건수 조회
     */
    int getYearPCartCnt(HashMap<String, Object> params);

    /**
     * 장바구니 연회원 종합반 건수 조회
     */
    int getYearJCartCnt(HashMap<String, Object> params);
}
