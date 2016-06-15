package com.shangpin.biz.bo.orderUnion;

/**
 * @author liushaoqing
 * 优惠券接口返回数据封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279954
 */
public class Coupon {

    public String couponno;// "939980387403",
    public String name; //"按商品",
    public String amount;// "200面值",
    public String expirydate;// "2015-05-04 00:00:00至2015-07-04 00:00:00",
    public String rule;// "按商品30001258",
    public String statuscode;// "给0",
    public String status;// "给空",
    public String usedate;// "给空",
    public String orderid;// "给空",
    public String type ;//优惠券类型，0优惠券，1现金券",
    public String isSelected;//"1"

    public String getCouponno() {
        return couponno;
    }

    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsedate() {
        return usedate;
    }

    public void setUsedate(String usedate) {
        this.usedate = usedate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
