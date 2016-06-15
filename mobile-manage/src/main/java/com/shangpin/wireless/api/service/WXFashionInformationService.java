package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.WXFashionInformation;

public interface WXFashionInformationService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.WXFashionInformationServiceImpl";

	void add(WXFashionInformation model) throws Exception;
	List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	Integer executeSqlCount(String queryListSql) throws Exception;
	WXFashionInformation findById(Long id) throws Exception;
	void delete(Long id) throws Exception;
	void update(WXFashionInformation wxFashionInformation) throws Exception;
	String findMinSort(Long id);
	void sortRetrude(Long id);
	void retrudeSortDel(Integer sort);
	String findMaxSort();
	String findBySort(Integer sort);
	void retrudeByEndSort(Integer sort, Integer sort2);
	void retrudeByStartSort(Integer sort, Integer sort2);
}
