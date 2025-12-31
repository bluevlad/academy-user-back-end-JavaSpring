package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * SearchMapper.java
 * 검색 관련 Mapper 인터페이스
 */
@Mapper
public interface SearchMapper {

    /**
     * 강사 검색
     */
    List<HashMap<String, Object>> searchTeacherInfo(HashMap<String, String> params);

    /**
     * 동영상 강의 검색
     */
    List<HashMap<String, Object>> searchMovie(HashMap<String, String> params);

    /**
     * 동영상 강의 교재 검색
     */
    List<HashMap<String, Object>> searchMovieBookList(HashMap<String, String> params);

    /**
     * 패스 검색
     */
    List<HashMap<String, Object>> searchPass(HashMap<String, String> params);

    /**
     * 오프라인 강의 검색
     */
    List<HashMap<String, Object>> searchLectureOffList(HashMap<String, String> params);

    /**
     * 오프라인 강의 교재 검색
     */
    List<HashMap<String, Object>> searchLectureOffBookList(HashMap<String, String> params);

    /**
     * 교재 검색
     */
    List<HashMap<String, Object>> searchBook(HashMap<String, String> params);

    /**
     * 교재 강의 목록 검색
     */
    List<HashMap<String, Object>> searchBookLectureList(HashMap<String, String> params);

    /**
     * 게시판 검색
     */
    List<HashMap<String, Object>> searchBoard(HashMap<String, String> params);

    /**
     * 강의 후기 검색
     */
    List<HashMap<String, Object>> searchLectureReply(HashMap<String, String> params);

    /**
     * 이벤트 검색
     */
    List<HashMap<String, Object>> searchEvent(HashMap<String, String> params);

    /**
     * 검색 키워드 저장
     */
    void insertSearchKeyword(HashMap<String, String> params);

}
