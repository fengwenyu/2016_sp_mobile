package com.shangpin.wireless.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AolaiActivityDao;
import com.shangpin.wireless.api.domain.AolaiActivity;
import com.shangpin.wireless.api.domain.DisplayEnum;
import com.shangpin.wireless.api.util.DateUtil;
@Repository(AolaiActivityDao.DAO_NAME)
public class AolaiActivityDaoImpl extends BaseDaoImpl<AolaiActivity> implements AolaiActivityDao{

	@SuppressWarnings("unchecked")
    @Override
	public List<AolaiActivity> findAolaiActivity() {
		String sql = "from AolaiActivity where ? between startTime and endTime and display=? order by startTime desc";
		return   getSession("").createQuery(sql).setString(0, DateUtil.getDateTime()).setParameter(1, DisplayEnum.YES).list();
	}
}
