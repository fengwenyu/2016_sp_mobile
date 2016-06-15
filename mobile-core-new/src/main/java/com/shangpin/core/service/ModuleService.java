/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.ModuleService.java
 * Class:			ModuleService
 * Date:			2012-8-6
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.Module;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;


/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:32:17 
 */

public interface ModuleService {
	void save(Module module) throws ExistedException;
	
	Module get(Long id);
	
	Module getBySn(String sn);
	
	void update(Module module);
	
	void delete(Long id) throws ServiceException;
	
	Module getTree();
	
	List<Module> findAll();
	
	List<Module> find(Long parentId, Page page);
	
	List<Module> find(Long parentId, String name, Page page);
}
