package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.ActivityUserDb;
import com.shangpin.biz.bo.Coupon;
import com.shangpin.biz.bo.CouponCount;
import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.basic.IBizCouponService;

/**
 * 优惠券业务处理接口
 * 
 * @author zghw
 *
 */
public interface SPBizCouponService extends IBizCouponService {

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
	 * @param coupontype
	 *            券类型 
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月11日
	 */
	public Map<String, Object> sendCoupon(String userId, String shopType, String type, String couponType);
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
	public Map<String, Object> sendCoupon(String userId, String shopType, String type);

	public Map<String, Object> activeCoupon(String userid, String string, String string2, String string3);
	
	/**
	 * 
	 * @Title:couponList
	 * @Description:优惠券激活后返回可用的优惠券列表
	 * @param userId
	 * @param orderSource 1表示从购物车来，2表示立即购买
	 * @return
	 * @author qinyingchun
	 * @date 2015年3月28日
	 */
	public List<Coupon> couponList(String userId, String orderSource, String buyId);
	
	/**
	 * 使用优惠券折扣码
	 * @param userId
	 * @param couponType 1使用优惠券； 2使用优惠码；
	 * @param couponCode couponType为1时，coupon为优惠券id；couponType为2时，coupon为优惠码串
	 * @param buyIds 购买商品的shodDetailId串，多个用“|”分割
	 * @param orderSource 从何处来到提交订单，1购物车，2立即购买
	 * @return
	 */
	public String useCoupon(String userId,String type,String totalAmount,String promoAmount,
			String ticketAmount,String ticketCondition,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource);
	
	/**
	 * 
	 * @param userId
	 * @param couponType
	 * @param couponCode
	 * @param buyIds
	 * @param orderSource
	 * @return
	 */
	public List<PriceShowVo> useCouponObj(String userId,String type,String totalAmount,String promoAmount,
			String ticketAmount,String ticketCondition,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource);
	
	/**
	 * 检查该手机用户是否领取过指定的优惠券
	 * @param phoneNum
	 * @param activeCode
	 * @return
	 */
	public ResultBase checkActiveCode(String phoneNum, String activeCode);
	
	/**
     * 检查手机号是否参加过女神活动
     * @param phoneNum
     * @return
     */
    public ResultBase checkPhoneActivity(String phoneNum, String activityNo);
    
    /**
     * 检查手机号是否参加过女神活动
     * @param phoneNum
     * @return
     */
    public ResultBase isCheckPhoneActivity(String phoneNum, String activityNo);
	public RecProductFor useCouponProductList(String payAmount,String start,String end,String minPrice,String maxPrice,String postArea,String includeBrandNo,String excludeBrandNo,String includeCategoryNo,String excludeCategoryNo,String includeProductNo,String excludeProductNo, String userLv);
	public Map<String,Object> searchUseCouponProductList(String payAmount,String start,String end,String minPrice,String maxPrice,String postArea,String includeBrandNo,String excludeBrandNo,String includeCategoryNo,String excludeCategoryNo,String includeProductNo,String excludeProductNo, String userLv);
	
	/***
	 * 获得劵信息
	 * @param batchNo
	 * @return 
	 * @throws Exception 
	 */
    public Coupon findCouponsInfo(String batchNo) throws Exception;
    
    /**检查用户是否领取过NBD鞋活动
     * @param userId
     * @return
     */
    public String checkActiveUserId(String userId);

    public String saveActivityUserDetail(ActivityUserDb activityUserDb);
    
    /**
     * 提交订单页可选的优惠券列表
     * @param userId 用户ID
     * @param pageIndex 页码
     * @param pageSize 显示条目数
     * @param buyId 购物ID，多个用“|”隔开
     * @param orderSource 订单来源，1表示购物车， 2表示立即购买
     * @param couponNo 请求接口时候本次选择的优惠券编号
     * @param excludeCouponNo
     * @return
     */
    public String canUseCoupons(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String excludeCouponNo);
    
    /**
     * 提交订单页可选的优惠券列表
     * @param userId 用户ID
     * @param pageIndex 页码
     * @param pageSize 显示条目数
     * @param buyId 购物ID，多个用“|”隔开
     * @param orderSource 订单来源，1表示购物车， 2表示立即购买
     * @param couponNo 请求接口时候本次选择的优惠券编号
     * @param excludeCouponNo
     * @return
     */
    public CouponCount canUseCouponsObj(String userId, String pageIndex, String pageSize, String buyId, String orderSource, String couponNo, String excludeCouponNo);

}
