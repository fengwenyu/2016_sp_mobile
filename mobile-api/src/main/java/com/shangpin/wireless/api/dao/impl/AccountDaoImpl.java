package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AccountDao;
import com.shangpin.wireless.api.domain.Account;

/**
 * @Author zhouyu
 * @CreateDate: 2012-09-16
 */
@Repository(AccountDao.DAO_NAME)
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

}
