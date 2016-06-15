package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import com.shangpin.mobileShangpin.platform.vo.LogisticeVO;

/**
 * 物流逻辑接口，用于获取物流信息相关操作
 * 
 * @author wangfeng
 * @date:2013-7-29
 */
public interface LogisticeService {

	public LogisticeVO getNewLogistice(String orderId,String userId) throws Exception;
	public List<LogisticeVO> getLogisticeList(String orderId,String userId,String ticketno) throws Exception;
	public List<LogisticeVO> getLogisticeOrderList(String userId,String pageIndex) throws Exception;
	public String getOrderLogistic(String userId, String orderId);
}
