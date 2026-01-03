package com.academy.lecture.packagelecture.service;

import com.academy.mapper.PackageLectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * PackageLectureService.java
 * 패키지 강의 서비스
 */
@Service
public class PackageLectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PackageLectureMapper packageLectureMapper;

    @Autowired
    public PackageLectureService(PackageLectureMapper packageLectureMapper) {
        this.packageLectureMapper = packageLectureMapper;
    }

    /**
     * 패키지 강의 목록 조회
     */
    public List<HashMap<String, Object>> packageLectureOnList(HashMap<String, Object> params) {
        return packageLectureMapper.packageLectureOnList(params);
    }

    /**
     * 패키지 강의 목록 건수 조회
     */
    public int packageLectureOnListCount(HashMap<String, Object> params) {
        return packageLectureMapper.packageLectureOnListCount(params);
    }

    /**
     * 패키지 강의 상세 조회 (필수 강좌)
     */
    public List<HashMap<String, Object>> packageLectureOnDetail1(HashMap<String, Object> params) {
        return packageLectureMapper.packageLectureOnDetail1(params);
    }

    /**
     * 패키지 강의 상세 조회 (선택 강좌)
     */
    public List<HashMap<String, Object>> packageLectureOnDetail2(HashMap<String, Object> params) {
        return packageLectureMapper.packageLectureOnDetail2(params);
    }

    /**
     * 오프라인 패키지 강의 목록 조회
     */
    public List<HashMap<String, Object>> packageLecturePassList(HashMap<String, Object> params) {
        return packageLectureMapper.packageLecturePassList(params);
    }

    /**
     * 오프라인 패키지 강의 상세 조회
     */
    public List<HashMap<String, Object>> packageLectureOffDetail(HashMap<String, Object> params) {
        return packageLectureMapper.packageLectureOffDetail(params);
    }

    /**
     * 오프라인 패키지 강의 상세 정보 조회
     */
    public HashMap<String, Object> lectureOffDetailS(HashMap<String, Object> params) {
        return packageLectureMapper.lectureOffDetailS(params);
    }
}
