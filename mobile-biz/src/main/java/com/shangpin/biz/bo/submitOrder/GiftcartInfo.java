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
public class GiftcartInfo implements Serializable{

    private String orderid;
    private String mainOrderId;
    private String date;
    private String giftcardbalance;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(String mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGiftcardbalance() {
        return giftcardbalance;
    }

    public void setGiftcardbalance(String giftcardbalance) {
        this.giftcardbalance = giftcardbalance;
    }
}
