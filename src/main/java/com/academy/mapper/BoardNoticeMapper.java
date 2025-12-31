package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeMapper.java
 * 공지사항 관련 Mapper 인터페이스
 */
@Mapper
public interface BoardNoticeMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 게시판 정보 목록 조회
     */
    List<HashMap<String, Object>> getBoardInfoList(HashMap<String, String> params);

    /**
     * 메인 게시판 목록 조회
     */
    List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params);

    /**
     * 게시글 목록 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 게시글 건수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 게시글 상세 조회
     */
    HashMap<String, Object> boardView(HashMap<String, String> params);

    /**
     * 조회수 증가
     */
    void updateBoardHits(HashMap<String, String> params);

    /**
     * FAQ 목록 조회
     */
    List<HashMap<String, Object>> boardFAQList(HashMap<String, String> params);

}
