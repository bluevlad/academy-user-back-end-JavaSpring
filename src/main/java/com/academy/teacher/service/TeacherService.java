package com.academy.teacher.service;

import com.academy.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * TeacherService.java
 * 교수 관련 서비스 클래스
 */
@Service
public class TeacherService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    /**
     * 좌측 교수 리스트 조회
     */
    public List<HashMap<String, Object>> leftTeacherList(HashMap<String, String> params) {
        return teacherMapper.leftTeacherList(params);
    }

    /**
     * 카테고리별 과목 리스트 조회
     */
    public List<HashMap<String, Object>> subjectListByCategory(HashMap<String, String> params) {
        return teacherMapper.subjectListByCategory(params);
    }

    /**
     * 교수 리스트 조회
     */
    public List<HashMap<String, Object>> teacherList(HashMap<String, String> params) {
        return teacherMapper.teacherList(params);
    }

    /**
     * 교수 상세 정보 조회
     */
    public HashMap<String, Object> teacherDetail(HashMap<String, String> params) {
        return teacherMapper.teacherDetail(params);
    }

    /**
     * 교수 저서 목록 조회
     */
    public List<HashMap<String, Object>> teacherBookLog(HashMap<String, String> params) {
        return teacherMapper.teacherBookLog(params);
    }

    /**
     * 교수 오프라인 강의 목록 조회
     */
    public List<HashMap<String, Object>> teacherLectureList(HashMap<String, String> params) {
        return teacherMapper.teacherLectureList(params);
    }

    /**
     * 교수 오프라인 강의 교재 목록 조회
     */
    public List<HashMap<String, Object>> teacherLectureBookList(HashMap<String, String> params) {
        return teacherMapper.teacherLectureBookList(params);
    }

    /**
     * 교수 온라인 공개강의 목록 조회
     */
    public List<HashMap<String, Object>> teacherMovieOpenLectureList(HashMap<String, String> params) {
        return teacherMapper.teacherMovieOpenLectureList(params);
    }

    /**
     * 교수 온라인 강의 베스트 목록 조회
     */
    public List<HashMap<String, Object>> teacherMovieLectureBestList(HashMap<String, String> params) {
        return teacherMapper.teacherMovieLectureBestList(params);
    }

    /**
     * 교수 온라인 강의 목록 조회
     */
    public List<HashMap<String, Object>> teacherMovieLectureList(HashMap<String, String> params) {
        return teacherMapper.teacherMovieLectureList(params);
    }

    /**
     * 패키지 교수 온라인 강의 목록 조회
     */
    public List<HashMap<String, Object>> packteacherMovieLectureList(HashMap<String, String> params) {
        return teacherMapper.packteacherMovieLectureList(params);
    }

    /**
     * 교수 온라인 강의 교재 목록 조회
     */
    public List<HashMap<String, Object>> teacherMovieLectureBookList(HashMap<String, String> params) {
        return teacherMapper.teacherMovieLectureBookList(params);
    }

    /**
     * 교재 정보 조회
     */
    public HashMap<String, Object> bookInfo(HashMap<String, String> params) {
        return teacherMapper.bookInfo(params);
    }

    /**
     * 교수 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return teacherMapper.getBoardInfo(params);
    }

    /**
     * 교수 게시판 리스트 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return teacherMapper.boardList(params);
    }

    /**
     * 교수 게시판 리스트 건수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return teacherMapper.boardListCount(params);
    }

    /**
     * 교수 게시판 표시 여부 조회
     */
    public String getPrfBrdOf(HashMap<String, String> params) {
        return teacherMapper.getPrfBrdOf(params);
    }

    /**
     * 온라인 강의 장바구니 등록
     */
    @Transactional
    public void movieLectureCartInsert(HashMap<String, String> params) {
        teacherMapper.movieLectureCartInsert(params);
    }

    /**
     * 온라인 강의 장바구니 수정
     */
    @Transactional
    public void movieLectureCartUpdate(HashMap<String, String> params) {
        teacherMapper.movieLectureCartUpdate(params);
    }

    /**
     * 온라인 강의 장바구니 삭제
     */
    @Transactional
    public void movieLectureCartDelete(HashMap<String, String> params) {
        teacherMapper.movieLectureCartDelete(params);
    }

    /**
     * 온라인 강의 장바구니 체크
     */
    public List<HashMap<String, Object>> movieLectureCartCheck(HashMap<String, String> params) {
        return teacherMapper.movieLectureCartCheck(params);
    }

    /**
     * 오프라인 강의 장바구니 체크
     */
    public List<HashMap<String, Object>> lectureCartCheck(HashMap<String, String> params) {
        return teacherMapper.lectureCartCheck(params);
    }

    /**
     * 오프라인 강의 장바구니 등록
     */
    @Transactional
    public void lectureCartInsert(HashMap<String, String> params) {
        teacherMapper.lectureCartInsert(params);
    }

}
