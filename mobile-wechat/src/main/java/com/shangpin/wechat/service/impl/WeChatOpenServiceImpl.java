package com.shangpin.wechat.service.impl;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shangpin.utils.JSONUtils;
import com.shangpin.wechat.bo.base.WeChatToken;
import com.shangpin.wechat.constants.OpenPlatformConstants;
import com.shangpin.wechat.service.WeChatOpenService;
import com.shangpin.wechat.utils.common.CacheUtil;
import com.shangpin.wechat.utils.openplatform.TenpayHttpClient;

@Service
public class WeChatOpenServiceImpl implements WeChatOpenService {

	public static final Logger logger = LoggerFactory.getLogger(WeChatOpenServiceImpl.class);

	static Map<String, String> tokenParams = null;

	static {
		tokenParams = new LinkedHashMap<String, String>();
		tokenParams.put("grant_type", OpenPlatformConstants.GRANT_TYPE);
		tokenParams.put("appid", OpenPlatformConstants.OPEN_APP_ID);
		tokenParams.put("secret", OpenPlatformConstants.OPEN_APP_SECRET);

	}

	@Override
	public String getToken() {

		String result = CacheUtil.findData(OpenPlatformConstants.OPEN_PLATFORM_CACHE, "getToken", tokenParams, OpenPlatformConstants.OPEN_TOKEN_URL, "GET");
		try {
			WeChatToken token = JSONUtils.json2pojo(result, WeChatToken.class);
			return token.getAccess_token();
		} catch (Exception e) {
			logger.error("WeChatPay getToken:e={}" + e);
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getPrepayid(String token, SortedMap packageParams) {
		Gson gson = new Gson();
		String prepayid = "";

		String postData = "{";
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (k != "appkey") {
				if (postData.length() > 1)
					postData += ",";
				postData += "\"" + k + "\":\"" + v + "\"";
			}
		}
		postData += "}";

		String requestUrl = OpenPlatformConstants.OPEN_GATE_URL + "?access_token=" + token;
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		if (httpClient.callHttpPost(requestUrl, postData)) {
			resContent = httpClient.getResContent();
			Map<String, String> map = gson.fromJson(resContent, new TypeToken<Map<String, String>>() {
			}.getType());
			if ("0".equals(map.get("errcode"))) {
				prepayid = map.get("prepayid");
			} else {
				logger.info("WeChatPay getPrepayid error , errmsg={}" + map.get("errmsg"));
			}
			logger.debug("WeChatPay getPrepayid, requestUrl={}, postData={}, resContent={}", requestUrl, postData, resContent);
		}

		return prepayid;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "pre.shangpin.com/mshangpin/weixin/send/text";
		String result = URLEncoder.encode(url, "utf-8");
		System.out.println("result:" + result);
	}

}
