package com.academy.mocktest.mouigosa.service;

import com.academy.mapper.MouigosaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MouigosaService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MouigosaMapper mouigosaMapper;

    @Autowired
    public MouigosaService(MouigosaMapper mouigosaMapper) {
        this.mouigosaMapper = mouigosaMapper;
    }

    /**
     * 모의고사 등록 리스트 조회
     */
    public List<HashMap<String, Object>> getMouigosaRegistrationList(Map<String, Object> params) {
        return mouigosaMapper.getMouigosaRegistrationList(params);
    }

    /**
     * 모의고사 등록 리스트 카운트
     */
    public int getRegistrationCount(Map<String, Object> params) {
        return mouigosaMapper.getRegistrationCount(params);
    }

    /**
     * 모의고사 정보 조회
     */
    public List<HashMap<String, Object>> getUpdateRegistrationDetail(Map<String, Object> params) {
        return mouigosaMapper.getUpdateRegistrationDetail(params);
    }

    /**
     * 과목 리스트 조회
     */
    public List<HashMap<String, Object>> getSubjectList(Map<String, Object> params) {
        return mouigosaMapper.getSubjectList(params);
    }

    /**
     * 문제 리스트 조회
     */
    public List<HashMap<String, Object>> getQuestionList(Map<String, Object> params) {
        return mouigosaMapper.getQuestionList(params);
    }

    /**
     * 답안 수 조회
     */
    public int getCnt(Map<String, Object> params) {
        return mouigosaMapper.getCnt(params);
    }

    /**
     * 미응시 답안 수 조회
     */
    public int getCnt2(Map<String, Object> params) {
        return mouigosaMapper.getCnt2(params);
    }

    /**
     * 전체 문항 수 조회
     */
    public int getCnt3(Map<String, Object> params) {
        return mouigosaMapper.getCnt3(params);
    }

    /**
     * 응시 완료 과목 수 조회
     */
    public int getCnt4(Map<String, Object> params) {
        return mouigosaMapper.getCnt4(params);
    }

    /**
     * 전체 과목 수 조회
     */
    public int getCnt5(Map<String, Object> params) {
        return mouigosaMapper.getCnt5(params);
    }

    /**
     * 답안 등록
     */
    public int insertAnswerMouigosa(Map<String, Object> params) {
        return mouigosaMapper.insertAnswerMouigosa(params);
    }

    /**
     * 답안 수정
     */
    public int updateAnswerMouigosa(Map<String, Object> params) {
        return mouigosaMapper.updateAnswerMouigosa(params);
    }

    /**
     * 응시상태 업데이트 (시작)
     */
    public int mouigosaStatusUpdate12(Map<String, Object> params) {
        return mouigosaMapper.mouigosaStatusUpdate12(params);
    }

    /**
     * 응시상태 업데이트 (과목)
     */
    public int mouigosaStatusUpdate2(Map<String, Object> params) {
        return mouigosaMapper.mouigosaStatusUpdate2(params);
    }

    /**
     * 응시상태 업데이트 (종료)
     */
    public int mouigosaStatusUpdate3(Map<String, Object> params) {
        return mouigosaMapper.mouigosaStatusUpdate3(params);
    }

    /**
     * 응시상태 업데이트 (전체 종료)
     */
    public int mouigosaStatusUpdate4(Map<String, Object> params) {
        return mouigosaMapper.mouigosaStatusUpdate4(params);
    }

    /**
     * 문제별 정답 조회
     */
    public HashMap<String, Object> selectQuestionAnswers(Map<String, Object> params) {
        return mouigosaMapper.selectQuestionAnswers(params);
    }

    /**
     * 다음 과목 조회
     */
    public List<HashMap<String, Object>> getCount1List2(Map<String, Object> params) {
        return mouigosaMapper.getCount1List2(params);
    }
}
