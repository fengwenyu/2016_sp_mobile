package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 使用优惠码返回对象
 * 
 * @author cuibinqiang
 *
 */
public class CouponReturn implements Serializable {
	private static final long serialVersionUID = 1L;
	private CouponCode couponcode;//优惠码信息对象
	private String amount;//购物袋商品总金额
    private String payamount;//购物袋商品需支付的金额(包括礼品卡)
    private String discountamount;//购物袋商品总优惠金额
    private String carriage;//运费
	public CouponCode getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(CouponCode couponcode) {
		this.couponcode = couponcode;
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
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
    
}
