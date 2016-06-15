package com.shangpin.base.service;

public interface CouponsService {

	/**
	 * 使用优惠券，折扣码接口
	 * @param userId
	 * @param type 1使用优惠券； 2使用优惠码；
	 * @param couponCode couponType为1时，coupon为优惠券id；couponType为2时，coupon为优惠码串
	 * @param buyIds 购买商品的shodDetailId串，多个用“|”分割
	 * @param orderSource 从何处来到提交订单 1：从购物车 2：立即购买
	 * @return
	 */
	public String useCoupons(String userId,String type,String totalAmount,String promoAmount,String ticketAmount,String ticketCondition,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource);
	
	/**
	 * 检查该手机用户是否领取过指定的优惠券
	 * @param phoneNum
	 * @param couponCode
	 * @return
	 */
	public String checkActiveCode(String phoneNum, String couponCode);
	/**
	 * 客户支付成功后，赠送优惠券的接口功能
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public String giveCoupons(String userId,String orderId);
	public String useCouponProductList(String start, String end,
			String minPrice, String maxPrice, String postArea,
			String includeBrandNo, String excludeBrandNo,
			String includeCategoryNo, String excludeCategoryNo,
			String includeProductNo, String excludeProductNo,String userLv);

    public String getCouponsInfo(String batchNo);

    public String checkActiveUserId(String userId);

    public String saveActivityUserDetails(String userId, String name, String sex, String size, String phone);
    
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
  }
