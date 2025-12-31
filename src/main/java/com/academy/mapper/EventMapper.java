package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * EventMapper.java
 * 이벤트 관련 Mapper 인터페이스
 */
@Mapper
public interface EventMapper {

    /**
     * 이벤트 목록 조회
     */
    List<HashMap<String, Object>> eventList(HashMap<String, String> params);

    /**
     * 이벤트 건수 조회
     */
    int eventListCount(HashMap<String, String> params);

    /**
     * 이벤트 상세 조회
     */
    HashMap<String, Object> eventDetail(HashMap<String, String> params);

    /**
     * 이벤트 첨부파일 조회
     */
    HashMap<String, Object> eventAttachFile(HashMap<String, String> params);

    /**
     * 이벤트 첨부파일 목록 조회
     */
    List<HashMap<String, Object>> eventAttachFileList(HashMap<String, String> params);

    /**
     * 이벤트 강좌 목록 조회
     */
    List<HashMap<String, Object>> eventLectureList(HashMap<String, String> params);

    /**
     * 이벤트 강좌 상세 조회
     */
    HashMap<String, Object> eventLectureDetail(HashMap<String, String> params);

    /**
     * 이벤트 조회수 증가
     */
    void updateEventHits(HashMap<String, String> params);

    /**
     * 이벤트 옵션1 조회
     */
    List<HashMap<String, Object>> eventDetailOption1(HashMap<String, String> params);

    /**
     * 이벤트 옵션2 조회
     */
    List<HashMap<String, Object>> eventDetailOption2(HashMap<String, String> params);

    /**
     * 이벤트 중복 체크
     */
    String dupCheck(HashMap<String, String> params);

    /**
     * 이벤트 결과 등록
     */
    void insertEventResult(HashMap<String, String> params);

    /**
     * 이벤트 댓글 등록
     */
    void insertEventComment(HashMap<String, String> params);

    /**
     * 이벤트 댓글 삭제
     */
    void deleteEventComment(HashMap<String, String> params);

}
