package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AccountAlipayDao;
import com.shangpin.wireless.api.domain.AccountAlipay;

/**
 * @Author zhouyu
 * @CreateDate: 2012-09-16
 */
@Repository(AccountAlipayDao.DAO_NAME)
public class AccountAlipayDaoImpl extends BaseDaoImpl<AccountAlipay> implements AccountAlipayDao {

}
