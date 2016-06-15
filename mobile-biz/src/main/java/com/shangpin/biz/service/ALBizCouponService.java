package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.basic.IBizCouponService;

/**
 * 优惠券业务处理接口
 * 
 * @author zghw
 *
 */
public interface ALBizCouponService extends IBizCouponService {
	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param couponType
	 *            优惠券类型；0未使用；1已使用； 3已过期；-1全部
	 * @return
	 * @author zghw
	 */
	public String findCoupons(String userId, String couponType, String pageIndex, String pageSize);

	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户Id
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页显示条数
	 * @param couponType
	 *            优惠券类型；0未使用；1已使用； 3已过期；-1全部
	 * @return
	 * @author zghw
	 */
	public List<Coupon> findCouponsList(String userId, String couponType, String pageIndex, String pageSize);

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
	 * @author qinyingchun
	 */
	public List<Coupon> findCouponsList(String userId, String shopType, String couponType, String pageIndex,
			String pageSize);

	/**
	 * 
	 * @Title: sendActivation
	 * @Description:发送优惠券激活码
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月11日
	 */
	public ResultBase sendActivation(String userid, String shoptype, String type);

	/**
	 * 根据couponPeriod内的时间段内，来判断是否送券
	 * 
	 * @param couponPeriod
	 * @return
	 */
	public boolean isSendCoupon(CouponPeriod couponPeriod);

	/**
	 * 
	 * @Title: sendCoupon
	 * @Description:发送优惠券激活码
	 * @param userid
	 *            用户唯一标识
	 * @param shoptype
	 *            订单来源（尚品1，奥莱2）
	 * @param type
	 *            操作类型(可组合，bind绑定手机号；coupon领取优惠券)；不同的类型用“+”相连接；如果是绑定手机，要指定手机号；
	 *            如果是领优惠券类型，还要指定激活码。如：bind:13819900999+coupon:661472359377
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月11日
	 */
	public Map<String, Object> sendCoupon(String userId, String shopType, String type, String couponType);

}
