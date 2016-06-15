package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.WeixinPayOrder;



public interface WeixinPayOrderService {
	
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.WeixinPayOrderServiceImpl";
	public void save(WeixinPayOrder weixinPayOrder);
	public WeixinPayOrder findByTransId(String string);
	public WeixinPayOrder findByOrderId(String string);
}
