package com.academy.event.on.service;

import com.academy.mapper.EventOnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * EventOnService.java
 * 온라인 동영상 이벤트 서비스
 */
@Service
public class EventOnService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EventOnMapper eventOnMapper;

    @Autowired
    public EventOnService(EventOnMapper eventOnMapper) {
        this.eventOnMapper = eventOnMapper;
    }

    /**
     * 온라인 이벤트 목록 조회
     */
    public List<HashMap<String, Object>> onEventList(HashMap<String, Object> params) {
        return eventOnMapper.onEventList(params);
    }

    /**
     * 온라인 이벤트 건수 조회
     */
    public int onEventListCount(HashMap<String, Object> params) {
        return eventOnMapper.onEventListCount(params);
    }

    /**
     * 온라인 이벤트 상세 조회
     */
    public HashMap<String, Object> onEventDetail(HashMap<String, Object> params) {
        return eventOnMapper.onEventDetail(params);
    }

    /**
     * 온라인 이벤트 첨부파일 목록 조회
     */
    public List<HashMap<String, Object>> onEventAttachFileList(HashMap<String, Object> params) {
        return eventOnMapper.onEventAttachFileList(params);
    }

    /**
     * 온라인 이벤트 강좌 목록 조회
     */
    public List<HashMap<String, Object>> onEventLectureList(HashMap<String, Object> params) {
        return eventOnMapper.onEventLectureList(params);
    }

    /**
     * 온라인 이벤트 댓글 목록 조회
     */
    public List<HashMap<String, Object>> onEventCommentList(HashMap<String, Object> params) {
        return eventOnMapper.onEventCommentList(params);
    }

    /**
     * 온라인 이벤트 조회수 증가
     */
    @Transactional
    public void updateOnEventHits(HashMap<String, Object> params) {
        eventOnMapper.updateOnEventHits(params);
    }

    /**
     * 온라인 이벤트 댓글 등록
     */
    @Transactional
    public void insertOnEventComment(HashMap<String, Object> params) {
        eventOnMapper.insertOnEventComment(params);
    }

    /**
     * 온라인 이벤트 댓글 삭제
     */
    @Transactional
    public void deleteOnEventComment(HashMap<String, Object> params) {
        eventOnMapper.deleteOnEventComment(params);
    }

    /**
     * 온라인 이벤트 출석 체크
     */
    @Transactional
    public void insertOnEventAttendance(HashMap<String, Object> params) {
        eventOnMapper.insertOnEventAttendance(params);
    }

    /**
     * 온라인 이벤트 출석 확인
     */
    public int checkOnEventAttendance(HashMap<String, Object> params) {
        return eventOnMapper.checkOnEventAttendance(params);
    }

    /**
     * 온라인 이벤트 출석 목록 조회
     */
    public List<HashMap<String, Object>> onEventAttendanceList(HashMap<String, Object> params) {
        return eventOnMapper.onEventAttendanceList(params);
    }
}
