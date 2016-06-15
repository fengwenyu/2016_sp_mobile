package com.shangpin.pay.bo;

/**
 * 支付使用单个商品支付实体
 */
public class PayOrderDetailBo {

    private String duty;
    private String goodFees ;
    private String count ;
    private String transportFee;
    private String productname;

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getGoodFees() {
        return goodFees;
    }

    public void setGoodFees(String goodFees) {
        this.goodFees = goodFees;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(String transportFee) {
        this.transportFee = transportFee;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
