package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface PassLectureMapper {

    /**
     * 학원실강 수강중인 강의 목록
     */
    List<HashMap<String, Object>> passLectureIngList(Map<String, Object> params);

    /**
     * 학원실강 수강중인 강의 카운트
     */
    int passLectureIngCount(Map<String, Object> params);

    /**
     * 학원실강 수강종료 강의 목록
     */
    List<HashMap<String, Object>> passLectureEndList(Map<String, Object> params);

    /**
     * 학원실강 수강종료 강의 카운트
     */
    int passLectureEndCount(Map<String, Object> params);

    /**
     * 학원실강 무료특강 강의 목록
     */
    List<HashMap<String, Object>> passLectureFreeList(Map<String, Object> params);

    /**
     * 학원실강 교재 목록
     */
    List<HashMap<String, Object>> passBookList(Map<String, Object> params);
}
