package com.academy.mypage.coupon.service;

import com.academy.common.CommonVO;

public class CouponVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String coupIdx;
    private String coupCcode;
    private String coupName;
    private String coupType;
    private String coupValue;
    private String coupUseYn;
    private String coupStartDate;
    private String coupEndDate;
    private String regTypecd;
    private String isAvailable;
    private String addFlag;
    private String mileagePoint;
    private String mileageType;
    private String mileageReason;

    // Getters and Setters
    public String getCoupIdx() {
        return coupIdx;
    }

    public void setCoupIdx(String coupIdx) {
        this.coupIdx = coupIdx;
    }

    public String getCoupCcode() {
        return coupCcode;
    }

    public void setCoupCcode(String coupCcode) {
        this.coupCcode = coupCcode;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public String getCoupType() {
        return coupType;
    }

    public void setCoupType(String coupType) {
        this.coupType = coupType;
    }

    public String getCoupValue() {
        return coupValue;
    }

    public void setCoupValue(String coupValue) {
        this.coupValue = coupValue;
    }

    public String getCoupUseYn() {
        return coupUseYn;
    }

    public void setCoupUseYn(String coupUseYn) {
        this.coupUseYn = coupUseYn;
    }

    public String getCoupStartDate() {
        return coupStartDate;
    }

    public void setCoupStartDate(String coupStartDate) {
        this.coupStartDate = coupStartDate;
    }

    public String getCoupEndDate() {
        return coupEndDate;
    }

    public void setCoupEndDate(String coupEndDate) {
        this.coupEndDate = coupEndDate;
    }

    public String getRegTypecd() {
        return regTypecd;
    }

    public void setRegTypecd(String regTypecd) {
        this.regTypecd = regTypecd;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(String addFlag) {
        this.addFlag = addFlag;
    }

    public String getMileagePoint() {
        return mileagePoint;
    }

    public void setMileagePoint(String mileagePoint) {
        this.mileagePoint = mileagePoint;
    }

    public String getMileageType() {
        return mileageType;
    }

    public void setMileageType(String mileageType) {
        this.mileageType = mileageType;
    }

    public String getMileageReason() {
        return mileageReason;
    }

    public void setMileageReason(String mileageReason) {
        this.mileageReason = mileageReason;
    }
}
