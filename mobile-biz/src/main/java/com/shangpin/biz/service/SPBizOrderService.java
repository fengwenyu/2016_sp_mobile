package com.shangpin.biz.service;

import java.util.Map;

import com.shangpin.biz.bo.GiftCardReturn;
import com.shangpin.biz.bo.OrderDetailResult;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderListResult;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.OverseasOrderParam;
import com.shangpin.biz.bo.QuickOrderParam;
import com.shangpin.biz.bo.QuickResult;
import com.shangpin.biz.bo.SubmitOrder;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.basic.IBizOrderService;

public interface SPBizOrderService extends IBizOrderService {

	/**
	 * 获取某一订单详细信息（尚品）
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author zhongchao
	 */
	public OrderItem findOrderDetail(String userId, String mainOrderNum);
	
	/**
	 * 获取订单详情 v8.4
	 * @param userId
	 * @param mainOrderId
	 * @param orderId
	 * @param isSplitOrder
	 * @return
	 */
	public OrderDetailResult findOrderDetail(String userId, String mainOrderId, String orderId, String isSplitOrder);

	/**
	 * 获取订单列表信息(尚品)
	 * 
	 * @param userId
	 *            用户ID
	 * @param status
	 *            订单状态
	 * @author zhongchao
	 */
	public OrderResult getOrderlist(String userId, String status);
	
	/**
	 * 获取订单列表信息(尚品) v8.3
	 * 
	 * @param userId
	 *            用户ID
	 * @param status
	 *            订单状态
	 * @author zhongchao
	 */
	public OrderListResult orderlist(String userId, String status);

	/**
	 * 根据用户ID、订单ID，取消订单
	 * 
	 * @param userid
	 *            用户ID
	 * @param orderid
	 *            订单ID
	 * 
	 * @return 操作结果
	 */
	public Map<String, Object> cancelOrder(String userid, String mainorderno);

	/**
	 * 
	 * @Title: quickOrder
	 * @Description:立即购买订单提交
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月20日
	 */
	public ResultObjOne<QuickResult> quickOrder(QuickOrderParam params);

	/**
	 * 
	 * @Title: getMoreOrderlist
	 * @Description: 订单列表
	 * @param userId
	 *            用户标识
	 * @param status
	 *            订单状态
	 * @param pageIndex
	 *            页码
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月22日
	 */
	public Map<String, Object> getMoreOrderlist(String userId, String status, String pageIndex);

	/**
	 * 
	 * @Title: updateOrderStatus
	 * @Description: TODO
	 * @param mainPay
	 *            主支付方式
	 * @param subPay
	 *            子支付方式
	 * @param orderId
	 *            订单号
	 * @param payMoney
	 *            支付金额
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月21日
	 */
	public boolean updateOrderStatus(String mainPay, String subPay, String orderId, String payMoney);

	/**
	 * 
	 * @Title: submitCommonOrder
	 * @Description:常规购物提交订单
	 * @param
	 * @return OrderReturn
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	public Map<String, Object> submitCommonOrderMap(Map<String, String> cookieMap,String... params);
	
	/**
	 * 
	 * @Title: submitCommonOrder
	 * @Description:常规购物提交订单
	 * @param
	 * @return OrderReturn
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	public OrderReturn submitCommonOrder(String... params);
	
	/**
	 * 礼品卡、实物卡提交订单
	 * @param params
	 * @return
	 */
	public Map<String, Object> submitGiftOrder(SubmitOrder params);

	/***
	 * 
	 * @Title: cardPay
	 * @Description:礼品卡支付
	 * @param
	 * @return GiftCardReturn
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月3日
	 */
	public GiftCardReturn cardPay(String userId, String shopType, String payType, String orderId, String password);

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
	 * @return GiftCardReturn
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月5日
	 */
	public GiftCardReturn payGiftCards(String userId, String shopType, String payType, String orderId, String password);

	/**
	 * 用户是否存在待支付订单
	 * 
	 * @param userId用户ID
	 * @param status
	 *            订单状态
	 * @return
	 * @author zghw
	 */
	public boolean hasWaitPayOrder(String userId, String status);
	
	/**
	 * 确认订单信息时更改地址和优惠码/券内容
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
	 * @author cuibinqiang
	 */
	public Map<String, Object> updateOrder(String userId, String couponFlag, String coupon, String buyGids,
			String addressId, String topicNo, String orderSource);
	/**
	 *海外提交订单
	 * 
	 * @param params
	 * @return
	 * @author zghw
	 */
	public ResultObjOne<OrderReturn> beSubmitCommonOrder(Map<String, String> cookieMap,OverseasOrderParam params);

	/**
	 * 确认收货
	 * @param userId
	 * @param mainOrderNo
	 * @return
	 */
	public Map<String, Object> finishedOrder(String userId, String mainOrderNo);
	
	/**
	 * 改变支付方式
	 * @param userId
	 * @param mainOrderNo
	 * @param payType
	 * @return
	 */
	public  ResultBase updatePayType(String userId, String mainOrderNo, String payType);

	OrderListResult orderlist(String userId, String status, String pageIndex);

	public Map<String, Object> cancelOrder(String userid, String mainOrderNo,
			String postArea);
	
	/**
	 * 更新待支付订单收货地址或者发票地址或者支付方式
	 * @param userId 用户ID
	 * @param addrId -1的时候不修改收货地址
	 * @param express 收货方式
	 * @param invoiceAddrId -1的时候不修改发票地址
	 * @param invoiceFlag 1开 0 不开
	 * @param invoiceType 发票类型
	 * @param invoiceTitle 发票抬头
	 * @param invoiceContent 发票内容
	 * @param isModifyInvoice 是否修改发票信息
	 * @return
	 * @author qinyingchun
	 */
	public ResultBase modifyOrder(String userId, String orderId, String addrId, String express,
			String invoiceAddrId, String invoiceFlag, String invoiceType,
			String invoiceTitle, String invoiceContent, String isModifyInvoice);
}
