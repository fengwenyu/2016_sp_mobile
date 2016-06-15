/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.dao.component.ResourceDAO.java
 * Class:			ResourceDAO
 * Date:			2013-6-28
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          3.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shangpin.core.entity.main.Resource;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  3.1.0
 * @since   2013-6-28 上午10:18:10 
 */

public interface ResourceDAO extends JpaRepository<Resource, Long> {
	Resource getByUuid(String uuid);
	
	Page<Resource> findByNameContaining(Pageable pageable, String name); 
}
