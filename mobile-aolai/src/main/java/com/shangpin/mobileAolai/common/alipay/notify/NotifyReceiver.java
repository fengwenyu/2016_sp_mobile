/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.mobileAolai.common.alipay.notify;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.mobileAolai.common.alipay.base.PartnerConfig;
import com.shangpin.mobileAolai.common.alipay.security.RSASignature;

/**
 * 接收通知并处理 支付宝异步调用此url
 * 
 * @author 3y
 * @version $Id: NotifyReceiver.java, v 0.1 2011-8-25 下午03:11:58 3y Exp $
 */
public class NotifyReceiver extends HttpServlet {
	private static final long serialVersionUID = 7216412938937049671L;
	private static  Log logInfo = LogFactory.getLog(NotifyReceiver.class);
	
	public String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String key : params.keySet()) {
			String value = (String) params.get(key);
			if (isFirst) {
				sb.append(key + "=" + URLEncoder.encode(value, "utf-8"));
				isFirst = false;
			} else {
				if (value != null) {
					sb.append("&" + key + "=" + URLEncoder.encode(value, "utf-8"));
				} else {
					sb.append("&" + key + "=");
				}
			}
		}
		return sb.toString();
	}

	@SuppressWarnings({"rawtypes" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获得通知参数
		Map map = request.getParameterMap();
		Iterator it = map.entrySet().iterator();
		logInfo.debug("Received parameters:");
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			logInfo.debug(m.getKey() + "=" + ((String[]) m.getValue())[0]);
		}
		PrintWriter out = response.getWriter();
		out.print("success");
		out.close();
		// //获得通知签名
		// String sign = (String) ((Object[]) map.get("sign"))[0];
		// System.out.println("notify sign:"+sign);
		// //获得待验签名的数据
		// String verifyData = getVerifyData(map);
		// System.out.println("notify verifyData:"+verifyData);
		// boolean verified = false;
		// //使用支付宝公钥验签名
		// try {
		// verified=RSASignature.doCheck(verifyData, sign, PartnerConfig.RSA_ALIPAY_PUBLIC,"");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// PrintWriter out = response.getWriter();
		// //验证签名通过
		// if (verified) {
		// //根据交易状态处理业务逻辑
		//
		// //当交易状态成功，处理业务逻辑成功。回写success
		// System.out.println("success");
		// out.print("success");
		// } else {
		// System.out.println("接收支付宝系统通知验证签名失败，请检查！");
		// out.print("fail");
		// }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	/**
	 * 获得验签名的数据
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private String getVerifyData(Map map) {
		String service = (String) ((Object[]) map.get("service"))[0];
		String v = (String) ((Object[]) map.get("v"))[0];
		String sec_id = (String) ((Object[]) map.get("sec_id"))[0];
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		try {
			// 对返回的notify_data数据用商户私钥解密
			notify_data = RSASignature.decrypt(notify_data, PartnerConfig.RSA_PRIVATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logInfo.debug("Notified params：" + "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data=" + notify_data);
		return "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data=" + notify_data;
	}
}
