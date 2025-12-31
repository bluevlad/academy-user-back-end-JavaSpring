package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * LoginMapper.java
 * 로그인/회원 관련 Mapper 인터페이스
 */
@Mapper
public interface LoginMapper {

    /**
     * 로그인 처리
     */
    HashMap<String, Object> login(HashMap<String, String> params);

    /**
     * 회원 정보 조회
     */
    HashMap<String, Object> memberInfo(HashMap<String, String> params);

    /**
     * 회원 ID 중복 체크
     */
    int memberIdCheck(HashMap<String, String> params);

    /**
     * 휴대폰 번호 중복 체크
     */
    int phoneNumCheck(HashMap<String, String> params);

    /**
     * 카테고리 목록 조회
     */
    List<HashMap<String, Object>> getCategory(HashMap<String, String> params);

    /**
     * 회원 가입
     */
    void userInsertProcess(HashMap<String, String> params);

    /**
     * 회원 카테고리 삭제
     */
    void userCategoryDelete(HashMap<String, String> params);

    /**
     * 회원 카테고리 등록
     */
    void userCategoryInsert(HashMap<String, String> params);

    /**
     * 아이디 찾기
     */
    HashMap<String, Object> getSearchId(HashMap<String, String> params);

    /**
     * 비밀번호 찾기
     */
    HashMap<String, Object> getSearchPW(HashMap<String, String> params);

    /**
     * 로그인 로그 등록
     */
    void loginLogInsert(HashMap<String, String> params);

    /**
     * 로그인 체크 건수
     */
    int loginCheckCnt(HashMap<String, String> params);

}
