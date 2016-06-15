/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.mobileAolai.common.alipay.callback;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.shangpin.mobileAolai.common.alipay.base.PartnerConfig;
import com.shangpin.mobileAolai.common.alipay.security.RSASignature;
import com.shangpin.mobileAolai.common.alipay.util.ParameterUtil;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.FileUtil;

/**
 * 
 * 支付成功后支付宝同步调用url
 * 
 * @author 3y
 * @version $Id: CallBack.java, v 0.1 2011-8-25 下午05:16:26 3y Exp $
 */
public class CallBack extends HttpServlet {
	private static final long serialVersionUID = -2234271646410251381L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 获得通知签名
		String sign = request.getParameter("sign");
		String result = request.getParameter("result");
		String requestToken = request.getParameter("request_token");
		String outTradeNo = request.getParameter("out_trade_no");
		String tradeNo = request.getParameter("trade_no");
		String totalFee = request.getParameter("total_fee");
		String res;//支付返回页所需参数 1为成功，0为失败
		Map<String, String> resMap = new HashMap<String, String>();
		resMap.put("result", result);
		resMap.put("request_token", requestToken);
		resMap.put("out_trade_no", outTradeNo);
		resMap.put("trade_no", tradeNo);
		String verifyData = ParameterUtil.getSignData(resMap);
		boolean verified = false;
		String channelNo = request.getParameter(
				Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(channelNo)) {
			if (CookieUtil.getCookieByName(request,
					Constants.CHANNEL_PARAM_NAME) != null) {
				channelNo = CookieUtil.getCookieByName(request,
						Constants.CHANNEL_PARAM_NAME).getValue();
			} else {
				channelNo = StringUtils.isEmpty(channelNo) ? Constants.SP_WAP_DEFAULT_CHANNELNO
						: channelNo;
			}
		}
		// 得到应用服务器地址
		//String path = request.getContextPath();
		//String basePath = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getServerPort() + path + "/";
		// 使用支付宝公钥验签名
		try {
			verified = RSASignature.doCheck(verifyData, sign, PartnerConfig.RSA_ALIPAY_PUBLIC, "");
			if (!verified || !result.equals("success")) {
				res="0";
				// 记录访问日志
				FileUtil.addLog(request,
						"/alipay/callBack",
						channelNo,
						"code", "fail",
						"tradeNo",tradeNo);
			} else {
				res="1";
//				response.sendRedirect("http://m.aolai.com");
				// 记录访问日志
				FileUtil.addLog(request, 
						"/alipay/callBack",
						channelNo,
						"code", "success",
						"tradeNo",tradeNo);
			}
			//组装返回参数
			
			String parmresult=res+"-"+outTradeNo+"-"+totalFee;
			response.sendRedirect(Constants.AOLAI_URL+"payokaction!result?parmresult="+parmresult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
