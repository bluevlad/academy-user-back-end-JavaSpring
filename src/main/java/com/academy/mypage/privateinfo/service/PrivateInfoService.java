package com.academy.mypage.privateinfo.service;

import com.academy.mapper.PrivateInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrivateInfoService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PrivateInfoMapper privateInfoMapper;

    @Autowired
    public PrivateInfoService(PrivateInfoMapper privateInfoMapper) {
        this.privateInfoMapper = privateInfoMapper;
    }

    public HashMap<String, Object> getUserInfo(Map<String, Object> params) {
        return privateInfoMapper.getUserInfo(params);
    }

    public int updateUserInfo(Map<String, Object> params) {
        return privateInfoMapper.updateUserInfo(params);
    }

    public int updatePassword(Map<String, Object> params) {
        return privateInfoMapper.updatePassword(params);
    }

    public int checkPassword(Map<String, Object> params) {
        return privateInfoMapper.checkPassword(params);
    }

    public int updateProfileImage(Map<String, Object> params) {
        return privateInfoMapper.updateProfileImage(params);
    }

    public int deleteUser(Map<String, Object> params) {
        return privateInfoMapper.deleteUser(params);
    }

    public int checkEmailDuplicate(Map<String, Object> params) {
        return privateInfoMapper.checkEmailDuplicate(params);
    }

    public int checkPhoneDuplicate(Map<String, Object> params) {
        return privateInfoMapper.checkPhoneDuplicate(params);
    }

    public int updateMarketingAgree(Map<String, Object> params) {
        return privateInfoMapper.updateMarketingAgree(params);
    }

    public int updateUserStatus(Map<String, Object> params) {
        return privateInfoMapper.updateUserStatus(params);
    }

    public int updateLastLogin(Map<String, Object> params) {
        return privateInfoMapper.updateLastLogin(params);
    }
}
