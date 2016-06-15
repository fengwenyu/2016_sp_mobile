/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, shangpin.com
 * Filename:		com.shangpin.manager.dao.OrganizationRoleDao.java
 * Class:			OrganizationRoleDao
 * Date:			2013-4-15
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shangpin.core.entity.main.OrganizationRole;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  2.0.0
 * @since   2013-4-15 下午4:11:05 
 */

public interface OrganizationRoleDAO extends JpaRepository<OrganizationRole, Long> {
	List<OrganizationRole> findByOrganizationId(Long organizationId);
}
