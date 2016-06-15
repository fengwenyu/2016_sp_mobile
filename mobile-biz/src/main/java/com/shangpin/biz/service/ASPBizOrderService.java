package com.shangpin.biz.service;

import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.base.vo.SubmitOrder;
import com.shangpin.biz.service.basic.IBizOrderService;

import java.util.Map;

/**
 * 尚品客户端订单的相关Service
 * @author yls
 *
 */
public interface ASPBizOrderService extends IBizOrderService {

	/**
	 * 获取某一订单详细信息（尚品）
	 * 
	 * @param userId
	 *            用户ID：用户的唯一标识
	 * @param orderId
	 *            订单ID
	 * @return
	 * 
	 * @author zhongchao
	 */
	public OrderItem findOrderDetail(String userId, String mainOrderNum);


	/**
	 * 2.9.12 订单提交(尚品)
	 *
	 * @param map
	 * @return
	 */
	ResultObjOne<SubmitOrder> submitOrderV2(Map<String,String> map);
}
