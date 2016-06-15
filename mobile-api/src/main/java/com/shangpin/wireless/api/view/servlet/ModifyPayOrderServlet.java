package com.shangpin.wireless.api.view.servlet;

import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.enums.PayType;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizLockSkuCommonService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.biz.utils.PayTypeUtil;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.api2client.domain.MergeOrderDetailResponse;
import com.shangpin.wireless.api.api2client.domain.PayOrderAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.OutPayConfigCacheUtil;
import com.shangpin.wireless.api.util.PayConfigCacheUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.action.PayOrderAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改订单信息 支付订单接口
 * 
 * @author xupengcheng
 * 
 */
public class ModifyPayOrderServlet extends BaseServlet {

	private static final long serialVersionUID = 8998841904428376524L;
	protected final Log log = LogFactory.getLog(ModifyPayOrderServlet.class);
	WeixinpayService weixinpayService;
	
	CommonPayService commonPayService;
	private ASPBizGiftCardService aspBizGiftCardService;
	private ASPBizOrderService aspBizOrderService;
	ASPBizLockSkuCommonService aspBizLockSkuCommonService;
	
	@Override
	public void init() throws ServletException {
		commonPayService=(CommonPayService) getBean(CommonPayService.class);
		aspBizGiftCardService = (ASPBizGiftCardService) getBean(ASPBizGiftCardService.class);
		aspBizOrderService = (ASPBizOrderService) getBean(ASPBizOrderService.class);
		aspBizLockSkuCommonService=(ASPBizLockSkuCommonService) getBean(ASPBizLockSkuCommonService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String product = req.getHeader("p");
		final String channel = req.getHeader("ch");
		final String version = req.getHeader("ver");
		final String postArea = req.getParameter("postArea");
		String userId = req.getParameter("userId");
		String orderId = req.getParameter("orderId");
		String addrId = req.getParameter("addrId");// 收货地址ID
		String express = req.getParameter("express");// 收货时间
		String isModifyInvoice = req.getParameter("isModifyInvoice");//是否修改发票信息
		String invoiceAddrId = req.getParameter("invoiceAddrId");// 发票地址ID
		String invoiceFlag = req.getParameter("invoiceFlag");// 是否开发票
		String invoiceType = req.getParameter("invoiceType");// 发票类型
		String invoiceTitle = req.getParameter("invoiceTitle"); // 发票抬头
		String invoiceContent = req.getParameter("invoiceContent"); // 发票内容
		String mainpaymode = req.getParameter("mainPayMode");
		String subpaymode = req.getParameter("subPayMode");
		String source = req.getParameter("source"); // 支付宝钱包标识
		String token = req.getParameter("token"); // 支付宝钱包token
		String phoneNo = req.getParameter("phoneNo"); // 翼支付用户手机号
		String sig = req.getParameter("sig"); // 翼支付签名
		String giftcard = req.getParameter("isUseGiftCard") == null ? "0" : req.getParameter("isUseGiftCard");// 1	// 使用礼品卡支付，0不适用礼品卡支付
		String type = req.getParameter("type"); // 0普通 1电子卡 2实物卡
		if(!StringUtil.isNotEmpty(type)){
			type="0";
		}
		boolean flag = false;
		int result = 0;
		if (StringUtil.isNotEmpty(userId, orderId, addrId, express, invoiceAddrId, invoiceFlag, invoiceType, isModifyInvoice)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userId);
			map.put("orderNo", orderId);
			map.put("addrid", addrId);
			map.put("timeRequest", express);
			map.put("invoiceaddrid", invoiceAddrId);
			if(isModifyInvoice.equals("0") || isModifyInvoice.equals("-1")){
				map.put("isModifyInvoice", "0");
			}else{
				map.put("isModifyInvoice", "1");
			}
			map.put("invoiceflag", invoiceFlag);
			map.put("invoicetype", invoiceType);
			map.put("invoicetitle", invoiceTitle);
			map.put("invoicecontent", invoiceContent);
			String url = Constants.BASE_TRADE_URL + "order/ModifyOrderAddress";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				if (data != null) {
					JSONObject dataObj = JSONObject.fromObject(data);
					if (dataObj != null) {
						String code = dataObj.getString("code");
						if (code != null && "0".equals(code)) {
							flag = true;
						}else{
							resp.getWriter().print(dataObj.toString());
						}
					} else {
						result = 1;
					}
				} else {
					result = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = 2;
		}

		if (flag) {
			if (StringUtil.isNotEmpty(product, channel, version, userId, orderId)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userId);
				map.put("mainOrderNo", orderId);
				String url = Constants.BASE_TRADE_URL + "order/getorder";
				try {
					String data = WebUtil.readContentFromGet(url, map);

					log.info("modifyPayOrder再支付页面,主站返回 data="+data);
					if (data == null || "".equals(data)) {
						result = 2;
					} else {
						MergeOrderDetailResponse memgeOrderDetailResponse = new MergeOrderDetailResponse();
						memgeOrderDetailResponse.setUserId(userId);
						JSONObject orderDetailObj = JSONObject.fromObject(memgeOrderDetailResponse.obj2Json("modifyPayOrder", data,product,version));
						JSONObject apiResultObj = new JSONObject();
						if (Constants.SUCCESS.equals(orderDetailObj.getString("code"))) {
							
							
							apiResultObj = new PayOrderAPIResponse().objJson(orderDetailObj, giftcard);
							JSONObject contentObj = JSONObject.fromObject(apiResultObj.getString("content"));
							String orderstatusdesc = contentObj.getString("statusDesc");
							String canPay = contentObj.getString("canPay");
							
							CommonAPIResponse apiResponse = new CommonAPIResponse();
							if (canPay.equals("0")) {
								apiResponse.setCode(Constants.ERROR_CANPAY);
								apiResponse.setMsg("订单状态已经为" + orderstatusdesc + "，无法支付");
								resp.getWriter().print(apiResponse.toString());
								return;
							} 
							
							// 获取的在线支付方式
							final boolean supportCod = "1".equals(contentObj.getString("cod"));
							JSONArray onlinePayment = new JSONArray();

							/**
							 * 特别声明
							 *
							 * 2.9.12增加了字段 payCategory 1国内支付 2海外支付 3分账支付
							 * 		此字段主站应该必须返回 , 可是站之前版本的请求不返回
							 * 那么根据此字段返回支付方式如下
							 *
							 */
							//payCategory 使用什么支付方式

							Object category = contentObj.get("payCategory");
							String payCategory = category == null ? null : contentObj.getString("payCategory");

							//如果 payCategory 为空 还走原来的代码
							if(StringUtils.isBlank(payCategory)){
								if ("2".equals(postArea)) {
									Map<String,String> lockSkuMap=aspBizLockSkuCommonService.orderLockSku(orderDetailObj.getJSONObject("content").getJSONObject("shangpinOrder").getString("orderId"), userId, orderId);
									String lockCode=lockSkuMap.get("code");
									if (Constants.ORDER_LOCKSKU_ERROR.equals(lockCode)||Constants.ORDER_LOCKSKU_FAIL.equals(lockCode)) {

										apiResponse.setCode(lockCode);
										apiResponse.setMsg(lockSkuMap.get("msg"));
										resp.getWriter().print(apiResponse.toString());
										return;
									}
									if("20".equals(mainpaymode)||"27".equals(mainpaymode)||"19".equals(mainpaymode)){
										onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
										if (onlinePayment == null) {
											onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
										}
									}else{
										onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
										if (onlinePayment == null) {
											onlinePayment = OutPayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
										}
									}
								} else {
									onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
									if (onlinePayment == null) {
										onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
									}
								}
								if(mainpaymode.equals("30")){
									subpaymode="120";
								}
							}
							//如果 payCategory 不为空,
							else{
								List<PayType> payTypeList = PayTypeUtil.getPaymentType(payCategory, product, channel, version);
								for (PayType payType : payTypeList) {

									//线下支付只返回一个就行了,这是以前的逻辑,客户端会拆成两个
									//把现金的去掉 只留pos的就好了
									if(PayType.CASH == payType){//枚举是单实例可以用==
										continue;
									}

									JSONObject jsonObject = new JSONObject();
									jsonObject.put("id",payType.getId());
									jsonObject.put("name",payType.getName());
									String enble = "1";
									if("2".equals(payType.getWey()) && !supportCod){//线下支付不可用
										enble = "0";
									}
									jsonObject.put("enable",enble);
									onlinePayment.add(jsonObject);
								}
							}


							contentObj.put("payment", onlinePayment);
							JSONObject selectedObj = new JSONObject();
							selectedObj.put("mainpaymode", mainpaymode);
							selectedObj.put("subpaymode", subpaymode);
							contentObj.put("selectedpayment", selectedObj);
							JSONObject payinfoJson = new JSONObject();
							
							String internalAmount = contentObj.get("internalAmount")==null ? "":contentObj.getString("internalAmount");
							
							if ("0".equals(contentObj.getString("giftcard"))) { //
								String apiPayResult = new PayOrderAction().payInfo(req,data,commonPayService,product, version, userId, mainpaymode, subpaymode, orderId, source, token, phoneNo, sig, this.getServletContext(),
										internalAmount);
								JSONObject payObj = new JSONObject();
								payObj = JSONObject.fromObject(apiPayResult);
								if (Constants.ERROR_CANPAY.equals(payObj.getString("code"))) {
									resp.getWriter().print(apiPayResult.toString());
									return;
								}
								if ("0".equals(payObj.getString("code"))) {
									payinfoJson = JSONObject.fromObject(payObj.getString("content"));
								}
								if ("-3".equals(payObj.getString("code"))) {
									resp.getWriter().print(apiResultObj.toString());
									return;
								}
							
							}
							contentObj.put("type", type);
							String pic = "";
							String rechargePasswd="";
							if (type != null && type.equals("1")) {
								// 得到图片
								OrderItem orderItem = aspBizOrderService.findOrderDetail(userId, orderId);
								if (orderItem != null && orderItem.getOrder() != null && orderItem.getOrder().get(0) != null && orderItem.getOrder().get(0).getOrderdetail() != null
										&& orderItem.getOrder().get(0).getOrderdetail().get(0) != null) {
									pic = orderItem.getOrder().get(0).getOrderdetail().get(0).getPic();
									pic=pic.replace("-100-100","-10-10" );
								}
								// 得到图片
								contentObj.put("pic", pic);
								rechargePasswd=aspBizGiftCardService.doGiftCardRechargePasswd(userId, orderId);
							}
							
							if(StringUtil.isNotEmpty(type)){
								contentObj.put("type", type);
							}else{
								contentObj.put("type", "0");
							}
						
							contentObj.put("pic", pic);
							contentObj.put("rechargePasswd", rechargePasswd);
							contentObj.put("postArea", postArea);
							payinfoJson.put("prompt", Constants.PROMPT);
							contentObj.put("payinfo", payinfoJson);
							apiResultObj.put("content", contentObj);

							log.info("modifyPayOrder 返回数据 apiResultObj="+ apiResultObj.toString());

							resp.getWriter().print(apiResultObj.toString());
						} else {
							resp.getWriter().print(orderDetailObj.toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					result = 2;
				}
			
			} else {
				result = 1;
			}
		}
		
		if(result == 1){
			try {
				WebUtil.sendApiException(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(result == 2){
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
