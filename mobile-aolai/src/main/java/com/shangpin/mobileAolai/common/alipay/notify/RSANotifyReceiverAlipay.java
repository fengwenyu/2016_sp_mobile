/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.mobileAolai.common.alipay.notify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.mobileAolai.common.alipay.base.PartnerConfig;
import com.shangpin.mobileAolai.common.alipay.security.RSASignature;
import com.shangpin.mobileAolai.common.alipay.util.StringUtil;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;

/**
 * 接收通知并处理
 * 
 */
public class RSANotifyReceiverAlipay extends HttpServlet {
	/**
	 * http://180.184.16.71:8080/api/
	 */
	private static final long serialVersionUID = -7743557078459452545L;
	protected final Log log = LogFactory.getLog(RSANotifyReceiverAlipay.class.getSimpleName());

	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获得通知参数
		Map map = request.getParameterMap();
		// 获得通知签名
		String sign = (String) ((Object[]) map.get("sign"))[0];
		log.debug("notify sign:" + sign);
		// 获得待验签名的数据
		String verifyData = getVerifyData(map);
		log.debug("notify verifyData:" + verifyData);
		boolean verified = false;
		// 使用支付宝公钥验签名
		try {
			verified = RSASignature.doCheck(verifyData, sign, PartnerConfig.RSA_ALIPAY_PUBLIC, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		// 验证签名通过
		if (verified) {
			// 当交易状态成功，处理业务逻辑成功。回写success
			// 交易成功后，调用接口，更新订单状态
			final String tradestatus = StringUtil.getTagContent(verifyData, "<trade_status>", "</trade_status>");
			if ("TRADE_FINISHED".endsWith(tradestatus) || "TRADE_SUCCESS".endsWith(tradestatus)) {
				final String orderno = StringUtil.getTagContent(verifyData, "<out_trade_no>", "</out_trade_no>");
				final String payTypeId = "20";
				final String payTypeChildId = "37";
//				Map<String, String> reqmap = new HashMap<String, String>();
//				reqmap.put("orderno", orderno);
//				reqmap.put("paytypeId", payTypeId);
//				reqmap.put("paytypechildId", payTypeChildId);
//				String url = Constants.BASE_URL + "payconfirmpayment/";
				HashMap<String, String> reqmap = new HashMap<String, String>();
				reqmap.put("mainorderNo", orderno);
				reqmap.put("payTypeId", payTypeId);
				reqmap.put("childPayTypeId", payTypeChildId);
				reqmap.put("orderAmount", Constants.PAY_AMOUNT);
				String url = Constants.BASE_NEWAPI_URL_SP + "order/UpdateOrderStatus";
				try {
					String data = WebUtil.readContentFromGet(url, reqmap);
					JSONObject content = JSONObject.fromObject(data);
					final String code = content.getString("code");
					if ("0".equals(code)) {
    					// 记录访问日志
    					FileUtil.addLog(request, "/alipay/notifyReceiver", "", "orderid", orderno, "payid", payTypeId, "paychildid", payTypeChildId, "success", "1");
    				} else {
    					FileUtil.addLog(request, "/alipay/notifyReceiver", "",  "orderid", orderno, "payid", payTypeId, "paychildid", payTypeChildId, "success", "0");
    					log.warn("Alipay failed orderid = " + orderno + " (" + code + ")(" + content.getString("msg") + ")");
    				}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("RSANotifyReceiverAlipay：" + e);
					throw new IOException("Order data synchronization error");
				}
			}
			out.print("success");
		} else {
			log.debug("Alipay system notify verify signature failed！");
			out.print("fail");
		}
		out.close();
		log.debug("RSANotifyReceiverAlipay finish!");
	}

	/**
	 * 获得验签名的数据
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes" })
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
		log.debug("Notified params：" + "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data=" + notify_data);
		return "service=" + service + "&v=" + v + "&sec_id=" + sec_id + "&notify_data=" + notify_data;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
}
