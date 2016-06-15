package com.shangpin.biz.domain.settlement;

import java.io.Serializable;

/**
 * <br/>
 * Date: 2016/4/22<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public class RefreshDo implements Serializable{

    private String userId;// 用户Id String 必须
    private String buyId;// 购物车id String必须 多个用竖线分割
    private String orderSource;//	1：购物车，2：立即购买	String	必须
    private String receivedId;// 收货地址ID	String	可选
    private String isUseGiftCard;//	用户是否使用礼品卡

    private String domesticCouponflag;
    private String domesticCoupon;
    private String abroadCouponflag;
    private String abroadCoupon;
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(String receivedId) {
        this.receivedId = receivedId;
    }

    public String getIsUseGiftCard() {
        return isUseGiftCard;
    }

    public void setIsUseGiftCard(String isUseGiftCard) {
        this.isUseGiftCard = isUseGiftCard;
    }

    public String getDomesticCouponflag() {
        return domesticCouponflag;
    }

    public void setDomesticCouponflag(String domesticCouponflag) {
        this.domesticCouponflag = domesticCouponflag;
    }

    public String getDomesticCoupon() {
        return domesticCoupon;
    }

    public void setDomesticCoupon(String domesticCoupon) {
        this.domesticCoupon = domesticCoupon;
    }

    public String getAbroadCouponflag() {
        return abroadCouponflag;
    }

    public void setAbroadCouponflag(String abroadCouponflag) {
        this.abroadCouponflag = abroadCouponflag;
    }

    public String getAbroadCoupon() {
        return abroadCoupon;
    }

    public void setAbroadCoupon(String abroadCoupon) {
        this.abroadCoupon = abroadCoupon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
