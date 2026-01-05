package com.academy.teacher.service;

import com.academy.mapper.TeacherZoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * TeacherZoneService.java
 * 교수 관리 페이지(Teacher Zone) Service
 */
@Service
public class TeacherZoneService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final TeacherZoneMapper teacherZoneMapper;

    @Autowired
    public TeacherZoneService(TeacherZoneMapper teacherZoneMapper) {
        this.teacherZoneMapper = teacherZoneMapper;
    }

    /**
     * 로그인 IP 이력 조회
     */
    public List<HashMap<String, Object>> loginIp(HashMap<String, String> params) {
        return teacherZoneMapper.loginIp(params);
    }

    /**
     * 교수 과목 목록 조회
     */
    public List<HashMap<String, Object>> getTeacherSubject(HashMap<String, String> params) {
        return teacherZoneMapper.getTeacherSubject(params);
    }

    /**
     * 오프라인 강의 수강 인원 통계
     */
    public List<HashMap<String, Object>> offLectureSum(HashMap<String, String> params) {
        return teacherZoneMapper.offLectureSum(params);
    }

    /**
     * 온라인 강의 수강 인원 통계
     */
    public List<HashMap<String, Object>> onLectureSum(HashMap<String, String> params) {
        return teacherZoneMapper.onLectureSum(params);
    }

    /**
     * 오프라인 단과 강의 목록
     */
    public List<HashMap<String, Object>> offLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.offLectureList(params);
    }

    /**
     * 오프라인 단과 강의 목록 카운트
     */
    public int offLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.offLectureListCount(params);
    }

    /**
     * 오프라인 단과 강의 상세
     */
    public HashMap<String, Object> offLectureView(HashMap<String, String> params) {
        return teacherZoneMapper.offLectureView(params);
    }

    /**
     * 오프라인 단과 강의 수강생 목록
     */
    public List<HashMap<String, Object>> offLectureStdList(HashMap<String, String> params) {
        return teacherZoneMapper.offLectureStdList(params);
    }

    /**
     * 오프라인 종합반 강의 목록
     */
    public List<HashMap<String, Object>> offPkgLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.offPkgLectureList(params);
    }

    /**
     * 오프라인 종합반 강의 목록 카운트
     */
    public int offPkgLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.offPkgLectureListCount(params);
    }

    /**
     * 오프라인 종합반 강의 상세
     */
    public HashMap<String, Object> offPkgLectureView(HashMap<String, String> params) {
        return teacherZoneMapper.offPkgLectureView(params);
    }

    /**
     * 오프라인 종합반 포함 강의 목록
     */
    public List<HashMap<String, Object>> offPkgLectureLecture(HashMap<String, String> params) {
        return teacherZoneMapper.offPkgLectureLecture(params);
    }

    /**
     * 오프라인 종합반 강의 수강생 목록
     */
    public List<HashMap<String, Object>> offPkgLectureStdList(HashMap<String, String> params) {
        return teacherZoneMapper.offPkgLectureStdList(params);
    }

    /**
     * 온라인 프리패스 강의 목록
     */
    public List<HashMap<String, Object>> onYearLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.onYearLectureList(params);
    }

    /**
     * 온라인 프리패스 강의 목록 카운트
     */
    public int onYearLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.onYearLectureListCount(params);
    }

    /**
     * 온라인 프리패스 강의 수강생 목록
     */
    public List<HashMap<String, Object>> onYearLectureStdList(HashMap<String, String> params) {
        return teacherZoneMapper.onYearLectureStdList(params);
    }

    /**
     * 온라인 단과 강의 목록
     */
    public List<HashMap<String, Object>> onLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.onLectureList(params);
    }

    /**
     * 온라인 단과 강의 목록 카운트
     */
    public int onLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.onLectureListCount(params);
    }

    /**
     * 온라인 강의 상세
     */
    public HashMap<String, Object> onLectureView(HashMap<String, String> params) {
        return teacherZoneMapper.onLectureView(params);
    }

    /**
     * 온라인 단과 강의 수강생 목록
     */
    public List<HashMap<String, Object>> onLectureStdList(HashMap<String, String> params) {
        return teacherZoneMapper.onLectureStdList(params);
    }

    /**
     * 온라인 종합반 강의 목록
     */
    public List<HashMap<String, Object>> onPkgLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.onPkgLectureList(params);
    }

    /**
     * 온라인 종합반 강의 목록 카운트
     */
    public int onPkgLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.onPkgLectureListCount(params);
    }

    /**
     * 온라인 종합반 포함 강의 목록
     */
    public List<HashMap<String, Object>> onPkgLectureLecture(HashMap<String, String> params) {
        return teacherZoneMapper.onPkgLectureLecture(params);
    }

    /**
     * 온라인 종합반 강의 수강생 목록
     */
    public List<HashMap<String, Object>> onPkgLectureStdList(HashMap<String, String> params) {
        return teacherZoneMapper.onPkgLectureStdList(params);
    }

    /**
     * 동영상 강의 목록
     */
    public List<HashMap<String, Object>> movieLectureList(HashMap<String, String> params) {
        return teacherZoneMapper.movieLectureList(params);
    }

    /**
     * 동영상 강의 목록 카운트
     */
    public int movieLectureListCount(HashMap<String, String> params) {
        return teacherZoneMapper.movieLectureListCount(params);
    }

    /**
     * 동영상 목록
     */
    public List<HashMap<String, Object>> movieList(HashMap<String, String> params) {
        return teacherZoneMapper.movieList(params);
    }

    /**
     * 강의 상세 정보
     */
    public HashMap<String, Object> lectureView(HashMap<String, String> params) {
        return teacherZoneMapper.lectureView(params);
    }

    /**
     * 주문 통계
     */
    public List<HashMap<String, Object>> onOrderStat(HashMap<String, String> params) {
        return teacherZoneMapper.onOrderStat(params);
    }
}
