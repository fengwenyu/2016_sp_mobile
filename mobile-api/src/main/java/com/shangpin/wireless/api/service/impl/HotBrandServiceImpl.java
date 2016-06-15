package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.HotBrandsDAO;
import com.shangpin.wireless.api.domain.HotBrands;
import com.shangpin.wireless.api.service.HotBrandsService;

@Service(HotBrandsService.SERVICE_NAME)
public class HotBrandServiceImpl implements HotBrandsService {

	@Resource
	private HotBrandsDAO hotBrandsDAO;
	public List<HotBrands> findAll() throws Exception {
		return hotBrandsDAO.findAll();
	}

	public HotBrandsDAO getHotBrandsDAO() {
		return hotBrandsDAO;
	}
	public void setHotBrandsDAO(HotBrandsDAO hotBrandsDAO) {
		this.hotBrandsDAO = hotBrandsDAO;
	}
}
