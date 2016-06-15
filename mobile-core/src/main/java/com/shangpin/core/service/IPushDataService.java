package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.PushData;


/**
 * 需要推送的设备服务（token）接口
 * @author qinyingchun
 *
 */
public interface IPushDataService {

	/**
	 * 根据token值查询token（设备）集合
	 * @param token
	 * @return
	 */
	public List<PushData> findByToken(String token);

}
