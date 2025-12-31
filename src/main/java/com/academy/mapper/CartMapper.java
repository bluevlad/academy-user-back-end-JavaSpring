package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * CartMapper.java
 * 장바구니 관련 Mapper 인터페이스
 */
@Mapper
public interface CartMapper {

    /**
     * 온라인 강좌 장바구니 리스트 조회
     */
    List<HashMap<String, Object>> cartLectureOnList(HashMap<String, String> params);

    /**
     * 온라인 도서 장바구니 리스트 조회
     */
    List<HashMap<String, Object>> cartBookOnList(HashMap<String, String> params);

    /**
     * 오프라인 강좌 장바구니 리스트 조회
     */
    List<HashMap<String, Object>> cartLectureOffList(HashMap<String, String> params);

    /**
     * 온라인 장바구니 전체 삭제
     */
    void clearOnCartAll(HashMap<String, String> params);

    /**
     * 온라인 장바구니 강좌 삭제 (SEQ 기준)
     */
    void deleteOnCartSeq(HashMap<String, String> params);

    /**
     * 온라인 장바구니 강좌 삭제 (강좌코드 기준)
     */
    void deleteOnCartLecCode(HashMap<String, String> params);

    /**
     * 온라인 장바구니 도서 삭제
     */
    void deleteOnCartBook(HashMap<String, String> params);

    /**
     * 오프라인 장바구니 전체 삭제
     */
    void clearOffCartAll(HashMap<String, String> params);

    /**
     * 오프라인 장바구니 강좌 삭제 (SEQ 기준)
     */
    void deleteOffCartSeq(HashMap<String, String> params);

    /**
     * 오프라인 장바구니 강좌 삭제 (강좌코드 기준)
     */
    void deleteOffCartLecCode(HashMap<String, String> params);

    /**
     * 회원 정보 조회
     */
    HashMap<String, Object> getMemberInfo(HashMap<String, String> params);

    /**
     * 장바구니 도서 포인트 조회
     */
    String getCartBookPoint(HashMap<String, String> params);

    /**
     * 장바구니 강좌 포인트 조회
     */
    String getCartLecPoint(HashMap<String, String> params);

}
