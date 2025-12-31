package com.academy.interview.service;

import com.academy.mapper.InterviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * InterviewService.java
 * 상담/문의 게시판 관련 서비스 클래스
 */
@Service
public class InterviewService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final InterviewMapper interviewMapper;

    @Autowired
    public InterviewService(InterviewMapper interviewMapper) {
        this.interviewMapper = interviewMapper;
    }

    /**
     * 게시판 리스트 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return interviewMapper.boardList(params);
    }

    /**
     * 게시판 리스트 건수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return interviewMapper.boardListCount(params);
    }

    /**
     * 게시글 등록
     */
    @Transactional
    public void insertProcess(HashMap<String, String> params) {
        interviewMapper.insertProcess(params);

        // 카테고리 게시판 정보 등록
        interviewMapper.insertBoardCategory(params);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void updateProcess(HashMap<String, String> params) {
        interviewMapper.updateProcess(params);
    }

    /**
     * 게시글 상세 조회
     */
    public HashMap<String, Object> boardDetail(HashMap<String, String> params) {
        // 조회수 증가
        interviewMapper.updateHits(params);

        return interviewMapper.boardDetail(params);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteProcess(HashMap<String, String> params) {
        interviewMapper.deleteProcess(params);
    }

}
