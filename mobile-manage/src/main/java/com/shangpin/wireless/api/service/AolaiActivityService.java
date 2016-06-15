package com.shangpin.wireless.api.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shangpin.wireless.domain.AolaiActivity;

public interface AolaiActivityService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AolaiActivityServiceImpl";

	List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	Integer executeSqlCount(String queryListSql) throws Exception;

	void edit(AolaiActivity model) throws Exception;

	void delete(Long id) throws Exception;

	AolaiActivity findById(Long id) throws Exception;

	Map<String, Object> isExist(Date startTime, Date endTime) throws Exception;

	Map<String, Object> isExist(Date startTime, Date endTime, Long id) throws Exception;

}
