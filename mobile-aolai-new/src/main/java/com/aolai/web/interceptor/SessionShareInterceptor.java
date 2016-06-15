package com.aolai.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangpin.utils.JedisUtil;
import com.shangpin.utils.SerializeUtil;

public class SessionShareInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		    //获取jedis的实例
		    JedisUtil jedisUtil = JedisUtil.getInstance();
//		    Jedis jedis = jedisUtil.getJedis();
		    
		    String userName = request.getParameter("userName");
		    HttpSession session = request.getSession();
		    if(session == null){
		    	//如果为空，放行登录
		    	System.out.println("session为空,放行");
		    	return true;
		    }else{
		    	//将以userName 为Key的session放入radis缓存
		    	byte[] serializeSession = SerializeUtil.serialize(session);
//		    	jedis.set(userName.getBytes(), serializeSession);
//		    	//回收jedis
//		    	jedisUtil.returnJedis(jedis);
		    	jedisUtil.new Strings().set(userName.getBytes(), serializeSession);
		    	System.out.println("session存入了radis缓存");
		    }
		    
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
}
