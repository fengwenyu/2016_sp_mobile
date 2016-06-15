package com.shangpin.wireless.api.api2client.domain;

public class CouponVO {

    private  String couponno;
    private String type;
    private String name;
    private  String desc;
    private String amount;
    private String discount;
    private String expiredate;
    private String isSelected;//是否选中状态
  
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCouponno() {
        return couponno;
    }
    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }
    public String getExpiredate() {
        return expiredate;
    }
    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
    
    
}
