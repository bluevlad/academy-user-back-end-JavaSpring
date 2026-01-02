package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * IndexMapper.java
 * 메인/인덱스 페이지 Mapper Interface
 */
@Mapper
public interface IndexMapper {

    /**
     * 카테고리 목록 조회
     */
    List<HashMap<String, Object>> getCategory(HashMap<String, Object> params);

    /**
     * 과목 목록 조회
     */
    List<HashMap<String, Object>> getSubjectList(HashMap<String, Object> params);

    /**
     * 강좌 목록 조회
     */
    List<HashMap<String, Object>> getLectureList(HashMap<String, Object> params);

    /**
     * 메인 공지사항 목록 조회
     */
    List<HashMap<String, Object>> getMainNotice(HashMap<String, Object> params);

    /**
     * 게시판 카테고리 목록 조회
     */
    List<HashMap<String, Object>> boardCateList(HashMap<String, Object> params);

    /**
     * 메인 추천 공지 목록 조회
     */
    List<HashMap<String, Object>> getMainRecommendNotice(HashMap<String, Object> params);

    /**
     * 카테고리별 동영상 과목 조회
     */
    List<HashMap<String, Object>> getMovieSubjectByCategory(HashMap<String, Object> params);

    /**
     * 과목별 동영상 강사 조회
     */
    List<HashMap<String, Object>> getMovieTeacherBySubject(HashMap<String, Object> params);

    /**
     * 시험 D-Day 조회
     */
    List<HashMap<String, Object>> getExamDday(HashMap<String, Object> params);

    /**
     * 신간 도서 목록 조회
     */
    List<HashMap<String, Object>> getNewBookList(HashMap<String, Object> params);

    /**
     * 교수 과목 목록 조회
     */
    List<HashMap<String, Object>> getTeacherSubject(HashMap<String, Object> params);

    /**
     * 교수 과목별 목록 조회
     */
    List<HashMap<String, Object>> getTeacherSubjectList(HashMap<String, Object> params);

    /**
     * 교수 목록 조회
     */
    List<HashMap<String, Object>> getTeacherList(HashMap<String, Object> params);

    /**
     * 빠른 교수 목록 조회
     */
    List<HashMap<String, Object>> getQuickTeacherList(HashMap<String, Object> params);

    /**
     * 이슈 목록 조회
     */
    List<HashMap<String, Object>> getIssueList(HashMap<String, Object> params);

    /**
     * 서브메인 강좌 목록 조회
     */
    List<HashMap<String, Object>> getSubMainLectureList(HashMap<String, Object> params);

    /**
     * 팝업 목록 조회
     */
    List<HashMap<String, Object>> getPopupList(HashMap<String, Object> params);

    /**
     * 팝업 조회수 증가
     */
    void updatePopupViewCount(HashMap<String, Object> params);

    /**
     * 배너 목록 조회
     */
    List<HashMap<String, Object>> getBannerList(HashMap<String, Object> params);

    /**
     * 배너 클릭수 증가
     */
    int updateBannerViewCount(HashMap<String, Object> params);
}
