package com.shangpin.wireless.dao;

import java.util.List;


public interface UserBehavioralStatisticsDao {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.UserBehavioralStatisticsDaoImpl";


	Integer executeSqlCount(String queryListSql) throws Exception;

	List executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

}
