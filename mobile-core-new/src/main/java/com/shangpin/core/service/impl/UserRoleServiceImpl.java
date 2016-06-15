/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.UserRoleServiceImpl.java
 * Class:			UserRoleServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.UserRoleDAO;
import com.shangpin.core.entity.main.UserRole;
import com.shangpin.core.service.UserRoleService;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:09:50 
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDAO;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.core.service.UserRoleService#get(java.lang.Long)  
	 */
	@Override
	public UserRole get(Long id) {
		return userRoleDAO.findOne(id);
	}

	@Override
	public void save(UserRole userRole) {
		userRoleDAO.save(userRole);
	}

	@Override
	public void delete(Long userRoleId) {
		userRoleDAO.delete(userRoleId);
	}

	/**   
	 * @param userId
	 * @return  
	 * @see com.shangpin.core.service.UserRoleService#find(Long)  
	 */
	public List<UserRole> find(Long userId) {
		return userRoleDAO.findByUserId(userId);
	}

}
