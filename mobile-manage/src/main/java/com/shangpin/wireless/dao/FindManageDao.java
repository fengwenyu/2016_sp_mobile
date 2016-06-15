package com.shangpin.wireless.dao;

import java.util.List;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.FindManage;

public interface FindManageDao extends ApiBaseDao<FindManage>{
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.FindManageDaoImpl";
//
//	FindManage findActivity(ShowPositionEnum showPosition);
//
//	List<FindManage> findNotCurrentActivities(ShowPositionEnum showPosition);

	void updateTopPostion();

	void sortRetrude(Long id);

	String findMinSort(Long id);

	String findBySort(String sort);

	void retrudeByStartSort(String startSort,String sort);

	void retrudeSortDel(String sort);

	void retrudeByEndSort(String startSort,String sort);

	String findMaxSort(Long id);

}
