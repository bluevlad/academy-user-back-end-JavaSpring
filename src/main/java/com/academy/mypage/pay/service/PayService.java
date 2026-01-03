package com.academy.mypage.pay.service;

import com.academy.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Service
public class PayService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final PayMapper payMapper;

    @Autowired
    public PayService(PayMapper payMapper) {
        this.payMapper = payMapper;
    }

    public List<HashMap<String, Object>> payOrderAlllist(HashMap<String, String> params) {
        return payMapper.payOrderAlllist(params);
    }

    public int orderAlllistCount(HashMap<String, String> params) {
        return payMapper.orderAlllistCount(params);
    }

    public List<HashMap<String, Object>> payOrderOnlist(HashMap<String, String> params) {
        return payMapper.payOrderOnlist(params);
    }

    public List<HashMap<String, Object>> payOrderOfflist(HashMap<String, String> params) {
        return payMapper.payOrderOfflist(params);
    }

    public List<HashMap<String, Object>> payTOrderList(HashMap<String, String> params) {
        return payMapper.payTOrderList(params);
    }

    public List<HashMap<String, Object>> orderDetailLectureOnlist(HashMap<String, String> params) {
        return payMapper.orderDetailLectureOnlist(params);
    }

    public List<HashMap<String, Object>> orderDetailBookOnlist(HashMap<String, String> params) {
        return payMapper.orderDetailBookOnlist(params);
    }

    public List<HashMap<String, Object>> orderDetailLectureOfflist(HashMap<String, String> params) {
        return payMapper.orderDetailLectureOfflist(params);
    }

    public List<HashMap<String, Object>> orderDetailBoxlist(HashMap<String, String> params) {
        return payMapper.orderDetailBoxlist(params);
    }

    public List<HashMap<String, Object>> orderDetailRoomlist(HashMap<String, String> params) {
        return payMapper.orderDetailRoomlist(params);
    }

    public List<HashMap<String, Object>> tOrderDetailList(HashMap<String, String> params) {
        return payMapper.tOrderDetailList(params);
    }

    public HashMap<String, Object> getOrdersOn(HashMap<String, String> params) {
        return payMapper.getOrdersOn(params);
    }

    public HashMap<String, Object> getApprovalsOn(HashMap<String, String> params) {
        return payMapper.getApprovalsOn(params);
    }

    public HashMap<String, Object> getOrdersOff(HashMap<String, String> params) {
        return payMapper.getOrdersOff(params);
    }

    public HashMap<String, Object> getApprovalsOff(HashMap<String, String> params) {
        return payMapper.getApprovalsOff(params);
    }

    public HashMap<String, Object> getTOrders(HashMap<String, String> params) {
        return payMapper.getTOrders(params);
    }

    public HashMap<String, Object> getTApprovals(HashMap<String, String> params) {
        return payMapper.getTApprovals(params);
    }

    public HashMap<String, Object> getDelivers(HashMap<String, String> params) {
        return payMapper.getDelivers(params);
    }

    public List<HashMap<String, Object>> bookDeliveryOnlist(HashMap<String, String> params) {
        return payMapper.bookDeliveryOnlist(params);
    }

    public void updateDeliverInfo(HashMap<String, String> params) {
        payMapper.updateDeliverInfo(params);
    }

    public String getUserCancelYN(HashMap<String, String> params) {
        return payMapper.getUserCancelYN(params);
    }
}
