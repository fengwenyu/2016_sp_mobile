/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.OrganizationRoleServiceImpl.java
 * Class:			OrganizationRoleServiceImpl
 * Date:			2013-4-15
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.OrganizationRoleDAO;
import com.shangpin.core.entity.main.OrganizationRole;
import com.shangpin.core.service.OrganizationRoleService;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  2.0.0
 * @since   2013-4-15 下午4:16:04 
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
	
    @Autowired
	private OrganizationRoleDAO organizationRoleDAO;
	
	/**
	 * 
	 * 构造函数
	 * @param organizationRoleDAO
	 */
	@Autowired
	public OrganizationRoleServiceImpl(OrganizationRoleDAO organizationRoleDAO) {
		this.organizationRoleDAO = organizationRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.core.service.OrganizationRoleService#get(java.lang.Long)  
	 */
	@Override
	public OrganizationRole get(Long id) {
		return organizationRoleDAO.findOne(id);
	}

	/**   
	 * @param organizationId
	 * @return  
	 * @see com.shangpin.core.service.OrganizationRoleService#find(java.lang.Long)  
	 */
	@Override
	public List<OrganizationRole> find(Long organizationId) {
		return organizationRoleDAO.findByOrganizationId(organizationId);
	}

	/**   
	 * @param organizationRole  
	 * @see com.shangpin.core.service.OrganizationRoleService#save(com.shangpin.core.entity.main.OrganizationRole)  
	 */
	@Override
	public void save(OrganizationRole organizationRole) {
		organizationRoleDAO.save(organizationRole);
	}

	/**   
	 * @param organizationRoleId  
	 * @see com.shangpin.core.service.OrganizationRoleService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long organizationRoleId) {
		organizationRoleDAO.delete(organizationRoleId);
	}

}
