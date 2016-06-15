package com.shangpin.biz.service.basic;

import com.shangpin.base.vo.QuickSubmitVO;
import com.shangpin.base.vo.SubmitOrderParam;
import com.shangpin.biz.bo.CouponReturn;
import com.shangpin.biz.bo.GiftCardReturn;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.QuickResult;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.base.ResultObjOneResult;

/**
 * 订单接口基础接口
 * 
 * @author zghw
 *
 */
public interface IBizOrderService {

	/**
	 * 常规提交订单
	 * 
	 * @param params
	 * @return
	 * @author zghw
	 */
	public String fromSubmitCommonOrder(SubmitOrderParam params);

	/**
	 * 常规提交订单
	 * 
	 * @param params
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<OrderReturn> beSubmitCommonOrder(SubmitOrderParam params);

	/**
	 * 查询订单列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param statusType
	 *            状态类型
	 * @param pageIndex
	 *            当前页
	 * @param pageNo
	 *            每页显示多少
	 * @return
	 * @author zghw
	 */
	public String fromOrderList(String userId, String statusType, String pageIndex, String pageSize, String isShowOverseas);

	/**
	 * 查询订单列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param statusType
	 *            状态类型
	 * @param pageIndex
	 *            当前页
	 * @param pageNo
	 *            每页显示多少
	 * @return
	 * @author zghw
	 */
	public ResultObjOneResult<OrderResult> beOrderList(String userId, String statusType, String pageIndex, String pageSize, String isShowOverseas);

	/**
	 * 获取某一订单详细信息
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author zghw
	 */
	public String fromOrderDetail(String userId, String orderId);

	/**
	 * 获取某一订单详细信息
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author zghw
	 */
	public ResultObjOneResult<OrderItem> beOrderDetail(String userId, String orderId);

	/**
	 * 根据用户ID、订单ID，取消订单
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * 
	 * @return 操作结果
	 */
	public String fromCancelOrder(String userId, String orderId);

	/**
	 * 根据用户ID、订单ID，取消订单
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * 
	 * @return 操作结果
	 */
	public ResultBase beCancelOrder(String userId, String orderId);

	/**
	 * 更新对单状态
	 * 
	 * @param mainPay
	 *            主支付方式
	 * @param subPay
	 *            子支付方式
	 * @param orderId
	 *            订单号
	 * @param payMoney
	 *            支付金额
	 * @return
	 * @author zghw
	 */
	public String fromUpdateOrderStatus(String mainPay, String subPay, String orderId, String payMoney);

	/**
	 * 更新对单状态
	 * 
	 * @param mainPay
	 *            主支付方式
	 * @param subPay
	 *            子支付方式
	 * @param orderId
	 *            订单号
	 * @param payMoney
	 *            支付金额
	 * @return
	 * @author zghw
	 */
	public ResultBase beUpdateOrderStatus(String mainPay, String subPay, String orderId, String payMoney);

	/**
	 * 礼品卡支付
	 * 
	 * @param userId
	 *            用户名
	 * @param password
	 *            密码
	 * @param giftCardnNo
	 *            礼品卡号
	 * @param orderId
	 *            订单号
	 * @param email
	 *            邮箱
	 * @param shopType
	 *            商店 1尚品 2为奥莱
	 * @param payType
	 *            支付方式
	 * @return
	 * @author zghw
	 */
	public String fromModifyPayGiftCards(String userId, String password, String giftCardnNo, String orderId, String email, String shopType, String payType);

	/**
	 * 礼品卡支付
	 * 
	 * @param userId
	 *            用户名
	 * @param password
	 *            密码
	 * @param giftCardnNo
	 *            礼品卡号
	 * @param orderId
	 *            订单号
	 * @param email
	 *            邮箱
	 * @param shopType
	 *            商店 1尚品 2为奥莱
	 * @param payType
	 *            支付方式
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<GiftCardReturn> beModifyPayGiftCards(String userId, String password, String giftCardnNo, String orderId, String email, String shopType, String payType);

	/**
	 * 支付方式变更
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * @param mainPay
	 *            主支付方式
	 * @param subPay
	 *            子支付方式
	 * @return
	 *
	 * @author zghw
	 */
	public String fromChangePay(String userId, String orderId, String mainPay, String subPay);

	/**
	 * 支付方式变更
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * @param mainPay
	 *            主支付方式
	 * @param subPay
	 *            子支付方式
	 * @return
	 *
	 * @author zghw
	 */
	public ResultBase beChangePay(String userId, String orderId, String mainPay, String subPay);

	/**
	 * 确认订单信息时更改地址和优惠码/券内容（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param couponFlag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addressId
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 * @param topicNo
	 *            专题编号
	 * @return
	 * @author zhanghongwei
	 */
	public String fromModifyConfirm(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);

	/**
	 * 确认订单信息时更改地址和优惠码/券内容（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param couponFlag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addressId
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 * @param topicNo
	 *            专题编号
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjOne<CouponReturn> beModifyConfirm(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);

	/**
	 * 获取生成订单的信息去结算
	 * 
	 * @param userId
	 *            用户ID
	 * @param isPromotion
	 *            是否促销
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public String fromSettlement(String userId, String isPromotion, String picW, String picH, String postArea);

	/**
	 * 获取生成订单的信息去结算
	 * 
	 * @param userId
	 *            用户ID
	 * @param isPromotion
	 *            是否促销
	 * @param picW
	 *            图片宽度
	 * @param picH
	 *            图片高度
	 * @return
	 *
	 * @author zghw
	 */
	public ResultObjOne<Settlement> beSettlement(String userId, String isPromotion, String picW, String picH, String postArea);

