package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * OpenLectureMapper.java
 * 공개강의 관련 Mapper 인터페이스
 */
@Mapper
public interface OpenLectureMapper {

    /**
     * 공개강의 상세 조회
     */
    HashMap<String, Object> openlectureOnDetail(HashMap<String, String> params);

    /**
     * 공개강의 목록 조회
     */
    List<HashMap<String, Object>> openlectureOnList(HashMap<String, String> params);

}
