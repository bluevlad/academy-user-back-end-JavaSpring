package com.academy.survey.service;

import com.academy.mapper.SurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * SurveyService.java
 * 설문조사 서비스
 */
@Service
public class SurveyService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SurveyMapper surveyMapper;

    @Autowired
    public SurveyService(SurveyMapper surveyMapper) {
        this.surveyMapper = surveyMapper;
    }

    /**
     * 설문조사 목록 조회
     */
    public List<HashMap<String, Object>> surveyList(HashMap<String, Object> params) {
        return surveyMapper.surveyList(params);
    }

    /**
     * 설문조사 상세 조회
     */
    public HashMap<String, Object> surveyView(HashMap<String, Object> params) {
        return surveyMapper.surveyView(params);
    }

    /**
     * 설문조사 질문 목록 조회
     */
    public List<HashMap<String, Object>> getSurveyById(HashMap<String, Object> params) {
        return surveyMapper.getSurveyById(params);
    }

    /**
     * 설문조사 결과 통계 조회
     */
    public List<HashMap<String, Object>> surveyRstList(HashMap<String, Object> params) {
        return surveyMapper.surveyRstList(params);
    }

    /**
     * 설문조사 참여 여부 확인
     */
    public int checkSurveyCnt(HashMap<String, Object> params) {
        return surveyMapper.checkSurveyCnt(params);
    }

    /**
     * 설문조사 결과 등록
     */
    @Transactional
    public void insertSurveyRst(HashMap<String, Object> params) {
        surveyMapper.insertSurveyRst(params);
    }

    /**
     * 설문조사 결과 항목 등록
     */
    @Transactional
    public void insertSurveyRstItem(HashMap<String, Object> params) {
        surveyMapper.insertSurveyRstItem(params);
    }

    /**
     * 설문조사 참여 (결과 및 항목 일괄 등록)
     */
    @Transactional
    public void submitSurvey(HashMap<String, Object> params, List<HashMap<String, Object>> answerList) {
        // 설문조사 결과 등록
        surveyMapper.insertSurveyRst(params);

        // 설문조사 항목별 결과 등록
        for (HashMap<String, Object> answer : answerList) {
            answer.put("SURVEYID", params.get("SURVEYID"));
            answer.put("USER_ID", params.get("USER_ID"));
            surveyMapper.insertSurveyRstItem(answer);
        }
    }
}
