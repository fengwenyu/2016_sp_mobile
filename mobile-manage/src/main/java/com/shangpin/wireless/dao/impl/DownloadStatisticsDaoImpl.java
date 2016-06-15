package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.DownloadStatisticsDao;
import com.shangpin.wireless.domain.DownloadStatistics;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(DownloadStatisticsDao.DAO_NAME)
public class DownloadStatisticsDaoImpl extends ManageBaseDaoImpl<DownloadStatistics> implements DownloadStatisticsDao {

}
