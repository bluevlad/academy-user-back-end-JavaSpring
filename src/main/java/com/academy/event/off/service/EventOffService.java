package com.academy.event.off.service;

import com.academy.mapper.EventOffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * EventOffService.java
 * 오프라인 이벤트(설명회) 서비스
 */
@Service
public class EventOffService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EventOffMapper eventOffMapper;

    @Autowired
    public EventOffService(EventOffMapper eventOffMapper) {
        this.eventOffMapper = eventOffMapper;
    }

    /**
     * 오프라인 이벤트 목록 조회
     */
    public List<HashMap<String, Object>> offEventList(HashMap<String, Object> params) {
        return eventOffMapper.offEventList(params);
    }

    /**
     * 오프라인 이벤트 건수 조회
     */
    public int offEventListCount(HashMap<String, Object> params) {
        return eventOffMapper.offEventListCount(params);
    }

    /**
     * 오프라인 이벤트 상세 조회
     */
    public HashMap<String, Object> offEventDetail(HashMap<String, Object> params) {
        return eventOffMapper.offEventDetail(params);
    }

    /**
     * 오프라인 이벤트 첨부파일 목록 조회
     */
    public List<HashMap<String, Object>> offEventAttachFileList(HashMap<String, Object> params) {
        return eventOffMapper.offEventAttachFileList(params);
    }

    /**
     * 오프라인 이벤트 옵션1 목록 조회 (선택 항목)
     */
    public List<HashMap<String, Object>> offEventDetailOption1(HashMap<String, Object> params) {
        return eventOffMapper.offEventDetailOption1(params);
    }

    /**
     * 오프라인 이벤트 조회수 증가
     */
    @Transactional
    public void updateOffEventHits(HashMap<String, Object> params) {
        eventOffMapper.updateOffEventHits(params);
    }

    /**
     * 오프라인 이벤트 중복 참여 체크
     */
    public int dupOffEventCheck(HashMap<String, Object> params) {
        return eventOffMapper.dupOffEventCheck(params);
    }

    /**
     * 오프라인 이벤트 참여 신청
     */
    @Transactional
    public void insertOffEventResult(HashMap<String, Object> params) {
        eventOffMapper.insertOffEventResult(params);
    }

    /**
     * 오프라인 이벤트 참여 취소
     */
    @Transactional
    public void deleteOffEventResult(HashMap<String, Object> params) {
        eventOffMapper.deleteOffEventResult(params);
    }

    /**
     * 오프라인 이벤트 참여 정보 조회
     */
    public HashMap<String, Object> getOffEventResult(HashMap<String, Object> params) {
        return eventOffMapper.getOffEventResult(params);
    }
}
