package com.shangpin.mobileAolai.platform.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.platform.vo.CouponVO;

/**
 * 优惠券业务逻辑接口，用于优惠券相关操作
 * 
 * @author yumeng
 */
public interface CouponService {
	/**
	 * 根据用户ID、优惠券状态、网站来源，获取优惠券列表
	 * 
	 * @param userid
	 *            用户ID
	 * @param coupontype
	 *            优惠券状态(0未使用；1已使用； 3已过期；-1全部)
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * 
	 * @return 惠券列表json对象
	 */
	public JSONObject getCouponJson(String userid, String coupontype, String shoptype);

	/**
	 * 根据用户ID、网站来源，获取与订单相关联的可使用的优惠券列表
	 * 
	 * @param userid
	 *            用户ID
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * 
	 * @return 惠券列表
	 */
	public List<CouponVO> getCouponList(String userid, String shoptype);

	/**
	 * 根据用户ID、网站来源、操作类型，发送优惠券
	 * 
	 * @param userid
	 *            用户ID
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * 
	 */
	public void sendCoupon(String userid, String shoptype, String type);
	
	public JSONObject sendCouponCode(String userid, String shoptype, String type);
	
	public JSONObject sendCoupon(String userid, String shoptype, String type,String coupontype);

}
