package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.NewProductBrand;

public interface NewProductBrandDao extends ApiBaseDao<NewProductBrand>{
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.NewProductBrandDaoImpl";


	public NewProductBrand getById(String categoryId);
}
