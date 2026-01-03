package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface StatsMapper {

    /**
     * 통계 전체 카운트
     */
    int statsTotalCnt(Map<String, Object> params);

    /**
     * 과목별 통계 리스트
     */
    List<HashMap<String, Object>> statsSubjectList(Map<String, Object> params);

    /**
     * 과목별 답안 리스트
     */
    List<HashMap<String, Object>> statsSubjectAnswerList(Map<String, Object> params);

    /**
     * 과목별 정보 리스트
     */
    List<HashMap<String, Object>> statsSubjectInfoList(Map<String, Object> params);

    /**
     * 전체 성적 정보 리스트
     */
    List<HashMap<String, Object>> statsTotalInfoList(Map<String, Object> params);

    /**
     * 전체 성적 정보 1 (총점, 평균, 석차 등)
     */
    List<HashMap<String, Object>> statsTotalInfo1(Map<String, Object> params);

    /**
     * 전체 성적 정보 2 (점수 분포)
     */
    List<HashMap<String, Object>> statsTotalInfo2(Map<String, Object> params);

    /**
     * 전체 성적 정보 2 테이블 헤더
     */
    List<HashMap<String, Object>> statsTotalInfo2_TblH(Map<String, Object> params);

    /**
     * 전체 성적 정보 2 테이블 데이터
     */
    List<HashMap<String, Object>> statsTotalInfo2_Tbl(Map<String, Object> params);
}
