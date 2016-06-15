package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import com.shangpin.mobileShangpin.base.model.FindManage;

public interface ActivityManageService {
	/**
	 * 根据时间查询发现信息
	 * 
	 * @param showType 展示类型 0代表轮播位，1代表最新活动
	 * @return ActivityManage 发现信息
	 */
	public List<FindManage> findByActivityManage(String date,String showType) throws Exception;

}
