package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.CartOrder;
import com.shangpin.base.vo.Logistics;
import com.shangpin.base.vo.LogisticsInfo;
import com.shangpin.base.vo.QuickSubmitVO;
import com.shangpin.base.vo.ResultObj;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.base.vo.SubmitOrderParam;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;

@SuppressWarnings("deprecation")
@Service
public class OrderServiceImpl implements OrderService {

	// 确认订单信息URL
	private StringBuffer confirmOrderInfoURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("confirmOrderInfo");

	// 获取某一订单简要信息URL
	private StringBuilder findOrderURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getorder");

	// 获取某一订单详细信息URL
	private StringBuilder findOrderDetailURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getorderdetail");

	// 获取用户购物车、优惠券、物流、订单数量的URL
	private StringBuffer findUserBuyInfoURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("UserBuyInfo");

	// 查询某订单的物流信息（尚品、奥莱共用）的URL
	private StringBuffer findOrderLogisticURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("OrderLogistic");

	// 物流详情（尚品、奥莱共用）的URL
	private StringBuffer findLogisticsDetailURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SPLogisticsDetail");

	// 我的优惠券列表（尚品、奥莱共用）的URL
	private StringBuffer findCouponsURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("coupons");

	// 优惠吗激活之后返回可用优惠券列表
	private StringBuffer couponListURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("order/Ticketlist/");

	// 发送优惠券激活码
	private StringBuffer sendActivationURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("codemactchedphone");

	// 订单优惠券列表（尚品、奥莱共用）的URL
	private StringBuffer findSelectCouponsURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("selectCoupon");

	// 更改订单支付方式（尚品、奥莱共用）的URL
	private StringBuffer modifyChagePayModeURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("chagePayMode");

	// 获取礼品卡列表（尚品、奥莱共用）的URL
	private StringBuffer findGiftCardsURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("giftCards");

	// 选择礼品卡（尚品、奥莱共用）的URL
	private StringBuffer findSelectGiftCardsURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("selectGiftCards");

