/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.dao.RoleDao.java
 * Class:			RoleDao
 * Date:			2012-8-7
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shangpin.core.entity.main.Role;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:03:07 
 */

public interface RoleDAO extends JpaRepository<Role, Long> {
	Page<Role> findByNameContaining(String name, Pageable pageable);
}
