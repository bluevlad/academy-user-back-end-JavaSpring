package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardCounselRoomMapper.java
 * 상담실 게시판 Mapper Interface
 */
@Mapper
public interface BoardCounselRoomMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 상담실 게시판 목록 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 상담실 게시판 목록 수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 게시물 상세 조회
     */
    HashMap<String, Object> boardView(HashMap<String, String> params);

    /**
     * 답변 상세 조회
     */
    HashMap<String, Object> boardReplyView(HashMap<String, String> params);

    /**
     * 게시물 첨부파일 조회
     */
    List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params);

    /**
     * 게시물 이미지 첨부파일 조회
     */
    List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params);

    /**
     * 답변 첨부파일 조회
     */
    List<HashMap<String, Object>> boardReplyAttachFile(HashMap<String, String> params);

    /**
     * 답변 이미지 첨부파일 조회
     */
    List<HashMap<String, Object>> boardReplyAttachFileImg(HashMap<String, String> params);

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
     * 답변 등록
     */
    void insertReply(HashMap<String, String> params);

    /**
     * 답변 수정
     */
    void updateReply(HashMap<String, String> params);

    /**
     * 답변 상태 업데이트
     */
    void updateReplyStatus(HashMap<String, String> params);
}
