package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * LectureReplyMapper.java
 * 강의 수강후기 Mapper Interface
 */
@Mapper
public interface LectureReplyMapper {

    /**
     * 수강후기 목록 조회
     */
    List<HashMap<String, Object>> lectureReplyList(HashMap<String, Object> params);

    /**
     * 수강후기 목록 건수 조회
     */
    int lectureReplyListCount(HashMap<String, Object> params);

    /**
     * 수강후기 상세 목록 조회
     */
    List<HashMap<String, Object>> detailReplyList(HashMap<String, Object> params);

    /**
     * 수강후기 상세 목록 건수 조회
     */
    int detailReplyListCount(HashMap<String, Object> params);

    /**
     * 수강후기 작성 가능 여부 체크
     */
    int lectureIsReply(HashMap<String, Object> params);

    /**
     * 과목 목록 조회
     */
    List<HashMap<String, Object>> getSubjectList(HashMap<String, Object> params);

    /**
     * 수강후기 등록
     */
    void insertReply(HashMap<String, Object> params);

    /**
     * 수강후기 삭제
     */
    void replyDelete(HashMap<String, Object> params);

    /**
     * 메인 수강후기 목록 조회
     */
    List<HashMap<String, Object>> lectureReplyMainList(HashMap<String, Object> params);
}
