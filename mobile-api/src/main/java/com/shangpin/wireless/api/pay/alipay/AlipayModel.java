package com.shangpin.wireless.api.pay.alipay;

import com.shangpin.wireless.api.domain.Constants;


public class AlipayModel {

	private String data;
	
	public String getData () {return data;}
	/**
	 * 支付宝安全支付 根据原始数据，获取加密后的字符串
	 * 
	 * @param orderid
	 *            订单编号
	 * @param name
	 *            商品名称
	 * @param descrition
	 *            商品描述
	 * @param totalFee
	 *            订单金额，以元为单位
	 * @param test
	 *            是否测试环境
	 * @return
	 */
	public AlipayModel(String orderid, String name, String descrition, String totalFee) throws Exception {

		final String partner = PartnerConfigAlipay.PARTNER;
		final String seller = PartnerConfigAlipay.SELLER;
		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append("partner=\"");
		digestBuff.append(partner);
		digestBuff.append("\"&seller=\"");
		digestBuff.append(seller);
		digestBuff.append("\"&out_trade_no=\"");
		digestBuff.append(orderid);
		digestBuff.append("\"&subject=\"");
		digestBuff.append(name);
		digestBuff.append("\"&body=\"");
		digestBuff.append(descrition);
		digestBuff.append("\"&total_fee=\"");
		digestBuff.append(totalFee);
		digestBuff.append("\"&notify_url=\"");
		String notifyUrl;
		notifyUrl = Constants.ALIPAY_URL +"alipay_notify";
//		digestBuff.append(java.net.URLEncoder.encode(notifyUrl, "UTF-8")); 不做URLEncode
		digestBuff.append(notifyUrl);
		digestBuff.append("\"");
		final String digest = digestBuff.toString();
		String signature = RSASignatureAlipay.sign(digest);
		StringBuffer strBuff = new StringBuffer(digest);
		strBuff.append("&sign_type=\"RSA\"&sign=\"");
		strBuff.append(java.net.URLEncoder.encode(signature, "UTF-8"));
		strBuff.append("\"");

		data = strBuff.toString();
	}
	
	public AlipayModel(String orderid, String name, String descrition, String totalFee, int leftsecond) throws Exception {

		final String partner = PartnerConfigAlipay.PARTNER;
		final String seller = PartnerConfigAlipay.SELLER;
		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append("partner=\"");
		digestBuff.append(partner);
		digestBuff.append("\"&seller_id=\"");
		digestBuff.append(seller);
		digestBuff.append("\"&out_trade_no=\"");
		digestBuff.append(orderid);
		digestBuff.append("\"&subject=\"");
		digestBuff.append(name);
		digestBuff.append("\"&body=\"");
		digestBuff.append(descrition);
		digestBuff.append("\"&total_fee=\"");
		digestBuff.append(totalFee);
		digestBuff.append("\"&notify_url=\"");
		String notifyUrl;
		notifyUrl = Constants.ALIPAY_URL +"alipay_notify";
		digestBuff.append(notifyUrl);
		//	极简收银台
		digestBuff.append("\"&service=\"mobile.securitypay.pay");
		digestBuff.append("\"&payment_type=\"1");
		digestBuff.append("\"&_input_charset=\"utf-8");
		digestBuff.append("\"&it_b_pay=\"");
		digestBuff.append(leftsecond/60 + "m");
		digestBuff.append("\"&show_url=\"m.shangpin.com");
		digestBuff.append("\"");
		final String digest = digestBuff.toString();
		String signature = RSASignatureAlipay.sign(digest);
		StringBuffer strBuff = new StringBuffer(digest);
		strBuff.append("&sign_type=\"RSA\"&sign=\"");
		strBuff.append(java.net.URLEncoder.encode(signature, "UTF-8"));
		strBuff.append("\"");

		data = strBuff.toString();
	}
	
	public AlipayModel(String orderid, String name, String descrition, String totalFee, String token) throws Exception {

//		final HashMap<String, String> map = new HashMap<String, String>();
//		map.put("partner", PartnerConfigAlipay.PARTNER);
//		map.put("seller", PartnerConfigAlipay.SELLER);
//		map.put("out_trade_no", orderid);
//		map.put("notify_url", test?"http://180.184.16.75:80/api/alipaywallet_notify":"http://api.m.shangpin.com/api/alipaywallet_notify");
//		map.put("subject", name);
//		map.put("body", descrition);
//		map.put("total_fee", totalFee);
//		if (StringUtil.isNotEmpty(token)) {
//			map.put("extern_token", token);
//		}
//		final String digest = StringUtil.buildParams(map, "\"", "\"", true, false);
//		String signature = RSASignatureAlipay.sign(digest);System.out.println(digest);
//		map.put("sign_type", "RSA");
//		map.put("sign", signature);
//		data = StringUtil.buildParams(map, "\"", "\"", false, true);
		
		final String partner = PartnerConfigAlipay.PARTNER;
		final String seller = PartnerConfigAlipay.SELLER;
		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append("partner=\"");
		digestBuff.append(partner);
		digestBuff.append("\"&seller=\"");
		digestBuff.append(seller);
		digestBuff.append("\"&out_trade_no=\"");
		digestBuff.append(orderid);
		digestBuff.append("\"&subject=\"");
		digestBuff.append(name);
		digestBuff.append("\"&body=\"");
		digestBuff.append(descrition);
		digestBuff.append("\"&total_fee=\"");
		digestBuff.append(totalFee);
		digestBuff.append("\"&notify_url=\"");
		String notifyUrl;
		notifyUrl = Constants.ALIPAY_URL +"alipaywallet_notify";
		digestBuff.append(notifyUrl);
		digestBuff.append("\"&extern_token=\"");
		digestBuff.append(token);
		digestBuff.append("\"");
		final String digest = digestBuff.toString();
		String signature = RSASignatureAlipay.sign(digest);
		StringBuffer strBuff = new StringBuffer(digest);
		strBuff.append("&sign_type=\"RSA\"&sign=\"");
		strBuff.append(java.net.URLEncoder.encode(signature, "UTF-8"));
		strBuff.append("\"");

		data = strBuff.toString();
	}
}
