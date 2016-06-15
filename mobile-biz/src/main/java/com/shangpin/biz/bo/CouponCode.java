package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 使用优惠码信息对象
 * 
 * @author cuibinqiang
 *
 */
public class CouponCode  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String couponcode;//优惠码
	private String amount;//购物袋商品总金额
	private String discountamount;//购物袋商品总优惠金额
    private String name;//优惠码名称
    private String desc;//优惠码描述
    private String expiredate;//优惠码有效期
    
	public String getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}
