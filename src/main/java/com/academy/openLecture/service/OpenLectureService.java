package com.academy.openLecture.service;

import com.academy.mapper.OpenLectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * OpenLectureService.java
 * 공개강의 관련 서비스 클래스
 */
@Service
public class OpenLectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final OpenLectureMapper openLectureMapper;

    @Autowired
    public OpenLectureService(OpenLectureMapper openLectureMapper) {
        this.openLectureMapper = openLectureMapper;
    }

    /**
     * 공개강의 상세 조회
     */
    public HashMap<String, Object> openlectureOnDetail(HashMap<String, String> params) {
        return openLectureMapper.openlectureOnDetail(params);
    }

    /**
     * 공개강의 목록 조회
     */
    public List<HashMap<String, Object>> openlectureOnList(HashMap<String, String> params) {
        return openLectureMapper.openlectureOnList(params);
    }

}
