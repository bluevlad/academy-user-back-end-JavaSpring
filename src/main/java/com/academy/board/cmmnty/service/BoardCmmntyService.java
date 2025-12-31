package com.academy.board.cmmnty.service;

import com.academy.mapper.BoardCmmntyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardCmmntyService.java
 * 커뮤니티 게시판 서비스
 */
@Service
public class BoardCmmntyService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardCmmntyMapper boardCmmntyMapper;

    @Autowired
    public BoardCmmntyService(BoardCmmntyMapper boardCmmntyMapper) {
        this.boardCmmntyMapper = boardCmmntyMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardCmmntyMapper.getBoardInfo(params);
    }

    /**
     * 커뮤니티 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardCmmntyMapper.boardList(params);
    }

    /**
     * 커뮤니티 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardCmmntyMapper.boardListCount(params);
    }

    /**
     * BEST 게시물 목록 조회
     */
    public List<HashMap<String, Object>> boardBestList(HashMap<String, String> params) {
        return boardCmmntyMapper.boardBestList(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardCmmntyMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardCmmntyMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardCmmntyMapper.boardAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardCmmntyMapper.updateBoardHits(params);
    }

    /**
     * 게시물 등록
     */
    public String insertProcess(HashMap<String, String> params) {
        boardCmmntyMapper.insertBoard(params);
        return params.get("BOARD_SEQ");
    }

    /**
     * 게시물 수정
     */
    public void updateProcess(HashMap<String, String> params) {
        boardCmmntyMapper.updateBoard(params);
    }

    /**
     * 게시물 삭제
     */
    public void deleteProcess(HashMap<String, String> params) {
        boardCmmntyMapper.deleteBoard(params);
    }

    /**
     * 추천 등록
     */
    public String insertRecommnd(HashMap<String, String> params) {
        boardCmmntyMapper.insertRecommnd(params);
        return boardCmmntyMapper.getRecommndCount(params);
    }

    /**
     * 댓글 목록 조회
     */
    public List<HashMap<String, Object>> getCommentList(HashMap<String, String> params) {
        return boardCmmntyMapper.getCommentList(params);
    }

    /**
     * 댓글 수 조회
     */
    public int getCommentCount(HashMap<String, String> params) {
        return boardCmmntyMapper.getCommentCount(params);
    }

    /**
     * 댓글 등록
     */
    public void insertComment(HashMap<String, String> params) {
        boardCmmntyMapper.insertComment(params);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(HashMap<String, String> params) {
        boardCmmntyMapper.deleteComment(params);
    }
}
