package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.domain.HotBrands;

public interface HotBrandsService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.HotBrandsServiceImpl";

	List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	Integer executeSqlCount(String queryListSql) throws Exception;

	void add(HotBrands model) throws Exception;

	HotBrands findById(Long id);

	void upadte(HotBrands model) throws Exception;

	void delete(Long id) throws Exception;
}
