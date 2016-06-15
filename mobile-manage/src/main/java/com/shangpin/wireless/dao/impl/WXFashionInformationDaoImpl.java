package com.shangpin.wireless.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.WXFashionInformationDao;
import com.shangpin.wireless.domain.WXFashionInformation;

@Repository(WXFashionInformationDao.DAO_NAME)
public class WXFashionInformationDaoImpl extends ApiBaseDaoImpl<WXFashionInformation> implements WXFashionInformationDao{

	@Override
	public void sortRetrude(Long id) {
		String sql = "UPDATE wxFashionInformation SET sort=ABS(sort)+1 WHERE id!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, id);
		query.executeUpdate();
		
	}

	@Override
	public String findMinSort(Long id) {
		String sql = "SELECT MIN(ABS(SORT)) FROM wxFashionInformation where id!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0,  id);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}

	@Override
	public void retrudeSortDel(Integer sort) {
		String sql = "UPDATE wxFashionInformation SET sort=ABS(sort)-1 WHERE ABS(sort)>? ";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(sort));
		query.executeUpdate();
		
	}

	@Override
	public String findMaxSort() {
		String sql = "SELECT COUNT(*) from wxFashionInformation ";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}

	@Override
	public String findBySort(Integer sort) {
		String sql = "SELECT  COUNT(*)  FROM wxFashionInformation where sort=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, sort);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}

	@Override
	public void retrudeByEndSort(Integer startSort, Integer sort) {
		String sql = "UPDATE wxFashionInformation SET sort=ABS(sort)+1 WHERE ABS(sort)<?  and ABS(sort)>=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(startSort)).setParameter(1, Integer.valueOf(sort));
		query.executeUpdate();
		
	}

	@Override
	public void retrudeByStartSort(Integer startSort, Integer sort) {
		String sql = "UPDATE wxFashionInformation SET sort=ABS(sort)-1 WHERE ABS(sort)<=? and ABS(sort)>? ";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(sort)).setParameter(1, Integer.valueOf(startSort));
		query.executeUpdate();
		
	}
}