	/**
	 * 
	 * @Title: payGiftCards
	 * @Description: 礼品卡支付
	 * @param userId
	 *            用户ID
	 * @param shopType
	 *            商品来源，1为尚品，2为奥莱
	 * @param payType
	 *            支付类型（暂时传空）
	 * @param orderId
	 *            订单ID
	 * @param password
	 *            礼品卡支付密码
	 * @return String
	 * @throws
	 * @Create By zghw
	 * @Create Date 2014年12月5日
	 */
	public String fromPayGiftCards(String userId, String shopType, String payType, String orderId, String password);

	/**
	 * 
	 * @Title: payGiftCards
	 * @Description: 礼品卡支付
	 * @param userId
	 *            用户ID
	 * @param shopType
	 *            商品来源，1为尚品，2为奥莱
	 * @param payType
	 *            支付类型（暂时传空）
	 * @param orderId
	 *            订单ID
	 * @param password
	 *            礼品卡支付密码
	 * @return String
	 * @throws
	 * @Create By zghw
	 * @Create Date 2014年12月5日
	 */
	public ResultObjOne<GiftCardReturn> bePayGiftCards(String userId, String shopType, String payType, String orderId, String password);

	/**
	 * 
	 * @Title: setGiftCardPassword
	 * @Description: 礼品卡修改密码
	 * @param
	 * @return String
	 * @throws
	 * @Create By zghw
	 * @Create Date 2014年12月16日
	 */
	public String fromSetGiftCardPassword(String userId, String password);

	/**
	 * 
	 * @Title: setGiftCardPassword
	 * @Description: 礼品卡修改密码
	 * @param
	 * @return String
	 * @throws
	 * @Create By zghw
	 * @Create Date 2014年12月16日
	 */
	public ResultBase beSetGiftCardPassword(String userId, String password);

	/**
	 * 一件购物提交订单
	 * 
	 * @param userId
	 *            用户Id
	 * @param productId
	 *            商品编号
	 * @param skuId
	 *            sku编号
	 * @param payTypeId
	 *            主支付方式
	 * @param payTypeChildId
	 *            子支付方式
	 * @param orderOrigin
	 *            订单来源
	 * @param shopType
	 *            表示订单来自尚品1，奥莱2
	 * @param consigneeName
	 *            收货人姓名
	 * @param province
	 *            省id
	 * @param provinceName
	 *            省份名称
	 * @param city
	 *            城市id
	 * @param cityName
	 *            城市名称
	 * @param area
	 *            区id
	 * @param areaName
	 *            区名称
	 * @param town
	 *            镇id
	 * @param townName
	 *            镇名
	 * @param address
	 *            详细地址
	 * @param consigneeName
	 *            收货人姓名
	 * @param zip
	 *            邮编
	 * @param tel
	 *            手机
	 * @return
	 * @author zghw
	 */
	public String fromQuickOrder(QuickSubmitVO quickSubmitVO);

	/**
	 * 一件购物提交订单
	 * 
	 * @param userId
	 *            用户Id
	 * @param productId
	 *            商品编号
	 * @param skuId
	 *            sku编号
	 * @param payTypeId
	 *            主支付方式
	 * @param payTypeChildId
	 *            子支付方式
	 * @param orderOrigin
	 *            订单来源
	 * @param shopType
	 *            表示订单来自尚品1，奥莱2
	 * @param consigneeName
	 *            收货人姓名
	 * @param province
	 *            省id
	 * @param provinceName
	 *            省份名称
	 * @param city
	 *            城市id
	 * @param cityName
	 *            城市名称
	 * @param area
	 *            区id
	 * @param areaName
	 *            区名称
	 * @param town
	 *            镇id
	 * @param townName
	 *            镇名
	 * @param address
	 *            详细地址
	 * @param consigneeName
	 *            收货人姓名
	 * @param zip
	 *            邮编
	 * @param tel
	 *            手机
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<QuickResult> beQuickOrder(QuickSubmitVO quickSubmitVO);

	/**
	 * 提交订单时，更改地址和选择优惠码/券内容（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param couponFlag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addressId
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 * @param topicNo
	 *            专题编号
	 * @return
	 * @author zhanghongwei
	 */
	public String fromModifyOrderItems(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);

	/**
	 * 提交订单时，更改地址和选择优惠码/券内容（尚品、奥莱共用）
	 * 
	 * @param userId
	 *            用户ID
	 * @param couponFlag
	 *            1使用优惠券； 2使用优惠码；未使用传空字符串；
	 * @param coupon
	 *            flag为优惠券时，coupon为优惠券id；flag为优惠码时，coupon为优惠码串
	 * @param buyGids
	 *            购买商品的gid串，多个用“|”分割
	 * @param addressId
	 *            使用的收货地址id（兼容以后运费与地址有关的情况）
	 * @param topicNo
	 *            专题编号
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjOne<CouponReturn> beModifyOrderItems(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource);

	/**
	 * 确认收货
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            子订单ID
	 * @return
	 * 
	 * @author yls
	 */
	public String finishOrder(String userId, String orderId);

	/**
	 * 2.9.0 订单列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param status
	 * @return
	 */
	String orderList(String userId, String pageIndex, String pageSize, String status);

	/**
	 * 2.9.0 订单详情
	 * 
	 * @param userId
	 * @param mainOrderId
	 * @param orderId
	 * @return
	 */
	String orderDetail(String userId, String mainOrderId, String orderId,String isSplitOrder);
}
