/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.RoleService.java
 * Class:			RoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.Role;
import com.shangpin.core.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:05 
 */

public interface RoleService {
	
	List<Role> find(Page page, String name);

	void save(Role role);

	Role get(Long id);

	void update(Role role);

	void delete(Long id);

	List<Role> findAll(Page page);
}
