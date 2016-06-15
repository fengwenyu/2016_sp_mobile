package com.shangpin.wechat.service;

import java.util.SortedMap;

/**
 * 微信开放平台的service
 * 
 * @author huangxiaoliang
 *
 */
public interface WeChatOpenService {

	/**
	 * 获取token，默认从缓存中取值
	 * 
	 * @return
	 */
	String getToken();

	/**
	 * 提交预支付订单,获取prepayid,如果token失效,重新获取
	 * 
	 * @param token
	 * @param postData
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	String getPrepayid(String token, SortedMap packageParams);
}
