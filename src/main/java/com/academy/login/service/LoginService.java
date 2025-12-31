package com.academy.login.service;

import com.academy.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * LoginService.java
 * 로그인/회원 관련 서비스 클래스
 */
@Service
public class LoginService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    /**
     * 로그인 처리
     */
    public HashMap<String, Object> login(HashMap<String, String> params) {
        return loginMapper.login(params);
    }

    /**
     * 회원 정보 조회
     */
    public HashMap<String, Object> memberInfo(HashMap<String, String> params) {
        return loginMapper.memberInfo(params);
    }

    /**
     * 회원 ID 중복 체크
     */
    public int memberIdCheck(HashMap<String, String> params) {
        return loginMapper.memberIdCheck(params);
    }

    /**
     * 휴대폰 번호 중복 체크
     */
    public int phoneNumCheck(HashMap<String, String> params) {
        return loginMapper.phoneNumCheck(params);
    }

    /**
     * 카테고리 목록 조회
     */
    public List<HashMap<String, Object>> getCategory(HashMap<String, String> params) {
        return loginMapper.getCategory(params);
    }

    /**
     * 회원 가입
     */
    @Transactional
    public void userInsertProcess(HashMap<String, String> params) {
        loginMapper.userInsertProcess(params);
    }

    /**
     * 회원 카테고리 등록
     */
    @Transactional
    public void userCategoryInsert(HashMap<String, String> params) {
        loginMapper.userCategoryDelete(params);
        loginMapper.userCategoryInsert(params);
    }

    /**
     * 아이디 찾기
     */
    public HashMap<String, Object> getSearchId(HashMap<String, String> params) {
        return loginMapper.getSearchId(params);
    }

    /**
     * 비밀번호 찾기
     */
    public HashMap<String, Object> getSearchPW(HashMap<String, String> params) {
        return loginMapper.getSearchPW(params);
    }

    /**
     * 로그인 로그 등록
     */
    public void loginLogInsert(HashMap<String, String> params) {
        loginMapper.loginLogInsert(params);
    }

    /**
     * 로그인 체크 건수
     */
    public int loginCheckCnt(HashMap<String, String> params) {
        return loginMapper.loginCheckCnt(params);
    }

}
