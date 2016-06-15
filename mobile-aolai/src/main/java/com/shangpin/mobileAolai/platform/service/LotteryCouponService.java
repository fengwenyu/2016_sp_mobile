package com.shangpin.mobileAolai.platform.service;

import java.util.List;

import com.shangpin.mobileAolai.base.model.Coupon;


public interface LotteryCouponService {
	
	public List<Coupon> findLotteryCoupon(String couponType);
	
	public void upate(Coupon coupon);

}
