package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface MouigosaMapper {

    /**
     * 모의고사 등록 리스트 조회
     */
    List<HashMap<String, Object>> getMouigosaRegistrationList(Map<String, Object> params);

    /**
     * 모의고사 등록 리스트 카운트
     */
    int getRegistrationCount(Map<String, Object> params);

    /**
     * 모의고사 정보 조회
     */
    List<HashMap<String, Object>> getUpdateRegistrationDetail(Map<String, Object> params);

    /**
     * 과목 리스트 조회
     */
    List<HashMap<String, Object>> getSubjectList(Map<String, Object> params);

    /**
     * 문제 리스트 조회
     */
    List<HashMap<String, Object>> getQuestionList(Map<String, Object> params);

    /**
     * 답안 수 조회
     */
    int getCnt(Map<String, Object> params);

    /**
     * 답안 등록
     */
    int insertAnswerMouigosa(Map<String, Object> params);

    /**
     * 답안 수정
     */
    int updateAnswerMouigosa(Map<String, Object> params);

    /**
     * 응시상태 업데이트 (시작)
     */
    int mouigosaStatusUpdate12(Map<String, Object> params);

    /**
     * 응시상태 업데이트 (과목)
     */
    int mouigosaStatusUpdate2(Map<String, Object> params);

    /**
     * 응시상태 업데이트 (종료)
     */
    int mouigosaStatusUpdate3(Map<String, Object> params);

    /**
     * 응시상태 업데이트 (전체 종료)
     */
    int mouigosaStatusUpdate4(Map<String, Object> params);

    /**
     * 미응시 답안 수 조회
     */
    int getCnt2(Map<String, Object> params);

    /**
     * 전체 문항 수 조회
     */
    int getCnt3(Map<String, Object> params);

    /**
     * 응시 완료 과목 수 조회
     */
    int getCnt4(Map<String, Object> params);

    /**
     * 전체 과목 수 조회
     */
    int getCnt5(Map<String, Object> params);

    /**
     * 문제별 정답 조회
     */
    HashMap<String, Object> selectQuestionAnswers(Map<String, Object> params);

    /**
     * 다음 과목 조회
     */
    List<HashMap<String, Object>> getCount1List2(Map<String, Object> params);
}
