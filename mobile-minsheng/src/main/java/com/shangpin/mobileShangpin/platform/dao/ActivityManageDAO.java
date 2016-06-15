package com.shangpin.mobileShangpin.platform.dao;

import java.util.List;

import com.shangpin.mobileShangpin.base.model.FindManage;
import com.shangpin.mobileShangpin.common.persistence.GenericDAO;

public interface ActivityManageDAO extends GenericDAO<FindManage, Long> {

	/**
	 * 时间查询发现信息表
	 * 
	 * @param 
	 * 
	 * @return 发现信息表
	 */
	public List<FindManage> findactivityManage(String date,String showType) throws Exception;
}
