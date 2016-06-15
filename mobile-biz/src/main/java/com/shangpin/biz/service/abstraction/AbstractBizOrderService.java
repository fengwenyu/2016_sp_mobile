package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.service.ShoppingCartService;
import com.shangpin.base.vo.QuickSubmitVO;
import com.shangpin.base.vo.SubmitOrderParam;
import com.shangpin.biz.bo.CouponReturn;
import com.shangpin.biz.bo.GiftCardReturn;
import com.shangpin.biz.bo.OrderDetailResult;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.OrderListResult;
import com.shangpin.biz.bo.OrderResult;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.QuickResult;
import com.shangpin.biz.bo.Settlement;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.base.ResultObjOneResult;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizOrderService {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private ShoppingCartService shoppingCartService;

	public String fromSubmitCommonOrder(SubmitOrderParam params) {
		String json = orderService.submitCommonOrder(params);
		return json;
	}

	public ResultObjOne<OrderReturn> beSubmitCommonOrder(SubmitOrderParam params) {
		String json = fromSubmitCommonOrder(params);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<OrderReturn> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<OrderReturn>>() {
			});
			return result;
		}
		return null;
	}

	public String fromOrderList(String userId, String statusType, String pageIndex, String pageSize,String isShowOverseas) {
		String json = orderService.getOrderlist(userId, statusType, pageIndex, pageSize,isShowOverseas);
		return json;
	}

	public ResultObjOneResult<OrderResult> beOrderList(String userId, String statusType, String pageIndex,
			String pageSize,String isShowOverseas) {
		String json = fromOrderList(userId, statusType, pageIndex, pageSize, isShowOverseas);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOneResult<OrderResult> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOneResult<OrderResult>>() {
					});
			return result;
		}
		return null;
	}
	
	public String fromOrderList(String userId, String statusType, String pageIndex, String pageSize) {
		String json = orderService.orderList(userId, pageIndex, pageSize, statusType);
		return json;
	}
	/**
	 * 获取订单列表
	 * @author zrs
	 * @date 2015-9-16 10:03:30
	 * @param userId
	 * @param statusType
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ResultObjOne<OrderListResult> beOrderList(String userId, String statusType, String pageIndex,
			String pageSize) {
		String json = fromOrderList(userId, statusType, pageIndex, pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<OrderListResult> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOne<OrderListResult>>() {
			});
			return result;
		}
		return null;
	}

	public String fromOrderDetail(String userId, String orderId) {
		String json = orderService.findOrderNew(userId, orderId);
		return json;
	}

	public ResultObjOneResult<OrderItem> beOrderDetail(String userId, String orderId) {
		String json = fromOrderDetail(userId, orderId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOneResult<OrderItem> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOneResult<OrderItem>>() {
					});
			return result;
		}
		return null;
	}

	public String fromOrderDetail(String userId,String mainOrderId, String orderId,String isSplitOrder) {
		String json = orderService.orderDetail(userId, mainOrderId, orderId, isSplitOrder);
		return json;
	}
	/**
	 * 获取订单详情
	 * @date 2015-9-16 10:05:20
	 * @param userId
	 * @param mainOrderId
	 * @param orderId
	 * @param isSplitOrder
	 * @return
	 */
	public ResultObjOne<OrderDetailResult> beOrderDetail(String userId,String mainOrderId, String orderId,String isSplitOrder) {
		String json = fromOrderDetail(userId, mainOrderId, orderId, isSplitOrder);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<OrderDetailResult> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOne<OrderDetailResult>>() {
			});
			return result;
		}
		return null;
	}

	public String fromCancelOrder(String userId, String orderId) {
		String json = orderService.cancelOrder(userId, orderId);
		return json;
	}
	public String fromCancelOrder(String userId, String orderId,String postArea) {
		String json = orderService.cancelOrder(userId, orderId,postArea);
		return json;
	}
	public ResultBase beCancelOrder(String userId, String orderId) {
		String json = fromCancelOrder(userId, orderId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromUpdateOrderStatus(String mainPay, String subPay, String orderId, String payMoney) {
		String json = shoppingCartService.updateStatus(mainPay, subPay, orderId, payMoney);
		return json;
	}

	public ResultBase beUpdateOrderStatus(String mainPay, String subPay, String orderId, String payMoney) {
		String json = fromUpdateOrderStatus(mainPay, subPay, orderId, payMoney);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromModifyPayGiftCards(String userId, String password, String giftCardnNo, String orderId,
			String email, String shopType, String payType) {
		String json = orderService.modifyPayGiftCards(userId, password, giftCardnNo, orderId, email, shopType, payType);
		return json;
	}

	public ResultObjOne<GiftCardReturn> beModifyPayGiftCards(String userId, String password, String giftCardnNo,
			String orderId, String email, String shopType, String payType) {
		String json = fromModifyPayGiftCards(userId, password, giftCardnNo, orderId, email, shopType, payType);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<GiftCardReturn> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOne<GiftCardReturn>>() {
					});
			return result;
		}
		return null;
	}

	public String fromChangePay(String userId, String orderId, String mainPay, String subPay) {
		String json = shoppingCartService.changePay(userId, orderId, mainPay, subPay);
		return json;
	}

	public ResultBase beChangePay(String userId, String orderId, String mainPay, String subPay) {
		String json = fromChangePay(userId, orderId, mainPay, subPay);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromModifyConfirm(String userId, String couponFlag, String coupon, String buyGids, String addressId,
			String topicNo, String orderSource) {
		String json = orderService.modifyConfirmOrderInfo(userId, couponFlag, coupon, buyGids, addressId, topicNo, orderSource);
		return json;
	}

	public ResultObjOne<CouponReturn> beModifyConfirm(String userId, String couponFlag, String coupon, String buyGids,
			String addressId, String topicNo, String orderSource) {
		String json = fromModifyConfirm(userId, couponFlag, coupon, buyGids, addressId, topicNo, orderSource);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CouponReturn> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponReturn>>() {
			});
			return result;
		}
		return null;
	}

	public String fromSettlement(String userId, String isPromotion, String picW, String picH, String postArea) {
		String json = shoppingCartService.getCart(userId, isPromotion, picW, picH, postArea);
		return json;
	}

	public ResultObjOne<Settlement> beSettlement(String userId, String isPromotion, String picW, String picH, String postArea) {
		String json = fromSettlement(userId, isPromotion, picW, picH, postArea);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<Settlement> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Settlement>>() {
			});
			return result;
		}
		return null;
	}

	public String fromPayGiftCards(String userId, String shopType, String payType, String orderId, String password) {
		String json = orderService.payGiftCards(userId, shopType, payType, orderId, password);
		return json;
	}

	public ResultObjOne<GiftCardReturn> bePayGiftCards(String userId, String shopType, String payType, String orderId,
			String password) {
		String json = fromPayGiftCards(userId, shopType, payType, orderId, password);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<GiftCardReturn> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjOne<GiftCardReturn>>() {
					});
			return result;
		}
		return null;
	}

	public String fromSetGiftCardPassword(String userId, String password) {
		String json = orderService.setGiftCardPassword(userId, password);
		return json;
	}

	public ResultBase beSetGiftCardPassword(String userId, String password) {
		String json = fromSetGiftCardPassword(userId, password);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromQuickOrder(QuickSubmitVO quickSubmitVO) {
		String json = orderService.quickOrder(quickSubmitVO);
		return json;
	}

	public ResultObjOne<QuickResult> beQuickOrder(QuickSubmitVO quickSubmitVO) {
		String json = fromQuickOrder(quickSubmitVO);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<QuickResult> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<QuickResult>>() {
			});
			return result;
		}
		return null;
	}

	public String fromModifyOrderItems(String userId, String couponFlag, String coupon, String buyGids,
			String addressId, String topicNo, String orderSource) {
		String json = orderService.modifyConfirmOrderInfo(userId, couponFlag, coupon, buyGids, addressId, topicNo, orderSource);
		return json;
	}

	public ResultObjOne<CouponReturn> beModifyOrderItems(String userId, String couponFlag, String coupon,
			String buyGids, String addressId, String topicNo, String orderSource) {
		String json = fromModifyOrderItems(userId, couponFlag, coupon, buyGids, addressId, topicNo, orderSource);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<CouponReturn> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CouponReturn>>() {
			});
			return result;
		}
		return null;
	}
	
    public String finishOrder(String userId, String orderId) {
    	return orderService.finishOrder(userId, orderId);
    }
    
	public ResultBase updatePayType(String userId, String mainOrderNo, String payType) {
		String json = orderService.updatePayType(userId, mainOrderNo, payType);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
		return resultBase;
	}

    /**2.9.0 订单列表*/
	public String orderList(String userId, String pageIndex, String pageSize, String status) {
		return orderService.orderList(userId, pageIndex, pageSize, status);
	}
	
    /**2.9.0 订单详情*/
	public String orderDetail(String userId, String mainOrderId, String orderId,String isSplitOrder) {
		return orderService.orderDetail(userId, mainOrderId, orderId,isSplitOrder);
	}
	
	public ResultBase modifyOrder(String userId, String orderId, String addrId, String express,
			String invoiceAddrId, String invoiceFlag, String invoiceType,
			String invoiceTitle, String invoiceContent, String isModifyInvoice) {
		String json = orderService.modifyOrder(userId, orderId, addrId, express, invoiceAddrId, invoiceFlag, invoiceType, invoiceTitle, invoiceContent, isModifyInvoice);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		return JsonUtil.fromJson(json, ResultBase.class);
	}
	
}