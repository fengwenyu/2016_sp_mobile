package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.UserBehavioralStatisticsDao;
import com.shangpin.wireless.manage.service.UserBehavioralStatisticsService;

/**
 * 用户行为统计
 * 
 * @Author liling
 * 
 * @CreatDate 2013-12-6
 */
@Service(UserBehavioralStatisticsService.SERVICE_NAME)
public class UserBehavioralStatisticsServiceImpl implements UserBehavioralStatisticsService{
	@Resource(name = UserBehavioralStatisticsDao.DAO_NAME)
	private UserBehavioralStatisticsDao userBehavioralStatisticsDao;




	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		return userBehavioralStatisticsDao.executeSqlToMap(queryListSql, page, rows);
	}


	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		// TODO Auto-generated method stub
		return userBehavioralStatisticsDao.executeSqlCount(queryListSql);
	}
	
}
