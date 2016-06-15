package com.shangpin.api.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sunweiwei
 * @Date 2014-10-18
 */
@Component
@Aspect
public class LoggerIntercept {

    private static final Logger logger = LoggerFactory.getLogger(LoggerIntercept.class);

    /**
     * biz项目中Service的每一个方法执行时间拦截
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.shangpin.biz.service..*.*(..))")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        StringBuffer sb = new StringBuffer("[" + className + "]-[" + methodName + "] takes ");
        Object object = joinPoint.proceed();
        sb.append(System.currentTimeMillis() - start).append("ms");
        logger.debug("Biz Log Intercept ---{}", sb.toString());
        return object;
    }
}
