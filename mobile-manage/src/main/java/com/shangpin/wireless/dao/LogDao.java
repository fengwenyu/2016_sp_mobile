package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ManageBaseDao;
import com.shangpin.wireless.domain.LogObject;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-11
 */
public interface LogDao extends ManageBaseDao<LogObject> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.LogDaoImpl";
}
