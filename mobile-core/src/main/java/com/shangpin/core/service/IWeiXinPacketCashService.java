package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.WeiXinPacketCash;

/**
 * 现金劵的信息
 * @author tongwenli
 *
 */
public interface IWeiXinPacketCashService {

	public List<WeiXinPacketCash> findCash();
	
	
}
