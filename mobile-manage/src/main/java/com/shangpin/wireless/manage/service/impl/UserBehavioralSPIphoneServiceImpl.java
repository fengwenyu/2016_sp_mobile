package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.UserBehavioralSPIphoneDao;
import com.shangpin.wireless.domain.UserBehavioralSPIphone;
import com.shangpin.wireless.manage.service.UserBehavioralSPIphoneService;
@Service(UserBehavioralSPIphoneService.SERVICE_NAME)
public class UserBehavioralSPIphoneServiceImpl implements UserBehavioralSPIphoneService{
	@Resource(name = UserBehavioralSPIphoneDao.DAO_NAME)
	private UserBehavioralSPIphoneDao userBehavioralSPIphoneDao;

	@Override
	public void save(UserBehavioralSPIphone userBehavioralSPIphone) throws Exception {
		//
		userBehavioralSPIphoneDao.save(userBehavioralSPIphone);
	}
}
