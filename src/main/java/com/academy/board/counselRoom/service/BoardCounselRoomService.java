package com.academy.board.counselRoom.service;

import com.academy.mapper.BoardCounselRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardCounselRoomService.java
 * 상담실 게시판 서비스
 */
@Service
public class BoardCounselRoomService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardCounselRoomMapper boardCounselRoomMapper;

    @Autowired
    public BoardCounselRoomService(BoardCounselRoomMapper boardCounselRoomMapper) {
        this.boardCounselRoomMapper = boardCounselRoomMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardCounselRoomMapper.getBoardInfo(params);
    }

    /**
     * 상담실 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardList(params);
    }

    /**
     * 상담실 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardListCount(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardView(params);
    }

    /**
     * 답변 상세 조회
     */
    public HashMap<String, Object> boardReplyView(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardReplyView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardAttachFileImg(params);
    }

    /**
     * 답변 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardReplyAttachFile(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardReplyAttachFile(params);
    }

    /**
     * 답변 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardReplyAttachFileImg(HashMap<String, String> params) {
        return boardCounselRoomMapper.boardReplyAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardCounselRoomMapper.updateBoardHits(params);
    }

    /**
     * 게시물 등록
     */
    public String insertProcess(HashMap<String, String> params) {
        boardCounselRoomMapper.insertBoard(params);
        return params.get("BOARD_SEQ");
    }

    /**
     * 게시물 수정
     */
    public void updateProcess(HashMap<String, String> params) {
        boardCounselRoomMapper.updateBoard(params);
    }

    /**
     * 게시물 삭제
     */
    public void deleteProcess(HashMap<String, String> params) {
        boardCounselRoomMapper.deleteBoard(params);
    }

    /**
     * 답변 등록
     */
    public void insertReply(HashMap<String, String> params) {
        boardCounselRoomMapper.insertReply(params);
        // 원글 답변 상태 업데이트
        boardCounselRoomMapper.updateReplyStatus(params);
    }

    /**
     * 답변 수정
     */
    public void updateReply(HashMap<String, String> params) {
        boardCounselRoomMapper.updateReply(params);
    }
}
