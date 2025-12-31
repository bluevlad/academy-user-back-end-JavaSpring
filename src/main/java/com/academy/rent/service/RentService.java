package com.academy.rent.service;

import com.academy.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * RentService.java
 * 사물함/독서실 대여 관련 서비스 클래스
 */
@Service
public class RentService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final RentMapper rentMapper;

    @Autowired
    public RentService(RentMapper rentMapper) {
        this.rentMapper = rentMapper;
    }

    /**
     * 사물함 리스트 조회
     */
    public List<HashMap<String, Object>> boxList(HashMap<String, String> params) {
        return rentMapper.boxList(params);
    }

    /**
     * 사물함 상세 번호 리스트 조회
     */
    public List<HashMap<String, Object>> boxNumList(HashMap<String, String> params) {
        return rentMapper.boxNumList(params);
    }

    /**
     * 현재 사용중인 사물함 조회
     */
    public List<HashMap<String, Object>> boxNumChk(HashMap<String, String> params) {
        return rentMapper.boxNumChk(params);
    }

    /**
     * 독서실 리스트 조회
     */
    public List<HashMap<String, Object>> roomList(HashMap<String, String> params) {
        return rentMapper.roomList(params);
    }

    /**
     * 독서실 상세 번호 리스트 조회
     */
    public List<HashMap<String, Object>> roomNumList(HashMap<String, String> params) {
        return rentMapper.roomNumList(params);
    }

    /**
     * 현재 사용중인 독서실 조회
     */
    public List<HashMap<String, Object>> roomNumChk(HashMap<String, String> params) {
        return rentMapper.roomNumChk(params);
    }

    /**
     * 오프라인 강의 수강 이력 조회 (독서실 신청 자격 체크)
     */
    public List<HashMap<String, Object>> offLearnedChkForRoom(HashMap<String, String> params) {
        return rentMapper.offLearnedChkForRoom(params);
    }

    /**
     * 대여 장바구니 등록/수정
     */
    @Transactional
    public void aplRentCartInsert(HashMap<String, String> params) {
        List<HashMap<String, Object>> existingCart = rentMapper.aplRentCartSelect(params);

        if (existingCart != null && !existingCart.isEmpty()) {
            rentMapper.aplRentCartUpdate(params);
        } else {
            rentMapper.aplRentCartInsert(params);
        }
    }

    /**
     * 사물함 결제 정보 조회
     */
    public List<HashMap<String, Object>> boxPayViewSelect(HashMap<String, String> params) {
        return rentMapper.boxPayViewSelect(params);
    }

    /**
     * 독서실 결제 정보 조회
     */
    public List<HashMap<String, Object>> roomPayViewSelect(HashMap<String, String> params) {
        return rentMapper.roomPayViewSelect(params);
    }

}
