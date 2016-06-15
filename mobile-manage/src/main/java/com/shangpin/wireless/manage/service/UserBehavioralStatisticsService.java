package com.shangpin.wireless.manage.service;

import java.util.List;

/**
 * 用户行为统计
 * 
 * @Author liling
 * 
 * @CreatDate 2013-12-6
 */
public interface UserBehavioralStatisticsService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.UserBehavioralStatisticsServiceImpl";

	List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	Integer executeSqlCount(String queryListSql) throws Exception;
}
