package com.academy.mocktest.offerer.service;

import com.academy.mapper.OffererMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OffererService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final OffererMapper offererMapper;

    @Autowired
    public OffererService(OffererMapper offererMapper) {
        this.offererMapper = offererMapper;
    }

    public List<HashMap<String, Object>> offererTakeList(Map<String, Object> params) {
        return offererMapper.offererTakeList(params);
    }

    public int offererTakeListCount(Map<String, Object> params) {
        return offererMapper.offererTakeListCount(params);
    }

    public HashMap<String, Object> offererMouiInfo(Map<String, Object> params) {
        return offererMapper.offererMouiInfo(params);
    }

    public List<HashMap<String, Object>> offererMouiAddClassInfo(Map<String, Object> params) {
        return offererMapper.offererMouiAddClassInfo(params);
    }

    public List<HashMap<String, Object>> offererMouiClsSeriesSbjtList(Map<String, Object> params) {
        return offererMapper.offererMouiClsSeriesSbjtList(params);
    }

    public String offererCurrentdate(Map<String, Object> params) {
        return offererMapper.offererCurrentdate(params);
    }

    public int offererRequestInsert(Map<String, Object> params) {
        return offererMapper.offererRequestInsert(params);
    }

    public int offererChoiceSubjectInsert(Map<String, Object> params) {
        return offererMapper.offererChoiceSubjectInsert(params);
    }

    public HashMap<String, Object> offererReqMouiInfo(Map<String, Object> params) {
        return offererMapper.offererReqMouiInfo(params);
    }

    public List<HashMap<String, Object>> offererSubjectList(Map<String, Object> params) {
        return offererMapper.offererSubjectList(params);
    }

    public int offererDeletes(Map<String, Object> params) {
        int result = offererMapper.offererChoiceSubjectDeletes(params);
        result += offererMapper.offererDeletes(params);
        return result;
    }

    public HashMap<String, Object> offererMoui(Map<String, Object> params) {
        return offererMapper.offererMoui(params);
    }

    public List<HashMap<String, Object>> offererMouiClassInfo(Map<String, Object> params) {
        return offererMapper.offererMouiClassInfo(params);
    }

    public HashMap<String, Object> offererView(Map<String, Object> params) {
        return offererMapper.offererView(params);
    }

    public List<HashMap<String, Object>> offererMouiSubjectList(Map<String, Object> params) {
        return offererMapper.offererMouiSubjectList(params);
    }

    public int offererRequestUpdate(Map<String, Object> params) {
        return offererMapper.offererRequestUpdate(params);
    }

    public int offererPaymentInsert(Map<String, Object> params) {
        return offererMapper.offererPaymentInsert(params);
    }

    public int offererPaymentUpdate(Map<String, Object> params) {
        return offererMapper.offererPaymentUpdate(params);
    }

    public int offererPrintUpdate(Map<String, Object> params) {
        return offererMapper.offererPrintUpdate(params);
    }

    public int offererListCount(Map<String, Object> params) {
        return offererMapper.offererListCount(params);
    }

    public List<HashMap<String, Object>> offererTakeSubjectList(Map<String, Object> params) {
        return offererMapper.offererTakeSubjectList(params);
    }

    public String getOffererId(Map<String, Object> params) {
        return offererMapper.getOffererId(params);
    }

    public String getIdentyId(Map<String, Object> params) {
        return offererMapper.getIdentyId(params);
    }
}
