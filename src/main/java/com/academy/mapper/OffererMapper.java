package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface OffererMapper {

    /**
     * 모의고사 접수 목록 조회
     */
    List<HashMap<String, Object>> offererTakeList(Map<String, Object> params);

    /**
     * 모의고사 접수 목록 카운트
     */
    int offererTakeListCount(Map<String, Object> params);

    /**
     * 모의고사 정보 조회
     */
    HashMap<String, Object> offererMouiInfo(Map<String, Object> params);

    /**
     * 모의고사 직렬 정보 조회
     */
    List<HashMap<String, Object>> offererMouiAddClassInfo(Map<String, Object> params);

    /**
     * 모의고사 과목 리스트 조회
     */
    List<HashMap<String, Object>> offererMouiClsSeriesSbjtList(Map<String, Object> params);

    /**
     * 현재 시간 조회
     */
    String offererCurrentdate(Map<String, Object> params);

    /**
     * 모의고사 접수 등록
     */
    int offererRequestInsert(Map<String, Object> params);

    /**
     * 사용자 선택 과목 등록
     */
    int offererChoiceSubjectInsert(Map<String, Object> params);

    /**
     * 모의고사 접수 조회
     */
    HashMap<String, Object> offererReqMouiInfo(Map<String, Object> params);

    /**
     * 선택 과목 리스트 조회
     */
    List<HashMap<String, Object>> offererSubjectList(Map<String, Object> params);

    /**
     * 모의고사 접수 삭제
     */
    int offererDeletes(Map<String, Object> params);

    /**
     * 사용자 선택 과목 삭제
     */
    int offererChoiceSubjectDeletes(Map<String, Object> params);

    /**
     * 모의고사 정보 조회 (수정용)
     */
    HashMap<String, Object> offererMoui(Map<String, Object> params);

    /**
     * 모의고사 직렬 정보 조회 (수정용)
     */
    List<HashMap<String, Object>> offererMouiClassInfo(Map<String, Object> params);

    /**
     * 모의고사 접수 정보 조회
     */
    HashMap<String, Object> offererView(Map<String, Object> params);

    /**
     * 모의고사 선택 과목 리스트 조회
     */
    List<HashMap<String, Object>> offererMouiSubjectList(Map<String, Object> params);

    /**
     * 모의고사 접수 수정
     */
    int offererRequestUpdate(Map<String, Object> params);

    /**
     * 모의고사 결제 등록
     */
    int offererPaymentInsert(Map<String, Object> params);

    /**
     * 모의고사 결제 상태 업데이트
     */
    int offererPaymentUpdate(Map<String, Object> params);

    /**
     * 응시표 출력 업데이트
     */
    int offererPrintUpdate(Map<String, Object> params);

    /**
     * 모의고사 접수 목록 카운트
     */
    int offererListCount(Map<String, Object> params);

    /**
     * 접수한 과목 리스트 조회
     */
    List<HashMap<String, Object>> offererTakeSubjectList(Map<String, Object> params);

    /**
     * 주문번호 생성
     */
    String getOffererId(Map<String, Object> params);

    /**
     * 응시번호 생성
     */
    String getIdentyId(Map<String, Object> params);
}
