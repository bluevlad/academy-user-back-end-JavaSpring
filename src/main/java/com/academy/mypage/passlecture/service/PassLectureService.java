package com.academy.mypage.passlecture.service;

import com.academy.mapper.PassLectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PassLectureService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PassLectureMapper passLectureMapper;

    @Autowired
    public PassLectureService(PassLectureMapper passLectureMapper) {
        this.passLectureMapper = passLectureMapper;
    }

    public List<HashMap<String, Object>> passLectureIngList(Map<String, Object> params) {
        return passLectureMapper.passLectureIngList(params);
    }

    public int passLectureIngCount(Map<String, Object> params) {
        return passLectureMapper.passLectureIngCount(params);
    }

    public List<HashMap<String, Object>> passLectureEndList(Map<String, Object> params) {
        return passLectureMapper.passLectureEndList(params);
    }

    public int passLectureEndCount(Map<String, Object> params) {
        return passLectureMapper.passLectureEndCount(params);
    }

    public List<HashMap<String, Object>> passLectureFreeList(Map<String, Object> params) {
        return passLectureMapper.passLectureFreeList(params);
    }

    public List<HashMap<String, Object>> passBookList(Map<String, Object> params) {
        return passLectureMapper.passBookList(params);
    }
}
