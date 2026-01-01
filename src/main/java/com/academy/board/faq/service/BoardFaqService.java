package com.academy.board.faq.service;

import com.academy.mapper.BoardFaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BoardFaqService.java
 * FAQ 게시판 서비스
 */
@Service
public class BoardFaqService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BoardFaqMapper boardFaqMapper;

    @Autowired
    public BoardFaqService(BoardFaqMapper boardFaqMapper) {
        this.boardFaqMapper = boardFaqMapper;
    }

    /**
     * 게시판 정보 조회
     */
    public HashMap<String, Object> getBoardInfo(HashMap<String, String> params) {
        return boardFaqMapper.getBoardInfo(params);
    }

    /**
     * FAQ 게시판 목록 조회
     */
    public List<HashMap<String, Object>> boardFaqList(HashMap<String, String> params) {
        return boardFaqMapper.boardFaqList(params);
    }

    /**
     * FAQ 게시판 목록 수 조회
     */
    public int boardFaqListCount(HashMap<String, String> params) {
        return boardFaqMapper.boardFaqListCount(params);
    }

    /**
     * FAQ 상세 조회
     */
    public HashMap<String, Object> boardFaqView(HashMap<String, String> params) {
        return boardFaqMapper.boardFaqView(params);
    }

    /**
     * FAQ 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> getFaqCategoryList(HashMap<String, String> params) {
        return boardFaqMapper.getFaqCategoryList(params);
    }
}
