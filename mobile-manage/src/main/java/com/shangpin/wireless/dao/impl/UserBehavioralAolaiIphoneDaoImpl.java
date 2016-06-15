package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.UserBehavioralAolaiIphoneDao;
import com.shangpin.wireless.domain.UserBehavioralAolaiIphone;

@Repository(UserBehavioralAolaiIphoneDao.DAO_NAME)
public class UserBehavioralAolaiIphoneDaoImpl extends ManageBaseDaoImpl<UserBehavioralAolaiIphone> implements UserBehavioralAolaiIphoneDao{
}
