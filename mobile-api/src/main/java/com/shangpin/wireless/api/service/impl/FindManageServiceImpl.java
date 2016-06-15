package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shangpin.wireless.api.dao.FindManageDAO;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.IsSliderEnum;
import com.shangpin.wireless.api.service.FindManageService;

@Service(FindManageService.SERVICE_NAME)
public class FindManageServiceImpl implements FindManageService {

	@Resource
	private FindManageDAO findManageDAO;
	public List<FindManage> findAll() throws Exception {
		return findManageDAO.findAll();
	}
	public FindManageDAO getFindManageDAO() {
		return findManageDAO;
	}
	public void setFindManageDAO(FindManageDAO findManageDAO) {
		this.findManageDAO = findManageDAO;
	}
	@Override
	public List<FindManage> findByActivityManage(IsSliderEnum isSlider) {
		//  Auto-generated method stub
		return findManageDAO.findByActivityManage(isSlider);
	}
	@Override
	public FindManage findStaticActivity() {
		 List<FindManage> list=findManageDAO.findStaticActivity();
		 if(list.size()>0){
			 return list.get(0); 
		 }
		return null;
		 
	}
	@Override
	public List<FindManage> findActManageByVer(String verType) {
		
		return findManageDAO.findActManageByVer(verType);
	}

	
}
