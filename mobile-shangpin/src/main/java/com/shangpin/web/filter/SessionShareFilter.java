package com.shangpin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SessionShareFilter implements Filter{
	
//	private static final String LOGIN = "/user/login";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//			// 请求的路径
//			HttpServletRequest req = (HttpServletRequest) request;
//			HttpServletResponse rep = (HttpServletResponse) response;
//	    	String contextPath = req.getContextPath();
//	        String url = req.getServletPath().toString();
//		    //获取JedisUtil的实例
//		    JedisUtil jedisUtil = JedisUtil.getInstance();
//		    
//		    Subject subject = SecurityUtils.getSubject();
//		    //false如果没有session返回null
//		    Session session = subject.getSession(false);
//		    if(session == null){
//		    	//如果为空，放行登录
//		    	System.out.println("session为空,放行");
//		    }else{
//		    	//将以userName 为Key的session放入radis缓存
//		    	byte[] serializeSession = SerializeUtil.serialize(session);
//		    	String userName = request.getParameter("userName");
//		    	if(StringUtils.isEmpty(userName)){
//		    		StringBuilder urlBuilder = new StringBuilder(contextPath).append(LOGIN).append("?redirectURL=" + URLEncoder.encode(url, Constants.DEFAULT_ENCODE));
//		            rep.sendRedirect(urlBuilder.toString());
//		    	}
//		    	jedisUtil.new Strings().set(userName.getBytes(), serializeSession);
//		    	System.out.println("session存入了radis缓存");
//		    }
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
