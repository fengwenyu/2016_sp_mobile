package com.shangpin.api.controller;

import com.shangpin.biz.bo.Freebie;
import com.shangpin.biz.service.ASPBiz520SellService;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 订单信息接口
 * 
 * @author yls
 */
@Controller
@RequestMapping("/")
public class OrderController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ASPBizOrderService aspBizOrderService;
	
	@Autowired
	private ASPBizGiftCardService aspBizGiftCardService;
	
	@Autowired
	private ASPBiz520SellService freebieSrv;

	/**
	 * 用于客户端确认收货
	 * 
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/finishOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public String finishOrder(@RequestParam String orderId) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		if (!StringUtil.isNotEmpty(imei, userId, orderId)) {
			return returnParamError();
		}

		try {
	    	return aspBizOrderService.finishOrder(userId, orderId);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}
	}

	/**
	 * 订单列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderList", method = { RequestMethod.POST, RequestMethod.GET })
	public String orderList(@RequestParam String pageIndex, @RequestParam String pageSize, @RequestParam String status) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		final String version = request.getHeader("ver");
		if (!StringUtil.isNotEmpty(imei, userId, pageIndex, pageSize, status)) {
			return returnParamError();
		}
		
		try {
			String data = aspBizOrderService.orderList(userId, pageIndex, pageSize, status);
			if (StringUtil.compareVersion("", "2.9.12", version) == 1){
				long end= DateUtils.strToDate("2016-06-04 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime();
				if(System.currentTimeMillis()<end){
					if (data == null) {
						return data;
					}
					JSONObject jsonObj = JSONObject.fromObject(data);
					if (jsonObj==null || !"0".equals(jsonObj.getString("code"))) {
						return data;
					}
					JSONObject jsonObj2 = jsonObj.getJSONObject("content");
					Freebie freebie = freebieSrv.getUserAllFreebies(userId);
					jsonObj2.put("freebie", freebie);
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", "0");
					resultMap.put("msg", "");
					resultMap.put("content", jsonObj2);
					logger.debug("买赠 order app " + JsonUtil.toJson(resultMap));
					return JsonUtil.toJson(resultMap);
				}
				return data;
		    }else{
		    	return data;
		    }
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/orderDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public String orderDetail(@RequestParam String mainOrderId, @RequestParam String orderId) {
		final String imei = request.getHeader("imei");
		final String userId = request.getHeader("userid");
		final String isSplitOrder=request.getParameter("isSplitOrder");
		if (!StringUtil.isNotEmpty(imei, userId, mainOrderId, orderId)) {
			return returnParamError();
		}

		try {
			return aspBizOrderService.orderDetail(userId, mainOrderId, orderId,isSplitOrder);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}
	}

//	/**
//	 * 提交订单
//	 *
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/submitOrderV2", method = {RequestMethod.POST, RequestMethod.GET})
//	public ContentBuilder<SubmitOrder> submitOrderV2(
//			@RequestHeader("userid") String userid,
//			@RequestHeader("p") String product,
//			@RequestHeader("ch") String channel,
//			@RequestHeader("ver") String version,
//			@RequestHeader("sessionid") String sessionid,
//			@RequestParam("receivedId") String receivedId,
//			@RequestParam("deliveryId") String deliveryId,
//			@RequestParam("invoiceButtonStatus") String invoiceButtonStatus,
//			@RequestParam(value = "invoiceType", 		required = false) String invoiceType,
//			@RequestParam(value = "invoiceTitle", 		required = false) String invoiceTitle,
//			@RequestParam(value = "invoiceContent",		required = false) String invoiceContent,
//			@RequestParam(value = "invoiceEmail",		required = false) String invoiceEmail,
//			@RequestParam(value = "invoiceTel",			required = false) String invoiceTel,
//			@RequestParam(value = "domesticCouponflag",	required = false) String domesticCouponflag,
//			@RequestParam(value = "domesticCoupon",		required = false) String domesticCoupon,
//			@RequestParam(value = "abroadCouponflag",	required = false) String abroadCouponflag,
//			@RequestParam(value = "abroadCoupon",		required = false) String abroadCoupon,
//			@RequestParam("giftCardButtonStatus") String giftCardButtonStatus,
//			@RequestParam("buyId") String buyId,
//			@RequestParam("subpayCode") String subpayCode,
////			@RequestParam("orderOrigin") String orderOrigin,
////			@RequestParam("orderType") String orderType,
//			@RequestParam("orderSource") String orderSource,
//			@RequestParam(value = "type", 				required = false) String type
//
//
//	) {
//
//		ContentBuilder<SubmitOrder> builder = new ContentBuilder<>();
//
//		String orderfrom = "9";//IOS订单渠道
//		if ("101".equals(product) || "102".equals(product)) {
//			orderfrom = "18";// 安卓订单渠道
//		}
//
//		//获取主支付方式
//		String payId = PayType.getIdBySubId(subpayCode);
//		if(StringUtil.isBlank(payId)){
//			return builder.buildDefError();
//		}
//
//		String orderType ="1";// 只有1尚品
//
//
//
//
//		//电子卡
//		if ("1".equals(type) || "2".equals(type)) {
//
////			ResultObjOne<GiftCardCommit> giftCardCommit = aspBizGiftCardService.doGiftCardCommit(
////					userid, receivedId, invoiceButtonStatus, invoiceaddrid, invoicetype, invoicetitle,
////					invoicecontent, deliveryId, orderOrigin, buyId, mainpaymode, subpayCode, orderType, type);
////			data = giftCardCommit.toJsonNullable();
////			doBiz(request, response, product, channel, version, userid, postArea, mainpaymode, chilidPayId, token, productno, sig, source, channelNo, data, type,subpaymode);
//
//
//		}
//		//普通订单
//		else {
//			Map<String, String> map = new HashMap<>();
//			map.put("userid", userid);
//			map.put("receivedId", receivedId);
//			map.put("invoiceFlag", invoiceButtonStatus);
//			map.put("invoiceType", invoiceType);
//			map.put("invoiceTitle", invoiceTitle);
//			map.put("invoiceContent", invoiceContent);
//			map.put("invoiceEmail", invoiceEmail);
//			map.put("invoiceTel", invoiceTel);
//			map.put("domesticCouponflag", domesticCouponflag);
//			map.put("domesticCoupon", domesticCoupon);
//			map.put("abroadCouponflag", abroadCouponflag);
//			map.put("abroadCoupon", abroadCoupon);
//			map.put("express", deliveryId);
//			map.put("orderfrom", orderfrom);//9：ios；18：安卓；19：M站订单渠道
//			map.put("buyId", buyId);
//			map.put("paytypeid", payId);
//			map.put("paytypechildid", subpayCode);
//			map.put("ordertype", orderType);
//			map.put("isUseGiftCardPay", giftCardButtonStatus);
//			map.put("orderSource", orderSource);
//			map.put("deliveryId", deliveryId);
//
//			//到主站提交订单
//			ResultObjOne<SubmitOrder> resultObjOne = aspBizOrderService.submitOrderV2(map);
//
//			if(resultObjOne == null ){
//				return builder.buildDefError();
//			}
//
//			if( !"0".equals(resultObjOne.getCode())){
//				return builder.buildDefError(resultObjOne.getMsg(),null);
//			}
//
//			SubmitOrder submitOrder = resultObjOne.getContent();
//
//			//获取支付方式
//			String isCod = submitOrder.getCod();
//			String postArea = submitOrder.getPostarea();
//			List<PayType> payTypeList = PayType.getPaymentType(postArea, product, channel, version);
//			List<Payment> list = new ArrayList<>();
//			for (PayType payType : payTypeList) {
//				Payment payment = new Payment();
//				payment.setId(payType.getId());
//				payment.setName(payType.getName());
//				//设置货到付款是否有效
//				String enable = "1";
//				if ("2".equals(payType.getWey()) && !"1".equals(isCod)) {
//					enable = "0";
//				}
//				payment.setEnable(enable);
//				list.add(payment);
//			}
//
//
//		}
//
//
//		return null;
//	}
}
