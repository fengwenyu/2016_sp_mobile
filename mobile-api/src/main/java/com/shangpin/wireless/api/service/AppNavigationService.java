package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.AppNavigation;



public interface AppNavigationService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AppNavigationServiceImpl";

	List<AppNavigation> findAll() throws Exception;

}
