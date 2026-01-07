package com.academy.mypage.cart.service;

import com.academy.common.CommonVO;

public class MypageCartVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    private String cartIdx;
    private String lecCode;
    private String bookSeq;
    private String productType;
    private String productName;
    private String productPrice;
    private String discountPrice;
    private String finalPrice;
    private String quantity;
    private String orderNo;
    private String orderType;
    private String paymentAmount;
    private String usePoint;
    private String useCoupon;
    private String topMenuType;
    private String lecGubun;
    private String categoryNm;
    private String teacherNm;

    // Getters and Setters
    public String getCartIdx() {
        return cartIdx;
    }

    public void setCartIdx(String cartIdx) {
        this.cartIdx = cartIdx;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getBookSeq() {
        return bookSeq;
    }

    public void setBookSeq(String bookSeq) {
        this.bookSeq = bookSeq;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(String usePoint) {
        this.usePoint = usePoint;
    }

    public String getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getLecGubun() {
        return lecGubun;
    }

    public void setLecGubun(String lecGubun) {
        this.lecGubun = lecGubun;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }
}
