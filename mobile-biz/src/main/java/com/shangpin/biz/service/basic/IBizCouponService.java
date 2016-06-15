package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.CouponCount;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 优惠券基础接口
 * 
 * @author zghw
 *
 */
public interface IBizCouponService {
	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param couponType
	 *            优惠券类型；0未使用；1已使用； 3已过期；-1全部
	 * @return
	 * @author zghw
	 */
	public String fromCoupons(String userId, String shopType, String couponType, String pageIndex, String pageSize);

	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param shopType
	 *            表示来自尚品1，奥莱2
	 * @param couponType
	 *            优惠券类型；0未使用；1已使用； 3已过期；-1全部
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<CouponCount> beCouponsCount(String userId, String shopType, String couponType, String pageIndex, String pageSize);

	/**
	 * 
	 * 
	 * 发送优惠券激活码
	 * 
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return
	 * @author zghw
	 */
	public String fromSendCoupon(String userId, String shopType, String type, String couponType);

	/**
	 * 
	 * 
	 * 发送优惠券激活码
	 * 
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return
	 * @author zghw
	 */
	public ResultBase beSendCoupon(String userId, String shopType, String type, String couponType);

	/**
	 * 
	 * 发送优惠券激活码
	 * 
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return
	 * @author zghw
	 */
	public String fromSendActivation(String userid, String shoptype, String type);

	/**
	 * 
	 * 发送优惠券激活码
	 * 
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return
	 * @author zghw
	 */
	public ResultBase beSendActivation(String userid, String shoptype, String type);

}