	// 礼品卡支付（尚品、奥莱共用）的URL
	private StringBuffer modifyPayGiftCardsURL = new StringBuffer(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("payGiftCards");

	// 礼品卡支付
	private StringBuffer payGiftCardsURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("order/payGiftCards");
	// 礼品卡修改密码
	private StringBuffer setGiftCardsPasswordURL = new StringBuffer(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("setgiftcardpaypass");

	// 确认订单信息时更改地址和优惠码/券内容URL
	private StringBuilder updateConfirmOrderInfoURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Trade/updateConfirmOrderInfo");

	// 获取优惠码信息URL
	private StringBuilder getCouponCodeInfoURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getCouponCodeInfo");

	// 获取用户礼品卡数量URL
	private StringBuilder giftCardsByUserURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("giftCardsByUser");

	// 获取24小时内未被激活的礼品卡密钥URL
	private StringBuilder giftCardNewSecretURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("giftcardnewsecret");

	// 获取礼品卡列表URL
	private StringBuilder giftCardsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("giftCards");

	// 获取某张礼品卡消费记录URL
	private StringBuilder giftCardConsumptionURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("giftcardconsumption");

	// 查询用户所有物流列表URL
	private StringBuilder logisticsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("Logistics");

	// 一件购物提交订单
	private StringBuilder quickOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/quickOrder");
	// 获取订单列表信息
	private StringBuilder getOrderListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/getorderlist");
	// 获取物流信息
	private StringBuilder getOrderLogistic = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("OrderLogistic");
	// 获取订单详细信息
	private StringBuilder getOrderDetailURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/getorder");
	// 取消订单
	private StringBuilder cancelOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/CancelOrder");
	// 提交订单（奥莱常规订单）
	private StringBuilder submitOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/ConfirmOrder");
	// 确认收货
	private StringBuilder finishOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/finishOrder");
	// 提交礼品卡购买订单
	private StringBuilder submitGiftCardOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/giftCardCommit");
	// 更改订单支付方式
	private StringBuilder updatePayTypeURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/UpdatePayType");
	
	//订单列表地址
	private StringBuilder OrderListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/orderList");
	
	//订单详情接口
	private StringBuilder OrderDetailURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/orderDetail");
	private StringBuilder queryGiftCardSecretKeyURL= new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/giftCardKeyt");
	//更新订单信息
	private StringBuilder modifyOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/ModifyOrderAddress");

	/**
	 * 确认订单信息（尚品 奥莱共用）
	 */
	@Override
	public String confirmOrderInfo(String userId, String detailPicw, String detailPich, String picw, String pich, String shopType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("detailpicw", detailPicw);
		params.put("detailpich", detailPich);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("shoptype", shopType);
		String result = HttpClientUtil.doGet(confirmOrderInfoURL.toString(), params);
		return result;
	}

	/**
	 * 获取用户购物车、优惠券、物流、订单数量（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findUserBuyInfo(String userId, String checkTime, String shopType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("checktime", checkTime);
		params.put("shoptype", shopType);
		String result = HttpClientUtil.doGet(findUserBuyInfoURL.toString(), params);
		return result;
	}

	/**
	 * 查询某订单的物流信息（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findOrderLogistic(String userId, String orderId, String flag, String pich, String picw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("ordered", orderId);
		params.put("flag", flag);
		params.put("pich", pich);
		params.put("picw", picw);
		String result = HttpClientUtil.doGet(findOrderLogisticURL.toString(), params);
		return result;
	}

	/**
	 * 物流详情（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findLogisticsDetail(String userId, String orderId, String ticketNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderid", orderId);
		params.put("ticketno", ticketNo);
		String result = HttpClientUtil.doGet(findLogisticsDetailURL.toString(), params);
		return result;
	}

	/**
	 * 我的优惠券列表（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findCoupons(String userId, String pageIndex, String pageSize, String shopType, String couponType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		params.put("shoptype", shopType);
		params.put("coupontype", couponType);
		String result = HttpClientUtil.doGet(findCouponsURL.toString(), params);
		return result;
	}

	/**
	 * 订单优惠券列表（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findSelectCoupon(String userId, String pageIndex, String pageSize, String shopType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		params.put("shoptype", shopType);
		String result = HttpClientUtil.doGet(findSelectCouponsURL.toString(), params);
		return result;
	}

	/**
	 * 更改订单支付方式（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String modifyChagePayMode(String userId, String orderId, String mainPaymode, String subPaymode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderId", orderId);
		params.put("mainpaymode", mainPaymode);
		params.put("subpaymode", subPaymode);
		String result = HttpClientUtil.doGet(modifyChagePayModeURL.toString(), params);
		return result;
	}

	/**
	 * 获取礼品卡列表（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findGiftCards(String userId, String type, String status, String shopType, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("type", type);
		params.put("status", status);
		params.put("shoptype", shopType);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		String result = HttpClientUtil.doGet(findGiftCardsURL.toString(), params);
		return result;
	}

	/**
	 * 选择礼品卡（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String findSelectGiftCards(String userId, String giftCardnNo, String orderId, String shopType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("giftcardno", giftCardnNo);
		params.put("orderid", orderId);
		params.put("shoptype", shopType);
		String result = HttpClientUtil.doGet(findSelectGiftCardsURL.toString(), params);
		return result;
	}

	/**
	 * 礼品卡支付（尚品、奥莱共用）
	 * 
	 * @author liujie
	 */
	@Override
	public String modifyPayGiftCards(String userId, String password, String giftCardnNo, String orderId, String email, String shopType, String payType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("password", password);
		params.put("giftcardno", giftCardnNo);
		params.put("shoptype", shopType);
		params.put("paytype", payType);
		params.put("orderid", orderId);
		params.put("email", email);
		String result = HttpClientUtil.doGet(modifyPayGiftCardsURL.toString(), params);
		return result;
	}

	/**
	 * 获取某一订单简要信息
	 */
	@Override
	public String findOrder(String userId, String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderid", orderId);
		String result = HttpClientUtil.doGet(findOrderURL.toString(), params);
		return result;
	}

	/**
	 * 获取某一订单的详细信息
	 */
	@Override
	public String findOrderNew(String userId, String mainOrderNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("mainOrderNo", mainOrderNo);
		String result = HttpClientUtil.doGet(getOrderDetailURL.toString(), params);
		return result;
	}

	/**
	 * 获取某一订单详细信息
	 */
	@Override
	public String findOrderDetail(String userId, String orderId, String picW, String picH) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderid", orderId);
		params.put("picw", picW);
		params.put("pich", picH);
		String result = HttpClientUtil.doGet(findOrderDetailURL.toString(), params);
		return result;
	}

	/**
	 * 确认订单信息时更改地址和优惠码/券内容（尚品、奥莱共用）
	 */
	@Override
	public String modifyConfirmOrderInfo(String userId, String couponFlag, String coupon, String buyGids, String addressId, String topicNo, String orderSource) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("couponflag", couponFlag);
		params.put("coupon", coupon);
		params.put("buysids", buyGids);
		params.put("addrid", addressId);
		params.put("topicno", topicNo);
		params.put("orderSource", orderSource);
		String result = HttpClientUtil.doGet(updateConfirmOrderInfoURL.toString(), params);
		return result;
	}

