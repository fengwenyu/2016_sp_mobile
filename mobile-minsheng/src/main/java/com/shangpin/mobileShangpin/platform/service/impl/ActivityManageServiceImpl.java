package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.base.model.FindManage;
import com.shangpin.mobileShangpin.platform.dao.ActivityManageDAO;
import com.shangpin.mobileShangpin.platform.service.ActivityManageService;

@Service("activityManageService")
public class ActivityManageServiceImpl implements ActivityManageService {
	@Autowired
	private ActivityManageDAO activityManageDAO;
	private final Log log = LogFactory.getLog(ActivityManageServiceImpl.class);

	/**
	 * 根据时间查询发现信息
	 * 
	 * @param 
	 * @return ActivityManage 发现信息
	 */
	@Override
	public List<FindManage> findByActivityManage(String date,String showType) throws Exception {
		List<FindManage> activityManageList = null;
		try {
			activityManageList = activityManageDAO.findactivityManage(date,showType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activityManageList;
	}

}
