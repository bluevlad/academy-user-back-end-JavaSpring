package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * CategoryMapper.java
 * 강의 카테고리 관련 Mapper 인터페이스
 */
@Mapper
public interface CategoryMapper {

    /**
     * 직렬 트리 리스트 조회
     */
    List<HashMap<String, Object>> getSeriesCateTree(HashMap<String, String> params);

    /**
     * 카테고리 리스트 조회
     */
    List<HashMap<String, Object>> selectCategory(HashMap<String, String> params);

    /**
     * 카테고리 건수 조회
     */
    int getCategoryCount(HashMap<String, String> params);

    /**
     * 직렬-과목 리스트 조회
     */
    List<HashMap<String, Object>> selectSeriesSubject(HashMap<String, String> params);

    /**
     * 직렬-교수 리스트 조회
     */
    List<HashMap<String, Object>> selectSeriesProf(HashMap<String, String> params);

    /**
     * 서브 메인화면 과목 목록 조회
     */
    List<HashMap<String, Object>> selectSubMainCateSubjectList(HashMap<String, String> params);

    /**
     * 서브 메인화면 교수 목록 조회
     */
    List<HashMap<String, Object>> selectSubMainCateTeacherList(HashMap<String, String> params);

}
