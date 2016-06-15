package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.UserBehavioralSPAndriod;


public interface UserBehavioralSPAndriodService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.UserBehavioralSPAndriodServiceImpl";
	/**
	 * 保存实体
	 * 
	 * @Author liling
	 * @CreatDate 2013-12-0s4
	 * @param userBehavioralSPAndriod
	 * @Return
	 */
	void save(UserBehavioralSPAndriod userBehavioralSPAndriod) throws Exception;
}
