package com.academy.mypage.service;

import com.academy.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * MypageService.java
 * 마이페이지 관련 서비스 클래스
 */
@Service
public class MypageService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MypageMapper mypageMapper;

    @Autowired
    public MypageService(MypageMapper mypageMapper) {
        this.mypageMapper = mypageMapper;
    }

    /**
     * 내 정보 조회
     */
    public HashMap<String, Object> getMyInfo(HashMap<String, String> params) {
        return mypageMapper.getMyInfo(params);
    }

    /**
     * 내 정보 수정
     */
    @Transactional
    public void infoUpdate(HashMap<String, String> params) {
        mypageMapper.infoUpdate(params);
    }

    /**
     * 비밀번호 확인
     */
    public HashMap<String, Object> pwdConfirm(HashMap<String, String> params) {
        return mypageMapper.pwdConfirm(params);
    }

    /**
     * 비밀번호 변경
     */
    @Transactional
    public void pwdUpdate(HashMap<String, String> params) {
        mypageMapper.pwdUpdate(params);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void secessionDelete(HashMap<String, String> params) {
        mypageMapper.secessionDelete(params);
    }

    /**
     * 내 학습 정보 조회
     */
    public HashMap<String, Object> myStudyInfo(HashMap<String, String> params) {
        return mypageMapper.myStudyInfo(params);
    }

    /**
     * 월별 학습 정보 조회
     */
    public List<HashMap<String, Object>> myStudyforMonth(HashMap<String, String> params) {
        return mypageMapper.myStudyforMonth(params);
    }

    /**
     * 월별 모의고사 정보 조회
     */
    public List<HashMap<String, Object>> myMockforMonth(HashMap<String, String> params) {
        return mypageMapper.myMockforMonth(params);
    }

    /**
     * 내 강의 리스트 조회
     */
    public List<HashMap<String, Object>> myLectureList(HashMap<String, String> params) {
        return mypageMapper.myLectureList(params);
    }

    /**
     * 내 강의 코스 리스트 조회
     */
    public List<HashMap<String, Object>> myLectureCourseList(HashMap<String, String> params) {
        return mypageMapper.myLectureCourseList(params);
    }

    /**
     * 수료증 강의 리스트 조회
     */
    public List<HashMap<String, Object>> myCertLectureList(HashMap<String, String> params) {
        return mypageMapper.myCertLectureList(params);
    }

    /**
     * 수료증 패키지 리스트 조회
     */
    public List<HashMap<String, Object>> myCertPackageList(HashMap<String, String> params) {
        return mypageMapper.myCertPackageList(params);
    }

    /**
     * 수료증 강의 상세 조회
     */
    public HashMap<String, Object> myCertLectureView(HashMap<String, String> params) {
        return mypageMapper.myCertLectureView(params);
    }

    /**
     * 수료증 패키지 상세 조회
     */
    public HashMap<String, Object> myCertPackageView(HashMap<String, String> params) {
        return mypageMapper.myCertPackageView(params);
    }

}
