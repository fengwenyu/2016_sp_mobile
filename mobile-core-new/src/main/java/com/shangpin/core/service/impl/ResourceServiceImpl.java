/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.component.ResourceServiceImpl.java
 * Class:			ResourceServiceImpl
 * Date:			2013-6-28
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          3.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.ResourceDAO;
import com.shangpin.core.entity.main.Resource;
import com.shangpin.core.service.ResourceService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  3.1.0
 * @since   2013-6-28 上午10:23:05 
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDAO resourceDAO;

	/**   
	 * @param resource  
	 * @see com.shangpin.manager.service.component.ResourceService#save(com.shangpin.manager.entity.main.component.Resource)  
	 */
	@Override
	public void save(Resource resource) {
		resourceDAO.save(resource);
	}

	/**   
	 * @param resource  
	 * @see com.shangpin.manager.service.component.ResourceService#update(com.shangpin.manager.entity.main.component.Resource)  
	 */
	@Override
	public void update(Resource resource) {
		resourceDAO.save(resource);
	}

	/**   
	 * @param id  
	 * @see com.shangpin.manager.service.component.ResourceService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		resourceDAO.delete(id);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.manager.service.component.ResourceService#get(java.lang.Long)  
	 */
	@Override
	public Resource get(Long id) {
		return resourceDAO.findOne(id);
	}
	
	/**   
	 * @param uuid
	 * @return  
	 * @see com.shangpin.manager.service.component.ResourceService#get(java.lang.String)  
	 */
	@Override
	public Resource get(String uuid) {
		return resourceDAO.getByUuid(uuid);
	}

	/**   
	 * @param page
	 * @return  
	 * @see com.shangpin.manager.service.component.ResourceService#findAll(com.shangpin.core.util.dwz.Page)  
	 */
	@Override
	public List<Resource> findAll(Page page) {
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findAll(PageUtils.createPageable(page)); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 
	 * @param page
	 * @param name
	 * @return  
	 * @see com.shangpin.manager.service.component.ResourceService#findByName(com.shangpin.core.util.dwz.Page, java.lang.String)
	 */
	@Override
	public List<Resource> findByName(Page page, String name) {
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findByNameContaining(PageUtils.createPageable(page), name); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**
	 * 
	 * @param page
	 * @return  
	 * @see com.shangpin.manager.service.component.ResourceService#find(com.shangpin.core.util.dwz.Page)
	 */
	@Override
	public List<Resource> find(Page page) {
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findAll(PageUtils.createPageable(page)); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
