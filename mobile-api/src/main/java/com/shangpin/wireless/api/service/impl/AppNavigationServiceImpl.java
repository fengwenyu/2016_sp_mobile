package com.shangpin.wireless.api.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AppNavigationDao;
import com.shangpin.wireless.api.domain.AppNavigation;
import com.shangpin.wireless.api.service.AppNavigationService;


@Service(AppNavigationService.SERVICE_NAME)
public class AppNavigationServiceImpl implements AppNavigationService{
	@Resource(name = AppNavigationDao.DAO_NAME)
	private AppNavigationDao appNavigationDao;

	@Override
	public List<AppNavigation> findAll() throws Exception {
		//  Auto-generated method stub
		return appNavigationDao.findAll();
	}
	
	
}
