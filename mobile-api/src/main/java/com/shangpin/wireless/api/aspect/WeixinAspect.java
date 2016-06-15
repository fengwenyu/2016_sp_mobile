package com.shangpin.wireless.api.aspect;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shangpin.wireless.api.annotation.WeixinTokenAnnotation;
import com.shangpin.wireless.api.domain.Token;
import com.shangpin.wireless.api.exception.NotFoundTokenException;
import com.shangpin.wireless.api.exception.TokenExpiredException;
import com.shangpin.wireless.api.service.TokenService;
import com.shangpin.wireless.api.util.StringUtil;

@Aspect
@Component
public class WeixinAspect {

	private static final int MAX_RETRY_GET_TOKEN_COUNT = 3;// 获取token重试次数
	private static final int GET_TOKEN_RETRY_INTERVAL = 1000;// 获取token重试间隔
	private int maxRetryGetTokenCount = MAX_RETRY_GET_TOKEN_COUNT;
	private int retryGetTokenCount = 0;
	@Resource
	private TokenService tokenService;

	/**
	 * 获取Token，并给业务方法的Token参数赋值，如果未获取到Token，按重试策略重试
	 * @author xupengcheng
	 * @createDate 2014-1-13 上午09:15:36
	 * @param pjp
	 * @param wt
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("rawtypes")
    @Around("execution(* com.shangpin.wireless.api.service.*.*(..))  && @annotation(wt) ")
	@Order(0)
	public Object getToken(final ProceedingJoinPoint pjp, WeixinTokenAnnotation wt) throws Throwable {
		Object result = null;
		List tokenList = tokenService.find("from Token");
		if(tokenList == null || tokenList.size() == 0 ){
			Token temp =tokenService.updateToken();//
			result = proceed(pjp, temp);
		}else{
			Token token = (Token) tokenList.get(0);
			String status = token.getStatus();
			if (status == null || "".equals(status) || !"normal".equals(status)) {
				// 如果没有获取token（一般为token正在被更新）,重试
				while (retryGetTokenCount <= maxRetryGetTokenCount) {
					retryGetTokenCount++;
					token = tokenService.findToken();//
					if (!StringUtil.isNotEmpty(token.getCode())) {
						Thread.sleep(GET_TOKEN_RETRY_INTERVAL);
						this.getToken(pjp, wt);
					} else {
						result = proceed(pjp, token);
						break;
					}
				}
				if(retryGetTokenCount > maxRetryGetTokenCount){
					// 如果超出重试次数（一般为抢到更新token服务的服务器宕机），调用weixinService.resetTokenStatus()方法重置争抢，让其他服务器去更新token
					tokenService.resetTokenStatus();
					throw new NotFoundTokenException();
				}else{
					result = proceed(pjp, token);
				}      
			}else{
				//验证token时效性
				long effTime = token.getEfftime().getTime();
				long now = new Date().getTime();
				if((effTime - now) < 0){//如果失效了 重新获取并更新数据库
					tokenService.updateToken();// 更新Token
				}
				result = proceed(pjp, token);
			}
		}
		return result;
	}
	
	private Object proceed(ProceedingJoinPoint pjp, Token token) throws Throwable{
		Object[] objects = pjp.getArgs();
		Object result = null;
		if(token == null){
			result = pjp.proceed();
		}else if(StringUtil.isNotEmpty(token.getCode())){
			// 给token赋值
			if (objects != null && objects.length > 0) {
				for (Object obj : objects) {// 修改参数为最新的token
					if (obj instanceof Token) {
						obj = token;
					}
				}
				result = pjp.proceed(objects);
			}else{
				result = pjp.proceed();
			}
		}
		return result;
	}

	
	/**
	 * 捕获Token过期异常，如果过期，更新Token并重试
	 * @author xupengcheng
	 * @createDate 2014-1-13 上午09:17:44
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.shangpin.wireless.api.service.*.*(..)) && @annotation(wt) ")
	@Order(1)
	public Object updateToken(final ProceedingJoinPoint pjp, WeixinTokenAnnotation wt) throws Throwable  {
		int maxRetryCount = 1;// 如果token过期，重试，一般只重试一次
		int retryCount = 0;
		TokenExpiredException tokenExpiredException = null;
		while (retryCount <= maxRetryCount) {
			try {
				retryCount++;
				return pjp.proceed();
			} catch (TokenExpiredException ex) {
				tokenService.updateToken();// 更新Token
				tokenExpiredException = ex;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		throw tokenExpiredException;
	}
}
