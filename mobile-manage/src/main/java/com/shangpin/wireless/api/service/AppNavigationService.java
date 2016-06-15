package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.AppNavigation;

public interface AppNavigationService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AppNavigationServiceImpl";

	public void edit(AppNavigation model) throws Exception;

	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	public Integer executeSqlCount(String queryListSql) throws Exception;

	public void delete(Long id) throws Exception;

	public AppNavigation findById(Long id) throws Exception;

	public String getSameTabId(AppNavigation model) throws Exception;
}
