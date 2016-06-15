package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.LogDao;
import com.shangpin.wireless.domain.LogObject;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(LogDao.DAO_NAME)
public class LogDaoImpl extends ManageBaseDaoImpl<LogObject> implements LogDao {

}
