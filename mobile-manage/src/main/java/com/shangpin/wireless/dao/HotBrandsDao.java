package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ApiBaseDao;
import com.shangpin.wireless.domain.HotBrands;

public interface HotBrandsDao extends ApiBaseDao<HotBrands>{

	HotBrands findById(Long id);

}
