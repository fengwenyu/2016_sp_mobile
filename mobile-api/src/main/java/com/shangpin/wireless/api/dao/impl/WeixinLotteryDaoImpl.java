package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinLotteryDao;
import com.shangpin.wireless.api.domain.WeixinLottery;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-01
 */
@Repository(WeixinLotteryDao.DAO_NAME)
public class WeixinLotteryDaoImpl extends BaseDaoImpl<WeixinLottery> implements WeixinLotteryDao {

}
