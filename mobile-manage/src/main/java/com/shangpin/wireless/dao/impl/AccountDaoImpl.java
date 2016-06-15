package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.AccountDao;
import com.shangpin.wireless.domain.Account;

/**
 * @Author zhouyu
 * @CreateDate  2012-07-27
 */
@Repository(AccountDao.DAO_NAME)
public class AccountDaoImpl extends ApiBaseDaoImpl<Account> implements AccountDao {

}
