package com.shangpin.wireless.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.HotBrandsDAO;
import com.shangpin.wireless.api.domain.HotBrands;

@Repository(HotBrandsDAO.DAO_NAME)
public class HotBrandsDAOImpl extends BaseDaoImpl<HotBrands> implements HotBrandsDAO {
	public List<HotBrands> findAll(){
		String sql = "from HotBrands  order by sort asc ,createDate desc";
		return getSession("").createQuery(sql).list();
	}
}
