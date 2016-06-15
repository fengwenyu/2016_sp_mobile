/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.service.UserService.java
 * Class:			UserService
 * Date:			2012-8-7
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.User;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:03:59 
 */

public interface UserService {
	
	User get(String username);
	
	List<User> find(Page page, String name);

	void update(User user);
	
	void updatePwd(User user, String newPwd) throws ServiceException;
	
	void resetPwd(User user, String newPwd);

	void save(User user) throws ExistedException;

	User get(Long id);

	void delete(Long id) throws ServiceException;

	List<User> findAll(Page page);

	List<User> findByExample(Specification<User> specification, Page page);
}
