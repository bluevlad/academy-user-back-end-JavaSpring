package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeMapper.java
 * 공지사항 게시판 Mapper Interface
 */
@Mapper
public interface BoardNoticeMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 공지사항 게시판 목록 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 공지사항 게시판 목록 수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 게시물 상세 조회
     */
    HashMap<String, Object> boardView(HashMap<String, String> params);

    /**
     * 게시물 첨부파일 조회
     */
    List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params);

    /**
     * 게시물 이미지 첨부파일 조회
     */
    List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params);

    /**
     * 조회수 증가
     */
    void updateBoardHits(HashMap<String, String> params);

    /**
     * 메인 공지사항 목록 조회
     */
    List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params);
}
