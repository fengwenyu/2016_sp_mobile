package com.shangpin.wireless.api.dao;

import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.AppNavigation;


public interface AppNavigationDao  extends BaseDao<AppNavigation>{
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.AppNavigationDaoImpl";
	public List<AppNavigation> findAll();
		
}
