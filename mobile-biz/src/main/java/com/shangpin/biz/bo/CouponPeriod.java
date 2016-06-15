package com.shangpin.biz.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "couponPeriod")
public class CouponPeriod implements Cloneable,Serializable{
	private static final long serialVersionUID = 1L;
	private String startDate;
    private String endDate;
    private String couponNo;
    private String couponDesc;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc;
    }

    @Override
    public Object clone() {
        CouponPeriod o = null;
        try {
            o = (CouponPeriod) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return o;
    }

}
