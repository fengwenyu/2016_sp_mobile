/**
 * 
 */
package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.DataControl;
import com.shangpin.core.util.dwz.Page;

public interface DataControlService {
	DataControl get(Long id);
	
	DataControl getByName(String name);

	void saveOrUpdate(DataControl dataControl);

	void delete(Long id);
	
	List<DataControl> findAll(Page page);
	
	List<DataControl> findByExample(Specification<DataControl> specification, Page page);
}
