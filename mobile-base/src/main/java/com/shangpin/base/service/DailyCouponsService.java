package com.shangpin.base.service;

import java.util.List;
import java.util.Map;

import com.shangpin.product.model.cbwfs.PromotionTicket;
import com.shangpin.product.model.common.ContentBuilder;

/**
 * 天天抢券频道相关接口
 * @author zkj
 *
 */
public interface DailyCouponsService {

	/**
	 * 天天抢券获取券的当前状态
	 * @param activeCode 激活码，支持批量传入
	 * @param userId
	 * @return
	 */
	String dailyCouponsGet(String activeCode,String userId);
	
	/**
	 * 券列表查询，（product服务）
	 * @param order 券面值金额排序
	 * @param type 券类型
	 * @param pageIndex 页码（从0开始）
	 * @param pageSize 一页显示多少条
	 * @return
	 */
	ContentBuilder<Map<String, List<PromotionTicket>>> promotionTickets(String order,String type,String pageIndex,String pageSize);
	
	
}
