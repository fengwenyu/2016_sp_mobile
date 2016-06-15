package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.HotBrandDAO;
import com.shangpin.core.entity.main.HotBrand;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.service.HotBrandService;
import com.shangpin.core.shiro.ShiroDbRealm;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/**
 * 
 * @author <a href="mailto:yangtongchui@126.com">yangtongchui</a> Version 1.1.0
 * @since 2014-8-6 上午午9:44:23
 */
@Service
@Transactional
public class HotBrandServiceImpl implements HotBrandService {

	@Autowired
	private HotBrandDAO hotBrandDAO;

	@Autowired
	private ShiroDbRealm shiroRealm;


	@Override
	public List<HotBrand> find(Page page, String name) {
		org.springframework.data.domain.Page<HotBrand> springDataPage = hotBrandDAO.findByBrandNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public void update(HotBrand hotBrand) {
		hotBrandDAO.save(hotBrand);
		shiroRealm.clearCachedAuthorizationInfo(hotBrand.getBrandName());
	}

	@Override
	public void save(HotBrand hotBrand) throws ExistedException {
		if (hotBrandDAO.findByBrandName(hotBrand.getBrandName()) != null) {
			throw new ExistedException("品牌添加失败，品牌名：" + hotBrand.getBrandName() + "已存在。");
		}
		hotBrandDAO.save(hotBrand);
		shiroRealm.clearCachedAuthorizationInfo(hotBrand.getBrandName());
	}

	@Override
	public HotBrand get(Long id) {
		// TODO Auto-generated method stub
		return hotBrandDAO.findOne(id);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		HotBrand hotBrand = hotBrandDAO.findOne(id);
		hotBrandDAO.delete(hotBrand.getId());
		shiroRealm.clearCachedAuthorizationInfo(hotBrand.getBrandName());
	}

	@Override
	public List<HotBrand> findAll(Page page) {
		org.springframework.data.domain.Page<HotBrand> springDataPage = hotBrandDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<HotBrand> findByExample(Specification<HotBrand> specification, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
