/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.mobileAolai.common.alipay.trade;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.alipay.base.PartnerConfig;
import com.shangpin.mobileAolai.common.alipay.base.ResponseResult;
import com.shangpin.mobileAolai.common.alipay.base.TradeConfig;
import com.shangpin.mobileAolai.common.alipay.security.RSASignature;
import com.shangpin.mobileAolai.common.alipay.util.ParameterUtil;
import com.shangpin.mobileAolai.common.alipay.util.StringUtil;
import com.shangpin.mobileAolai.common.alipay.util.XMapUtil;
import com.shangpin.mobileAolai.common.alipay.vo.DirectTradeCreateRes;
import com.shangpin.mobileAolai.common.alipay.vo.ErrorCode;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.vo.OrderVO;

//
//
//调用支付宝的开放平台创建、支付交易步骤
//
//1.将业务参数：外部交易号、商品名称、商品总价、卖家帐户、卖家帐户、notify_url这些东西按照xml 的格式放入<req_data></req_data>中 
//2.将通用参数也放入请求参数中 
//3.对以上的参数进行签名，签名结果也放入请求参数中 
//4.请求支付宝开放平台的alipay.wap.trade.create.direct服务
//5.从开放平台返回的内容中取出request_token（对返回的内容要先用私钥解密，再用支付宝的公钥验签名） 
//6.使用拿到的request_token组装alipay.wap.auth.authAndExecute服务的跳转url 
//7.根据组装出来的url跳转到支付宝的开放平台页面，交易创建和支付在支付宝的页面上完成
//
//
//@author 3y
//@version $Id: Trade.java, v 0.1 2011-08-28 下午17:52:02 3y Exp $
//
public class WebTrade extends HttpServlet {
	private static final long serialVersionUID = -3035307235076650766L;
	private static final String SEC_ID = "0001";
	private static Log logInfo = LogFactory.getLog(WebTrade.class);
	//支付宝回调地址修改为线上外网环境
	String basePath = Constants.AOLAI_URL;
	//支付宝回调地址修改为线上外网环境
	String callbackUrl = "";
	String spCallBackUrl = Constants.AOLAI_URL+"alipay/spwebcallBack";
	String alCallbackUrl = Constants.AOLAI_URL+"alipay/alwebcallBack";
	// String callbackUrl = "http://m.aolai.com/alipay/callBack";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userId");
		String orderId = request.getParameter("orderId");
		String shopType = request.getParameter("shopType");
		if("1".equals(shopType))
			callbackUrl = spCallBackUrl;
		else if("2".equals(shopType))
			callbackUrl = alCallbackUrl;
//		String mainpaymode = request.getParameter("mainpaymode");
//		String subpaymode = request.getParameter("subpaymode");
		String mainpaymode = "20";
		String subpaymode = "37";
	
		String totalFee = "-1";
		//获取订单信息：订单编号，订单时间yyyyMMDDHHmmss，订单金额100.00，订单状态，订单状态描述
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("mainOrderNo", orderId);
		String url = Constants.BASE_NEWAPI_URL_SP + "order/getorder";
		OrderVO orderVO = null;
		String json = null;
		try {
			// 获取订单详情json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (!(jsonObj.isEmpty() || jsonObj.isNullObject()) && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				orderVO = OrderVO.json2NewBean(jsonObj.getJSONObject("result"));
				totalFee=orderVO.getOnlineamount();
			}
		}
		// 记录访问日志
		FileUtil.addLog(request, "alipay/webtrade",//
				"32",
				"userid", userid,//
				"orderid", orderId,
				"mainpaymode", mainpaymode,
				"subpaymode", subpaymode,
				"ordertime", orderVO.getDate(),
				"orderamount", orderVO.getAmount(),
				"orderstatus", orderVO.getStatus(),
				"orderstatusdesc", orderVO.getStatusdesc(),
				"orderOrigin", orderVO.getOrderOrigin(),
				"orderOriginDesc", orderVO.getOrderOriginDesc(),
				//"data", apiResponse.getData(),
				"cancancel", orderVO.getCancancel(),
				"canpay", orderVO.getCanpay(),
				"canconfirm", orderVO.getCanconfirm()
				);
		
