package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.DownloadStatisticsDao;
import com.shangpin.wireless.domain.DownloadStatistics;
import com.shangpin.wireless.manage.service.DownloadStatisticsService;

/**
 * PV统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(DownloadStatisticsService.SERVICE_NAME)
public class DownloadStatisticsServiceImpl implements DownloadStatisticsService {
	@Resource(name = DownloadStatisticsDao.DAO_NAME)
	private DownloadStatisticsDao statisticsDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(DownloadStatistics entity) throws Exception {
		statisticsDao.save(entity);
	}
}
