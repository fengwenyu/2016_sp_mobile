package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AccountWeixinBindedDao;
import com.shangpin.wireless.api.domain.AccountWeixinBinded;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-20
 */
@Repository(AccountWeixinBindedDao.DAO_NAME)
public class AccountWeixinBindedDaoImpl extends BaseDaoImpl<AccountWeixinBinded> implements AccountWeixinBindedDao {

}
