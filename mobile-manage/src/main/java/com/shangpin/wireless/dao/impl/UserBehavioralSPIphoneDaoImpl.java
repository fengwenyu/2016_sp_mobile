package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.UserBehavioralSPIphoneDao;
import com.shangpin.wireless.domain.UserBehavioralSPIphone;
@Repository(UserBehavioralSPIphoneDao.DAO_NAME)
public class UserBehavioralSPIphoneDaoImpl extends ManageBaseDaoImpl<UserBehavioralSPIphone> implements UserBehavioralSPIphoneDao{
}
