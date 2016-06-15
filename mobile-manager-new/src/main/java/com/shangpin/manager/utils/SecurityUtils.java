/**
 * 
 */
package com.shangpin.manager.utils;

import org.apache.shiro.subject.Subject;

import com.shangpin.core.entity.main.User;
import com.shangpin.core.shiro.ShiroUser;

/**
 * @author sundful
 *
 */
public abstract class SecurityUtils {
	public static User getLoginUser() {
		return getShiroUser().getUser();
	}
	
	public static ShiroUser getShiroUser() {
		Subject subject = getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		
		return shiroUser;
	}

	public static Subject getSubject() {
		return org.apache.shiro.SecurityUtils.getSubject();
	}
}
