package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * TeacherMapper.java
 * 교수 관련 Mapper 인터페이스
 */
@Mapper
public interface TeacherMapper {

    /**
     * 좌측 교수 리스트 조회
     */
    List<HashMap<String, Object>> leftTeacherList(HashMap<String, String> params);

    /**
     * 카테고리별 과목 리스트 조회
     */
    List<HashMap<String, Object>> subjectListByCategory(HashMap<String, String> params);

    /**
     * 교수 리스트 조회
     */
    List<HashMap<String, Object>> teacherList(HashMap<String, String> params);

    /**
     * 교수 상세 정보 조회
     */
    HashMap<String, Object> teacherDetail(HashMap<String, String> params);

    /**
     * 교수 저서 목록 조회
     */
    List<HashMap<String, Object>> teacherBookLog(HashMap<String, String> params);

    /**
     * 교수 오프라인 강의 목록 조회
     */
    List<HashMap<String, Object>> teacherLectureList(HashMap<String, String> params);

    /**
     * 교수 오프라인 강의 교재 목록 조회
     */
    List<HashMap<String, Object>> teacherLectureBookList(HashMap<String, String> params);

    /**
     * 교수 온라인 공개강의 목록 조회
     */
    List<HashMap<String, Object>> teacherMovieOpenLectureList(HashMap<String, String> params);

    /**
     * 교수 온라인 강의 베스트 목록 조회
     */
    List<HashMap<String, Object>> teacherMovieLectureBestList(HashMap<String, String> params);

    /**
     * 교수 온라인 강의 목록 조회
     */
    List<HashMap<String, Object>> teacherMovieLectureList(HashMap<String, String> params);

    /**
     * 패키지 교수 온라인 강의 목록 조회
     */
    List<HashMap<String, Object>> packteacherMovieLectureList(HashMap<String, String> params);

    /**
     * 교수 온라인 강의 교재 목록 조회
     */
    List<HashMap<String, Object>> teacherMovieLectureBookList(HashMap<String, String> params);

    /**
     * 교재 정보 조회
     */
    HashMap<String, Object> bookInfo(HashMap<String, String> params);

    /**
     * 교수 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * 교수 게시판 리스트 조회
     */
    List<HashMap<String, Object>> boardList(HashMap<String, String> params);

    /**
     * 교수 게시판 리스트 건수 조회
     */
    int boardListCount(HashMap<String, String> params);

    /**
     * 교수 게시판 표시 여부 조회
     */
    String getPrfBrdOf(HashMap<String, String> params);

    /**
     * 온라인 강의 상세 정보 (장바구니용)
     */
    HashMap<String, Object> movieLectureDetailForCart(HashMap<String, String> params);

    /**
     * 온라인 강의 장바구니 체크
     */
    List<HashMap<String, Object>> movieLectureCartCheck(HashMap<String, String> params);

    /**
     * 온라인 강의 장바구니 등록
     */
    void movieLectureCartInsert(HashMap<String, String> params);

    /**
     * 온라인 강의 장바구니 수정
     */
    void movieLectureCartUpdate(HashMap<String, String> params);

    /**
     * 온라인 강의 장바구니 삭제
     */
    void movieLectureCartDelete(HashMap<String, String> params);

    /**
     * 오프라인 강의 상세 정보 (장바구니용)
     */
    HashMap<String, Object> lectureDetailForCart(HashMap<String, String> params);

    /**
     * 오프라인 강의 장바구니 체크
     */
    List<HashMap<String, Object>> lectureCartCheck(HashMap<String, String> params);

    /**
     * 오프라인 강의 장바구니 등록
     */
    void lectureCartInsert(HashMap<String, String> params);

}
