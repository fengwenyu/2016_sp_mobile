package com.shangpin.core.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * Spring Ioc 上下文工具类
 * @author qinyingchun
 *
 */
public class SpringUtil implements ApplicationContextAware{
    
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
        
    }
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    /**
     * 获取对象
     * @param name
     * @return 返回给定名字注册的bean的实例
     */
    public static Object getBean(String name) throws BeansException{
        return applicationContext.getBean(name);
    }
    
    /**
     * 
     * @param name 
     * @param requiredType 对象的类型
     * @return 返回给定类型的对象的实例
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object getBean(String name, Class requiredType) throws BeansException{
        return applicationContext.getBean(name, requiredType);
    }
}
