package com.shangpin.biz.service.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.biz.service.ALBizWeixinService;
import com.shangpin.utils.HttpClientUtil;

@Service
public class ALBizWeixinServiceImpl implements ALBizWeixinService {
	private static final Logger logger = LoggerFactory.getLogger(SPBizOrderServiceImpl.class);

	@Override
	public String getWeixinId(String url,String appId, String secret, String code, String grantType) {
		HashMap<String, String> params = new HashMap<String, String>();

		try {
			params.put("appid", appId);
			params.put("secret", secret);
			params.put("code", code);
			params.put("grant_type", grantType);
			String data = HttpClientUtil.doGet(url, params);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode readTree;
			readTree = mapper.readTree(data);
			if (readTree.has("openid")) {
				return readTree.path("openid").asText();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用微信接口返回数据错误" + e);
		}

		return null;
	}
}
