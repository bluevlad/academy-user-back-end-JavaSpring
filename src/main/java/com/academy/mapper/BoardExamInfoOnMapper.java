package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardExamInfoOnMapper.java
 * 시험정보 온라인 게시판 Mapper Interface
 */
@Mapper
public interface BoardExamInfoOnMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 시험정보 게시판 목록 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 시험정보 게시판 목록 수 조회
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
     * 공고 구분 코드 목록 조회
     */
    List<HashMap<String, Object>> getPubGubun(HashMap<String, Object> params);

    /**
     * 공고 게시판 목록 조회
     */
    List<HashMap<String, Object>> pubNoticeList(HashMap<String, Object> params);

    /**
     * 공고 게시판 목록 수 조회
     */
    int pubNoticeListCount(HashMap<String, Object> params);

    /**
     * 공고 상세 조회
     */
    HashMap<String, Object> pubNoticeView(HashMap<String, String> params);

    /**
     * 공고 조회수 증가
     */
    void updatePubNoticeHits(HashMap<String, String> params);

    /**
     * 공고 첨부파일 조회
     */
    List<HashMap<String, Object>> pubNoticeAttachFile(HashMap<String, String> params);

    /**
     * 공고 카테고리 조회
     */
    List<HashMap<String, Object>> getPubCategorySel(HashMap<String, Object> params);
}
