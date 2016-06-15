package com.shangpin.wireless.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.NewProductBrandDao;
import com.shangpin.wireless.domain.NewProductBrand;

@Repository(NewProductBrandDao.DAO_NAME)
public class NewProductBrandDaoImpl extends ApiBaseDaoImpl<NewProductBrand> implements NewProductBrandDao {
	

	@Override
	public NewProductBrand getById(String categoryId) {
		
		Query query=getSession().createQuery("select t from NewProductBrand t where t.categoryId=?");
		query.setString(0, categoryId);
		 return (NewProductBrand) query.uniqueResult();
	}
}
