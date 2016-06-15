package com.shangpin.wireless.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AppNavigationDao;
import com.shangpin.wireless.api.domain.AppNavigation;



@Repository(AppNavigationDao.DAO_NAME)
public class AppNavigationDaoImpl  extends BaseDaoImpl<AppNavigation> implements AppNavigationDao{

	@Override
	public List<AppNavigation> findAll() {
		String sql = "from AppNavigation order by ABS(sort) asc,createDate desc";
		return  getSession("").createQuery(sql).list();
	}
}
