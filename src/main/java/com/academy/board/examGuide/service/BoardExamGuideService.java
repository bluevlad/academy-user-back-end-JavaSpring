package com.academy.board.examGuide.service;

import com.academy.mapper.BoardExamGuideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardExamGuideService.java
 * 시험안내 게시판 서비스
 */
@Service
public class BoardExamGuideService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardExamGuideMapper boardExamGuideMapper;

    @Autowired
    public BoardExamGuideService(BoardExamGuideMapper boardExamGuideMapper) {
        this.boardExamGuideMapper = boardExamGuideMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardExamGuideMapper.getBoardInfo(params);
    }

    /**
     * 시험안내 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardExamGuideMapper.boardList(params);
    }

    /**
     * 시험안내 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardExamGuideMapper.boardListCount(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardExamGuideMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardExamGuideMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardExamGuideMapper.boardAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardExamGuideMapper.updateBoardHits(params);
    }

    /**
     * 공고 구분 코드 목록 조회
     */
    public List<HashMap<String, Object>> getPubGubun(HashMap<String, Object> params) {
        return boardExamGuideMapper.getPubGubun(params);
    }
}