//		if("0".equals(balanceServerResponse.getCanpay())) {
//			try {
//				request.getRequestDispatcher("/prompt.jsp").forward(request, response);
//			} catch (ServletException e) {
//				e.printStackTrace();
//			}
//			return;
//		}
		
		// 得到应用服务器地址
		//String path = request.getContextPath();
		//basePath = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getServerPort() + path + "/";
		Map<String, String> reqParams = prepareTradeRequestParamsMap(request,totalFee);
		String error = reqParams.get("error");
		if("-1".equals(error)){
			response.getWriter().print("错误的订单号，交易失败！");
			return;
		}
		// 签名类型
		String signAlgo = SEC_ID;
		String reqUrl = TradeConfig.REQ_URL;
		// 签名
		String sign = sign(reqParams, signAlgo, PartnerConfig.RSA_PRIVATE);
		reqParams.put("sign", sign);
		ResponseResult resResult = new ResponseResult();
		String businessResult = "";
		try {
			resResult = send(reqParams, reqUrl, signAlgo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (resResult.isSuccess()) {
			businessResult = resResult.getBusinessResult();
		} else {
			return;
		}
		DirectTradeCreateRes directTradeCreateRes = null;
		XMapUtil.register(DirectTradeCreateRes.class);
		try {
			directTradeCreateRes = (DirectTradeCreateRes) XMapUtil.load(new ByteArrayInputStream(businessResult.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		} catch (Exception e) {
		}
		// 开放平台返回的内容中取出request_token
		String requestToken = directTradeCreateRes.getRequestToken();
		logInfo.debug("Token:" + requestToken);
		Map<String, String> authParams = prepareAuthParamsMap(request, requestToken);
		// 对调用授权请求数据签名
		String authSign = sign(authParams, signAlgo, PartnerConfig.RSA_PRIVATE);
		authParams.put("sign", authSign);
		String redirectURL = "";
		try {
			redirectURL = getRedirectUrl(authParams, reqUrl);
			logInfo.debug("authAndExecute URL:" + redirectURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtil.isNotBlank(redirectURL)) {
			response.sendRedirect(redirectURL);
		}
		return;
		// try {
		// request.setAttribute("sign", authParams.get("sign"));
		// request.setAttribute("sec_id", authParams.get("sec_id"));
		// request.setAttribute("v", authParams.get("v"));
		// request.setAttribute("call_back_url", authParams.get("call_back_url"));
		// request.setAttribute("req_data", authParams.get("req_data"));
		// request.setAttribute("service", authParams.get("service"));
		// request.setAttribute("partner", authParams.get("partner"));
		// request.setAttribute("format", authParams.get("format"));
		// request.getRequestDispatcher("/WEB-INF/pages/market/alipay.jsp").forward(request, response);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 准备alipay.wap.trade.create.direct服务的参数
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	private Map<String, String> prepareTradeRequestParamsMap(HttpServletRequest request,String totalFee) throws IOException {
		Map<String, String> requestParams = new HashMap<String, String>();
		request.setCharacterEncoding("utf-8");
		// 商品名称
		// String subject = TradeConfig.SUBJECT;
		// 支付所需的订单id
		String orderId = request.getParameter("orderId");
		// String totalFee = TradeConfig.TOTAL_FEE;
		// String totalFee = request.getParameter("totalFee");
		if("-1".equals(totalFee)){
			requestParams.put("error", totalFee);
			return requestParams;
		}
		// String totalFee = "0.03";
		// 外部交易号 这里取当前时间，商户可根据自己的情况修改此参数，但保证唯一性
		//String outTradeNo = System.currentTimeMillis() + "";
		// String outTradeNo = "abcde123";
		// 卖家帐号
		String sellerAccountName = PartnerConfig.SELLER_EMAIL;
		// 接收支付宝发送的通知的url
		// String notifyUrl = basePath + "alipay/notifyReceiver";
		//支付宝通知地址修改为线上外网环境
		String notifyUrl = Constants.AOLAI_URL+"alipay/notifyReceiver";
		// 未完成支付，用户点击链接返回商户url
		String merchantUrl = basePath;
		// req_data的内容
		String reqData = "<direct_trade_create_req><subject>" //
				+ "订单号：" + orderId + "</subject><out_trade_no>"//
				+ orderId + "</out_trade_no><total_fee>" //
				+ totalFee + "</total_fee><seller_account_name>" //
				+ sellerAccountName + "</seller_account_name><notify_url>" //
				+ notifyUrl + "</notify_url><call_back_url>" //
				+ callbackUrl + "</call_back_url><merchant_url>" //
				+ merchantUrl + "</merchant_url>";
		reqData = reqData + "</direct_trade_create_req>";
		requestParams.put("req_data", reqData);
		requestParams.put("req_id", System.currentTimeMillis() + "");
		requestParams.putAll(prepareCommonParams(request));
		return requestParams;
	}

	/**
	 * 准备alipay.wap.auth.authAndExecute服务的参数
	 * 
	 * @param request
	 * @param requestToken
	 * @return
	 */
	private Map<String, String> prepareAuthParamsMap(HttpServletRequest request, String requestToken) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String reqData = "<auth_and_execute_req><request_token>" + requestToken + "</request_token></auth_and_execute_req>";
		requestParams.put("req_data", reqData);
		requestParams.putAll(prepareCommonParams(request));
		// 支付成功跳转链接
		requestParams.put("call_back_url", callbackUrl);
		requestParams.put("service", "alipay.wap.auth.authAndExecute");
		return requestParams;
	}

	/**
	 * 准备通用参数
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> prepareCommonParams(HttpServletRequest request) {
		Map<String, String> commonParams = new HashMap<String, String>();
		commonParams.put("service", "alipay.wap.trade.create.direct");
		commonParams.put("sec_id", SEC_ID);
		commonParams.put("partner", PartnerConfig.PARTNER);
		// String callBackUrl = basePath + "alipay/callBack";
		commonParams.put("call_back_url", callbackUrl);
		commonParams.put("format", "xml");
		commonParams.put("v", "2.0");
		return commonParams;
	}

	/**
	 * 对参数进行签名
	 * 
	 * @param reqParams
	 * @return
	 */
	private String sign(Map<String, String> reqParams, String signAlgo, String key) {
		String signData = ParameterUtil.getSignData(reqParams);
		logInfo.debug("Unsigned Data:" + signData);
		String sign = "";
		try {
			sign = RSASignature.sign(signData, key, "");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sign;
	}

	/**
	 * 调用alipay.wap.auth.authAndExecute服务的时候需要跳转到支付宝的页面，组装跳转url
	 * 
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	private String getRedirectUrl(Map<String, String> reqParams, String reqUrl) throws Exception {
		String redirectUrl = reqUrl + "?";
		redirectUrl = redirectUrl + ParameterUtil.mapToUrl(reqParams);
		return redirectUrl;
	}

	/**
	 * 调用支付宝开放平台的服务
	 * 
	 * @param reqParams
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	private ResponseResult send(Map<String, String> reqParams, String reqUrl, String secId) throws Exception {
		String response = "";
		String invokeUrl = reqUrl + "?";
		URL serverUrl = new URL(invokeUrl);
		HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.connect();
		String params = ParameterUtil.mapToUrl(reqParams);
		logInfo.debug("Request Token:" + URLDecoder.decode(params, "utf-8"));
		conn.getOutputStream().write(params.getBytes());
		InputStream is = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		response = URLDecoder.decode(buffer.toString(), "utf-8");
		logInfo.debug("Response:" + response);
		conn.disconnect();
		return praseResult(response, secId);
	}

	/**
	 * 解析支付宝返回的结果
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ResponseResult praseResult(String response, String secId) throws Exception {
		// 调用成功
		HashMap<String, String> resMap = new HashMap<String, String>();
		String v = ParameterUtil.getParameter(response, "v");
		String service = ParameterUtil.getParameter(response, "service");
		String partner = ParameterUtil.getParameter(response, "partner");
		String sign = ParameterUtil.getParameter(response, "sign");
		String reqId = ParameterUtil.getParameter(response, "req_id");
		resMap.put("v", v);
		resMap.put("service", service);
		resMap.put("partner", partner);
		resMap.put("sec_id", secId);
		resMap.put("req_id", reqId);
		String businessResult = "";
		ResponseResult result = new ResponseResult();
		logInfo.debug("Token Result:" + response);
		if (response.contains("<err>")) {
			result.setSuccess(false);
			businessResult = ParameterUtil.getParameter(response, "res_error");
			// 转换错误信息
			XMapUtil.register(ErrorCode.class);
			ErrorCode errorCode = (ErrorCode) XMapUtil.load(new ByteArrayInputStream(businessResult.getBytes("UTF-8")));
			result.setErrorMessage(errorCode);
			resMap.put("res_error", ParameterUtil.getParameter(response, "res_error"));
		} else {
			businessResult = ParameterUtil.getParameter(response, "res_data");
			result.setSuccess(true);
			// 对返回的res_data数据先用商户私钥解密
			String resData = RSASignature.decrypt(businessResult, PartnerConfig.RSA_PRIVATE);
			result.setBusinessResult(resData);
			resMap.put("res_data", resData);
		}
		// 获取待签名数据
		String verifyData = ParameterUtil.getSignData(resMap);
		logInfo.debug("verifyData:" + verifyData);
		// 对待签名数据使用支付宝公钥验签名
		boolean verified = RSASignature.doCheck(verifyData, sign, PartnerConfig.RSA_ALIPAY_PUBLIC, "");
		if (!verified) {
			throw new Exception("Verify the signature failure!");
		}
		return result;
	}
	
	/**
	 * 获取订单总金额
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单ID
	 * 
	 * @return 返回订单总金额
	 */
	@Deprecated
	public String getTotalFee(String userId, String orderId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("orderid", orderId);
		String url = Constants.BASE_URL + "getorder/";
		String totalFee = "-1";
		try {
			String data = WebUtil.readContentFromGet(url, map);
			JSONObject obj = JSONObject.fromObject(data);
			String code = obj.getString("code");
			if (Constants.SUCCESS.equals(code)) {
				obj = JSONObject.fromObject(obj.getJSONObject("content"));
				totalFee = obj.getString("amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalFee;
	}
}
