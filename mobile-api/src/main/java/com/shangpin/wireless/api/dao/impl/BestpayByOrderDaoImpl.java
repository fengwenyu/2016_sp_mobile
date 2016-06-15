package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;
import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.BestpayByOrderDao;
import com.shangpin.wireless.api.domain.BestpayorderShangpin;

/**
 *  翼支付生成订单实现类
 * 
 * @Author: wangfeng
 * @CreatDate: 2013-09-11
 */
@Repository(BestpayByOrderDao.DAO_NAME)
public class BestpayByOrderDaoImpl extends BaseDaoImpl<BestpayorderShangpin> implements BestpayByOrderDao {

}
