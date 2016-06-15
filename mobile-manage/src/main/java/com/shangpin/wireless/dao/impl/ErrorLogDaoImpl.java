package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.ErrorLogDao;
import com.shangpin.wireless.domain.ErrorLog;

/**
 * 错误日志
 * 
 * @Author zhouyu
 * @CreateDate  2012-10-18
 */
@Repository(ErrorLogDao.DAO_NAME)
public class ErrorLogDaoImpl extends ApiBaseDaoImpl<ErrorLog> implements ErrorLogDao {

}
