package com.shangpin.mobileAolai.platform.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切入点定义
 * @author mengqiang@shangpin.com
 *
 */
@Aspect
public class SystemArchitecture {
	
	@Pointcut("@annotation(com.shangpin.mobileAolai.common.annotation.AppAuthAnnotation)")// the pointcut expression
	public void appAuth() {}// the pointcut signature
}
