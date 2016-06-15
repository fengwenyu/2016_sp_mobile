/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.component.ResourceService.java
 * Class:			ResourceService
 * Date:			2013-6-28
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          3.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.Resource;
import com.shangpin.core.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  3.1.0
 * @since   2013-6-28 上午10:19:01 
 */

public interface ResourceService {
	void save(Resource resource);
	
	void update(Resource resource);
	
	void delete(Long id);
	
	Resource get(Long id);
	
	Resource get(String uuid);
	
	List<Resource> findAll(Page page);
	
	List<Resource> findByName(Page page, String name);
	
	List<Resource> find(Page page);
}
