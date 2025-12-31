package com.academy.board.examInfoOn.service;

import com.academy.mapper.BoardExamInfoOnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardExamInfoOnService.java
 * 시험정보 온라인 게시판 서비스
 */
@Service
public class BoardExamInfoOnService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardExamInfoOnMapper boardExamInfoOnMapper;

    @Autowired
    public BoardExamInfoOnService(BoardExamInfoOnMapper boardExamInfoOnMapper) {
        this.boardExamInfoOnMapper = boardExamInfoOnMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardExamInfoOnMapper.getBoardInfo(params);
    }

    /**
     * 시험정보 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardList(HashMap<String, String> params) {
        return boardExamInfoOnMapper.boardList(params);
    }

    /**
     * 시험정보 게시판 목록 수 조회
     */
    public int boardListCount(HashMap<String, String> params) {
        return boardExamInfoOnMapper.boardListCount(params);
    }

    /**
     * 게시물 상세 조회
     */
    public HashMap<String, Object> boardView(HashMap<String, String> params) {
        return boardExamInfoOnMapper.boardView(params);
    }

    /**
     * 게시물 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFile(HashMap<String, String> params) {
        return boardExamInfoOnMapper.boardAttachFile(params);
    }

    /**
     * 게시물 이미지 첨부파일 조회
     */
    public List<HashMap<String, Object>> boardAttachFileImg(HashMap<String, String> params) {
        return boardExamInfoOnMapper.boardAttachFileImg(params);
    }

    /**
     * 조회수 증가
     */
    public void updateBoardHits(HashMap<String, String> params) {
        boardExamInfoOnMapper.updateBoardHits(params);
    }

    /**
     * 공고 구분 코드 목록 조회
     */
    public List<HashMap<String, Object>> getPubGubun(HashMap<String, Object> params) {
        return boardExamInfoOnMapper.getPubGubun(params);
    }

    /**
     * 공고 게시판 목록 조회
     */
    public List<HashMap<String, Object>> pubNoticeList(HashMap<String, Object> params) {
        return boardExamInfoOnMapper.pubNoticeList(params);
    }

    /**
     * 공고 게시판 목록 수 조회
     */
    public int pubNoticeListCount(HashMap<String, Object> params) {
        return boardExamInfoOnMapper.pubNoticeListCount(params);
    }

    /**
     * 공고 상세 조회
     */
    public HashMap<String, Object> pubNoticeView(HashMap<String, String> params) {
        return boardExamInfoOnMapper.pubNoticeView(params);
    }

    /**
     * 공고 조회수 증가
     */
    public void updatePubNoticeHits(HashMap<String, String> params) {
        boardExamInfoOnMapper.updatePubNoticeHits(params);
    }

    /**
     * 공고 첨부파일 조회
     */
    public List<HashMap<String, Object>> pubNoticeAttachFile(HashMap<String, String> params) {
        return boardExamInfoOnMapper.pubNoticeAttachFile(params);
    }

    /**
     * 공고 카테고리 조회
     */
    public List<HashMap<String, Object>> getPubCategorySel(HashMap<String, Object> params) {
        return boardExamInfoOnMapper.getPubCategorySel(params);
    }
}
