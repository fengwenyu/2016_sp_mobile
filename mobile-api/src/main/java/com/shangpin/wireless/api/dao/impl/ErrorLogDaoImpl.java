package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.ErrorLogDao;
import com.shangpin.wireless.api.domain.ErrorLog;

/**
 * @Author zhouyu
 * @CreateDate: 2012-07-31
 */
@Repository(ErrorLogDao.DAO_NAME)
public class ErrorLogDaoImpl extends BaseDaoImpl<ErrorLog> implements ErrorLogDao {

}
