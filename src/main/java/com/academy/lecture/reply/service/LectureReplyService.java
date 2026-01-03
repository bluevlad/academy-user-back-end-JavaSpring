package com.academy.lecture.reply.service;

import com.academy.mapper.LectureReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * LectureReplyService.java
 * 강의 수강후기 서비스
 */
@Service
public class LectureReplyService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LectureReplyMapper lectureReplyMapper;

    @Autowired
    public LectureReplyService(LectureReplyMapper lectureReplyMapper) {
        this.lectureReplyMapper = lectureReplyMapper;
    }

    /**
     * 수강후기 목록 조회
     */
    public List<HashMap<String, Object>> lectureReplyList(HashMap<String, Object> params) {
        return lectureReplyMapper.lectureReplyList(params);
    }

    /**
     * 수강후기 목록 건수 조회
     */
    public int lectureReplyListCount(HashMap<String, Object> params) {
        return lectureReplyMapper.lectureReplyListCount(params);
    }

    /**
     * 수강후기 상세 목록 조회
     */
    public List<HashMap<String, Object>> detailReplyList(HashMap<String, Object> params) {
        return lectureReplyMapper.detailReplyList(params);
    }

    /**
     * 수강후기 상세 목록 건수 조회
     */
    public int detailReplyListCount(HashMap<String, Object> params) {
        return lectureReplyMapper.detailReplyListCount(params);
    }

    /**
     * 수강후기 작성 가능 여부 체크
     */
    public int lectureIsReply(HashMap<String, Object> params) {
        return lectureReplyMapper.lectureIsReply(params);
    }

    /**
     * 과목 목록 조회
     */
    public List<HashMap<String, Object>> getSubjectList(HashMap<String, Object> params) {
        return lectureReplyMapper.getSubjectList(params);
    }

    /**
     * 수강후기 등록
     */
    @Transactional
    public void insertReply(HashMap<String, Object> params) {
        lectureReplyMapper.insertReply(params);
    }

    /**
     * 수강후기 삭제
     */
    @Transactional
    public void replyDelete(HashMap<String, Object> params) {
        lectureReplyMapper.replyDelete(params);
    }

    /**
     * 메인 수강후기 목록 조회
     */
    public List<HashMap<String, Object>> lectureReplyMainList(HashMap<String, Object> params) {
        return lectureReplyMapper.lectureReplyMainList(params);
    }
}
