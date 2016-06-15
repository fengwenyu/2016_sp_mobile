package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.UserBehavioralAolaiAndriodDao;
import com.shangpin.wireless.domain.UserBehavioralAolaiAndriod;
import com.shangpin.wireless.manage.service.UserBehavioralAolaiAndriodService;
@Service(UserBehavioralAolaiAndriodService.SERVICE_NAME)
			
public class UserBehavioralAolaiAndriodServiceImpl implements UserBehavioralAolaiAndriodService{
	@Resource(name = UserBehavioralAolaiAndriodDao.DAO_NAME)
	private UserBehavioralAolaiAndriodDao userBehavioralAolaiAndriodDao;

	@Override
	public void save(UserBehavioralAolaiAndriod userBehavioralAolaiAndriod) throws Exception {
		//
		userBehavioralAolaiAndriodDao.save(userBehavioralAolaiAndriod);
	}
}
