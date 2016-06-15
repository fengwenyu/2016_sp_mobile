package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.OperateLogDao;
import com.shangpin.wireless.domain.OperateLog;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(OperateLogDao.DAO_NAME)
public class OperateLogDaoImpl extends ManageBaseDaoImpl<OperateLog> implements OperateLogDao {

}
