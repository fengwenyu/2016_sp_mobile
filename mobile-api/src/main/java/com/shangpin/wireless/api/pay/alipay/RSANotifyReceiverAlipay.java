/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2008 All Rights Reserved.
 */
package com.shangpin.wireless.api.pay.alipay;

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

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 接收通知并处理
 * 
 */
public class RSANotifyReceiverAlipay extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(RSANotifyReceiverAlipay.class.getSimpleName());

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
//		("Alipay通知!");
		log.debug("RSANotifyReceiverAlipay : AlipayAdvice");
		String orderno = null;
		PrintWriter out = response.getWriter();
		// 获得通知参数
		Map map = request.getParameterMap();
		// 获得通知签名
		boolean verified = false;
		String sign = (String) ((Object[]) map.get("sign"))[0];
		// 获得待验签名的数据
		String verifyData;
		Object notify_data = map.get("notify_data");
		Object total_fee = null;
		for (Object key : map.keySet().toArray()) {
			if (key.equals("total_fee")) {
				Object[] oo = (Object[]) map.get(key);
				total_fee = oo[0];
			}
		}

		if (notify_data != null) {
			verifyData = (String) ((Object[]) notify_data)[0];
			verifyData = "notify_data=" + verifyData;
			FileUtil.addLog(request, "RSANotifyReceiverAlipay", "xml verifyData",verifyData);
			// 使用支付宝公钥验签名
			try {
				verified = RSASignatureAlipay.doCheck(verifyData, sign, PartnerConfigAlipay.RSA_ALIPAY_PUBLIC);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 验证签名通过
			if (verified) {
				// 根据交易状态处理业务逻辑
				// 当交易状态成功，处理业务逻辑成功。回写success
				// String verifyData =
				// "notify_data=<notify><partner>2088101954925026</partner><discount>0.00</discount><payment_type>1</payment_type><subject>订单号：2012092505139</subject><trade_no>2012092564594636</trade_no><buyer_email>qiufengfeilu@hotmail.com</buyer_email><gmt_create>2012-09-25 16:24:14</gmt_create><quantity>1</quantity><out_trade_no>2012092505139</out_trade_no><seller_id>2088101954925026</seller_id><trade_status>TRADE_FINISHED</trade_status><is_total_fee_adjust>N</is_total_fee_adjust><total_fee>1.00</total_fee><gmt_payment>2012-09-25 16:24:16</gmt_payment><seller_email>zhifubao@vansky.cn</seller_email><gmt_close>2012-09-25 16:24:16</gmt_close><price>1.00</price><buyer_id>2088002912023364</buyer_id><use_coupon>N</use_coupon></notify>";
				// 交易成功后，调用接口，更新订单状态
				final String tradestatus = StringUtil.getTagContent(verifyData, "<trade_status>", "</trade_status>");
				if ("TRADE_FINISHED".equals(tradestatus) || "TRADE_SUCCESS".equals(tradestatus)) {
					orderno = StringUtil.getTagContent(verifyData, "<out_trade_no>", "</out_trade_no>");
				} else {
//					记录访问日志("支付宝系统非支付成功通知：tradestatus = " + tradestatus);
					FileUtil.addLog(request, "alipay_notify","channelNo",channelNo,
							"tradestatus",tradestatus,
							"code","fail");
					out.print("fail");
				}
			} else {
//				("接收支付宝系统通知验证签名失败，请检查！");
				FileUtil.addLog(request, "alipay_notify","channelNo",channelNo,
						"code","fail");
				out.print("fail");
			}
		} else {
			HashMap<String, String> args = new HashMap<String, String>();
			for (Object key : map.keySet().toArray()) {
				if ("sign_type".equals(key) || "sign".equals(key))continue;
				args.put((String) key, (String) ((Object[]) map.get(key))[0]);
			}
			verifyData = StringUtil.buildParams(args, true, false);
			FileUtil.addLog(request, "RSANotifyReceiverAlipay", "string verifyData",verifyData);
			// 使用支付宝公钥验签名
			try {
				verified = RSASignatureAlipay.doCheck(verifyData, sign, PartnerConfigAlipay.RSA_ALIPAY_PUBLIC_SIMPLEST);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("RSANotifyReceiverAlipay :"+e);
			}
			// 验证签名通过
			if (verified) {
				final String tradestatus = args.get("trade_status");
				if ("TRADE_FINISHED".equals(tradestatus) || "TRADE_SUCCESS".equals(tradestatus)) {
					orderno = args.get("out_trade_no");
				} else {
//					记录访问日志("支付宝系统极简收银台非支付成功通知：tradestatus = " + tradestatus);
					FileUtil.addLog(request, "alipay_notify","channelNo",channelNo,
							"tradestatus", tradestatus,
							"code", "fail");
					out.print("fail");
				}
			} else {
//				("接收支付宝系统极简收银台通知验证签名失败，请检查！");
				log.debug("RSANotifyReceiverAlipay : alipay_notify fail");
				out.print("fail");
			}
		}
		// 验证签名通过
		if (orderno != null) {
			// 根据交易状态处理业务逻辑
			// 当交易状态成功，处理业务逻辑成功。回写success
			final String payTypeId = "20";
			final String payTypeChildId = "38";
			HashMap<String, String> reqmap = new HashMap<String, String>();
			reqmap.put("mainorderNo", orderno);
			reqmap.put("payTypeId", payTypeId);
			reqmap.put("childPayTypeId", payTypeChildId);
			reqmap.put("orderAmount", Constants.PAY_AMOUNT);
			String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
			try {
				log.debug("RSANotifyReceiverAlipay::payconfirmpayment " + Thread.currentThread().getName());
				String data = WebUtil.readContentFromGet(url, reqmap);
				JSONObject content = JSONObject.fromObject(data);
				final String code = content.getString("code");
				if ("0".equals(code)) {
					log.debug("Alipay success orderid = " + orderno + "; " + Thread.currentThread().getName());
					FileUtil.addLog(request, "alipay_notify","channelNo", channelNo,
							"orderid",orderno,
							"code", "success",
							"currentThreadName",Thread.currentThread().getName());
				} else {
//					加入日志记录
					FileUtil.addLog(request, "alipay_notify","channelNo", channelNo,
							"orderid", orderno, 
							"payid", payTypeId, 
							"paychildid", payTypeChildId, 
							"code", "fail", 
							"msg", content.getString("msg"));
					log.warn("Alipay failed orderid = " + orderno + " (" + code + ")(" + content.getString("msg") + ")" + Thread.currentThread().getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				FileUtil.addLog(request, "alipay_notify", "channelNo",channelNo,
						"orderid", orderno, 
						"payid", payTypeId, 
						"paychildid", payTypeChildId, 
						"code", "fail");
				throw new IOException("订单数据同步出错");
			}
			out.print("success");
		}
		out.close();
		log.debug("RSANotifyReceiverAlipay : Alipay finish");
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
	@SuppressWarnings("unchecked")
	private String getVerifyData(Map map) {
		String notify_data = (String) ((Object[]) map.get("notify_data"))[0];
		return "notify_data=" + notify_data;
	}
}
