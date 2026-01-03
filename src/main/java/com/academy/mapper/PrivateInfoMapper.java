package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface PrivateInfoMapper {

    /**
     * 회원 정보 조회
     */
    HashMap<String, Object> getUserInfo(Map<String, Object> params);

    /**
     * 회원 정보 수정
     */
    int updateUserInfo(Map<String, Object> params);

    /**
     * 비밀번호 변경
     */
    int updatePassword(Map<String, Object> params);

    /**
     * 비밀번호 확인
     */
    int checkPassword(Map<String, Object> params);

    /**
     * 프로필 이미지 수정
     */
    int updateProfileImage(Map<String, Object> params);

    /**
     * 회원 탈퇴
     */
    int deleteUser(Map<String, Object> params);

    /**
     * 이메일 중복 체크
     */
    int checkEmailDuplicate(Map<String, Object> params);

    /**
     * 휴대폰 중복 체크
     */
    int checkPhoneDuplicate(Map<String, Object> params);

    /**
     * 마케팅 수신 동의 수정
     */
    int updateMarketingAgree(Map<String, Object> params);

    /**
     * 회원 상태 수정
     */
    int updateUserStatus(Map<String, Object> params);

    /**
     * 최근 로그인 정보 수정
     */
    int updateLastLogin(Map<String, Object> params);
}
