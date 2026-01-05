package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * TeacherZoneMapper.java
 * 교수 관리 페이지(Teacher Zone) Mapper Interface
 */
@Mapper
public interface TeacherZoneMapper {

    /**
     * 로그인 IP 이력 조회
     */
    List<HashMap<String, Object>> loginIp(HashMap<String, String> params);

    /**
     * 교수 과목 목록 조회
     */
    List<HashMap<String, Object>> getTeacherSubject(HashMap<String, String> params);

    /**
     * 오프라인 강의 수강 인원 통계
     */
    List<HashMap<String, Object>> offLectureSum(HashMap<String, String> params);

    /**
     * 온라인 강의 수강 인원 통계
     */
    List<HashMap<String, Object>> onLectureSum(HashMap<String, String> params);

    /**
     * 오프라인 단과 강의 목록
     */
    List<HashMap<String, Object>> offLectureList(HashMap<String, String> params);

    /**
     * 오프라인 단과 강의 목록 카운트
     */
    int offLectureListCount(HashMap<String, String> params);

    /**
     * 오프라인 단과 강의 상세
     */
    HashMap<String, Object> offLectureView(HashMap<String, String> params);

    /**
     * 오프라인 단과 강의 수강생 목록
     */
    List<HashMap<String, Object>> offLectureStdList(HashMap<String, String> params);

    /**
     * 오프라인 종합반 강의 목록
     */
    List<HashMap<String, Object>> offPkgLectureList(HashMap<String, String> params);

    /**
     * 오프라인 종합반 강의 목록 카운트
     */
    int offPkgLectureListCount(HashMap<String, String> params);

    /**
     * 오프라인 종합반 강의 상세
     */
    HashMap<String, Object> offPkgLectureView(HashMap<String, String> params);

    /**
     * 오프라인 종합반 포함 강의 목록
     */
    List<HashMap<String, Object>> offPkgLectureLecture(HashMap<String, String> params);

    /**
     * 오프라인 종합반 강의 수강생 목록
     */
    List<HashMap<String, Object>> offPkgLectureStdList(HashMap<String, String> params);

    /**
     * 온라인 프리패스 강의 목록
     */
    List<HashMap<String, Object>> onYearLectureList(HashMap<String, String> params);

    /**
     * 온라인 프리패스 강의 목록 카운트
     */
    int onYearLectureListCount(HashMap<String, String> params);

    /**
     * 온라인 프리패스 강의 수강생 목록
     */
    List<HashMap<String, Object>> onYearLectureStdList(HashMap<String, String> params);

    /**
     * 온라인 단과 강의 목록
     */
    List<HashMap<String, Object>> onLectureList(HashMap<String, String> params);

    /**
     * 온라인 단과 강의 목록 카운트
     */
    int onLectureListCount(HashMap<String, String> params);

    /**
     * 온라인 강의 상세
     */
    HashMap<String, Object> onLectureView(HashMap<String, String> params);

    /**
     * 온라인 단과 강의 수강생 목록
     */
    List<HashMap<String, Object>> onLectureStdList(HashMap<String, String> params);

    /**
     * 온라인 종합반 강의 목록
     */
    List<HashMap<String, Object>> onPkgLectureList(HashMap<String, String> params);

    /**
     * 온라인 종합반 강의 목록 카운트
     */
    int onPkgLectureListCount(HashMap<String, String> params);

    /**
     * 온라인 종합반 포함 강의 목록
     */
    List<HashMap<String, Object>> onPkgLectureLecture(HashMap<String, String> params);

    /**
     * 온라인 종합반 강의 수강생 목록
     */
    List<HashMap<String, Object>> onPkgLectureStdList(HashMap<String, String> params);

    /**
     * 동영상 강의 목록
     */
    List<HashMap<String, Object>> movieLectureList(HashMap<String, String> params);

    /**
     * 동영상 강의 목록 카운트
     */
    int movieLectureListCount(HashMap<String, String> params);

    /**
     * 동영상 목록
     */
    List<HashMap<String, Object>> movieList(HashMap<String, String> params);

    /**
     * 강의 상세 정보
     */
    HashMap<String, Object> lectureView(HashMap<String, String> params);

    /**
     * 주문 통계
     */
    List<HashMap<String, Object>> onOrderStat(HashMap<String, String> params);
}
