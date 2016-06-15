package com.shangpin.biz.bo.submitOrder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * <br/>
 * Date: 2016/4/26<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Wechat implements Serializable{

    private String payCode;
    private String payMsg;
    private String partnerId ;
    private String prepayId;
    private String dataPackage;
    private String nonceStr;
    private String timestamp;
    private String sign;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(String dataPackage) {
        this.dataPackage = dataPackage;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
