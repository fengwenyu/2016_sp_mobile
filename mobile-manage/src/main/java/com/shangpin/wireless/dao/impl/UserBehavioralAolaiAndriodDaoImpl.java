package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.UserBehavioralAolaiAndriodDao;
import com.shangpin.wireless.domain.UserBehavioralAolaiAndriod;


@Repository(UserBehavioralAolaiAndriodDao.DAO_NAME)
public class UserBehavioralAolaiAndriodDaoImpl extends ManageBaseDaoImpl<UserBehavioralAolaiAndriod> implements UserBehavioralAolaiAndriodDao{
}
