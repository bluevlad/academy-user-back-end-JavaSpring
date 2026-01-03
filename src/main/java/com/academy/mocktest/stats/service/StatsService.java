package com.academy.mocktest.stats.service;

import com.academy.mapper.StatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final StatsMapper statsMapper;

    @Autowired
    public StatsService(StatsMapper statsMapper) {
        this.statsMapper = statsMapper;
    }

    public int statsTotalCnt(Map<String, Object> params) {
        return statsMapper.statsTotalCnt(params);
    }

    public List<HashMap<String, Object>> statsSubjectList(Map<String, Object> params) {
        return statsMapper.statsSubjectList(params);
    }

    public List<HashMap<String, Object>> statsSubjectAnswerList(Map<String, Object> params) {
        return statsMapper.statsSubjectAnswerList(params);
    }

    public List<HashMap<String, Object>> statsSubjectInfoList(Map<String, Object> params) {
        return statsMapper.statsSubjectInfoList(params);
    }

    public List<HashMap<String, Object>> statsTotalInfoList(Map<String, Object> params) {
        return statsMapper.statsTotalInfoList(params);
    }

    public List<HashMap<String, Object>> statsTotalInfo1(Map<String, Object> params) {
        return statsMapper.statsTotalInfo1(params);
    }

    public List<HashMap<String, Object>> statsTotalInfo2(Map<String, Object> params) {
        return statsMapper.statsTotalInfo2(params);
    }

    public List<HashMap<String, Object>> statsTotalInfo2_TblH(Map<String, Object> params) {
        return statsMapper.statsTotalInfo2_TblH(params);
    }

    public List<HashMap<String, Object>> statsTotalInfo2_Tbl(Map<String, Object> params) {
        return statsMapper.statsTotalInfo2_Tbl(params);
    }
}
