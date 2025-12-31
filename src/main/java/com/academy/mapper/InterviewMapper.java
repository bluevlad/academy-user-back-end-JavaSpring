package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * InterviewMapper.java
 * 상담/문의 게시판 관련 Mapper 인터페이스
 */
@Mapper
public interface InterviewMapper {

    /**
     * 게시판 리스트 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 게시판 리스트 건수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 게시글 등록
     */
    void insertProcess(HashMap<String, String> params);

    /**
     * 게시글 수정
     */
    void updateProcess(HashMap<String, String> params);

    /**
     * 게시글 상세 조회
     */
    HashMap<String, Object> boardDetail(HashMap<String, String> params);

    /**
     * 조회수 증가
     */
    void updateHits(HashMap<String, String> params);

    /**
     * 게시글 삭제
     */
    void deleteProcess(HashMap<String, String> params);

    /**
     * 카테고리 게시판 정보 등록
     */
    void insertBoardCategory(HashMap<String, String> params);

}
