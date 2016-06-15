package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.HotBrands;

public interface HotBrandsService{
	public static final String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.HotBrandServiceImpl";
	/**
	 * 返回所有热门品牌数据
	 * @author xupengcheng
	 * @createDate 2013-12-31 下午03:25:01
	 * @return
	 * @throws Exception
	 */
	public List<HotBrands> findAll() throws Exception;
 }
