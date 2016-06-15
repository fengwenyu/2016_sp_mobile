/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.impl.RoleServiceImpl.java
 * Class:			RoleServiceImpl
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

import com.shangpin.core.dao.RoleDAO;
import com.shangpin.core.entity.main.Role;
import com.shangpin.core.service.RoleService;
import com.shangpin.core.shiro.ShiroDbRealm;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:52 
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired(required = false)
	private ShiroDbRealm shiroRealm;
	
	@Override
	public void save(Role role) {
		roleDAO.save(role);
	}

	@Override
	public Role get(Long id) {
		return roleDAO.findOne(id);
	}
	
	@Override
	public List<Role> findAll(Page page) {
		org.springframework.data.domain.Page<Role> springDataPage = roleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param role  
	 * @see com.shangpin.core.service.RoleService#update(com.shangpin.core.entity.main.Role)  
	 */
	public void update(Role role) {
		roleDAO.save(role);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.shangpin.core.service.RoleService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		roleDAO.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.shangpin.core.service.RoleService#find(com.shangpin.core.util.dwz.Page, java.lang.String)  
	 */
	public List<Role> find(Page page, String name) {
		org.springframework.data.domain.Page<Role> springDataPage = 
				(org.springframework.data.domain.Page<Role>)roleDAO.findByNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
