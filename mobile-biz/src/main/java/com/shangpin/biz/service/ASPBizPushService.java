package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.PushInfo;


/**
 * 读取push消息信息
 * @author qinyingchun
 *
 */
public interface ASPBizPushService {
	
	public List<PushInfo> pushInfo(String userId, String pageIndex, String pageSize, String plateForm);

}
