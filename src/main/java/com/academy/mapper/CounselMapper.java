package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CounselMapper {

    /**
     * 상담 목록 조회
     */
    List<HashMap<String, Object>> boardList(Map<String, Object> params);

    /**
     * 상담 목록 카운트
     */
    int boardListCount(Map<String, Object> params);

    /**
     * 상담 상세 조회
     */
    HashMap<String, Object> boardView(Map<String, Object> params);

    /**
     * 상담 등록
     */
    int insertBoard(Map<String, Object> params);

    /**
     * 상담 수정
     */
    int updateBoard(Map<String, Object> params);

    /**
     * 상담 삭제
     */
    int deleteBoard(Map<String, Object> params);

    /**
     * 조회수 증가
     */
    int updateHits(Map<String, Object> params);
}
