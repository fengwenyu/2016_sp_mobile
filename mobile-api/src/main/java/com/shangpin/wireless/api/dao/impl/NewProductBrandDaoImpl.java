package com.shangpin.wireless.api.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.NewProductBrandDao;
import com.shangpin.wireless.api.domain.NewProductBrand;

@Repository(NewProductBrandDao.DAO_NAME)
public class NewProductBrandDaoImpl extends BaseDaoImpl<NewProductBrand> implements NewProductBrandDao {
	@Override
	public NewProductBrand getById(String categoryId, String dbType) {
		Query query = getSession(dbType).createQuery("select t from NewProductBrand t where t.categoryId=?");
		query.setString(0, categoryId);
		return (NewProductBrand) query.uniqueResult();
	}
}
