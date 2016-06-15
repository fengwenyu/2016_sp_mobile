/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.controller.IndexController.java
 * Class:			IndexController
 * Date:			2012-8-2
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.manager.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.Module;
import com.shangpin.core.entity.main.Permission;
import com.shangpin.core.entity.main.User;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.ModuleService;
import com.shangpin.core.service.UserService;
import com.shangpin.core.shiro.ShiroUser;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.manager.SecurityConstants;
import com.shangpin.manager.log.impl.LogUitl;
import com.shangpin.manager.utils.SecurityUtils;
import com.google.common.collect.Lists;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * Version  1.1.0
 * @since   2012-8-2 下午5:45:57 
 */
@Controller
@RequestMapping("/management/index")
public class IndexController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModuleService moduleService;
	
	private static final String INDEX = "management/index/index";
	private static final String UPDATE_PASSWORD = "management/index/updatePwd";
	private static final String UPDATE_BASE = "management/index/updateBase";
	
	@Log(message="{0}登录了系统。")
	@RequiresUser 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		ShiroUser shiroUser = SecurityUtils.getShiroUser();
		// 加入ipAddress
		shiroUser.setIpAddress(request.getRemoteAddr());
		
		Module menuModule = getMenuModule(SecurityUtils.getSubject());

		// 这个是放入user还是shiroUser呢？
		request.getSession().setAttribute(SecurityConstants.LOGIN_USER, shiroUser.getUser());
		request.setAttribute("menuModule", menuModule);

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{shiroUser.getLoginName()}));
		return INDEX;
	}
	
	private Module getMenuModule(Subject subject) {
		Module rootModule = moduleService.getTree();

		check(rootModule, subject);
		return rootModule;
	}
	
	private void check(Module module, Subject subject) {
		List<Module> list1 = Lists.newArrayList();
		for (Module m1 : module.getChildren()) {
			// 只加入拥有view权限的Module
			if (subject.isPermitted(m1.getSn() + ":"
					+ Permission.PERMISSION_SHOW)) {
				check(m1, subject);
				list1.add(m1);
			}
		}
		module.setChildren(list1);
	}
	
	@RequestMapping(value="/updatePwd", method=RequestMethod.GET)
	public String preUpdatePassword() {
		return UPDATE_PASSWORD;
	}
	
	@Log(message="{0}修改了密码。")
	@RequestMapping(value="/updatePwd", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpServletRequest request, String plainPassword, 
			String newPassword, String rPassword) {
		User user = (User)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		if (newPassword != null && newPassword.equals(rPassword)) {
			user.setPlainPassword(plainPassword);
			try {
				userService.updatePwd(user, newPassword);
			} catch (ServiceException e) {
				LogUitl.putArgs(LogMessageObject.newIgnore());//忽略日志
				return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
			}
			LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getUsername()}));
			return AjaxObject.newOk("修改密码成功！").toString();
		}
		
		return AjaxObject.newError("修改密码失败！").setCallbackType("").toString();
	}
	
	@RequestMapping(value="/updateBase", method=RequestMethod.GET)
	public String preUpdateBase() {
		return UPDATE_BASE;
	}
	
	@Log(message="{0}修改了详细信息。")
	@RequestMapping(value="/updateBase", method=RequestMethod.POST)
	public @ResponseBody String update(User user, HttpServletRequest request,Locale locale) {
		User loginUser = (User)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		loginUser.setPhone(user.getPhone());
		loginUser.setEmail(user.getEmail());

		userService.update(loginUser);
		
		//取得国际化提示信息
		String message = getMessage(locale, USER_EDIT_SUCCESS_MSG);
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getUsername()}));
		return AjaxObject.newOk(message).toString();
	}
}
