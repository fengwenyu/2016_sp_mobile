package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.base.service.ShangPinService;

public abstract class AbstractBizLogisticeService {
	@Autowired
	private ShangPinService shangpinService;

	public String fromOrderMoreLogistic(String userId, String orderId,String postArea,String isNew) {
		String json = shangpinService.findOrderMoreLogistic(userId, orderId,postArea,isNew);
		return json;
	}
/**
 * 
 * @param userId
 * @param orderId
 * @return
 
	public ResultObjMapList<Logistics> beOrderMoreLogistic(String userId, String orderId) {
		String json = fromOrderMoreLogistic(userId, orderId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Logistics> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Logistics>>() {
			});
			return result;
		}
		return null;
	}
	*/
}
