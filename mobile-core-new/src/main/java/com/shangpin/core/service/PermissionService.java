/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, shangpin.com
 * Filename:		com.shangpin.manager.service.PermissionService.java
 * Class:			PermissionService
 * Date:			2013-4-16
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service;

import com.shangpin.core.entity.main.Permission;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:11:41 
 */

public interface PermissionService {
	
	void save(Permission permission);
	
	Permission get(Long id);
	
	void update(Permission permission);
	
	void delete(Long id);
}
