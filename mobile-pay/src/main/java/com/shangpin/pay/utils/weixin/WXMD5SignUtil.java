package com.shangpin.pay.utils.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.pay.utils.common.MD5Util;


public class WXMD5SignUtil {
	public static Logger logger = LoggerFactory.getLogger(WXMD5SignUtil.class);
	public static String Sign(String content, String key)
			throws SDKRuntimeException, UnsupportedEncodingException {
		String signStr = "";

		if ("".equals(key)) {
			throw new SDKRuntimeException("财付通签名key不能为空！");
		}
		if ("".equals(content)) {
			throw new SDKRuntimeException("财付通签名内容不能为空");
		}
		signStr = content + "&key=" + key;
		signStr=URLDecoder.decode(signStr, "UTF-8");
		logger.info("WXPay jsApiPay:signStr={}",signStr );
		return MD5Util.MD5(signStr).toUpperCase();

	}
	public static boolean VerifySignature(String content, String sign, String md5Key) {
		String signStr = content + "&key=" + md5Key;
		String calculateSign = MD5Util.MD5(signStr).toUpperCase();
		String tenpaySign = sign.toUpperCase();
		return (calculateSign.equals(tenpaySign));
	}
	public static void main(String[] args) throws SDKRuntimeException, UnsupportedEncodingException {
	System.out.println(Sign("bank_type=WX&body=Wa+Obi%E5%A5%B3%E5%A3%AB%E7%BC%96%E8%85%BE%E7%BA%B9%E7%B3%BB%E5%88%97%E5%A4%B4%E5%B1%82%E7%89%9B%E7%9A%AE%E7%99%BD%E8%89%B2%E9%95%BF%E9%92%B1%E5%A4%B9&fee_type=1&input_charset=UTF-8&notify_url=http%3A%2F%2Fm.shangpin.com%2Forder%2Fweixin%2Fpay%2Fnotify&out_trade_no=20150513625260&partner=1217974201&spbill_create_ip=111.204.231.218&time_expire=20150513210445&time_start=20150513200445&total_fee=59900","030d62e64075b703a28592ce672220da"));	 
	}
}
