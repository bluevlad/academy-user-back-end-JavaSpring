package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * BoardFaqMapper.java
 * FAQ 게시판 Mapper Interface
 */
@Mapper
public interface BoardFaqMapper {

    /**
     * 게시판 정보 조회
     */
    HashMap<String, Object> getBoardInfo(HashMap<String, String> params);

    /**
     * FAQ 게시판 목록 조회
     */
    List<HashMap<String, Object>> boardFaqList(HashMap<String, String> params);

    /**
     * FAQ 게시판 목록 수 조회
     */
    int boardFaqListCount(HashMap<String, String> params);

    /**
     * FAQ 상세 조회
     */
    HashMap<String, Object> boardFaqView(HashMap<String, String> params);

    /**
     * FAQ 카테고리 목록 조회
     */
    List<HashMap<String, Object>> getFaqCategoryList(HashMap<String, String> params);
}
