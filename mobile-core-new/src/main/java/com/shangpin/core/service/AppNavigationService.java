package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.AppNavigation;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

/**
 * 奥莱客户端导航编辑
 * 
 * @author yangtongchui
 * @since 2014-8-12
 * 
 */
public interface AppNavigationService {

	List<AppNavigation> find(Page page, String name);

	void update(AppNavigation appNavigation);

	void save(AppNavigation user) throws ExistedException;

	AppNavigation get(Long id);

	void delete(Long id) throws ServiceException;

	List<AppNavigation> findAll(Page page);

	List<AppNavigation> findByExample(Specification<AppNavigation> specification, Page page);

}
