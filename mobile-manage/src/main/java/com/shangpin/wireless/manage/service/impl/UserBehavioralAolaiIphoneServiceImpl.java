package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.UserBehavioralAolaiIphoneDao;
import com.shangpin.wireless.domain.UserBehavioralAolaiIphone;
import com.shangpin.wireless.manage.service.UserBehavioralAolaiIphoneService;
@Service(UserBehavioralAolaiIphoneService.SERVICE_NAME)
public class UserBehavioralAolaiIphoneServiceImpl implements UserBehavioralAolaiIphoneService{
	@Resource(name = UserBehavioralAolaiIphoneDao.DAO_NAME)
	private UserBehavioralAolaiIphoneDao usserBehavioralAolaiIphoneDao;

	@Override
	public void save(UserBehavioralAolaiIphone userBehavioralAolaiIphone) throws Exception {
		usserBehavioralAolaiIphoneDao.save(userBehavioralAolaiIphone);
		
	}
}
