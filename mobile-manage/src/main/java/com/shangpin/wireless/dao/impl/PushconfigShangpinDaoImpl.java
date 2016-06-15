package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.PushconfigShangpinDao;
import com.shangpin.wireless.domain.PushconfigShangpin;

/**
 * @Author zhouyu
 * @CreateDate: 2013-01-16
 */
@Repository(PushconfigShangpinDao.DAO_NAME)
public class PushconfigShangpinDaoImpl extends ApiBaseDaoImpl<PushconfigShangpin> implements PushconfigShangpinDao {

}
