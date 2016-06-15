package com.shangpin.mobileShangpin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.shangpin.mobileShangpin.common.util.SysContent;

/**
 * 
 * 
 * @author: vinceyu
 * @date: 2012-10-26
 */
//@Aspect
//@Component
public class LogAspectInterceptor {

	/** 日志服务类 */
	private static Log logger = LogFactory.getLog(LogAspectInterceptor.class);

	// 设置切点，logPointcut()方法就是代理方法，代理service下的方法
	@Pointcut("execution (* com.shangpin.mobileShangpin.account.service.*.*(..))")
	public void logPointcut() {
	}

//	@Around("logPointcut()")
	public Object exceptionHandleBody(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("GenericLoggerBean:"+pjp.getTarget().getClass());
		long startTime = System.currentTimeMillis();
		try
		{
			System.out.println("Beginning method : " + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "()");
		logger.warn("Beginning method : " + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "()");
		
		
			Object result = pjp.proceed();
			return result;
		}
		catch ( Exception e )
		{
			logger.warn(pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "() invoke error");
			logger.warn("error info [" + e.getMessage() + "]");
		} finally
		{
			logger.warn("Ending method : " + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "()");
			logger.warn("Method invocation time : " + ( System.currentTimeMillis() - startTime ) + " ms.");
		}
		return null;
	}

	// 设置通知，即再执行logPointcut()方法时执行SecurityHandleBody方法
	@Around("logPointcut()")
	public Object SecurityHandleBody(ProceedingJoinPoint pjp) throws Throwable {

		// 连接点方法返回值
		Object retVal = null;
		// 获取将要执行的方法名称
		String method = pjp.getSignature().getName();
		pjp.getTarget().getClass().getName();

		// 获取拦截方法中得参数
		Object[] obj = pjp.getArgs();
		StringBuffer para = new StringBuffer();
		if (null != obj && obj.length > 0) {

			for (Object o : obj) {
				para.append(o.toString());
			}
		}
		HttpServletRequest request = SysContent.getRequest();
		HttpServletResponse response = SysContent.getResponse();
		HttpSession session = SysContent.getSession();
		String ip = request.getRemoteAddr();
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = request.getHeader("imei");
		String os = request.getHeader("os");// 手机平台:ios、android
		String osv = request.getHeader("osv");// 系统版本
		String productNo = request.getHeader("p");// 产品号
		String channelNo = request.getHeader("ch");// 渠道号
		String version = request.getHeader("ver");// 版本号
		String apn = request.getHeader("apn");// 接入点
		String wh = request.getHeader("wh");// 屏幕宽高640x960
		String mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
		String model = request.getHeader("model"); // 手机型号(iPhone 4S)
		String operator = request.getHeader("operator"); // 运营商
		logger.info("className：" + pjp.getTarget().getClass().getName()
				+ "; method name: " + method + "; parameter:" + para.toString()
				+ "; url" + request.getRequestURI() + " ; ip:"
				+ request.getRemoteAddr());

		retVal = pjp.proceed();
		return retVal;
	}


}