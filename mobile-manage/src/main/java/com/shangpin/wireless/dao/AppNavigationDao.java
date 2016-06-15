package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.AppNavigation;

public interface AppNavigationDao  extends ApiBaseDao<AppNavigation>{
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.AppNavigationDaoImpl";

	String findMaxSort();

	String getSameTabId(String tabId,Long id);

	String getSameTabId(String tabId);
}
