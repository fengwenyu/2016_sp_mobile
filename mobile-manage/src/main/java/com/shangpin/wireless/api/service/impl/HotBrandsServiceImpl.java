package com.shangpin.wireless.api.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.HotBrandsService;
import com.shangpin.wireless.dao.HotBrandsDao;
import com.shangpin.wireless.domain.HotBrands;
import com.shangpin.wireless.util.StringUtils;

@Service(HotBrandsService.SERVICE_NAME)
public class HotBrandsServiceImpl implements HotBrandsService {
	@Resource(name = HotBrandsDao.DAO_NAME)
	private HotBrandsDao hotBrandsDao;

	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		return hotBrandsDao.executeSqlToMap(queryListSql, page, rows);
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		return hotBrandsDao.executeSqlCount(queryListSql);
	}

	@Override
	public void add(HotBrands model) throws Exception {
//		int count = hotBrandsCount();
//		model.setSort(count + 1);
		if(model.getSort()==null){
			model.setSort(0);
		}
		model.setCreateDate(new Date());
		hotBrandsDao.save(model);
	}

	public Integer hotBrandsCount() throws Exception {
		String res = "SELECT count(*) FROM hotBrands ";
		Integer count = hotBrandsDao.executeSqlCount(res);
		return count;
	}

	@Override
	public HotBrands findById(Long brandId) {
		return hotBrandsDao.findById(brandId);
	}

	@Override
	public void upadte(HotBrands model) throws Exception {
		model.setCreateDate(new Date());
		hotBrandsDao.update(model);
	}

	@Override
	public void delete(Long id) throws Exception {
		hotBrandsDao.delete(id);
		
	}
}
