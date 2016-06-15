package com.shangpin.wireless.api.dao;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.NewProductBrand;





public interface NewProductBrandDao extends BaseDao<NewProductBrand>{
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.NewProductBrandDaoImpl";

	public NewProductBrand getById(String categoryID, String dbType);
}
