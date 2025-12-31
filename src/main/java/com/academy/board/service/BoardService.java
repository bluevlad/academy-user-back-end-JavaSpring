package com.academy.board.service;

import com.academy.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardService.java
 * 게시판 관련 서비스 클래스
 */
@Service
public class BoardService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardMapper.getBoardInfo(params);
    }

    /**
     * 게시판 정보 리스트 조회
     */
    public List<HashMap<String, Object>> getBoardInfoList(HashMap<String, String> params) {
        return boardMapper.getBoardInfoList(params);
    }

    /**
     * 메인 게시판 리스트 조회
     */
    public List<HashMap<String, Object>> getMainBoardList(HashMap<String, String> params) {
        return boardMapper.getMainBoardList(params);
    }

    /**
     * 게시판 리스트 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardMapper.boardList(params);
    }

    /**
     * 게시판 리스트 건수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardMapper.boardListCount(params);
    }

    /**
     * 게시물 조회수 업데이트
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardMapper.updateBoardHits(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardMapper.boardAttachFileImg(params);
    }

    /**
     * 게시물 등록
     */
    @Transactional
    public void insertProcess(HashMap<String, String> params) {
        boardMapper.insertProcess(params);
    }

    /**
     * 게시물 수정
     */
    @Transactional
    public void updateProcess(HashMap<String, String> params) {
        boardMapper.updateProcess(params);
    }

    /**
     * 게시물 삭제
     */
    @Transactional
    public void deleteProcess(HashMap<String, String> params) {
        boardMapper.deleteBoardCateInfo(params);
        boardMapper.deleteBoardFileData(params);
        boardMapper.deleteBoardData(params);
    }

    /**
     * 답글 조회
     */
    public HashMap<String, Object> boardReplyView(HashMap<String, String> params) {
        return boardMapper.boardReplyView(params);
    }

    /**
     * 답글 등록
     */
    @Transactional
    public void insertReplyProcess(HashMap<String, String> params) {
        boardMapper.insertReplyProcess(params);
    }

    /**
     * 답글 존재 여부 확인
     */
    public int getIsReply(HashMap<String, String> params) {
        return boardMapper.getIsReply(params);
    }

}
