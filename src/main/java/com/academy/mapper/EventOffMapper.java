package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * EventOffMapper.java
 * 오프라인 이벤트(설명회) Mapper Interface
 */
@Mapper
public interface EventOffMapper {

    /**
     * 오프라인 이벤트 목록 조회
     */
    List<HashMap<String, Object>> offEventList(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 건수 조회
     */
    int offEventListCount(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 상세 조회
     */
    HashMap<String, Object> offEventDetail(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 첨부파일 목록 조회
     */
    List<HashMap<String, Object>> offEventAttachFileList(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 옵션1 목록 조회 (선택 항목)
     */
    List<HashMap<String, Object>> offEventDetailOption1(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 조회수 증가
     */
    void updateOffEventHits(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 중복 참여 체크
     */
    int dupOffEventCheck(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 참여 신청
     */
    void insertOffEventResult(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 참여 취소
     */
    void deleteOffEventResult(HashMap<String, Object> params);

    /**
     * 오프라인 이벤트 참여 정보 조회
     */
    HashMap<String, Object> getOffEventResult(HashMap<String, Object> params);
}
