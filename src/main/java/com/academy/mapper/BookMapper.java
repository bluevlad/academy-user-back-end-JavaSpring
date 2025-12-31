package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BookMapper.java
 * 도서 관련 Mapper 인터페이스
 */
@Mapper
public interface BookMapper {

    /**
     * 도서 리스트 조회
     */
    List<HashMap<String, Object>> bookList(HashMap<String, String> params);

    /**
     * 도서 리스트 건수 조회
     */
    int bookListCount(HashMap<String, String> params);

    /**
     * 좌측 학습형태 리스트 조회
     */
    List<HashMap<String, Object>> leftFormList(HashMap<String, String> params);

    /**
     * 좌측 교수 리스트 조회
     */
    List<HashMap<String, Object>> leftTeacherList(HashMap<String, String> params);

    /**
     * 교수 리스트 조회
     */
    List<HashMap<String, Object>> selectTeacherList(HashMap<String, String> params);

    /**
     * 도서 연관 강의 리스트 조회
     */
    List<HashMap<String, Object>> bookLectureList(HashMap<String, String> params);

    /**
     * 과목 정보 조회
     */
    HashMap<String, Object> getSubjectInfo(HashMap<String, String> params);

    /**
     * 도서 장바구니 체크
     */
    List<HashMap<String, Object>> bookCartCheck(HashMap<String, String> params);

    /**
     * 도서 장바구니 등록
     */
    void bookCartInsert(HashMap<String, String> params);

    /**
     * 도서 장바구니 수정
     */
    void bookCartUpdate(HashMap<String, String> params);

    /**
     * 학습형태 리스트 조회
     */
    List<HashMap<String, Object>> getLearningFormList(HashMap<String, String> params);

    /**
     * 과목-교수 리스트 조회
     */
    List<HashMap<String, Object>> getCaSubjectTeacherList(HashMap<String, String> params);

    /**
     * 도서 상세 조회 (리스트형)
     */
    List<HashMap<String, Object>> bookViewList(HashMap<String, String> params);

    /**
     * 도서 상세 조회
     */
    HashMap<String, Object> bookView(HashMap<String, String> params);

    /**
     * 도서 사용 여부 체크
     */
    int bookUseCheck(HashMap<String, String> params);

}
