/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.RolePermissionImpl.java
 * Class:			RolePermissionImpl
 * Date:			2013-4-16
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.PermissionDAO;
import com.shangpin.core.entity.main.Permission;
import com.shangpin.core.service.PermissionService;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:12:41 
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	/**   
	 * @param permission  
	 * @see com.shangpin.core.service.PermissionService#save(com.shangpin.core.entity.main.Permission)  
	 */
	@Override
	public void save(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.core.service.PermissionService#get(java.lang.Long)  
	 */
	@Override
	public Permission get(Long id) {
		return permissionDAO.findOne(id);
	}

	/**   
	 * @param permission  
	 * @see com.shangpin.core.service.PermissionService#update(com.shangpin.core.entity.main.Permission)  
	 */
	@Override
	public void update(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id  
	 * @see com.shangpin.core.service.PermissionService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		permissionDAO.delete(id);
	}
}
