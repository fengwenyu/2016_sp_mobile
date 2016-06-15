package com.shangpin.wireless.api.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.WeixinPayOrderDAO;
import com.shangpin.wireless.api.domain.WeixinPayOrder;
import com.shangpin.wireless.api.service.WeixinPayOrderService;



@Service(WeixinPayOrderService.SERVICE_NAME)
public class WeixinPayOrderServiceImpl  implements WeixinPayOrderService{
	
	@Resource(name = WeixinPayOrderDAO.DAO_NAME)
	private WeixinPayOrderDAO weixinPayOrderDAO;
	
	@Override
	public void save(WeixinPayOrder weixinPayOrder) {
		//  Auto-generated method stub
		
	}

	@Override
	public WeixinPayOrder findByTransId(String transId) {
		WeixinPayOrder weixinPayOrder=new WeixinPayOrder();
		weixinPayOrder=weixinPayOrderDAO.findByTransId(transId);
		return weixinPayOrder;
	}

	@Override
	public WeixinPayOrder findByOrderId(String orderId) {
		WeixinPayOrder weixinPayOrder=new WeixinPayOrder();
		weixinPayOrder=weixinPayOrderDAO.findByOrderId(orderId);
		return weixinPayOrder;
		
	}
	
}
