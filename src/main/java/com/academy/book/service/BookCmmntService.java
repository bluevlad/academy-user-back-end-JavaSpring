package com.academy.book.service;

import com.academy.mapper.BookCmmntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BookCmmntService.java
 * 도서 후기(댓글) 서비스
 */
@Service
public class BookCmmntService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookCmmntMapper bookCmmntMapper;

    @Autowired
    public BookCmmntService(BookCmmntMapper bookCmmntMapper) {
        this.bookCmmntMapper = bookCmmntMapper;
    }

    /**
     * 도서 후기 목록 조회
     */
    public List<HashMap<String, Object>> selectBookCmmt(HashMap<String, String> params) {
        return bookCmmntMapper.selectBookCmmt(params);
    }

    /**
     * 도서 후기 목록 수 조회
     */
    public int getBookCmmtCount(HashMap<String, String> params) {
        return bookCmmntMapper.getBookCmmtCount(params);
    }

    /**
     * 도서 구매 여부 확인
     */
    public int isBookCmmt(HashMap<String, String> params) {
        return bookCmmntMapper.isBookCmmt(params);
    }

    /**
     * 사용자별 도서 후기 수 조회
     */
    public int getBookCmmtCntByUser(HashMap<String, String> params) {
        return bookCmmntMapper.getBookCmmtCntByUser(params);
    }

    /**
     * 도서 상세 후기 목록 조회
     */
    public List<HashMap<String, Object>> detailCmmtList(HashMap<String, String> params) {
        return bookCmmntMapper.detailCmmtList(params);
    }

    /**
     * 도서 상세 후기 목록 수 조회
     */
    public int detailCmmtListCount(HashMap<String, String> params) {
        return bookCmmntMapper.detailCmmtListCount(params);
    }

    /**
     * 도서 후기 등록
     */
    public void insertCmmt(HashMap<String, String> params) {
        bookCmmntMapper.insertCmmt(params);
    }

    /**
     * 도서 후기 삭제
     */
    public void deleteCmmt(HashMap<String, String> params) {
        bookCmmntMapper.deleteCmmt(params);
    }
}
