package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardCmmntyMapper.java
 * 커뮤니티 게시판 Mapper Interface
 */
@Mapper
public interface BoardCmmntyMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 커뮤니티 게시판 목록 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 커뮤니티 게시판 목록 수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * BEST 게시물 목록 조회
     */
    List<HashMap<String, Object>> boardBestList(HashMap<String, String> params);

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
     * 게시물 등록
     */
    void insertBoard(HashMap<String, String> params);

    /**
     * 게시물 수정
     */
    void updateBoard(HashMap<String, String> params);

    /**
     * 게시물 삭제
     */
    void deleteBoard(HashMap<String, String> params);

    /**
     * 추천 등록
     */
    void insertRecommnd(HashMap<String, String> params);

    /**
     * 추천 수 조회
     */
    String getRecommndCount(HashMap<String, String> params);

    /**
     * 댓글 목록 조회
     */
    List<HashMap<String, Object>> getCommentList(HashMap<String, String> params);

    /**
     * 댓글 수 조회
     */
    int getCommentCount(HashMap<String, String> params);

    /**
     * 댓글 등록
     */
    void insertComment(HashMap<String, String> params);

    /**
     * 댓글 삭제
     */
    void deleteComment(HashMap<String, String> params);
}
