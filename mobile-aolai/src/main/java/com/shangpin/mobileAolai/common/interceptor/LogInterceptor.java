package com.shangpin.mobileAolai.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.vo.AccountVO;

/**
 * 日志拦截器
 * 
 * @Author zhouyu
 * @CreatDate 2012-11-30
 */
@SuppressWarnings("serial")
public class LogInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = SysContent.getRequest();
		HttpSession session = SysContent.getSession();
		// String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		// String url = namespace + actionName;
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames();
		String method = invocation.getProxy().getMethod();
		sb.append("userId:");
		AccountVO user = WebUtil.getSessionUser();
		sb.append(user == null || "".equals(user.getUserid())  ? "#" : user.getUserid());
		sb.append(",");
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getParameter(name);
			sb.append(name == null ? "#" : name);
			sb.append(":");
			sb.append("".equals(value) || value == null ? "#" : value);
			sb.append(",");
		}
		String platform = request.getParameter("platform");
		if("android".equals(platform)) {
			session.setAttribute("platform", platform);
		}
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(channelNo)) {
			if (CookieUtil.getCookieByName(request, Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				channelNo = CookieUtil.getCookieByName(request, Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				channelNo=StringUtils.isEmpty(channelNo)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:channelNo;
			}
			
		}
//		ActionContext.getContext().getValueStack().set(Constants.CHANNEL_PARAM_NAME, channelNo);
		String interfaceName = actionName + "!" + method;
		// 注册，登录，提交订单，请求支付，支付成功
		// 需要单独处理log的接口
		if (!"accountaction!register".equals(interfaceName)&&
			!"accountaction!login".equals(interfaceName)&&
			!"orderaction!saveorder".equals(interfaceName)&&
			!"aolaiindex!toAppStore".equals(interfaceName)
			) {
			final String params = sb.toString();
			if (!"".equals(params)){
				// 记录访问日志
				FileUtil.addLog(request, interfaceName, channelNo, params.substring(0, params.length() - 1));
			}else{
				// 记录访问日志
				FileUtil.addLog(request, interfaceName, channelNo,  "");
			}
		}
		return invocation.invoke();
	}
}