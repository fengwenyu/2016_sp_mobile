package com.shangpin.wireless.api.dao;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.BestpayorderShangpin;

/**
 *  翼支付生成订单实现类
 * 
 * @Author: wangfeng
 * @CreatDate: 2013-09-11
 */
public interface BestpayByOrderDao extends BaseDao<BestpayorderShangpin> {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.BestpayByOrderDaoImpl";
}
