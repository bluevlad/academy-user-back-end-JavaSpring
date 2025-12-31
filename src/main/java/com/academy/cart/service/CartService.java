package com.academy.cart.service;

import com.academy.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * CartService.java
 * 장바구니 관련 서비스 클래스
 */
@Service
public class CartService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    /**
     * 온라인 강좌 장바구니 리스트 조회
     */
    public List<HashMap<String, Object>> cartLectureOnList(HashMap<String, String> params) {
        return cartMapper.cartLectureOnList(params);
    }

    /**
     * 온라인 도서 장바구니 리스트 조회
     */
    public List<HashMap<String, Object>> cartBookOnList(HashMap<String, String> params) {
        return cartMapper.cartBookOnList(params);
    }

    /**
     * 오프라인 강좌 장바구니 리스트 조회
     */
    public List<HashMap<String, Object>> cartLectureOffList(HashMap<String, String> params) {
        return cartMapper.cartLectureOffList(params);
    }

    /**
     * 온라인 장바구니 전체 삭제
     */
    @Transactional
    public void clearOnCartAll(HashMap<String, String> params) {
        cartMapper.clearOnCartAll(params);
    }

    /**
     * 온라인 장바구니 강좌 삭제 (SEQ 기준)
     */
    @Transactional
    public void deleteOnCartSeq(HashMap<String, String> params) {
        cartMapper.deleteOnCartSeq(params);
    }

    /**
     * 온라인 장바구니 강좌 삭제 (강좌코드 기준)
     */
    @Transactional
    public void deleteOnCartLecCode(HashMap<String, String> params) {
        cartMapper.deleteOnCartLecCode(params);
    }

    /**
     * 온라인 장바구니 도서 삭제
     */
    @Transactional
    public void deleteOnCartBook(HashMap<String, String> params) {
        cartMapper.deleteOnCartBook(params);
    }

    /**
     * 오프라인 장바구니 전체 삭제
     */
    @Transactional
    public void clearOffCartAll(HashMap<String, String> params) {
        cartMapper.clearOffCartAll(params);
    }

    /**
     * 오프라인 장바구니 강좌 삭제 (SEQ 기준)
     */
    @Transactional
    public void deleteOffCartSeq(HashMap<String, String> params) {
        cartMapper.deleteOffCartSeq(params);
    }

    /**
     * 오프라인 장바구니 강좌 삭제 (강좌코드 기준)
     */
    @Transactional
    public void deleteOffCartLecCode(HashMap<String, String> params) {
        cartMapper.deleteOffCartLecCode(params);
    }

    /**
     * 회원 정보 조회
     */
    public HashMap<String, Object> getMemberInfo(HashMap<String, String> params) {
        return cartMapper.getMemberInfo(params);
    }

    /**
     * 장바구니 도서 포인트 조회
     */
    public String getCartBookPoint(HashMap<String, String> params) {
        return cartMapper.getCartBookPoint(params);
    }

    /**
     * 장바구니 강좌 포인트 조회
     */
    public String getCartLecPoint(HashMap<String, String> params) {
        return cartMapper.getCartLecPoint(params);
    }

}
