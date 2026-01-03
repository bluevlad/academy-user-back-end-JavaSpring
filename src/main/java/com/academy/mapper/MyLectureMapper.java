package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MyLectureMapper {

    /**
     * 나의 학습정보 조회
     */
    HashMap<String, Object> myStudyInfo(Map<String, Object> params);

    /**
     * 월별 학습정보 조회
     */
    List<HashMap<String, Object>> myStudyforMonth(Map<String, Object> params);

    /**
     * 월별 모의고사 정보 조회
     */
    List<HashMap<String, Object>> myMockforMonth(Map<String, Object> params);

    /**
     * 나의 모의고사 정보 조회
     */
    HashMap<String, Object> getMyMockInfo(Map<String, Object> params);

    /**
     * 모의고사 등록
     */
    int insertMock(Map<String, Object> params);

    /**
     * 시험 등록
     */
    int insertExam(Map<String, Object> params);

    /**
     * 나의 강의 목록 조회
     */
    List<HashMap<String, Object>> getMyLectureList(Map<String, Object> params);

    /**
     * 나의 강의 목록 카운트
     */
    int getMyLectureListCount(Map<String, Object> params);
}
