package com.shangpin.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangpin.web.utils.Constants;

public class ThridBuyInterceptor extends HandlerInterceptorAdapter {
	public static final Logger logger = LoggerFactory.getLogger(ThridBuyInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String query = request.getQueryString();
		//在如果请求中带有尚品客的channelNo和channelId 就放入session中
		if (query != null && query.length() > 0) {
			//判断是否属于尚品客来源,尚品客优先级高于网盟
			if(StringUtils.isNotBlank(request.getParameter("channelNo"))){
				 this.setShangPinKe(request);
			}else if(StringUtils.isNotBlank(request.getParameter("Source"))){
				 this.setWangMeng(request,response);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	private void setShangPinKe(HttpServletRequest request){
		//判断是否有sku
		String spuNo = request.getParameter("productNo");
		String channelNo = request.getParameter("channelNo");
		String channelId = request.getParameter("channelId");
		String topicId = request.getParameter("topicId");
		HttpSession session = request.getSession();
		
		//判断是否是固定活动
		String[] topics = Constants.THRID_TOPIC_LIST.split(",");
		session.removeAttribute(Constants.THRID_TOKEN_ChannelNo);
		for (String string : topics) {
			if(string.equals(channelNo)){
				session.setAttribute(Constants.THRID_TOKEN_ChannelNo, channelNo);
				break;
			}
		}
		
		//判断是否有商品的活动号
		if(StringUtils.isNotBlank(channelId)){
			logger.debug("ThridBuyInterceptor===========>======>spuNo:"+spuNo+"  topicId:"+topicId+"   channelNo:"+channelNo+"  channelId:"+channelId);
			boolean flag = false;
			
			//判断是否是从详情页进来的推荐
			if(StringUtils.isNotBlank(spuNo)){
				session.setAttribute(Constants.THRID_TOKEN_SpuNo, spuNo);
				flag = true;
			}
			//判断是否是从活动列表页进来的推荐
			if(StringUtils.isNotBlank(topicId)){
				session.setAttribute(Constants.THRID_TOKEN_TopicId, topicId);
				flag = true;
			}
			
			if(flag){
				//第三方传递过来的token均不为空，重置session中的token
				session.setAttribute(Constants.THRID_TOKEN_ChannelNo, channelNo);
				session.setAttribute(Constants.THRID_TOKEN_ChannelId, channelId);
			}
			
		}
	}
	
	 
	
	
	private void setWangMeng(HttpServletRequest request,HttpServletResponse response){
		String source = request.getParameter("Source");
		String campaign = request.getParameter("Campaign");
		String param = request.getParameter("Param");
		
		if(StringUtils.isNotBlank(campaign)){//cam的参数必须不为空
			String level = null;
			String expires = null;
			for(String cookieLevel :Constants.THRID_COOKIE_LEVELS){
				JSONObject obj = JSONObject.fromObject(cookieLevel);
				if(source.equals(obj.getString("source"))){
					source = obj.getString("source");
					level = obj.getString("level");
					expires = obj.getString("expires");
					break;
				}
			}
			if(level==null){
				level="1";//默认的最低权重和时间
				expires="14";
			}
			
			if(level!=null){
				String lastLevel = null;
				Cookie[] reqCookies = request.getCookies();
				if(reqCookies!=null && reqCookies.length>0){
					for (Cookie cookie : reqCookies) {
						if(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE.equals(cookie.getName())){
							for(String cookieLevel :Constants.THRID_COOKIE_LEVELS){
								JSONObject obj = JSONObject.fromObject(cookieLevel);
								if((cookie.getValue()).equals(obj.getString("source"))){
									lastLevel = obj.getString("level");
									break;
								}
							}
							break;
						}
					}
				}
				//验证cookie 并存储
				if(lastLevel == null|| Integer.parseInt(level)>=Integer.parseInt(lastLevel)){
					Cookie cookie_source = new Cookie(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE, source);
					Cookie cookie_param = new Cookie(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM, param);
					Cookie cookie_campaign = new Cookie(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN, campaign);
					Cookie[] cookies = {cookie_source,cookie_param,cookie_campaign};
					for (Cookie ck : cookies) {
						ck.setPath("/");
						ck.setMaxAge(Integer.parseInt(expires)*60*60*24);
						response.addCookie(ck);
					}
				}
			}
		}

	}
}
