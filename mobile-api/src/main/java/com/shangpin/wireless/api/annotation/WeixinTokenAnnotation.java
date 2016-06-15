package com.shangpin.wireless.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 微信Token
 * @author xupengcheng
 * @createDate 2014-1-13 上午09:09:42
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeixinTokenAnnotation {
}
