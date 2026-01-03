package com.academy.mypage.mylecture.service;

import com.academy.mapper.MyLectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyLectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MyLectureMapper myLectureMapper;

    @Autowired
    public MyLectureService(MyLectureMapper myLectureMapper) {
        this.myLectureMapper = myLectureMapper;
    }

    public HashMap<String, Object> myStudyInfo(Map<String, Object> params) {
        return myLectureMapper.myStudyInfo(params);
    }

    public List<HashMap<String, Object>> myStudyforMonth(Map<String, Object> params) {
        return myLectureMapper.myStudyforMonth(params);
    }

    public List<HashMap<String, Object>> myMockforMonth(Map<String, Object> params) {
        return myLectureMapper.myMockforMonth(params);
    }

    public HashMap<String, Object> getMyMockInfo(Map<String, Object> params) {
        return myLectureMapper.getMyMockInfo(params);
    }

    public int insertMock(Map<String, Object> params) {
        return myLectureMapper.insertMock(params);
    }

    public int insertExam(Map<String, Object> params) {
        return myLectureMapper.insertExam(params);
    }

    public List<HashMap<String, Object>> getMyLectureList(Map<String, Object> params) {
        return myLectureMapper.getMyLectureList(params);
    }

    public int getMyLectureListCount(Map<String, Object> params) {
        return myLectureMapper.getMyLectureListCount(params);
    }
}
