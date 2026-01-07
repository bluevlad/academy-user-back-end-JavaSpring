package com.academy.mypage.passlecture.service;

import com.academy.common.CommonVO;

public class PassLectureVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String lecCode;
    private String categoryCd;
    private String learningCd;
    private String subjectTitle;
    private String subjectOpenDate;
    private String lecSchedule;
    private String lecTypeChoice;
    private String subjectSjtCd;
    private String subjectDiscount;
    private String subjectPrice;
    private String subjectRealPrice;
    private String subjectIsuse;
    private String lecGubun;
    private String subjectTeacher;
    private String orderNo;
    private String lectureNo;

    // Getters and Setters
    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectOpenDate() {
        return subjectOpenDate;
    }

    public void setSubjectOpenDate(String subjectOpenDate) {
        this.subjectOpenDate = subjectOpenDate;
    }

    public String getLecSchedule() {
        return lecSchedule;
    }

    public void setLecSchedule(String lecSchedule) {
        this.lecSchedule = lecSchedule;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public String getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(String subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectRealPrice() {
        return subjectRealPrice;
    }

    public void setSubjectRealPrice(String subjectRealPrice) {
        this.subjectRealPrice = subjectRealPrice;
    }

    public String getSubjectIsuse() {
        return subjectIsuse;
    }

    public void setSubjectIsuse(String subjectIsuse) {
        this.subjectIsuse = subjectIsuse;
    }

    public String getLecGubun() {
        return lecGubun;
    }

    public void setLecGubun(String lecGubun) {
        this.lecGubun = lecGubun;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }
}
