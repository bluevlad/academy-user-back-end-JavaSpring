package com.academy.board.notice.service;

import com.academy.mapper.BoardNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeService.java
 * 공지사항 게시판 서비스
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
     * 공지사항 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardNoticeMapper.boardList(params);
    }

    /**
     * 공지사항 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardNoticeMapper.boardListCount(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardNoticeMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardNoticeMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardNoticeMapper.boardAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardNoticeMapper.updateBoardHits(params);
    }

    /**
     * 메인 공지사항 목록 조회
     */
    public List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params) {
        return boardNoticeMapper.getMainBoardList(params);
    }
}
