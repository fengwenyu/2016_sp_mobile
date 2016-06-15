package com.shangpin.wireless.dao.impl;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.FindManageDao;
import com.shangpin.wireless.domain.FindManage;
import com.shangpin.wireless.domain.TypeEnum;
import com.shangpin.wireless.util.DateUtil;

@Repository(FindManageDao.DAO_NAME)
public class FindManageDaoImpl extends ApiBaseDaoImpl<FindManage> implements FindManageDao{

	@Override
	public void updateTopPostion() {
		String sql = "UPDATE findManage SET sort=1 WHERE sort=0";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public void sortRetrude(Long id) {
		String sql = "UPDATE findManage SET sort=ABS(sort)+1 WHERE id!=? and ?<showEndDate and type!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, id).setParameter(1, DateUtil.getDateTime()).setParameter(2,  TypeEnum.STATICATC);
		query.executeUpdate();
		
	}

	@Override
	public String findMinSort(Long id) {
		String sql = "SELECT MIN(ABS(SORT)) FROM findManage where ?<showEndDate and type!=? and id!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, DateUtil.getDateTime()).setParameter(1,  TypeEnum.STATICATC).setParameter(2,  id);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}

	@Override
	public String findBySort(String sort) {
	
		String sql = "SELECT  COUNT(*)  FROM findManage where sort=? and ?<showEndDate and type!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, sort).setParameter(1, DateUtil.getDateTime()).setParameter(2,  TypeEnum.STATICATC);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}

	@Override
	public void retrudeByStartSort(String startSort,String sort) {
		String sql = "UPDATE findManage SET sort=ABS(sort)-1 WHERE ABS(sort)<=? and ABS(sort)>? and ?<showEndDate and type!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(sort)).setParameter(1, Integer.valueOf(startSort)).setParameter(2, DateUtil.getDateTime()).setParameter(3,  TypeEnum.STATICATC);
		query.executeUpdate();
		
	}

	@Override
	public void retrudeSortDel(String sort) {
		String sql = "UPDATE findManage SET sort=ABS(sort)-1 WHERE ABS(sort)>?  and ?<showEndDate and type!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(sort)).setParameter(1, DateUtil.getDateTime()).setParameter(2,  TypeEnum.STATICATC);
		query.executeUpdate();
		
	}

	@Override
	public void retrudeByEndSort(String startSort,String sort) {
		String sql = "UPDATE findManage SET sort=ABS(sort)+1 WHERE ABS(sort)<?  and ABS(sort)>=? and ?<showEndDate and type!=?";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, Integer.valueOf(startSort)).setParameter(1, Integer.valueOf(sort)).setParameter(2, DateUtil.getDateTime()).setParameter(3,  TypeEnum.STATICATC);
		query.executeUpdate();
		
	}

	@Override
	public String findMaxSort(Long id) {
		String sql = "SELECT MAX(ABS(SORT)) FROM (SELECT * from findManage where id!=? and ?<showEndDate and type!=?)t";
		SQLQuery query = (SQLQuery) this.getSession().createSQLQuery(sql).setParameter(0, id).setParameter(1, DateUtil.getDateTime()).setParameter(2,  TypeEnum.STATICATC);
		List list=query.list();
		if(list.size()>0){
			return  String.valueOf(list.get(0)) ;
		}
		return null;
	}


//	@Override
//	public FindManage findActivity(ShowPositionEnum showPosition) {
////		String hql = "select t from FindManage t where t.showPosition=? and t.showStartDate<=? and t.showEndDate>=?";
////		FindManage FindManage=new FindManage()
//		Query query = getSession().createQuery("select t from FindManage t where t.showPosition=? and t.showStartDate<=? and t.showEndDate>=?");
//		query.setParameter(0, showPosition);
//		Date date=Calendar.getInstance().getTime();  
//		query.setTimestamp(1, date);
//		query.setTimestamp(2, date);
//		List list=query.list();
//		if(list.size()>0){
//			return (FindManage)list.get(0);
//		}
//
//
//		return null;
//	
//	
//		
//		
//	}

//	@Override
//	public List<FindManage> findNotCurrentActivities(ShowPositionEnum showPosition) {
//
////		
//		Date date=Calendar.getInstance().getTime();  
//		Query query = getSession().createQuery("select t from FindManage t where t.showPosition=? and t.showStartDate>? ");
//		
//		query.setParameter(0, showPosition);
//		query.setTimestamp(1, date);
//	
//		
//
//		return query.list();
//	
//	
//	}
	
}
