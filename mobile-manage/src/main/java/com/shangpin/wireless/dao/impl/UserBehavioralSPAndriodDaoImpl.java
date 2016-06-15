package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.UserBehavioralSPAndriodDao;
import com.shangpin.wireless.domain.UserBehavioralSPAndriod;


@Repository(UserBehavioralSPAndriodDao.DAO_NAME)
public class UserBehavioralSPAndriodDaoImpl extends ManageBaseDaoImpl<UserBehavioralSPAndriod> implements UserBehavioralSPAndriodDao{
}
