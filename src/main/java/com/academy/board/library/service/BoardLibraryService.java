package com.academy.board.library.service;

import com.academy.mapper.BoardLibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardLibraryService.java
 * 자료실 게시판 서비스
 */
@Service
public class BoardLibraryService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardLibraryMapper boardLibraryMapper;

    @Autowired
    public BoardLibraryService(BoardLibraryMapper boardLibraryMapper) {
        this.boardLibraryMapper = boardLibraryMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardLibraryMapper.getBoardInfo(params);
    }

    /**
     * 자료실 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardLibraryMapper.boardList(params);
    }

    /**
     * 자료실 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardLibraryMapper.boardListCount(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardLibraryMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardLibraryMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardLibraryMapper.boardAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardLibraryMapper.updateBoardHits(params);
    }
}
