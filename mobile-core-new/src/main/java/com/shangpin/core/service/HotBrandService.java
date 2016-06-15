package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.HotBrand;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

/**
 * 
 * @author <a href="mailto:yangtongchui@126.com">yangtongchui</a> Version 1.1.0
 * @since 2014-8-6 上午午9:44:23
 */

public interface HotBrandService {
	
	List<HotBrand> find(Page page, String name);

	void update(HotBrand user);

	void save(HotBrand user) throws ExistedException;

	HotBrand get(Long id);

	void delete(Long id) throws ServiceException;

	List<HotBrand> findAll(Page page);

	List<HotBrand> findByExample(Specification<HotBrand> specification, Page page);
	
}
