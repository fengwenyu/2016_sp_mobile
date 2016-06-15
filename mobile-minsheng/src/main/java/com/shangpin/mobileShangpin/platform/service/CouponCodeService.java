package com.shangpin.mobileShangpin.platform.service;

import java.util.Map;

public interface CouponCodeService {

	/**
	 * 确认订单信息时更改地址和优惠码/券内容
	 * 
	 * @param userid
	 *            用户ID
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param couponflag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param buysids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addrid
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 *            
	 * @return Map<String, Object> 验证信息
	 */
	public Map<String, Object> updateConfirmOrderInfo(String userid, String coupon, String couponflag, String buysids, String addrid);
}
