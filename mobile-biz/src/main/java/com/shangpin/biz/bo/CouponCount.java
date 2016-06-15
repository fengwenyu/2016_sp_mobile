package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CouponCount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String count;
	private List<Coupon> list;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<Coupon> getList() {
		return list;
	}
	public void setList(List<Coupon> list) {
		this.list = list;
	}
	
}
