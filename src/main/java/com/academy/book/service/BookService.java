package com.academy.book.service;

import com.academy.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * BookService.java
 * 도서 관련 서비스 클래스
 */
@Service
public class BookService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 도서 리스트 조회
     */
    public List<HashMap<String, Object>> bookList(HashMap<String, String> params) {
        return bookMapper.bookList(params);
    }

    /**
     * 도서 리스트 건수 조회
     */
    public int bookListCount(HashMap<String, String> params) {
        return bookMapper.bookListCount(params);
    }

    /**
     * 좌측 학습형태 리스트 조회
     */
    public List<HashMap<String, Object>> leftFormList(HashMap<String, String> params) {
        return bookMapper.leftFormList(params);
    }

    /**
     * 좌측 교수 리스트 조회
     */
    public List<HashMap<String, Object>> leftTeacherList(HashMap<String, String> params) {
        return bookMapper.leftTeacherList(params);
    }

    /**
     * 교수 리스트 조회
     */
    public List<HashMap<String, Object>> selectTeacherList(HashMap<String, String> params) {
        return bookMapper.selectTeacherList(params);
    }

    /**
     * 도서 연관 강의 리스트 조회
     */
    public List<HashMap<String, Object>> bookLectureList(HashMap<String, String> params) {
        return bookMapper.bookLectureList(params);
    }

    /**
     * 과목 정보 조회
     */
    public HashMap<String, Object> getSubjectInfo(HashMap<String, String> params) {
        return bookMapper.getSubjectInfo(params);
    }

    /**
     * 도서 장바구니 체크
     */
    public List<HashMap<String, Object>> bookCartCheck(HashMap<String, String> params) {
        return bookMapper.bookCartCheck(params);
    }

    /**
     * 도서 장바구니 등록
     */
    @Transactional
    public void bookCartInsert(HashMap<String, String> params) {
        bookMapper.bookCartInsert(params);
    }

    /**
     * 도서 장바구니 수정
     */
    @Transactional
    public void bookCartUpdate(HashMap<String, String> params) {
        bookMapper.bookCartUpdate(params);
    }

    /**
     * 학습형태 리스트 조회
     */
    public List<HashMap<String, Object>> getLearningFormList(HashMap<String, String> params) {
        return bookMapper.getLearningFormList(params);
    }

    /**
     * 과목-교수 리스트 조회
     */
    public List<HashMap<String, Object>> getCaSubjectTeacherList(HashMap<String, String> params) {
        return bookMapper.getCaSubjectTeacherList(params);
    }

    /**
     * 도서 상세 조회 (리스트형)
     */
    public List<HashMap<String, Object>> bookViewList(HashMap<String, String> params) {
        return bookMapper.bookViewList(params);
    }

    /**
     * 도서 상세 조회
     */
    public HashMap<String, Object> bookView(HashMap<String, String> params) {
        return bookMapper.bookView(params);
    }

    /**
     * 도서 사용 여부 체크
     */
    public int bookUseCheck(HashMap<String, String> params) {
        return bookMapper.bookUseCheck(params);
    }

}