	/**
	 * 获取优惠码信息（尚品、奥莱共用）
	 */
	@Override
	public String findCouponCodeInfo(String userId, String couponCode, String buyGids, String topicNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("couponcode", couponCode);
		params.put("buysids", buyGids);
		params.put("topicno", topicNo);
		String result = HttpClientUtil.doGet(getCouponCodeInfoURL.toString(), params);
		return result;
	}

	/**
	 * 获取用户礼品卡数量（尚品、奥莱共用）
	 */
	@Override
	public String findGiftCardsByUser(String userId, String shopType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("shopType", shopType);
		String result = HttpClientUtil.doGet(giftCardsByUserURL.toString(), params);
		return result;
	}

	/**
	 * 获取24小时内未被激活的礼品卡密钥
	 */
	@Override
	public String findGiftCardNewSecret(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		String result = HttpClientUtil.doGet(giftCardNewSecretURL.toString(), params);
		return result;
	}

	/**
	 * 获取礼品卡列表（尚品、奥莱共用）
	 */
	@Override
	public String findGiftCardList(String userId, String type, String status, String shopType, String pageNo, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("type", type);
		params.put("status", status);
		params.put("shoptype", shopType);
		params.put("pageindex", pageNo);
		params.put("pagesize", pageSize);
		String result = HttpClientUtil.doGet(giftCardsURL.toString(), params);
		return result;
	}

	/**
	 * 获取某张礼品卡消费记录
	 */
	@Override
	public String findGiftCardConsumption(String userId, String cardNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("cardno", cardNo);
		String result = HttpClientUtil.doGet(giftCardConsumptionURL.toString(), params);
		return result;
	}

