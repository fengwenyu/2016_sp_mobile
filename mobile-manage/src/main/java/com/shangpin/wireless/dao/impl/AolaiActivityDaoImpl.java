package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.AolaiActivityDao;
import com.shangpin.wireless.domain.AolaiActivity;
@Repository(AolaiActivityDao.DAO_NAME)
public class AolaiActivityDaoImpl extends ApiBaseDaoImpl<AolaiActivity> implements AolaiActivityDao{
}
