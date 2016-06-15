package com.shangpin.wireless.api.view.servlet;

import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizLockSkuCommonService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.api2client.domain.SubmitOrderAPIResponse;
import com.shangpin.wireless.api.api2client.domain.SubmitOrderVO;
import com.shangpin.wireless.api.api2server.domain.SubmitOrderServerVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.*;
import com.shangpin.wireless.api.view.action.PayOrderAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
 * 提交订单接口
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-07-10
 */
public class SubmitOrderServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(SubmitOrderServlet.class);

	CommonPayService commonPayService;
	private ASPBizGiftCardService aspBizGiftCardService;
	private ASPBizOrderService aspBizOrderService;
	private ASPBizLockSkuCommonService aspBizLockSkuCommonService;
	@Override
	public void init() throws ServletException {
		aspBizGiftCardService = (ASPBizGiftCardService) getBean(ASPBizGiftCardService.class);
		aspBizOrderService = (ASPBizOrderService) getBean(ASPBizOrderService.class);
		commonPayService = (CommonPayService) getBean(CommonPayService.class);
		aspBizLockSkuCommonService=(ASPBizLockSkuCommonService) getBean(ASPBizLockSkuCommonService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		String postArea = request.getParameter("postArea");
		final String addrid = request.getParameter("addrid");
		final String invoiceflag = request.getParameter("invoiceflag");
		final String couponflag = request.getParameter("couponflag");
		final String express = request.getParameter("express");
		final String coupon = request.getParameter("coupon");
		final String invoiceaddrid = request.getParameter("invoiceaddrid");
		final String invoicetitle = request.getParameter("invoicetitle");
		final String invoicecontent = request.getParameter("invoicecontent");
		final String invoicetype = request.getParameter("invoicetype");
		final String giftcard = request.getParameter("isusegiftcard");// 1
																		// 使用礼品卡支付，0不适用礼品卡支付
		final String mainpaymode = request.getParameter("mainpaymode");
		String subpaymode = request.getParameter("subpaymode");
		final String buysIds = request.getParameter("shopdetailids");
		final String token = request.getParameter("token"); // 支付宝钱包token
		final String productno = request.getParameter("productno");
		final String sig = request.getParameter("sig");
		final String source = request.getParameter("source");
		String orderSource = request.getParameter("orderSource");// 来源 1：从购物车
																	// 2：立即购买
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		String channelNo = request.getHeader("ch");

		final String type = request.getParameter("type");
		String chilidPayId=subpaymode;
		if (StringUtil.isNotEmpty(product, channel, version, userid, imei, sessionid)) {
			if(mainpaymode.equals("30")){
				chilidPayId="120";
			 }
			if (postArea == null && orderSource == null) {
				postArea = "1";
				orderSource = "1";
			}
			String orderorigin = "9";
			if ("101".equals(product) || "102".equals(product)) {
				orderorigin = "18";// 安卓订单渠道
			}
			String ordertype;
			if ("1".equals(product) || "101".equals(product)) { // 奥莱客户端
				ordertype = "2";// (表示订单来自尚品1，奥莱2)
			} else if ("2".equals(product) || "102".equals(product)) { // 尚品客户端
				ordertype = "1";// (表示订单来自尚品1，奥莱2)
			} else {
				ordertype = "";
			}
			String data = null;
			if (StringUtil.isNotEmpty(type) &&( type.equals("1") || type.equals("2"))) {
				// 电子卡
				ResultObjOne<GiftCardCommit> giftCardCommit = aspBizGiftCardService.doGiftCardCommit(userid, addrid, invoiceflag, invoiceaddrid, invoicetype, invoicetitle,
						invoicecontent, express, orderorigin, buysIds, mainpaymode, chilidPayId, ordertype, type,
						"","");//2.9.12之前版本没有发票手机号，email
				data = giftCardCommit.toJsonNullable();
				doBiz(request, response, product, channel, version, userid, postArea, mainpaymode, chilidPayId, token, productno, sig, source, channelNo, data, type,subpaymode);
			} else {
				// 普通
				if (StringUtil.isNotEmpty(addrid, invoiceflag, express)) {
					if (SessionUtil.validate(userid, imei, sessionid)) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("userid", userid);
						map.put("postArea", postArea);
						map.put("addrid", addrid);
						map.put("invoiceflag", invoiceflag);
						map.put("isUseGiftCardPay", giftcard);
						map.put("express", express);
						map.put("coupon", null == coupon ? "0" : coupon);
						map.put("couponflag", couponflag);
						map.put("invoiceaddrid", invoiceaddrid);
						map.put("invoicetitle", invoicetitle);
						map.put("invoicecontent", invoicecontent);
						map.put("invoicetype", invoicetype);
						map.put("paytypeid", mainpaymode);
						map.put("paytypechildid", chilidPayId);
						map.put("buysIds", buysIds);
						map.put("orderfrom", orderorigin);// (订单来源)
						map.put("ordertype", ordertype);// (表示订单来自尚品1，奥莱2)
						map.put("orderSource", orderSource);// (表示从哪来到提交订单。1：从购物车
															// 2：立即购买)
						String url = Constants.BASE_TRADE_URL + "order/ConfirmOrder/";

						try {
							log.info("2.9.12之前调用主站接口：url="+url+"参数="+map);
							data = WebUtil.readContentFromGet(url, map);
							log.info("2.9.12之前提交订单主站返回data="+data);

							doBiz(request, response, product, channel, version, userid, postArea, mainpaymode, chilidPayId, token, productno, sig, source, channelNo, data, type,subpaymode);
						} catch (Exception e) {
							e.printStackTrace();
							log.error("OrderManageServlet：" + e);
							try {
								WebUtil.sendApiException(response);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

					} else {
						try {
							WebUtil.sendErrorNoSessionid(response);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	private void doBiz(HttpServletRequest request, HttpServletResponse response, final String product, final String channel, final String version, final String userid,
			String postArea, final String mainpaymode, final String childPayId, final String token, final String productno, final String sig, final String source,
			String channelNo, String data, String type,String subpaymode) throws IOException {
		SubmitOrderServerVO submitOrderServerVO = new SubmitOrderServerVO().jsonObj(data);
		SubmitOrderVO submitOrderVO = submitOrderServerVO.getSubmitOrderVO();
		JSONObject apiResultObj = new SubmitOrderAPIResponse().objJson(submitOrderServerVO);
		if (Constants.SUCCESS.equals(submitOrderServerVO.getCode())) {
			try {
				JSONObject contentObj = JSONObject.fromObject(apiResultObj.getString("content"));
				// 获取的在线支付方式
				final boolean supportCod = "1".equals(submitOrderVO.getCod());
				JSONArray onlinePayment = null;
				if ("2".equals(postArea)) {
					Map<String,String> lockSkuMap=new HashMap<String,String>();
					List<SupplierSkuNoInfo> list =submitOrderVO.getSupplierskuno();
					String orderNo=submitOrderVO.getOrderno();
					if(!StringUtils.isEmpty(orderNo)){
						if(list!=null&&list.size()>0){
							lockSkuMap=aspBizLockSkuCommonService.orderLockSku(submitOrderVO.getOrderno(), userid, submitOrderVO.getOrderid(),list);
						}else{
							lockSkuMap=aspBizLockSkuCommonService.orderLockSku(submitOrderVO.getOrderno(), userid, submitOrderVO.getOrderid());
						}
					}
				
					
					String lockCode=lockSkuMap.get("code");
					if (Constants.ORDER_LOCKSKU_ERROR.equals(lockCode)||Constants.ORDER_LOCKSKU_FAIL.equals(lockCode)) {
						CommonAPIResponse apiResponse = new CommonAPIResponse();
						apiResponse.setCode(lockCode);
						apiResponse.setMsg(lockSkuMap.get("msg"));
						response.getWriter().print(apiResponse.toString());
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
			
				contentObj.put("payment", onlinePayment);
				JSONObject selectedObj = new JSONObject();
				selectedObj.put("mainpaymode", mainpaymode);
				selectedObj.put("subpaymode", subpaymode);
				contentObj.put("selectedpayment", selectedObj);
				JSONObject payinfoJson = new JSONObject();
				
				
				
				if ("0".equals(submitOrderVO.getGiftcard())) {

					String apiPayResult = new PayOrderAction().payInfo(request,null, commonPayService, product, version, userid, mainpaymode, childPayId,
							submitOrderVO.getOrderid(), source, token, productno,sig, this.getServletContext());
					JSONObject payObj = new JSONObject();
					payObj = JSONObject.fromObject(apiPayResult);
					if (Constants.ERROR_CANPAY.equals(payObj.getString("code"))) {
						response.getWriter().print(apiPayResult.toString());
						return;
					}
					if ("0".equals(payObj.getString("code"))) {
						payinfoJson = JSONObject.fromObject(payObj.getString("content"));
					}
					if ("-3".equals(payObj.getString("code"))) {
						response.getWriter().print(apiPayResult.toString());
						return;
					}
				}
				String pic = "";
				String rechargePasswd="";
				if (type != null && type.equals("1")) {
					// 得到图片
					String orderId = submitOrderVO.getOrderid();
					OrderItem orderItem = aspBizOrderService.findOrderDetail(userid, orderId);
					if (orderItem != null && orderItem.getOrder() != null && orderItem.getOrder().get(0) != null && orderItem.getOrder().get(0).getOrderdetail() != null
							&& orderItem.getOrder().get(0).getOrderdetail().get(0) != null) {
						pic = orderItem.getOrder().get(0).getOrderdetail().get(0).getPic();
						pic=pic.replace("-100-100","-10-10" );
					}
					// 得到图片
					contentObj.put("pic", pic);
					rechargePasswd=aspBizGiftCardService.doGiftCardRechargePasswd(userid, orderId);
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
				if(null!=submitOrderServerVO.getSubmitOrderVO()&&null!=submitOrderServerVO.getSubmitOrderVO().getExpiretime()){
					payinfoJson.put("expiretime", submitOrderServerVO.getSubmitOrderVO().getExpiretime());
				}
//				if(payinfoJson.get("date")==null||"".equals(payinfoJson.get("date"))){
//					Date date = new Date();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					payinfoJson.put("date", sdf.format(date));
//				}
				
				contentObj.put("payinfo", payinfoJson);
				apiResultObj.put("content", contentObj);
				apiResultObj.put("cod", apiResultObj.get("code"));
				String result =apiResultObj.toString();
				response.getWriter().print(result);
			} catch (Exception e) {
				apiResultObj = new JSONObject();
				apiResultObj.put("code", Constants.ERROR_GETPAY);
				apiResultObj.put("msg", "订单支付失败啦，请重新支付");
				apiResultObj.put("content", new JSONObject());
				response.getWriter().print(apiResultObj.toString());
			}
			// 记录访问日志
			FileUtil.addLog(request, "submitOrder", channelNo, "userid", userid, "orderid", submitOrderVO.getOrderid(), "amount", submitOrderVO.getAmount(), "data",
					submitOrderVO.getDate());
		} else {
			apiResultObj = new JSONObject();
			apiResultObj.put("code", Constants.ERROR_ORDERSUBMIT);
			apiResultObj.put("msg", submitOrderServerVO.getMsg()==null?"订单提交失败了，请重新提交":submitOrderServerVO.getMsg());
			apiResultObj.put("content", new JSONObject());
			response.getWriter().print(apiResultObj.toString());
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
