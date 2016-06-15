package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.OnlineManageDao;
import com.shangpin.wireless.domain.OnlineManage;

/**
 * 上线管理
 * 
 * @Author zhouyu
 * @CreateDate  2012-10-18
 */
@Repository(OnlineManageDao.DAO_NAME)
public class OnlineManageDaoImpl extends ApiBaseDaoImpl<OnlineManage> implements OnlineManageDao {

}
