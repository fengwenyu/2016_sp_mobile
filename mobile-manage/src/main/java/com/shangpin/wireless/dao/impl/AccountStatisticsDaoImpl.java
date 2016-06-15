package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.AccountStatisticsDao;
import com.shangpin.wireless.domain.AccountStatistics;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-16
 */
@Repository(AccountStatisticsDao.DAO_NAME)
public class AccountStatisticsDaoImpl extends ManageBaseDaoImpl<AccountStatistics> implements AccountStatisticsDao {
}
