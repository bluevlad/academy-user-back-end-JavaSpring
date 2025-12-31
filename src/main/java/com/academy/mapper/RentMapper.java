package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * RentMapper.java
 * 사물함/독서실 대여 관련 Mapper 인터페이스
 */
@Mapper
public interface RentMapper {

    /**
     * 사물함 리스트 조회
     */
    List<HashMap<String, Object>> boxList(HashMap<String, String> params);

    /**
     * 사물함 상세 번호 리스트 조회
     */
    List<HashMap<String, Object>> boxNumList(HashMap<String, String> params);

    /**
     * 현재 사용중인 사물함 조회
     */
    List<HashMap<String, Object>> boxNumChk(HashMap<String, String> params);

    /**
     * 독서실 리스트 조회
     */
    List<HashMap<String, Object>> roomList(HashMap<String, String> params);

    /**
     * 독서실 상세 번호 리스트 조회
     */
    List<HashMap<String, Object>> roomNumList(HashMap<String, String> params);

    /**
     * 현재 사용중인 독서실 조회
     */
    List<HashMap<String, Object>> roomNumChk(HashMap<String, String> params);

    /**
     * 오프라인 강의 수강 이력 조회 (독서실 신청 자격 체크)
     */
    List<HashMap<String, Object>> offLearnedChkForRoom(HashMap<String, String> params);

    /**
     * 대여 장바구니 조회
     */
    List<HashMap<String, Object>> aplRentCartSelect(HashMap<String, String> params);

    /**
     * 대여 장바구니 등록
     */
    void aplRentCartInsert(HashMap<String, String> params);

    /**
     * 대여 장바구니 수정
     */
    void aplRentCartUpdate(HashMap<String, String> params);

    /**
     * 사물함 결제 정보 조회
     */
    List<HashMap<String, Object>> boxPayViewSelect(HashMap<String, String> params);

    /**
     * 독서실 결제 정보 조회
     */
    List<HashMap<String, Object>> roomPayViewSelect(HashMap<String, String> params);

}
