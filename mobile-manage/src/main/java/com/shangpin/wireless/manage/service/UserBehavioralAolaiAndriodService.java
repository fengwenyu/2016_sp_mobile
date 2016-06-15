package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.UserBehavioralAolaiAndriod;


public interface UserBehavioralAolaiAndriodService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.UserBehavioralAolaiAndriodServiceImpl";
	/**
	 * 保存实体
	 * 
	 * @Author liling
	 * @CreatDate 2013-12-0s4
	 * @param userBehavioralAolaiAnriod
	 * @Return
	 */
	void save(UserBehavioralAolaiAndriod userBehavioralAolaiAndriod) throws Exception;
}
