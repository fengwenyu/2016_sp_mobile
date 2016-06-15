package com.shangpin.base.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.PointBuyService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.product.service.intf.cbwfs.IspPointBuyService;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
@Service
public class PointBuyServiceImpl implements PointBuyService {

	//整点抢购时间轴接口
	//private StringBuilder pointBuyTimesRUL = new StringBuilder(GlobalConstants.BASE_URL_PRODUCT).append("pointBuyTimes");
	//整点抢购下的商品列表
	private StringBuilder pointBuyProductListRUL = new StringBuilder(GlobalConstants.BASE_URL_PRODUCT).append("pointBuyProductList");
	@Autowired
	private IspPointBuyService pointBuyService;
	@Override
	public String pointBuyTimesList() {
		String data = JsonUtil.toJson(pointBuyService.findTimeline());
		return data;
	}
	@Override
	public String showProductList(String pharseId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("pharseId", pharseId);
		return HttpClientUtil.doGet(pointBuyProductListRUL.toString(), params);
	}
}
