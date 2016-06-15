package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.OrderStatisticsDao;
import com.shangpin.wireless.domain.OrderStatistics;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(OrderStatisticsDao.DAO_NAME)
public class OrderStatisticsDaoImpl extends ManageBaseDaoImpl<OrderStatistics> implements OrderStatisticsDao {

}
