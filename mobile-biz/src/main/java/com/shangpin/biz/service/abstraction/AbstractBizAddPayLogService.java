package com.shangpin.biz.service.abstraction;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizAddPayLogService {
	
	@Autowired 
	CommonService commonService;
	public String fromAddPayLog(String orderId, String payInfo, String payType) {
		String json = commonService.addPayLog(orderId, payInfo, payType);
		return json;
	}
	
	
	public ResultBase baseAddPayLog(String orderId, String payInfo, String payType) {
		String json= fromAddPayLog(orderId, payInfo, payType);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	
}
