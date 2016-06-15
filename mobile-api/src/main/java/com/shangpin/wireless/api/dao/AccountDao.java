package com.shangpin.wireless.api.dao;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.Account;

/**
 * @Author zhouyu
 * @CreateDate: 2012-09-16
 */
public interface AccountDao extends BaseDao<Account> {
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.AccountDaoImpl";
}
