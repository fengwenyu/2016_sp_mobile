package com.shangpin.wireless.view.interceptor;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangpin.wireless.domain.User;

/**
 * 权限拦截器
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@SuppressWarnings( { "unchecked", "serial" })
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String url = namespace + actionName;
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		if (url.endsWith("UI")) { // 去掉UI后缀（如果有）
			url = url.substring(0, url.length() - 2);
		}
		if (url.startsWith("download")) {
			return invocation.invoke();
		}
		// 1，如果未登录
		if (user == null) {
			if (url.startsWith("userAction_login") || url.startsWith("userAction_ajaxCheckUserInfo") || url.startsWith("userAction_ajaxCheckMobileNum") || url.startsWith("userAction_loginSys")) { // loginUI, login
				// >> 如果正在使用登录功能，就放行
				return invocation.invoke();
			} else {
				// >> 如果不是正在使用登录功能，就转到登录页面
				return "loginUI";
			}
		}
		// 2，如果已登录
		else {
			if (url.startsWith("userAction_editPassword")) {
				return invocation.invoke();
			}
			if ("81dc9bdb52d04dc20036dbd8313ed055".equals(user.getPwd())) {
				return "editPasswordUI";
			}
			// 如果这个功能不需要控制，则登录后就可以使用，此时应放行
			List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls"); // 所有要控制的权限的URL集合
			if (!allPrivilegeUrls.contains(url)) {
				return invocation.invoke();
			} else {
				// 如果这个功能需要控制，就是有权限才行
				if (user.hasPrivilegeByUrl(url)) {
					// >> 如果有权限，就放行
					return invocation.invoke(); // 放行
				} else {
					// >> 如果没有权限，就转到“没有权限的错误提示页面”
					return "privilegeError";
				}
			}
		}
	}
}
