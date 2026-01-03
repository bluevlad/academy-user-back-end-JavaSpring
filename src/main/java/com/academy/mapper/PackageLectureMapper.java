package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * PackageLectureMapper.java
 * 패키지 강의 Mapper Interface
 */
@Mapper
public interface PackageLectureMapper {

    /**
     * 패키지 강의 목록 조회
     */
    List<HashMap<String, Object>> packageLectureOnList(HashMap<String, Object> params);

    /**
     * 패키지 강의 목록 건수 조회
     */
    int packageLectureOnListCount(HashMap<String, Object> params);

    /**
     * 패키지 강의 상세 조회 (필수 강좌)
     */
    List<HashMap<String, Object>> packageLectureOnDetail1(HashMap<String, Object> params);

    /**
     * 패키지 강의 상세 조회 (선택 강좌)
     */
    List<HashMap<String, Object>> packageLectureOnDetail2(HashMap<String, Object> params);

    /**
     * 오프라인 패키지 강의 목록 조회
     */
    List<HashMap<String, Object>> packageLecturePassList(HashMap<String, Object> params);

    /**
     * 오프라인 패키지 강의 상세 조회
     */
    List<HashMap<String, Object>> packageLectureOffDetail(HashMap<String, Object> params);

    /**
     * 오프라인 패키지 강의 상세 정보 조회
     */
    HashMap<String, Object> lectureOffDetailS(HashMap<String, Object> params);
}
