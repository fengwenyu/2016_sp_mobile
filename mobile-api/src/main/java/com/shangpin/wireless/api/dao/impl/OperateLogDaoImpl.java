package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.OperateLogDao;
import com.shangpin.wireless.api.domain.OperateLog;

/**
 * @Author zhouyu
 * @CreateDate: 2012-08-02
 */
@Repository(OperateLogDao.DAO_NAME)
public class OperateLogDaoImpl extends BaseDaoImpl<OperateLog> implements OperateLogDao {

}
