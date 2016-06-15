package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.WXFashionInformation;

public interface WXFashionInformationDao  extends ApiBaseDao<WXFashionInformation>{
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.WXFashionInformationDaoImpl";

	void sortRetrude(Long id);

	String findMinSort(Long id);

	void retrudeSortDel(Integer sort);

	String findMaxSort();

	String findBySort(Integer sort);

	void retrudeByEndSort(Integer startSort, Integer sort);

	void retrudeByStartSort(Integer startSort, Integer sort);

}
