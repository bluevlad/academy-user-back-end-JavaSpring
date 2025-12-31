package com.academy.lecture.service;

import com.academy.mapper.LectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * LectureService.java
 * 강의 관련 서비스 클래스
 */
@Service
public class LectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureMapper lectureMapper;

    @Autowired
    public LectureService(LectureMapper lectureMapper) {
        this.lectureMapper = lectureMapper;
    }

    /**
     * 카테고리 정보명 조회
     */
    public String getCategoryInfoName(HashMap<String, String> params) {
        return lectureMapper.getCategoryInfoName(params);
    }

    /**
     * 직종 목록 조회
     */
    public List<HashMap<String, Object>> getKindList(HashMap<String, String> params) {
        return lectureMapper.getKindList(params);
    }

    /**
     * 학습형태 목록 조회
     */
    public List<HashMap<String, Object>> getLearningFormList(HashMap<String, String> params) {
        return lectureMapper.getLearningFormList(params);
    }

    /**
     * 학습형태명 조회
     */
    public String getLearningFormName(HashMap<String, String> params) {
        return lectureMapper.getLearningFormName(params);
    }

    /**
     * 오프라인 강의 목록 조회
     */
    public List<HashMap<String, Object>> lectureOffList(HashMap<String, String> params) {
        return lectureMapper.lectureOffList(params);
    }

    /**
     * 오프라인 강의 교재 목록 조회
     */
    public List<HashMap<String, Object>> lectureOffBookList(HashMap<String, String> params) {
        return lectureMapper.lectureOffBookList(params);
    }

    /**
     * 온라인 강의 목록 조회
     */
    public List<HashMap<String, Object>> lectureOnList(HashMap<String, String> params) {
        return lectureMapper.lectureOnList(params);
    }

    /**
     * 온라인 강의 교재 목록 조회
     */
    public List<HashMap<String, Object>> lectureOnBookList(HashMap<String, String> params) {
        return lectureMapper.lectureOnBookList(params);
    }

    /**
     * 무료특강 상단 목록 조회
     */
    public List<HashMap<String, Object>> lectureFreeNewTopOnList(HashMap<String, String> params) {
        return lectureMapper.lectureFreeNewTopOnList(params);
    }

    /**
     * 오프라인 강의 상세 조회
     */
    public HashMap<String, Object> lectureOffDetail(HashMap<String, String> params) {
        return lectureMapper.lectureOffDetail(params);
    }

    /**
     * 온라인 강의 상세 조회
     */
    public HashMap<String, Object> lectureOnDetail(HashMap<String, String> params) {
        return lectureMapper.lectureOnDetail(params);
    }

    /**
     * 온라인 강의 교재 상세 목록 조회
     */
    public List<HashMap<String, Object>> lectureOnDetailBookList(HashMap<String, String> params) {
        return lectureMapper.lectureOnDetailBookList(params);
    }

    /**
     * 오프라인 강의 교재 상세 목록 조회
     */
    public List<HashMap<String, Object>> lectureOffDetailBookList(HashMap<String, String> params) {
        return lectureMapper.lectureOffDetailBookList(params);
    }

    /**
     * 온라인 강의 커리큘럼(동영상) 목록 조회
     */
    public List<HashMap<String, Object>> lectureOnDetailMovieList(HashMap<String, String> params) {
        return lectureMapper.lectureOnDetailMovieList(params);
    }

    /**
     * 과목별 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> subjectListByCategory(HashMap<String, String> params) {
        return lectureMapper.subjectListByCategory(params);
    }

    /**
     * 온라인 장바구니 담기
     */
    @Transactional
    public void lectureOnCartInsert(HashMap<String, String> params) {
        lectureMapper.lectureOnCartInsert(params);
    }

    /**
     * 온라인 장바구니 중복 체크
     */
    public HashMap<String, Object> lectureOnCartCheck(HashMap<String, String> params) {
        return lectureMapper.lectureOnCartCheck(params);
    }

    /**
     * 오프라인 장바구니 담기
     */
    @Transactional
    public void lectureOffCartInsert(HashMap<String, String> params) {
        lectureMapper.lectureOffCartInsert(params);
    }

    /**
     * 오프라인 장바구니 중복 체크
     */
    public HashMap<String, Object> lectureOffCartCheck(HashMap<String, String> params) {
        return lectureMapper.lectureOffCartCheck(params);
    }

    /**
     * 시리즈별 과목 목록 조회
     */
    public List<HashMap<String, Object>> selectSeriesSubject(HashMap<String, String> params) {
        return lectureMapper.selectSeriesSubject(params);
    }

    /**
     * 시리즈별 강사 목록 조회
     */
    public List<HashMap<String, Object>> selectSeriesProf(HashMap<String, String> params) {
        return lectureMapper.selectSeriesProf(params);
    }

    /**
     * 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> selectCategory(HashMap<String, String> params) {
        return lectureMapper.selectCategory(params);
    }

    /**
     * 카테고리 건수 조회
     */
    public int getCategoryCount(HashMap<String, String> params) {
        return lectureMapper.getCategoryCount(params);
    }

    /**
     * 메인 카테고리 과목 목록 조회
     */
    public List<HashMap<String, Object>> selectSubMainCateSubjectList(HashMap<String, String> params) {
        return lectureMapper.selectSubMainCateSubjectList(params);
    }

    /**
     * 메인 카테고리 강사 목록 조회
     */
    public List<HashMap<String, Object>> selectSubMainCateTeacherList(HashMap<String, String> params) {
        return lectureMapper.selectSubMainCateTeacherList(params);
    }

}
