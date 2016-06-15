package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.UserBehavioralSPIphone;

public interface UserBehavioralSPIphoneService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.UserBehavioralSPIphoneServiceImpl";
	/**
	 * 保存实体
	 * 
	 * @Author liling
	 * @CreatDate 2013-12-0s4
	 * @param userBehavioralSPIphone
	 * @Return
	 */
	void save(UserBehavioralSPIphone userBehavioralSPIphone) throws Exception;
}
