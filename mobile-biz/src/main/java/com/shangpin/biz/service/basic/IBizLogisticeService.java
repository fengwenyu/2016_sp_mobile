package com.shangpin.biz.service.basic;


/**
 * 物流基础接口
 * 
 * @author zghw
 *
 */
public interface IBizLogisticeService {
	/**
	 * 查询订单物流跟踪
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单号
	 * @return
	 * @author zhanghongwei
	 */
	public String fromOrderMoreLogistic(String userId, String orderId,String postArea);

	/**
	 * 查询订单物流跟踪
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单号
	 * @return
	 * @author zhanghongwei
	 
	public ResultObjMapList<Logistics> beOrderMoreLogistic(String userId, String orderId);
	*/
}
