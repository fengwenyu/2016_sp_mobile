package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.AppNavigationDAO;
import com.shangpin.core.entity.main.AppNavigation;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.service.AppNavigationService;
import com.shangpin.core.shiro.ShiroDbRealm;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/**
 * 奥莱客户端导航编辑
 * 
 * @author yangtongchui
 * @since 2014-8-12
 * 
 */
@Service
@Transactional
public class AppNavigationServiceImpl implements AppNavigationService {

	@Autowired
	private AppNavigationDAO appNavigationDAO;

	@Autowired
	private ShiroDbRealm shiroRealm;

	@Override
	public List<AppNavigation> find(Page page, String name) {
		org.springframework.data.domain.Page<AppNavigation> springDataPage = appNavigationDAO
				.findByNavNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public void update(AppNavigation appNavigation) {
		appNavigationDAO.save(appNavigation);
		shiroRealm.clearCachedAuthorizationInfo(appNavigation.getNavName());
	}

	@Override
	public void save(AppNavigation appNavigation) throws ExistedException {
		if (appNavigationDAO.findByNavName(appNavigation.getNavName()) != null) {
			throw new ExistedException("品牌添加失败，品牌名：" + appNavigation.getNavName() + "已存在。");
		}
		appNavigationDAO.save(appNavigation);
		shiroRealm.clearCachedAuthorizationInfo(appNavigation.getNavName());
	}

	@Override
	public AppNavigation get(Long id) {
		return appNavigationDAO.findOne(id);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		AppNavigation appNavigation = appNavigationDAO.findOne(id);
		appNavigationDAO.delete(appNavigation.getId());
		shiroRealm.clearCachedAuthorizationInfo(appNavigation.getNavName());
	}

	@Override
	public List<AppNavigation> findAll(Page page) {
		org.springframework.data.domain.Page<AppNavigation> springDataPage = appNavigationDAO
				.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<AppNavigation> findByExample(Specification<AppNavigation> specification, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
