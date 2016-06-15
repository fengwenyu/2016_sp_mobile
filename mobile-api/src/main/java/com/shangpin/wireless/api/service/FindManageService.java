package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.IsSliderEnum;

public interface FindManageService{
	public static final String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.FindManageServiceImpl";
	/**
	 * 返回所有发现数据
	 * @author wanghaibo
	 * @createDate 2013-1-9
	 * @return
	 * @throws Exception
	 */
	public List<FindManage> findAll() throws Exception;
	public List<FindManage> findByActivityManage(IsSliderEnum yes);
	public FindManage findStaticActivity();
	public List<FindManage> findActManageByVer(String verType);
 }
