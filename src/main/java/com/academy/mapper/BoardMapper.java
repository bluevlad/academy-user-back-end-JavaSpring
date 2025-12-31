package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardMapper.java
 * 게시판 관련 Mapper 인터페이스
 */
@Mapper
public interface BoardMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 게시판 정보 리스트 조회
     */
    List<HashMap<String, Object>> getBoardInfoList(HashMap<String, String> params);

    /**
     * 메인 게시판 리스트 조회
     */
    List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params);

    /**
     * 게시판 리스트 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 게시판 리스트 건수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 게시물 조회수 업데이트
     */
    void updateBoardHits(HashMap<String, String> params);

    /**
     * 게시물 상세 조회
     */
    HashMap<String, Object> boardView(HashMap<String, String> params);

    /**
     * 게시물 첨부파일 조회
     */
    List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params);

    /**
     * 게시물 이미지 파일 조회
     */
    List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params);

    /**
     * 게시물 등록
     */
    void insertProcess(HashMap<String, String> params);

    /**
     * 게시물 수정
     */
    void updateProcess(HashMap<String, String> params);

    /**
     * 게시물 카테고리 정보 등록
     */
    void insertBoardCatInfo(HashMap<String, String> params);

    /**
     * 게시물 파일 정보 업데이트
     */
    void updateBoardFile(HashMap<String, String> params);

    /**
     * 게시물 삭제
     */
    void deleteBoardData(HashMap<String, String> params);

    /**
     * 게시물 카테고리 정보 삭제
     */
    void deleteBoardCateInfo(HashMap<String, String> params);

    /**
     * 게시물 파일 등록
     */
    void insertBoardFile(HashMap<String, String> params);

    /**
     * 게시물 파일 삭제
     */
    void deleteBoardFileData(HashMap<String, String> params);

    /**
     * 답글 조회
     */
    HashMap<String, Object> boardReplyView(HashMap<String, String> params);

    /**
     * 답글 등록
     */
    void insertReplyProcess(HashMap<String, String> params);

    /**
     * 답글 수정
     */
    void updateReplyProcess(HashMap<String, String> params);

    /**
     * 답글 존재 여부 확인
     */
    int getIsReply(HashMap<String, String> params);

}
