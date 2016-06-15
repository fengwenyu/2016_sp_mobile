package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.WeixinActivitiesDao;
import com.shangpin.wireless.api.domain.WeixinActivities;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-01
 */
@Repository(WeixinActivitiesDao.DAO_NAME)
public class WeixinActivitiesDaoImpl extends BaseDaoImpl<WeixinActivities> implements WeixinActivitiesDao {

}
