package com.shangpin.biz.service;

import java.util.Map;

public interface SPBizAddPayLogService {
	public Map<String, Object> addPayLog(String orderId,String payInfo,String payType);
}
