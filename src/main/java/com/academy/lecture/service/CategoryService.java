package com.academy.lecture.service;

import com.academy.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * CategoryService.java
 * 강의 카테고리 관련 서비스 클래스
 */
@Service
public class CategoryService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 직렬 트리 리스트 조회
     */
    public List<HashMap<String, Object>> getSeriesCate(HashMap<String, String> params) {
        return categoryMapper.getSeriesCateTree(params);
    }

    /**
     * 카테고리 리스트 조회
     */
    public List<HashMap<String, Object>> selectCategory(HashMap<String, String> params) {
        return categoryMapper.selectCategory(params);
    }

    /**
     * 카테고리 건수 조회
     */
    public int getCategoryCount(HashMap<String, String> params) {
        return categoryMapper.getCategoryCount(params);
    }

    /**
     * 직렬-과목 리스트 조회
     */
    public List<HashMap<String, Object>> selectSeriesSubject(HashMap<String, String> params) {
        return categoryMapper.selectSeriesSubject(params);
    }

    /**
     * 직렬-교수 리스트 조회
     */
    public List<HashMap<String, Object>> selectSeriesProf(HashMap<String, String> params) {
        return categoryMapper.selectSeriesProf(params);
    }

    /**
     * 서브 메인화면 과목 목록 조회
     */
    public List<HashMap<String, Object>> selectSubMainCateSubjectList(HashMap<String, String> params) {
        return categoryMapper.selectSubMainCateSubjectList(params);
    }

    /**
     * 서브 메인화면 교수 목록 조회
     */
    public List<HashMap<String, Object>> selectSubMainCateTeacherList(HashMap<String, String> params) {
        return categoryMapper.selectSubMainCateTeacherList(params);
    }

}
