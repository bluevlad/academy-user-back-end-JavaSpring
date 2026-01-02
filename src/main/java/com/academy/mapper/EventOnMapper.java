package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * EventOnMapper.java
 * 온라인 동영상 이벤트 Mapper Interface
 */
@Mapper
public interface EventOnMapper {

    /**
     * 온라인 이벤트 목록 조회
     */
    List<HashMap<String, Object>> onEventList(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 건수 조회
     */
    int onEventListCount(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 상세 조회
     */
    HashMap<String, Object> onEventDetail(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 첨부파일 목록 조회
     */
    List<HashMap<String, Object>> onEventAttachFileList(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 강좌 목록 조회
     */
    List<HashMap<String, Object>> onEventLectureList(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 댓글 목록 조회
     */
    List<HashMap<String, Object>> onEventCommentList(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 조회수 증가
     */
    void updateOnEventHits(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 댓글 등록
     */
    void insertOnEventComment(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 댓글 삭제
     */
    void deleteOnEventComment(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 출석 체크
     */
    void insertOnEventAttendance(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 출석 확인
     */
    int checkOnEventAttendance(HashMap<String, Object> params);

    /**
     * 온라인 이벤트 출석 목록 조회
     */
    List<HashMap<String, Object>> onEventAttendanceList(HashMap<String, Object> params);
}
