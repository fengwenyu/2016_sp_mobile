package com.shangpin.base.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;

/** 
 * ClassName:FreebieService <br/> 
 * 给520 买赠 临时写的service
 * Date:     2016年5月10日 <br/> 
 * @author   fengwenyu
 * @since    JDK 7
 */
@Service
public class FreebieBaseService {
	// 确认订单信息URL
	private StringBuffer orderFreebieDeatilURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("order/FullGiftActivitiesOrder");
	// check用户下是否可参与520活动的数据
	private StringBuffer userAllFreebiesURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("order/HasFullGiftActivitiesOrder");
	// check用户下的参与520活动的数据列表
	private StringBuffer userFreebieProductListURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("order/FullGiftActivitiesOrderOrderList");

	/**
	 * 根据orderId 查询用户订单明细 
	 * @param userId 用户id
	 * @param orderId 主订单id
	 */
	public String getOrderDeatil(String userId, String orderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		return HttpClientUtil.doGet(orderFreebieDeatilURL.toString(), params);
	}
	
	/**
	 * 查询用户下是否有可参与520买赠活动的商品
	 * @param userId
	 * @return
	 */
	public String getUserAllFreebies(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		return HttpClientUtil.doGet(userAllFreebiesURL.toString(), params);
	}
	
	/**
	 * 查询用户下的所有可参与活动的商品列表
	 * @param userId
	 * @return
	 */
	public String getUserFreebieProductList(String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		return HttpClientUtil.doGet(userFreebieProductListURL.toString(), params);
	}
	
}
