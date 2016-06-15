package com.shangpin.wireless.api.view.servlet;

import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.enums.PayType;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizLockSkuCommonService;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.biz.utils.PayTypeUtil;
import com.shangpin.pay.service.CommonPayService;
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
 * 支付接口
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-07-17
 */
public class PayOrderNewServlet extends BaseServlet {

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
		commonPayService=(CommonPayService) getBean(CommonPayService.class);
		aspBizLockSkuCommonService=(ASPBizLockSkuCommonService) getBean(ASPBizLockSkuCommonService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String channel = request.getHeader("ch");
		final String version = request.getHeader("ver");
		final String userid = request.getParameter("userid");
		final String orderid = request.getParameter("orderid");
		final String giftcard = request.getParameter("isusegiftcard");// 1使用礼品卡支付，0不适用礼品卡支付
		final String postArea = request.getParameter("postArea");// 1国内商品，2海外商品
		String mainpaymode = request.getParameter("mainpaymode");
		String subpaymode = request.getParameter("subpaymode");
		final String token = request.getParameter("token"); // 支付宝钱包token
		final String productno = request.getParameter("productno");
		final String sig = request.getParameter("sig");
		final String source = request.getParameter("source");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String type=request.getParameter("type");
		boolean flag=false;
		if (StringUtil.isNotEmpty(product, channel, version, userid, imei, sessionid)) {
			if (StringUtil.isNotEmpty(userid, orderid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("mainOrderNo", orderid);
				String url = Constants.BASE_TRADE_URL + "order/getorder";

				try {
					String data = WebUtil.readContentFromGet(url, map);

					log.info("payOrderNew支付页面,主站返回 data="+data);

					if (data == null || "".equals(data)) {
						try {
							WebUtil.sendApiException(response);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						
						
						MergeOrderDetailResponse memgeOrderDetailResponse = new MergeOrderDetailResponse();
						memgeOrderDetailResponse.setUserId(userid);
						JSONObject orderDetailObj = JSONObject.fromObject(memgeOrderDetailResponse.obj2Json("payOrderNew", data,product,version));
						JSONObject apiResultObj = new JSONObject();
						if (Constants.SUCCESS.equals(orderDetailObj.getString("code"))) {
							apiResultObj = new PayOrderAPIResponse().objJson(orderDetailObj, giftcard);
							JSONObject contentObj = JSONObject.fromObject(apiResultObj.getString("content"));
							//2.9.9新加开始
							JSONObject giftcardinfo = JSONObject.fromObject(contentObj.getString("giftcardinfo"));
							String mainOrderId = "";
							if(giftcardinfo!=null&&giftcardinfo.has("mainOrderId")&&giftcardinfo.getString("mainOrderId")!=null){
								mainOrderId = giftcardinfo.getString("mainOrderId");
							}
							//2.9.9新加结束
							String orderstatusdesc = contentObj.getString("statusDesc");
							CommonAPIResponse apiResponse = new CommonAPIResponse();
							String canPay = contentObj.getString("canPay");
							
							if (canPay.equals("0")) {
								apiResponse.setCode(Constants.ERROR_CANPAY);
								apiResponse.setCod(Constants.ERROR_CANPAY);
								apiResponse.setMsg("订单状态已经为" + orderstatusdesc + "，无法支付");
								response.getWriter().print(apiResponse.toString());
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
							if(StringUtils.isBlank(payCategory)) {
								if ("2".equals(postArea)) {//如果是海外订单
									Map<String, String> lockSkuMap = aspBizLockSkuCommonService.orderLockSku(contentObj.getString("orderNo"), userid, orderid);
									String lockCode = lockSkuMap.get("code");
									if (Constants.ORDER_LOCKSKU_ERROR.equals(lockCode) || Constants.ORDER_LOCKSKU_FAIL.equals(lockCode)) {
										apiResponse.setCode(lockCode);
										apiResponse.setCod(lockCode);
										apiResponse.setMsg(lockSkuMap.get("msg"));
										response.getWriter().print(apiResponse.toString());
										return;
									}
									if ("20".equals(mainpaymode) || "27".equals(mainpaymode) || "19".equals(mainpaymode)) {
										onlinePayment = PayConfigCacheUtil.getPayConfig(product, channel, version, supportCod);
										if (onlinePayment == null) {
											onlinePayment = PayConfigCacheUtil.getPayConfig(product, "0", version, supportCod);
										}
									} else {
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
								if (onlinePayment != null && !"".equals(onlinePayment)) {
									//对于主站下单移动端没有的支付方式默认调取支付宝
									for (int i = 0; i < onlinePayment.size(); i++) {
										JSONObject onlinePayObj = JSONObject.fromObject(onlinePayment.get(i));
										if (mainpaymode.equals(onlinePayObj.getString("id"))) {
											flag = true;
											break;
										}
									}
									if (!flag) {
										if ("2".equals(postArea)) {
											mainpaymode = "30";
											subpaymode = "107";//原代码为120  安卓版本比较的是107
										} else {
											mainpaymode = "20";
											subpaymode = "38";
										}

									}
								}
								if (mainpaymode.equals("30")) {
									subpaymode = "107";//原代码为120  安卓版本比较的是107
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
								String apiPayResult = new PayOrderAction().payInfo(request,data,commonPayService,product, version, userid, mainpaymode, subpaymode, orderid, source, token, productno, sig, this.getServletContext(),
										internalAmount);
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
							}else{//2.9.9新加开始
								payinfoJson.put("mainOrderId", mainOrderId);
							}
							//2.9.9新加结束
							String pic = "";
							String rechargePasswd="";
							if (type != null && type.equals("1")) {
								// 得到图片
								OrderItem orderItem = aspBizOrderService.findOrderDetail(userid, orderid);
								if (orderItem != null && orderItem.getOrder() != null && orderItem.getOrder().get(0) != null && orderItem.getOrder().get(0).getOrderdetail() != null
										&& orderItem.getOrder().get(0).getOrderdetail().get(0) != null) {
									pic = orderItem.getOrder().get(0).getOrderdetail().get(0).getPic();
								}
								// 得到图片
								contentObj.put("pic", pic);
								rechargePasswd=aspBizGiftCardService.doGiftCardRechargePasswd(userid, orderid);
							}
							contentObj.put("type", type);
							contentObj.put("pic", pic);
							contentObj.put("rechargePasswd", rechargePasswd);
							
							contentObj.put("postArea", postArea);
							payinfoJson.put("prompt", Constants.PROMPT);
							contentObj.put("payinfo", payinfoJson);
							apiResultObj.put("content", contentObj);

							log.info("payOrderNew 返回数据 apiResultObj="+ apiResultObj.toString());
							response.getWriter().print(apiResultObj.toString());
						} else {
							response.getWriter().print(orderDetailObj.toString());
						}
					}
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
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
