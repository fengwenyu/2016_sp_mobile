package com.shangpin.wireless.api.dao;

import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.HotBrands;

public interface HotBrandsDAO extends BaseDao<HotBrands> {
	public static final String DAO_NAME = "hotBrandsDAO";
	public List<HotBrands> findAll();
}
