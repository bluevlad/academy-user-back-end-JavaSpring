package com.academy.mocktest.stats.service;

import com.academy.common.CommonVO;

public class StatsVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String identyId;
    private String mockCode;
    private String classCode;
    private String classSeriesCode;
    private String subjectCd;
    private String itemId;

    // Getters and Setters
    public String getIdentyId() {
        return identyId;
    }

    public void setIdentyId(String identyId) {
        this.identyId = identyId;
    }

    public String getMockCode() {
        return mockCode;
    }

    public void setMockCode(String mockCode) {
        this.mockCode = mockCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassSeriesCode() {
        return classSeriesCode;
    }

    public void setClassSeriesCode(String classSeriesCode) {
        this.classSeriesCode = classSeriesCode;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
