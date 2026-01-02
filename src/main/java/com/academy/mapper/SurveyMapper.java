package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * SurveyMapper.java
 * 설문조사 Mapper Interface
 */
@Mapper
public interface SurveyMapper {

    /**
     * 설문조사 목록 조회
     */
    List<HashMap<String, Object>> surveyList(HashMap<String, Object> params);

    /**
     * 설문조사 상세 조회
     */
    HashMap<String, Object> surveyView(HashMap<String, Object> params);

    /**
     * 설문조사 질문 목록 조회
     */
    List<HashMap<String, Object>> getSurveyById(HashMap<String, Object> params);

    /**
     * 설문조사 결과 통계 조회
     */
    List<HashMap<String, Object>> surveyRstList(HashMap<String, Object> params);

    /**
     * 설문조사 참여 여부 확인
     */
    int checkSurveyCnt(HashMap<String, Object> params);

    /**
     * 설문조사 결과 등록
     */
    void insertSurveyRst(HashMap<String, Object> params);

    /**
     * 설문조사 결과 항목 등록
     */
    void insertSurveyRstItem(HashMap<String, Object> params);
}
