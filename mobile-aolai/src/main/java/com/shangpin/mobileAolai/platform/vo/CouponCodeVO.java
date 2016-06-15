package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

public class CouponCodeVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -8300641543548818113L;
    // 返回错误码，0为返回成功
	private String code;
	// 错误信息
	private String msg;
	// 优惠码
	private String couponcode;
	// 优惠码名称
	private String name;
	// 优惠码描述
	private String desc;
	// 优惠码有效期
	private String validitydate;
	// 购物袋商品总金额
	private String amount;
	// 购物袋商品需支付的金额(包括礼品卡)
	private String payamount;
	// 购物袋商品总优惠金额
	private String discountamount;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getValiditydate() {
		return validitydate;
	}
	public void setValiditydate(String validitydate) {
		this.validitydate = validitydate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayamount() {
		return payamount;
	}
	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}
	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}
	
	
}
