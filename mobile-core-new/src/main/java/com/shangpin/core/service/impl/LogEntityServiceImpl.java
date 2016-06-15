/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.LogEntityServiceImpl.java
 * Class:			LogEntityServiceImpl
 * Date:			2013-5-3
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.LogEntityDAO;
import com.shangpin.core.entity.main.LogEntity;
import com.shangpin.core.log.LogLevel;
import com.shangpin.core.service.LogEntityService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @version  2.1.0
 * @since   2013-5-3 下午5:08:05 
 */
@Service
@Transactional
public class LogEntityServiceImpl implements LogEntityService {
	
	@Autowired
	private LogEntityDAO logEntityDAO;

	/**   
	 * @param logEntity  
	 * @see com.shangpin.core.service.LogEntityService#save(com.shangpin.core.entity.main.LogEntity)  
	 */
	@Override
	public void save(LogEntity logEntity) {
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.core.service.LogEntityService#get(java.lang.Long)  
	 */
	@Override
	public LogEntity get(Long id) {
		return logEntityDAO.findOne(id);
	}

	/**   
	 * @param logEntity  
	 * @see com.shangpin.core.service.LogEntityService#update(com.shangpin.core.entity.main.LogEntity)  
	 */
	@Override
	public void update(LogEntity logEntity) {
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id  
	 * @see com.shangpin.core.service.LogEntityService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		logEntityDAO.delete(id);
	}

	/**
	 * 
	 * @param logLevel
	 * @param page
	 * @return  
	 * @see com.shangpin.core.service.LogEntityService#findByLogLevel(com.shangpin.core.log.LogLevel, com.shangpin.core.util.dwz.Page)
	 */
	@Override
	public List<LogEntity> findByLogLevel(LogLevel logLevel, Page page) {
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findByLogLevel(logLevel, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @return  
	 * @see com.shangpin.core.service.LogEntityService#findAll()  
	 */
	@Override
	public List<LogEntity> findAll() {
		return logEntityDAO.findAll();
	}

	/**
	 * 
	 * @param specification
	 * @param page
	 * @return  
	 * @see com.shangpin.core.service.LogEntityService#findByExample(org.springframework.data.jpa.domain.Specification, com.shangpin.core.util.dwz.Page)
	 */
	@Override
	public List<LogEntity> findByExample(
			Specification<LogEntity> specification, Page page) {
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
