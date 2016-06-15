package com.shangpin.wireless.api.view.servlet;

import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.enums.PayType;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.biz.utils.PayTypeUtil;
import com.shangpin.pay.service.CommonPayService;
import com.shangpin.wireless.api.api2client.domain.SubmitOrderAPIResponse;
import com.shangpin.wireless.api.api2client.domain.SubmitOrderVO;
import com.shangpin.wireless.api.api2server.domain.SubmitOrderServerVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.action.PayOrderAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
public class SubmitOrderServletV2 extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(SubmitOrderServletV2.class);

	CommonPayService commonPayService;
	private ASPBizGiftCardService aspBizGiftCardService;
	private ASPBizOrderService aspBizOrderService;

	@Override
	public void init() throws ServletException {
		aspBizGiftCardService = (ASPBizGiftCardService) getBean(ASPBizGiftCardService.class);
		aspBizOrderService = (ASPBizOrderService) getBean(ASPBizOrderService.class);
		commonPayService = (CommonPayService) getBean(CommonPayService.class);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
        final String imei = request.getHeader("imei");
        final String channelNo = request.getHeader("ch");
        final String userid = request.getHeader("userid");

        final String sessionid = request.getParameter("sessionid");

        String addrid = request.getParameter("receivedId");

		//注意这里啊  接受的是deliveryId
        String express = request.getParameter("deliveryId");
        String invoiceButtonStatus = request.getParameter("invoiceButtonStatus");
        String invoiceType = request.getParameter("invoiceType");
        String invoiceTitle = request.getParameter("invoiceTitle");
        String invoiceContent = request.getParameter("invoiceContent");
        String invoiceEmail = request.getParameter("invoiceEmail");
        String invoiceTel = request.getParameter("invoiceTel");
        String domesticCouponflag = request.getParameter("domesticCouponflag");
        String domesticCoupon = request.getParameter("domesticCoupon");
        String abroadCouponflag = request.getParameter("abroadCouponflag");
        String abroadCoupon = request.getParameter("abroadCoupon");
        String giftCardButtonStatus = request.getParameter("giftCardButtonStatus");
		String mainpaymode = request.getParameter("mainpayCode");
        String subpaymode = request.getParameter("subpayCode");
//        String postArea = request.getParameter("postArea");//国内海外
        String buyId = request.getParameter("buyId");
		String productno = request.getParameter("productno");
		String orderSource = request.getParameter("orderSource");// 来源 1：从购物车	// 2：立即购买
		String type = request.getParameter("type");


        String sig = "";
        String token = "";
        String source = "";
        String postArea = "";

        String chilidPayId = subpaymode;

        if (StringUtil.isNotEmpty(product, channel, version, userid, imei, sessionid)) {
			String orderorigin = "9";
			if ("101".equals(product) || "102".equals(product)) {
				orderorigin = "18";// 安卓订单渠道
			}

			String ordertype="1";//标识来自尚品

			String data = null;
			if (StringUtil.isNotEmpty(type) &&( type.equals("1") || type.equals("2"))) {
				// 电子卡
				ResultObjOne<GiftCardCommit> giftCardCommit = aspBizGiftCardService.doGiftCardCommit(
						userid, addrid, invoiceButtonStatus, "", invoiceType, invoiceTitle,
                        invoiceContent, express, orderorigin, buyId, mainpaymode, chilidPayId, ordertype, type,
						invoiceEmail, invoiceTel);

				//设置 payCategory = 1国内支付
				if(giftCardCommit != null && "0".equals(giftCardCommit.getCode()) && giftCardCommit.getContent() != null ){
					giftCardCommit.getContent().setPayCategory("1");
				}

				data = giftCardCommit.toJsonNullable();
				log.info("电子卡订单，主站返回=" + data);

				doBiz(request, response, product, channel, version, userid, postArea, mainpaymode, chilidPayId, token, productno, sig, source, channelNo, data, type,subpaymode);
			} else {
				// 普通
				if (StringUtil.isNotEmpty(addrid, invoiceButtonStatus, express)) {
					if (SessionUtil.validate(userid, imei, sessionid)) {
						Map<String, String> map = new HashMap<>();

						map.put("userid", userid);
						map.put("receivedId", addrid);
						map.put("invoiceFlag", invoiceButtonStatus);
						map.put("invoiceType", invoiceType);
						map.put("invoiceTitle", invoiceTitle);
						map.put("invoiceContent", invoiceContent);
						map.put("invoiceEmail", invoiceEmail);
						map.put("invoiceTel", invoiceTel);
						map.put("domesticCouponflag", domesticCouponflag);
						map.put("domesticCoupon", domesticCoupon);
						map.put("abroadCouponflag", abroadCouponflag);
						map.put("abroadCoupon", abroadCoupon);
						map.put("express", express);
						map.put("orderfrom", orderorigin);//9：ios；18：安卓；19：M站订单渠道
						map.put("buyId", buyId);
						map.put("paytypeid", mainpaymode);
						map.put("paytypechildid", chilidPayId);
						map.put("ordertype", ordertype);
						map.put("isUseGiftCardPay", giftCardButtonStatus);
						map.put("orderSource", orderSource);

						//没用的参数,主站还要要
						map.put("deliveryId", "2");

						String url = Constants.BASE_TRADE_URL + "order/ConfirmOrderV2/";

						try {
							log.info("提交普通订单调用主站接口：url="+url+"参数="+map);
							data = WebUtil.readContentFromGet(url, map);
//							data = "{\"code\":\"0\",\"msg\":\"\",\"content\":{\"orderid\":\"201604270052556\",\"date\":\"2016/04/27 19:17:34\",\"amount\":\"1575\",\"cod\":\"0\",\"codmsg\":\"\",\"skucounts\":\"2\",\"objectcounts\":\"6\",\"giftcardbalance\":\"12291.00\",\"giftcard\":\"0\",\"carriage\":\"240\",\"systime\":\"1461755858048\",\"expiretime\":\"1461757658057\",\"internalAmount\":\"0\",\"payCategory\":\"2\"}}";
							log.info("提交普通订单调用主站接口返回数据："+data);
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
				//payCategory 使用什么支付方式
				String payCategory = submitOrderVO.getPayCategory();

				JSONObject contentObj = JSONObject.fromObject(apiResultObj.getString("content"));

				JSONArray onlinePayment = new JSONArray();
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
					if("2".equals(payType.getWey()) && "0".equals(submitOrderVO.getCod())){//线下支付不可用
						enble = "0";
					}

					jsonObject.put("enable",enble);
					onlinePayment.add(jsonObject);
				}

				contentObj.put("payment", onlinePayment);
				JSONObject selectedObj = new JSONObject();
				selectedObj.put("mainpaymode", mainpaymode);
				selectedObj.put("subpaymode", subpaymode);
				contentObj.put("selectedpayment", selectedObj);
				JSONObject payinfoJson = new JSONObject();


				//先去调用支付接口, 获取到支付凭据 支付凭据唤醒支付APP
				if ("0".equals(submitOrderVO.getGiftcard())) {

					String apiPayResult = new PayOrderAction().payInfo(request,null, commonPayService, product, version, userid, mainpaymode, childPayId,
							submitOrderVO.getOrderid(), source, token, productno,sig, this.getServletContext(),
							submitOrderVO.getInternalAmount());
					JSONObject payObj = JSONObject.fromObject(apiPayResult);
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

				contentObj.put("payinfo", payinfoJson);
				apiResultObj.put("content", contentObj);
				apiResultObj.put("cod", apiResultObj.get("code"));
				String result =apiResultObj.toString();
				log.info("提交订单返回结果：result=" + result);

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
