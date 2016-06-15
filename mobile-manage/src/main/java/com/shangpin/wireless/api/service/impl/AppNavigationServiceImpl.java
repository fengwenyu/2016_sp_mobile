package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.AppNavigationService;
import com.shangpin.wireless.dao.AppNavigationDao;
import com.shangpin.wireless.domain.AppNavigation;


@Service(AppNavigationService.SERVICE_NAME)
public class AppNavigationServiceImpl implements AppNavigationService{
	@Resource(name = AppNavigationDao.DAO_NAME)
	private AppNavigationDao appNavigationDao;
	
	@Override
	public void edit(AppNavigation model) throws Exception {
		if(model.getId()!=null){
			appNavigationDao.update(model);
		}else{
			String sort = appNavigationDao.findMaxSort();
			if (StringUtils.isNotEmpty(sort)) {
				model.setSort(String.valueOf(Integer.valueOf(sort.split("[.]")[0]) + 1));
			} else {
				model.setSort("1");
			}
			appNavigationDao.save(model);
		}
		
		
	}

	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		// TODO Auto-generated method stub
		return appNavigationDao.executeSqlToMap(queryListSql);
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		// TODO Auto-generated method stub
		return appNavigationDao.executeSqlCount(queryListSql);
	}

	@Override
	public void delete(Long id) throws Exception {
		appNavigationDao.delete(id);
		
	}

	@Override
	public AppNavigation findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return appNavigationDao.getById(id);
	}

	@Override
	public String getSameTabId(AppNavigation model) throws Exception {
		if(model.getId()!=null){
			return appNavigationDao.getSameTabId(model.getTabId(),model.getId());
		}else{
			return appNavigationDao.getSameTabId(model.getTabId());
		}
	}
}
