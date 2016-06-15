package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AccountWeixinDao;
import com.shangpin.wireless.api.domain.AccountWeixin;

/**
 * @Author wangwenguan
 * @CreateDate: 2013-08-19
 */
@Repository(AccountWeixinDao.DAO_NAME)
public class AccountWeixinDaoImpl extends BaseDaoImpl<AccountWeixin> implements AccountWeixinDao {

}
