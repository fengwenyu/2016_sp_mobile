/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.dao.LogEntityDao.java
 * Class:			LogEntityDao
 * Date:			2013-5-3
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.LogEntity;
import com.shangpin.core.log.LogLevel;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @version  2.1.0
 * @since   2013-5-3 下午5:06:37 
 */

public interface LogEntityDAO extends JpaRepository<LogEntity, Long>, JpaSpecificationExecutor<LogEntity>{
	Page<LogEntity> findByLogLevel(LogLevel level, Pageable pageable);
}
