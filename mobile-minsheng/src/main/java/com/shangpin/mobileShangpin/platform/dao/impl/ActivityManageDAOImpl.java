package com.shangpin.mobileShangpin.platform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.mobileShangpin.base.model.FindManage;
import com.shangpin.mobileShangpin.common.persistence.GenericDAOHibernateImpl;
import com.shangpin.mobileShangpin.platform.dao.ActivityManageDAO;

@Repository("activityManageDAO")
@SuppressWarnings("unchecked")
public class ActivityManageDAOImpl extends GenericDAOHibernateImpl<FindManage, Long> implements ActivityManageDAO {

	private static final long serialVersionUID = -6459850241370001027L;

	@Override
	public List<FindManage> findactivityManage(String date,String showType) throws Exception {
		String sql = "from FindManage where type!='STATICATC' AND ? between showStartDate and showEndDate order by showStartDate desc";
		if(showType.equals("0")){
			return getSession().createQuery(sql).setString(0, date).setFirstResult(0).setMaxResults(5).list();
		}else{
			return getSession().createQuery(sql).setString(0, date).list();
		}
		
	}	
}
