package com.academy.mypage.cart.service;

import com.academy.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Service
public class CartService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public List<HashMap<String, Object>> cartLectureOnList(HashMap<String, String> params) {
        return cartMapper.cartLectureOnList(params);
    }

    public List<HashMap<String, Object>> cartBookOnList(HashMap<String, String> params) {
        return cartMapper.cartBookOnList(params);
    }

    public List<HashMap<String, Object>> cartLectureOffList(HashMap<String, String> params) {
        return cartMapper.cartLectureOffList(params);
    }

    public void clearOnCartAll(HashMap<String, String> params) {
        cartMapper.clearOnCartAll(params);
    }

    public void deleteOnCartSeq(HashMap<String, String> params) {
        cartMapper.deleteOnCartSeq(params);
    }

    public void deleteOnCartLecCode(HashMap<String, String> params) {
        cartMapper.deleteOnCartLecCode(params);
    }

    public void deleteOnCartBook(HashMap<String, String> params) {
        cartMapper.deleteOnCartBook(params);
    }

    public void clearOffCartAll(HashMap<String, String> params) {
        cartMapper.clearOffCartAll(params);
    }

    public void deleteOffCartSeq(HashMap<String, String> params) {
        cartMapper.deleteOffCartSeq(params);
    }

    public void deleteOffCartLecCode(HashMap<String, String> params) {
        cartMapper.deleteOffCartLecCode(params);
    }

    public HashMap<String, Object> getMemberInfo(HashMap<String, String> params) {
        return cartMapper.getMemberInfo(params);
    }

    public String getCartBookPoint(HashMap<String, String> params) {
        return cartMapper.getCartBookPoint(params);
    }

    public String getCartLecPoint(HashMap<String, String> params) {
        return cartMapper.getCartLecPoint(params);
    }
}
