package com.academy.boardNotice.service;

import com.academy.mapper.BoardNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeService.java
 * 공지사항 관련 서비스 클래스
 */
@Service
public class BoardNoticeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardNoticeMapper boardNoticeMapper;

    @Autowired
    public BoardNoticeService(BoardNoticeMapper boardNoticeMapper) {
        this.boardNoticeMapper = boardNoticeMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardNoticeMapper.getBoardInfo(params);
    }

    /**
     * 게시판 정보 목록 조회
     */
    public List<HashMap<String, Object>> getBoardInfoList(HashMap<String, String> params) {
        return boardNoticeMapper.getBoardInfoList(params);
    }

    /**
     * 메인 게시판 목록 조회
     */
    public List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params) {
        return boardNoticeMapper.getMainBoardList(params);
    }

    /**
     * 게시글 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardNoticeMapper.boardList(params);
    }

    /**
     * 게시글 건수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardNoticeMapper.boardListCount(params);
    }

    /**
     * 게시글 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardNoticeMapper.boardView(params);
    }

    /**
     * 조회수 증가
     */
    @Transactional
    public void updateBoardHits(HashMap<String, String> params) {
        boardNoticeMapper.updateBoardHits(params);
    }

    /**
     * FAQ 목록 조회
     */
    public List<HashMap<String, Object>> boardFAQList(HashMap<String, String> params) {
        return boardNoticeMapper.boardFAQList(params);
    }

}
