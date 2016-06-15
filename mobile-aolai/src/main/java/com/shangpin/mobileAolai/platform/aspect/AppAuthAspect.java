package com.shangpin.mobileAolai.platform.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weibo4sina.util.URLEncodeUtils;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.AccountService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;


@Aspect
@Component
public class AppAuthAspect {

	private final Log log = LogFactory.getLog(AppAuthAspect.class);

	@Autowired
	private AccountService accountService;

	@Around("com.shangpin.mobileAolai.platform.aspect.SystemArchitecture.appAuth()")
	public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = SysContent.getRequest();
		HttpServletResponse response = SysContent.getResponse();
		String paramsStr = request.getQueryString();
		String ua =request.getHeader("user-agent").toLowerCase();
        String appstr = "AolaiAndroidApp";
        boolean appFlag = false;
         if(ua.indexOf(appstr.toLowerCase())>-1){
             if(ua.indexOf("4.0.0")>-1){
                 appFlag = true;
             }               
         } 
		
		try {
			Object result = null;
			AccountVO user = WebUtil.getSessionUser();
			if (user == null || !StringUtil.isNotEmpty(user.getName())) {// 判断用户是否已在mobile站登录				
				String origin = SysContent.getRequest().getHeader(Constants.APP_COOKIE_NAME_ORIGIN);		
				String callbackUrl = URLEncodeUtils.encodeURL(Constants.APP_CALLBACK_LOGIN+((SysContent.getRequest().getQueryString()==null)?"":"?"+SysContent.getRequest().getQueryString()));				
//				String callbackUrl ="http://m.shangpin.com/motherDay/index.jsp";
				if (origin != null && "app".equals(origin)) {// 判断用户来源
					String token = SysContent.getRequest().getHeader(Constants.APP_COOKIE_NAME_TOKEN);
					String userId = SysContent.getRequest().getHeader(Constants.APP_COOKIE_NAME_UID);
					String imei = SysContent.getRequest().getHeader(Constants.APP_COOKIE_NAME_IMEI);					
					if (StringUtil.isNotEmpty(token,userId,imei)) {// 拿到APP登录的Token
						if (accountService.loginFromApp(userId,imei,token)) {// 调用API判断是否登录，如果登录写入session
							result = joinPoint.proceed();
						} else {
							if(appFlag){//安卓4.0兼容联合登陆问题
							    response.sendRedirect(Constants.APP_ANDROID_NOT_LOGIN_URL+"?"+paramsStr+"&title=login&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
							}else{							    
							    response.sendRedirect(Constants.APP_NOT_LOGIN_URL+"?"+paramsStr+"&title=login&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
							}
						}
					} else {
						if(appFlag){//安卓4.0兼容联合登陆问题
						    response.sendRedirect(Constants.APP_ANDROID_NOT_LOGIN_URL+"?"+paramsStr+"&title=login&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
		                 }else{		                     
		                     response.sendRedirect(Constants.APP_NOT_LOGIN_URL+"?"+paramsStr+"&title=login&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
		                 }
					}
				} else {
					if(appFlag){//安卓4.0兼容联合登陆问题
					    response.sendRedirect(Constants.APP_ANDROID_NOT_LOGIN_URL+"?"+paramsStr+"&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
		            }else{		                
		                response.sendRedirect(Constants.APP_NOT_LOGIN_URL+"?"+paramsStr+"&"+Constants.APP_NAME_CALLBACK_URL+"="+callbackUrl.replaceAll("%26", "8uuuuu8"));
		            }
				}
			} else {
				result = joinPoint.proceed();
			}
			return result;
		} catch (Throwable e) {
			log.error("An exception  " + e + " has been thrown in " + joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}

}
