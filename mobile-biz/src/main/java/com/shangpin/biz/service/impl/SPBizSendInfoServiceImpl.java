package com.shangpin.biz.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.CustomerService;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizSendInfoServiceImpl implements SPBizSendInfoService{
	
	private static Logger logger = LoggerFactory.getLogger(SPBizSendInfoServiceImpl.class);

	@Autowired
	private CustomerService customerService;
	@Override
	public boolean sendInfo(String userid, String phone, String msgTempl) {
		try {
			String json = customerService.sendVerifyCode(userid, phone, msgTempl);
			logger.debug("base interface sendMsg return data:" + json);
			ResultBase base = JsonUtil.fromJson(json, ResultBase.class);
			if(Constants.SUCCESS.equals(base.getCode())){
				return true;
			}
		} catch (Exception e) {
			logger.error("base interface sendMsg return data error!");
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unused")
    @Override
	public Map<String,Object> sendPhoneCode(String userid, String phone, String msgTempl) {
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String json = customerService.sendVerifyCode(userid, phone, msgTempl);
			logger.debug("base interface sendMsg return data:" + json);
			ResultBase base = JsonUtil.fromJson(json, ResultBase.class);
			if(Constants.SUCCESS.equals(base.getCode())){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode;
					rootNode = mapper.readTree(json);
					JsonNode content = rootNode.path("content");
				String verifycode=content.path("verifycode").asText();
				map.put("code", "0");
				//map.put("verifycode", verifycode);
				return map;
			}else{
				map.put("code", "1");
				map.put("msg", base.getMsg());
				return map;
			}
		} catch (Exception e) {
			logger.error("base interface sendMsg return data error!");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public ResultBase verifyPhoneCode(String userid, String phone,
			String verifycode) {
		try {
			String json = customerService.verifyPhoneCode(userid, phone, verifycode);
			logger.debug("base interface verify phone code return data:" + json);
			ResultBase base = JsonUtil.fromJson(json, ResultBase.class);
			return base;
		} catch (Exception e) {
			logger.error("base interface verify phone code return data occur error!");
			e.printStackTrace();
		}
		return null;
	}

}
