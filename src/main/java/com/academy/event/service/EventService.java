package com.academy.event.service;

import com.academy.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * EventService.java
 * 이벤트 관련 서비스 클래스
 */
@Service
public class EventService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    /**
     * 이벤트 목록 조회
     */
    public List<HashMap<String, Object>> eventList(HashMap<String, String> params) {
        return eventMapper.eventList(params);
    }

    /**
     * 이벤트 건수 조회
     */
    public int eventListCount(HashMap<String, String> params) {
        return eventMapper.eventListCount(params);
    }

    /**
     * 이벤트 상세 조회
     */
    public HashMap<String, Object> eventDetail(HashMap<String, String> params) {
        return eventMapper.eventDetail(params);
    }

    /**
     * 이벤트 첨부파일 조회
     */
    public HashMap<String, Object> eventAttachFile(HashMap<String, String> params) {
        return eventMapper.eventAttachFile(params);
    }

    /**
     * 이벤트 첨부파일 목록 조회
     */
    public List<HashMap<String, Object>> eventAttachFileList(HashMap<String, String> params) {
        return eventMapper.eventAttachFileList(params);
    }

    /**
     * 이벤트 강좌 목록 조회
     */
    public List<HashMap<String, Object>> eventLectureList(HashMap<String, String> params) {
        return eventMapper.eventLectureList(params);
    }

    /**
     * 이벤트 강좌 상세 조회
     */
    public HashMap<String, Object> eventLectureDetail(HashMap<String, String> params) {
        return eventMapper.eventLectureDetail(params);
    }

    /**
     * 이벤트 조회수 증가
     */
    @Transactional
    public void updateEventHits(HashMap<String, String> params) {
        eventMapper.updateEventHits(params);
    }

    /**
     * 이벤트 옵션1 조회
     */
    public List<HashMap<String, Object>> eventDetailOption1(HashMap<String, String> params) {
        return eventMapper.eventDetailOption1(params);
    }

    /**
     * 이벤트 옵션2 조회
     */
    public List<HashMap<String, Object>> eventDetailOption2(HashMap<String, String> params) {
        return eventMapper.eventDetailOption2(params);
    }

    /**
     * 이벤트 중복 체크
     */
    public String dupCheck(HashMap<String, String> params) {
        return eventMapper.dupCheck(params);
    }

    /**
     * 이벤트 결과 등록
     */
    @Transactional
    public void insertEventResult(HashMap<String, String> params) {
        eventMapper.insertEventResult(params);
    }

    /**
     * 이벤트 댓글 등록
     */
    @Transactional
    public void insertEventComment(HashMap<String, String> params) {
        eventMapper.insertEventComment(params);
    }

    /**
     * 이벤트 댓글 삭제
     */
    @Transactional
    public void deleteEventComment(HashMap<String, String> params) {
        eventMapper.deleteEventComment(params);
    }

}
