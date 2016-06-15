package com.shangpin.wireless.api.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.NewProductBrandDao;
import com.shangpin.wireless.api.domain.NewProductBrand;
import com.shangpin.wireless.api.service.NewProductBrandReService;

/**
 * 首页新品和品牌推荐 
 * @Author liling
 * 
 * @CreatDate 2013-9-2
 */
@Service(NewProductBrandReService.SERVICE_NAME)
public class NewProductBrandReServiceImpl implements NewProductBrandReService {
	@Resource(name = NewProductBrandDao.DAO_NAME)
	private NewProductBrandDao newProductBrandDao;

	@Override
	public NewProductBrand findByCategoryID(String categoryID, String dbType) {
		return newProductBrandDao.getById(categoryID, dbType);
	}

}
