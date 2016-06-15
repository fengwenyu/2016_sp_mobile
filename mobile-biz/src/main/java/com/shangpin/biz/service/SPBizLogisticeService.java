package com.shangpin.biz.service;

import com.shangpin.biz.bo.OrderLogistics;
import com.shangpin.biz.bo.Track;

/**
 * @ClassName: LogisticeBizService
 * @Description:物流接口
 * @author liling
 * @date 2014年11月29日
 * @version 1.0
 */
public interface SPBizLogisticeService  {
	/***
	 * 
	 * @Title: getOrderLogisticsInfo
	 * @Description:订单物流
	 * @param orderId
	 *            订单编号
	 * @param userId
	 *            用户id
	 * @param postArea
	 *            海外
	 * @param @return
	 * @return OrderLogistics
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月18日
	 */
	OrderLogistics getOrderLogisticsInfo(String orderId, String userId, String postArea,String isNew);

	/**
	 * 获取物流信息
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Track getNewLogistice(String orderId, String userId);

}
