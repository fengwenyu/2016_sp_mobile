package com.shangpin.wireless.api.dao;

import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.AolaiActivity;

public interface AolaiActivityDao extends BaseDao<AolaiActivity>{
	public final static String DAO_NAME = "com.shangpin.wireless.api.dao.impl.AolaiActivityDaoImpl";

	List<AolaiActivity> findAolaiActivity();
}
