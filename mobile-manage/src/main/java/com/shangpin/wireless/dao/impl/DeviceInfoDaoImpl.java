package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.DeviceInfoDao;
import com.shangpin.wireless.domain.DeviceInfo;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(DeviceInfoDao.DAO_NAME)
public class DeviceInfoDaoImpl extends ManageBaseDaoImpl<DeviceInfo> implements DeviceInfoDao {

}
