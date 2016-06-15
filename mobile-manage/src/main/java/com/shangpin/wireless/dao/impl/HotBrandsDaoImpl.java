package com.shangpin.wireless.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.HotBrandsDao;
import com.shangpin.wireless.domain.HotBrands;
import com.shangpin.wireless.domain.NewProductBrand;
@Repository(HotBrandsDao.DAO_NAME)
public class HotBrandsDaoImpl  extends ApiBaseDaoImpl<HotBrands> implements HotBrandsDao{

	@Override
	public HotBrands findById(Long id) {
		Query query=getSession().createQuery("select t from HotBrands t where t.id=?");
		query.setLong(0,id);
		 return (HotBrands) query.uniqueResult();
	}

	
}