	/**
	 * 查询用户所有物流列表（分页）（尚品、奥莱共用）
	 */
	@Override
	public String findLogisticsList(String userId, String flag, String picw, String pich, String pageNo, String pageSize, String orderType, String isAll) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("flag", flag);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("pageindex", pageNo);
		params.put("pagesize", pageSize);
		params.put("ordertype", orderType);
		params.put("isall", isAll);
		String result = HttpClientUtil.doGet(logisticsURL.toString(), params);
		return result;
	}

	@Override
	public List<LogisticsInfo> findOrderListObj(String userId, String flag, String picw, String pich, String pageNo, String pageSize, String orderType, String isAll) {
		String result = findLogisticsList(userId, flag, picw, pich, pageNo, pageSize, orderType, isAll);
		ResultObj<LogisticsInfo> obj = JsonUtil.fromJson(result, new TypeToken<ResultObj<LogisticsInfo>>() {
		});
		return obj.getList();
	}

	@Override
	public Logistics findLogisticsDetailObj(String userId, String orderId, String ticketNo) {
		String result = this.findLogisticsDetail(userId, orderId, ticketNo);
		ResultObjOne<Logistics> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<Logistics>>() {
		});
		return obj.getObj();
	}

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
	 * @author wangfeng
	 */
	@Override
	public String quickOrder(QuickSubmitVO quickSubmitVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", quickSubmitVO.getUserId());
		params.put("productId", quickSubmitVO.getProductId());
		params.put("skuId", quickSubmitVO.getSkuId());
		params.put("payTypeId", quickSubmitVO.getPayTypeId());
		params.put("payTypeChildId", quickSubmitVO.getPayTypeChildId());
		params.put("orderOrigin", "24");// M站一键购买
		params.put("shopType", quickSubmitVO.getShopType());
		params.put("consigneeName", quickSubmitVO.getConsigneeName());
		params.put("province", quickSubmitVO.getProvince());
		params.put("provinceName", quickSubmitVO.getProvinceName());
		params.put("city", quickSubmitVO.getCity());
		params.put("cityName", quickSubmitVO.getCityName());
		params.put("area", quickSubmitVO.getArea());
		params.put("areaName", quickSubmitVO.getAreaName());
		params.put("town", quickSubmitVO.getTown());
		params.put("townName", quickSubmitVO.getTownName());
		params.put("address", quickSubmitVO.getAddress());
		params.put("zip", quickSubmitVO.getZip());
		params.put("tel", quickSubmitVO.getTel());
		String result = HttpClientUtil.doGet(quickOrderURL.toString(), params);
		return result;
	}

	@Override
	public String getOrderlist(String userId, String status, String page, String pagesize, String isShowOverseas) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("status", status);
		params.put("page", page);
		params.put("pagesize", pagesize);
		params.put("isShowOverseas", isShowOverseas);
		String result = HttpClientUtil.doGet(getOrderListURL.toString(), params);
		return result;
	}

	@Override
	public String getNewLogistice(String orderId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("flag", "1");
		params.put("orderid", orderId);
		params.put("userid", userId);
		params.put("pich", GlobalConstants.SAMLL_PICTURE_HEIGHT);
		params.put("picw", GlobalConstants.SAMLL_PICTURE_WIDTH);
		String result = HttpClientUtil.doGet(getOrderLogistic.toString(), params);
		return result;
	}

	@Override
	public String cancelOrder(String userid, String mainorderno) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		params.put("mainorderno", mainorderno);
		String result = HttpClientUtil.doGet(cancelOrderURL.toString(), params);
		return result;
	}

	@Override
	public String sendActivation(String userid, String shoptype, String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userid);
		params.put("shoptype", shoptype);
		params.put("type", type);
		return HttpClientUtil.doGet(sendActivationURL.toString(), params);
	}

	/**
	 * 提交订单
	 */
	@Override
	public String submitOrder(CartOrder orderVO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", orderVO.getUserid());// 用户ID
		map.put("addrid", orderVO.getAddrid());// 　收货人地址ID
		map.put("express", orderVO.getExpress());// 快递信息 id
		map.put("coupon", orderVO.getCoupon());// 优惠券id，不用为0
		boolean tag = null == orderVO.getInvoiceflag() || "0".equals(orderVO.getInvoiceflag());
		map.put("invoiceflag", tag ? "0" : orderVO.getInvoiceflag());// 是否开发票，0不开；1开
		map.put("invoicecontent", tag ? "" : orderVO.getInvoicecontent());// 发票内容：商品全称，箱包，饰品，钟表，化妆品，服装，鞋帽，眼镜
		map.put("invoicetype", tag ? "" : orderVO.getInvoicetype());// 发票类型，0单位；1个人
		map.put("invoicetitle", tag ? "" : orderVO.getInvoicetitle());// 发票抬头
		map.put("invoiceaddrid", tag ? "" : orderVO.getInvoiceaddrid());// 发票收货地址id
		map.put("orderfrom", orderVO.getOrderfrom());// 订单来源，手机、网站
		map.put("ordertype", orderVO.getOrdertype());// 表示订单来自尚品1，奥莱2
		map.put("errorskus", orderVO.getErrorskus());
		map.put("couponflag", orderVO.getCouponflag());// 1使用优惠券；
														// 2使用优惠码；未使用传空字符串
		map.put("buysids", orderVO.getBuysids());// 购买商品的gid串，多个用“|”分割
		String result = HttpClientUtil.doGet(submitOrderURL.toString(), map);

		return result;
	}

	@Override
	public String submitCommonOrder(String... params) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userid", params[0]);
		param.put("addrid", params[1]);
		param.put("invoiceaddrid", params[2]);
		param.put("invoiceflag", params[3]);
		param.put("invoicecontent", params[4]);
		param.put("invoicetype", params[5]);
		param.put("invoicetitle", params[6]);
		param.put("couponflag", params[7]);
		param.put("coupon", params[8]);
		param.put("express", params[9]);
		param.put("orderfrom", params[10]);
		param.put("buysids", params[11]);
		param.put("paytypeid", params[12]);
		param.put("paytypechildid", params[13]);
		param.put("ordertype", params[14]);
		param.put("isUseGiftCardPay", params[15]);
		return HttpClientUtil.doGet(submitOrderURL.toString(), param);
	}

	@Override
	public String submitCommonOrder(SubmitOrderParam params) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userid", params.getUserId());
		param.put("addrid", params.getAddrId());
		param.put("invoiceaddrid", params.getInvoiceAddrId());
		param.put("invoiceflag", params.getInvoiceFlag());
		param.put("invoicecontent", params.getInvoiceContent());
		param.put("invoicetype", params.getInvoiceType());
		param.put("invoicetitle", params.getInvoiceTitle());
		param.put("couponflag", params.getCouponFlag());
		param.put("coupon", params.getCoupon());
		param.put("express", params.getExpress());
		param.put("orderfrom", params.getOrderFrom());
		param.put("buysids", params.getBuysIds());
		param.put("paytypeid", params.getPayTypeId());
		param.put("paytypechildid", params.getPayTypeChildId());
		param.put("ordertype", params.getOrderType());
		param.put("isUseGiftCardPay", params.getIsUseGiftCardPay());
		param.put("orderSource", params.getOrderSource());
		param.put("postArea", params.getPostArea());
		param.put("type", params.getType());
		param.put("channelNo", params.getChannelNo());
		param.put("channelId", params.getChannelId());
		param.put("param", params.getParam());
		param.put("channelType", params.getChannelType());
		String type = params.getType() == null ? "" : params.getType();
		if ("1".equals(type) || "2".equals(type)) {
			return HttpClientUtil.doGet(submitGiftCardOrderURL.toString(), param);
		}
		return HttpClientUtil.doGet(submitOrderURL.toString(), param);
	}
	
	@Override
	public String submitGiftOrder(SubmitOrderParam params) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userid", params.getUserId());
		param.put("type", params.getType());
		param.put("addrid", params.getAddrId());
		param.put("invoiceflag", params.getInvoiceFlag());
		param.put("invoiceaddrid", params.getInvoiceAddrId());
		param.put("invoicetype", params.getInvoiceType());
		param.put("invoicetitle", params.getInvoiceTitle());
		param.put("invoicecontent", params.getInvoiceContent());
		param.put("express", params.getExpress());
		param.put("orderfrom", params.getOrderFrom());
		param.put("buysIds", params.getBuysIds());
		param.put("paytypeid", params.getPayTypeId());
		param.put("paytypechildid", params.getPayTypeChildId());
		param.put("ordertype", params.getOrderType());
		return HttpClientUtil.doGet(submitGiftCardOrderURL.toString(), param);
	}

	@Override
	public String sendCoupon(String userId, String shopType, String type, String couponType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("shoptype", shopType);
		params.put("type", type);
		params.put("coupontype", couponType);
		return HttpClientUtil.doGet(sendActivationURL.toString(), params);
	}

	@Override
	public String payGiftCards(String userId, String shopType, String payType, String orderId, String password) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userid", userId);
		param.put("shoptype", shopType);
		param.put("paytype", payType);
		param.put("orderid", orderId);
		param.put("password", password);
		return HttpClientUtil.doGet(payGiftCardsURL.toString(), param);
	}

	@Override
	public String setGiftCardPassword(String userId, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pass", password);
		String result = HttpClientUtil.doGet(setGiftCardsPasswordURL.toString(), params);
		return result;
	}

	@Override
	public String couponList(String userId, String orderSource, String buyId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("orderSource", orderSource);
		params.put("buyId", buyId);
		return HttpClientUtil.doGet(couponListURL.toString(), params);
	}

	/**
	 * 确认收货
	 */
	@Override
	public String finishOrder(String userId, String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		String result = HttpClientUtil.doPost(finishOrderURL.toString(), params);
		return result;
	}

	@Override
	public String updatePayType(String userId, String mainOrderNo, String payType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("mainOrderNo", mainOrderNo);
		params.put("payType", payType);
		String result = HttpClientUtil.doGet(updatePayTypeURL.toString(), params);
		return result;
	}

	@Override
	public String orderList(String userId, String pageIndex, String pageSize, String status) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("status", status);
		return HttpClientUtil.doGet(OrderListURL.toString(), params);
	}

	@Override
	public String orderDetail(String userId, String mainOrderId, String orderId,String isSplitOrder) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("mainOrderId", mainOrderId);
		params.put("orderId", orderId);
		params.put("isSplitOrder", isSplitOrder);
		return HttpClientUtil.doGet(OrderDetailURL.toString(), params);
	}

	@Override
	public String cancelOrder(String userId, String mainOrderNo, String postArea) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("mainOrderNo", mainOrderNo);
		params.put("postArea", postArea);
		String result = HttpClientUtil.doGet(cancelOrderURL.toString(), params);
		return result;
	}
	@Override
	public String queryGiftCardSecretKey(String mainOrderId, String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mainOrderId", mainOrderId);
        params.put("userId", userId);
        return HttpClientUtil.doGet(queryGiftCardSecretKeyURL.toString(), params);
	}

	@Override
	public String modifyOrder(String userId, String orderId, String addrId, String express,
			String invoiceAddrId, String invoiceFlag, String invoiceType,
			String invoiceTitle, String invoiceContent, String isModifyInvoice) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("orderNo", orderId);
		params.put("addrId", addrId);
		params.put("timeRequest", express);
		params.put("invoiceAddrId", invoiceAddrId);
		params.put("invoiceFlag", invoiceFlag);
		params.put("invoiceType", invoiceType);
		params.put("invoiceTitle", invoiceTitle);
		params.put("invoiceContent", invoiceContent);
		params.put("isModifyInvoice", isModifyInvoice);
		return HttpClientUtil.doGet(modifyOrderURL.toString(), params);
	}

}
