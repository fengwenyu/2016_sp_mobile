package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.shangpin.wireless.dao.UserBehavioralSPAndriodDao;
import com.shangpin.wireless.domain.UserBehavioralSPAndriod;
import com.shangpin.wireless.manage.service.UserBehavioralSPAndriodService;

@Service(UserBehavioralSPAndriodService.SERVICE_NAME)
			 
public class UserBehavioralSPAndriodServiceImpl implements UserBehavioralSPAndriodService{
	
	@Resource(name = UserBehavioralSPAndriodDao.DAO_NAME)
	private UserBehavioralSPAndriodDao userBehavioralSPAnriodDao;

	@Override
	public void save(UserBehavioralSPAndriod userBehavioralSPAndriod) throws Exception {
		//
		userBehavioralSPAnriodDao.save(userBehavioralSPAndriod);
	}
}
