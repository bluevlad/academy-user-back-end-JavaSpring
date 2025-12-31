package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * LectureMapper.java
 * 강의 관련 Mapper 인터페이스
 */
@Mapper
public interface LectureMapper {

    /**
     * 카테고리 정보명 조회
     */
    String getCategoryInfoName(HashMap<String, String> params);

    /**
     * 직종 목록 조회
     */
    List<HashMap<String, Object>> getKindList(HashMap<String, String> params);

    /**
     * 학습형태 목록 조회
     */
    List<HashMap<String, Object>> getLearningFormList(HashMap<String, String> params);

    /**
     * 학습형태명 조회
     */
    String getLearningFormName(HashMap<String, String> params);

    /**
     * 오프라인 강의 목록 조회
     */
    List<HashMap<String, Object>> lectureOffList(HashMap<String, String> params);

    /**
     * 오프라인 강의 교재 목록 조회
     */
    List<HashMap<String, Object>> lectureOffBookList(HashMap<String, String> params);

    /**
     * 온라인 강의 목록 조회
     */
    List<HashMap<String, Object>> lectureOnList(HashMap<String, String> params);

    /**
     * 온라인 강의 교재 목록 조회
     */
    List<HashMap<String, Object>> lectureOnBookList(HashMap<String, String> params);

    /**
     * 무료특강 상단 목록 조회
     */
    List<HashMap<String, Object>> lectureFreeNewTopOnList(HashMap<String, String> params);

    /**
     * 오프라인 강의 상세 조회
     */
    HashMap<String, Object> lectureOffDetail(HashMap<String, String> params);

    /**
     * 온라인 강의 상세 조회
     */
    HashMap<String, Object> lectureOnDetail(HashMap<String, String> params);

    /**
     * 온라인 강의 교재 상세 목록 조회
     */
    List<HashMap<String, Object>> lectureOnDetailBookList(HashMap<String, String> params);

    /**
     * 오프라인 강의 교재 상세 목록 조회
     */
    List<HashMap<String, Object>> lectureOffDetailBookList(HashMap<String, String> params);

    /**
     * 온라인 강의 커리큘럼(동영상) 목록 조회
     */
    List<HashMap<String, Object>> lectureOnDetailMovieList(HashMap<String, String> params);

    /**
     * 과목별 카테고리 목록 조회
     */
    List<HashMap<String, Object>> subjectListByCategory(HashMap<String, String> params);

    /**
     * 온라인 장바구니 담기
     */
    void lectureOnCartInsert(HashMap<String, String> params);

    /**
     * 온라인 장바구니 중복 체크
     */
    HashMap<String, Object> lectureOnCartCheck(HashMap<String, String> params);

    /**
     * 오프라인 장바구니 담기
     */
    void lectureOffCartInsert(HashMap<String, String> params);

    /**
     * 오프라인 장바구니 중복 체크
     */
    HashMap<String, Object> lectureOffCartCheck(HashMap<String, String> params);

    /**
     * 시리즈별 과목 목록 조회
     */
    List<HashMap<String, Object>> selectSeriesSubject(HashMap<String, String> params);

    /**
     * 시리즈별 강사 목록 조회
     */
    List<HashMap<String, Object>> selectSeriesProf(HashMap<String, String> params);

    /**
     * 카테고리 목록 조회
     */
    List<HashMap<String, Object>> selectCategory(HashMap<String, String> params);

    /**
     * 카테고리 건수 조회
     */
    int getCategoryCount(HashMap<String, String> params);

    /**
     * 메인 카테고리 과목 목록 조회
     */
    List<HashMap<String, Object>> selectSubMainCateSubjectList(HashMap<String, String> params);

    /**
     * 메인 카테고리 강사 목록 조회
     */
    List<HashMap<String, Object>> selectSubMainCateTeacherList(HashMap<String, String> params);

}
