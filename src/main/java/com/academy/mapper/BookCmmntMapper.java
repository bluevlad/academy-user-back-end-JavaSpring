package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BookCmmntMapper.java
 * 도서 후기(댓글) Mapper Interface
 */
@Mapper
public interface BookCmmntMapper {

    /**
     * 도서 후기 목록 조회
     */
    List<HashMap<String, Object>> selectBookCmmt(HashMap<String, String> params);

    /**
     * 도서 후기 목록 수 조회
     */
    int getBookCmmtCount(HashMap<String, String> params);

    /**
     * 도서 구매 여부 확인
     */
    int isBookCmmt(HashMap<String, String> params);

    /**
     * 사용자별 도서 후기 수 조회
     */
    int getBookCmmtCntByUser(HashMap<String, String> params);

    /**
     * 도서 상세 후기 목록 조회
     */
    List<HashMap<String, Object>> detailCmmtList(HashMap<String, String> params);

    /**
     * 도서 상세 후기 목록 수 조회
     */
    int detailCmmtListCount(HashMap<String, String> params);

    /**
     * 도서 후기 등록
     */
    void insertCmmt(HashMap<String, String> params);

    /**
     * 도서 후기 삭제
     */
    void deleteCmmt(HashMap<String, String> params);
}
