package com.shangpin.biz.bo.dailyCoupons;

import java.io.Serializable;

/**
 * 判断用户领取券的状态
 * @author zkj
 *
 */
public class CouponStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2517007688346549163L;
	String activeCode;
	String isReceive;
	
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getIsReceive() {
		return isReceive;
	}
	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}
	
	
}
