package com.shangpin.cache.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.shangpin.cache.CacheKey;
import com.shangpin.cache.Cacheable;
import com.shangpin.cache.Cacheable.KeyMode;
import com.shangpin.utils.GenericsUtil;
import com.shangpin.utils.JedisUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.MD5Util;

@Aspect
@Component
public class CacheableAop {

	private static Logger log =LoggerFactory.getLogger(CacheableAop.class);
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    
    

    @Around("@annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {
    	String key = getCacheKey(pjp, cache);
    	//ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
    	//Object value = valueOper.get(key); // 从缓存获取数据
    	Object value=null;
    	String objJson=JedisUtil.getInstance().get(key);
    	if(StringUtils.isEmpty(objJson)){//1.缓存不存在那么调取缓存数据处理
    		value=pjp.proceed();
    		if(value!=null){
    			JedisUtil.getInstance().set(key, JsonUtil.toJson(value));
    			if (cache.expire() > 0) {
    				JedisUtil.getInstance().expire(key, cache.expire());
    			}
    		}
    		return value;
    	}else{//2.缓存存在那么直接读取缓存，反序列化并返回
    		Signature signature=pjp.getSignature();
    		MethodSignature ms=((MethodSignature) signature);
    		Type returnType=getReturnType(ms);
    		if(returnType!=null){
    			return JsonUtil.fromJson(objJson, returnType);
    		}else{
    			log.warn("{}方法{}返回类型是空",ms.getDeclaringTypeName(),ms.getMethod().getName());
    		}
    		return value;
    	}
    }
    
    public Type getReturnType(MethodSignature ms){
    	Class<?> c=ms.getDeclaringType();
    	return GenericsUtil.getReturnType(ms.getMethod().getName(), c);
    }
    
    /*@Around("@annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {
        String key = getCacheKey(pjp, cache);
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        Object value = valueOper.get(key); // 从缓存获取数据
        if (value != null) {
            return value; // 如果有数据,则直接返回
        }
        value = pjp.proceed(); // 跳过缓存,到后端查询数据
        if (cache.expire() <= 0) { // 如果没有设置过期时间,则无限期缓存
            valueOper.set(key, value);
        } else { // 否则设置缓存时间
            valueOper.set(key, value, cache.expire(), TimeUnit.SECONDS);
        }
        return value;
    }
*/
    /**
     * 获取缓存的key值
     * 
     * @param pjp
     * @param cache
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp, Cacheable cache) {


        StringBuilder buf = new StringBuilder();
        buf.append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName());
        if (cache.key().length() > 0) {
            buf.append(".").append(cache.key());
        }

        Object[] args = pjp.getArgs();
        if (cache.keyMode() == KeyMode.DEFAULT) {
            Annotation[][] pas = ((MethodSignature) pjp.getSignature()).getMethod().getParameterAnnotations();
            for (int i = 0; i < pas.length; i++) {
                for (Annotation an : pas[i]) {
                    if (an instanceof CacheKey) {
                        buf.append(".").append(args[i].toString());
                        break;
                    }
                }
            }
        } else if (cache.keyMode() == KeyMode.BASIC) {
            for (Object arg : args) {
                if (arg instanceof String) {
                    buf.append(".").append(arg);
                } else if (arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
                    buf.append(".").append(arg.toString());
                } else if (arg instanceof Boolean) {
                    buf.append(".").append(arg.toString());
                }
            }
        } else if (cache.keyMode() == KeyMode.ALL) {
            for (Object arg : args) {
                buf.append(".").append(arg.toString());
            }
        }

        return cache.key() + MD5Util.MD5(buf.toString());
    }
}