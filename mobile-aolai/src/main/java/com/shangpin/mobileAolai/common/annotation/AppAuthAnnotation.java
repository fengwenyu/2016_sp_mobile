package com.shangpin.mobileAolai.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机客户端登录权限拦截器
 * @author mengqiang@shangpin.com
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD)
public @interface AppAuthAnnotation {

}
