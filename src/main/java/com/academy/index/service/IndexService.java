package com.academy.index.service;

import com.academy.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * IndexService.java
 * 메인/인덱스 페이지 서비스
 */
@Service
public class IndexService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final IndexMapper indexMapper;

    @Autowired
    public IndexService(IndexMapper indexMapper) {
        this.indexMapper = indexMapper;
    }

    /**
     * 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> getCategory(HashMap<String, Object> params) {
        return indexMapper.getCategory(params);
    }

    /**
     * 과목 목록 조회
     */
    public List<HashMap<String, Object>> getSubjectList(HashMap<String, Object> params) {
        return indexMapper.getSubjectList(params);
    }

    /**
     * 강좌 목록 조회
     */
    public List<HashMap<String, Object>> getLectureList(HashMap<String, Object> params) {
        return indexMapper.getLectureList(params);
    }

    /**
     * 메인 공지사항 목록 조회
     */
    public List<HashMap<String, Object>> getMainNotice(HashMap<String, Object> params) {
        return indexMapper.getMainNotice(params);
    }

    /**
     * 게시판 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> boardCateList(HashMap<String, Object> params) {
        return indexMapper.boardCateList(params);
    }

    /**
     * 메인 추천 공지 목록 조회
     */
    public List<HashMap<String, Object>> getMainRecommendNotice(HashMap<String, Object> params) {
        return indexMapper.getMainRecommendNotice(params);
    }

    /**
     * 카테고리별 동영상 과목 조회
     */
    public List<HashMap<String, Object>> getMovieSubjectByCategory(HashMap<String, Object> params) {
        return indexMapper.getMovieSubjectByCategory(params);
    }

    /**
     * 과목별 동영상 강사 조회
     */
    public List<HashMap<String, Object>> getMovieTeacherBySubject(HashMap<String, Object> params) {
        return indexMapper.getMovieTeacherBySubject(params);
    }

    /**
     * 시험 D-Day 조회
     */
    public List<HashMap<String, Object>> getExamDday(HashMap<String, Object> params) {
        return indexMapper.getExamDday(params);
    }

    /**
     * 신간 도서 목록 조회
     */
    public List<HashMap<String, Object>> getNewBookList(HashMap<String, Object> params) {
        return indexMapper.getNewBookList(params);
    }

    /**
     * 교수 과목 목록 조회
     */
    public List<HashMap<String, Object>> getTeacherSubject(HashMap<String, Object> params) {
        return indexMapper.getTeacherSubject(params);
    }

    /**
     * 교수 과목별 목록 조회
     */
    public List<HashMap<String, Object>> getTeacherSubjectList(HashMap<String, Object> params) {
        return indexMapper.getTeacherSubjectList(params);
    }

    /**
     * 교수 목록 조회
     */
    public List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> params) {
        return indexMapper.getTeacherList(params);
    }

    /**
     * 빠른 교수 목록 조회
     */
    public List<HashMap<String, Object>> getQuickTeacherList(HashMap<String, Object> params) {
        return indexMapper.getQuickTeacherList(params);
    }

    /**
     * 이슈 목록 조회
     */
    public List<HashMap<String, Object>> getIssueList(HashMap<String, Object> params) {
        return indexMapper.getIssueList(params);
    }

    /**
     * 서브메인 강좌 목록 조회
     */
    public List<HashMap<String, Object>> getSubMainLectureList(HashMap<String, Object> params) {
        return indexMapper.getSubMainLectureList(params);
    }

    /**
     * 팝업 목록 조회
     */
    public List<HashMap<String, Object>> getPopupList(HashMap<String, Object> params) {
        return indexMapper.getPopupList(params);
    }

    /**
     * 팝업 조회수 증가
     */
    @Transactional
    public void updatePopupViewCount(HashMap<String, Object> params) {
        indexMapper.updatePopupViewCount(params);
    }

    /**
     * 배너 목록 조회
     */
    public List<HashMap<String, Object>> getBannerList(HashMap<String, Object> params) {
        return indexMapper.getBannerList(params);
    }

    /**
     * 배너 클릭수 증가
     */
    @Transactional
    public int updateBannerViewCount(HashMap<String, Object> params) {
        return indexMapper.updateBannerViewCount(params);
    }
}
