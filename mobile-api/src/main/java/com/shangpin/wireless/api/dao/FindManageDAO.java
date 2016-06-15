package com.shangpin.wireless.api.dao;

import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.IsSliderEnum;

public interface FindManageDAO extends BaseDao<FindManage> {
	public static final String DAO_NAME = "findManageDAO";
	public List<FindManage> findAll();
	public List<FindManage> findByActivityManage(IsSliderEnum isSlider);
	public  List<FindManage> findStaticActivity();
	public List<FindManage> findActManageByVer(String verType);
}
