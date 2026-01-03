package com.academy.mypage.counsel.service;

import com.academy.mapper.CounselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CounselService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final CounselMapper counselMapper;

    @Autowired
    public CounselService(CounselMapper counselMapper) {
        this.counselMapper = counselMapper;
    }

    public List<HashMap<String, Object>> boardList(Map<String, Object> params) {
        return counselMapper.boardList(params);
    }

    public int boardListCount(Map<String, Object> params) {
        return counselMapper.boardListCount(params);
    }

    public HashMap<String, Object> boardView(Map<String, Object> params) {
        return counselMapper.boardView(params);
    }

    public int insertBoard(Map<String, Object> params) {
        return counselMapper.insertBoard(params);
    }

    public int updateBoard(Map<String, Object> params) {
        return counselMapper.updateBoard(params);
    }

    public int deleteBoard(Map<String, Object> params) {
        return counselMapper.deleteBoard(params);
    }

    public int updateHits(Map<String, Object> params) {
        return counselMapper.updateHits(params);
    }
}
