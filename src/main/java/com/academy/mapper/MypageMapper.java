package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * MypageMapper.java
 * 마이페이지 관련 Mapper 인터페이스
 */
@Mapper
public interface MypageMapper {

    /**
     * 내 정보 조회
     */
    HashMap<String, Object> getMyInfo(HashMap<String, String> params);

    /**
     * 내 정보 수정
     */
    void infoUpdate(HashMap<String, String> params);

    /**
     * 비밀번호 확인
     */
    HashMap<String, Object> pwdConfirm(HashMap<String, String> params);

    /**
     * 비밀번호 변경
     */
    void pwdUpdate(HashMap<String, String> params);

    /**
     * 회원 탈퇴
     */
    void secessionDelete(HashMap<String, String> params);

    /**
     * 내 학습 정보 조회
     */
    HashMap<String, Object> myStudyInfo(HashMap<String, String> params);

    /**
     * 월별 학습 정보 조회
     */
    List<HashMap<String, Object>> myStudyforMonth(HashMap<String, String> params);

    /**
     * 월별 모의고사 정보 조회
     */
    List<HashMap<String, Object>> myMockforMonth(HashMap<String, String> params);

    /**
     * 내 강의 리스트 조회
     */
    List<HashMap<String, Object>> myLectureList(HashMap<String, String> params);

    /**
     * 내 강의 코스 리스트 조회
     */
    List<HashMap<String, Object>> myLectureCourseList(HashMap<String, String> params);

    /**
     * 수료증 강의 리스트 조회
     */
    List<HashMap<String, Object>> myCertLectureList(HashMap<String, String> params);

    /**
     * 수료증 패키지 리스트 조회
     */
    List<HashMap<String, Object>> myCertPackageList(HashMap<String, String> params);

    /**
     * 수료증 강의 상세 조회
     */
    HashMap<String, Object> myCertLectureView(HashMap<String, String> params);

    /**
     * 수료증 패키지 상세 조회
     */
    HashMap<String, Object> myCertPackageView(HashMap<String, String> params);

}
