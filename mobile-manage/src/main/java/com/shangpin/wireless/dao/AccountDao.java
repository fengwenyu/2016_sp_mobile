package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.Account;

/**
 * @Author zhouyu
 * @CreateDate  2012-07-27
 */
public interface AccountDao extends ApiBaseDao<Account> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.AccountDaoImpl";
}
