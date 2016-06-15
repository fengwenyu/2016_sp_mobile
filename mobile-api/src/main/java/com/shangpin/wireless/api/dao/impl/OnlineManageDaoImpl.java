package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.OnlineManageDao;
import com.shangpin.wireless.api.domain.OnlineManage;

/**
 * @Author zhouyu
 * @CreateDate: 2012-07-27
 */
@Repository(OnlineManageDao.DAO_NAME)
public class OnlineManageDaoImpl extends BaseDaoImpl<OnlineManage> implements OnlineManageDao {

}
