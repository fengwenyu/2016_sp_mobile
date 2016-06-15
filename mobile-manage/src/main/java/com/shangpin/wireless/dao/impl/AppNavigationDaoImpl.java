package com.shangpin.wireless.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.AppNavigationDao;
import com.shangpin.wireless.domain.AppNavigation;


@Repository(AppNavigationDao.DAO_NAME)
public class AppNavigationDaoImpl  extends ApiBaseDaoImpl<AppNavigation> implements AppNavigationDao{

	@Override
	public String findMaxSort() {
		String sql = "SELECT MAX(ABS(SORT)) FROM app_navigation";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	
		
	}

	@Override
	public String getSameTabId(String tabId,Long id) {
		String sql = "SELECT  COUNT(*)  FROM app_navigation where tabId=? and id!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, tabId).setParameter(1, id);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	
		
	}

	@Override
	public String getSameTabId(String tabId) {
		String sql = "SELECT  COUNT(*)  FROM app_navigation where tabId=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, tabId);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
		
	}
}
