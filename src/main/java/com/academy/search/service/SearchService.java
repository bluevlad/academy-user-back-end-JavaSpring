package com.academy.search.service;

import com.academy.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * SearchService.java
 * 검색 관련 서비스 클래스
 */
@Service
public class SearchService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SearchMapper searchMapper;

    @Autowired
    public SearchService(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    /**
     * 강사 검색
     */
    public List<HashMap<String, Object>> searchTeacherInfo(HashMap<String, String> params) {
        return searchMapper.searchTeacherInfo(params);
    }

    /**
     * 동영상 강의 검색
     */
    public List<HashMap<String, Object>> searchMovie(HashMap<String, String> params) {
        return searchMapper.searchMovie(params);
    }

    /**
     * 동영상 강의 교재 검색
     */
    public List<HashMap<String, Object>> searchMovieBookList(HashMap<String, String> params) {
        return searchMapper.searchMovieBookList(params);
    }

    /**
     * 패스 검색
     */
    public List<HashMap<String, Object>> searchPass(HashMap<String, String> params) {
        return searchMapper.searchPass(params);
    }

    /**
     * 오프라인 강의 검색
     */
    public List<HashMap<String, Object>> searchLectureOffList(HashMap<String, String> params) {
        return searchMapper.searchLectureOffList(params);
    }

    /**
     * 오프라인 강의 교재 검색
     */
    public List<HashMap<String, Object>> searchLectureOffBookList(HashMap<String, String> params) {
        return searchMapper.searchLectureOffBookList(params);
    }

    /**
     * 교재 검색
     */
    public List<HashMap<String, Object>> searchBook(HashMap<String, String> params) {
        return searchMapper.searchBook(params);
    }

    /**
     * 교재 강의 목록 검색
     */
    public List<HashMap<String, Object>> searchBookLectureList(HashMap<String, String> params) {
        return searchMapper.searchBookLectureList(params);
    }

    /**
     * 게시판 검색
     */
    public List<HashMap<String, Object>> searchBoard(HashMap<String, String> params) {
        return searchMapper.searchBoard(params);
    }

    /**
     * 강의 후기 검색
     */
    public List<HashMap<String, Object>> searchLectureReply(HashMap<String, String> params) {
        return searchMapper.searchLectureReply(params);
    }

    /**
     * 이벤트 검색
     */
    public List<HashMap<String, Object>> searchEvent(HashMap<String, String> params) {
        return searchMapper.searchEvent(params);
    }

    /**
     * 검색 키워드 저장
     */
    @Transactional
    public void insertSearchKeyword(HashMap<String, String> params) {
        searchMapper.insertSearchKeyword(params);
    }

}
