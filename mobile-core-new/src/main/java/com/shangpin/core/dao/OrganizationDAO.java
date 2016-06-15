/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.dao.OrganizationDao.java
 * Class:			OrganizationDao
 * Date:			2012-8-27
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.shangpin.core.entity.main.Organization;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-27 下午3:55:47 
 */

public interface OrganizationDAO extends JpaRepository<Organization, Long>{
	Page<Organization> findByParentId(Long parentId, Pageable pageable);
	
	Page<Organization> findByParentIdAndNameContaining(Long parentId, String name, Pageable pageable);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="com.shangpin.manager.entity.main.Organization")
		}
	)
	@Query("from Organization")
	List<Organization> findAllWithCache();
}
