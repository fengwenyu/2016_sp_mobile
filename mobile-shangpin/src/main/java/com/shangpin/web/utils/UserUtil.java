package com.shangpin.web.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shangpin.biz.utils.ClientUtil;

public class UserUtil {
	
	/**
	 * 检测是否是app  ture:是; false:不是
	 * @param request
	 * @return
	 */
	public static boolean isApp(HttpServletRequest request){
		String ua1 = request.getHeader("User-Agent");//iso
    	String ua2 = request.getHeader("origin");//安卓
    	
    	if(org.apache.commons.lang3.StringUtils.isNotBlank(ua2)){
    		ua2 = ua2.toLowerCase();
    	}
    	
    	if(ClientUtil.CheckApp(ua1)||ClientUtil.CheckOrigin(ua2)){
    		return true;
    	}
    	return false;
	}
	
	
	/**
	 * 得到用户id  ture:是; false:不是
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request){
		boolean isApp = isApp(request);
		String userId;
		if(isApp){
			userId=request.getHeader(Constants.APP_COOKIE_NAME_UID);
		}else{
			userId =(String) request.getSession().getAttribute(Constants.SESSION_USERID);
		}
		return userId;
	}
	
	/**
	 * 
	 * getThridCookie:<br/>
	 * (TODO 描述这个方法的作用). <br/>
	 * @param channelNo 对应网盟的参数为source
	 * @param channelId 对应网盟的参数为campaign
	 * @param request
	 * @return
	 */
	public static Map<String, String> getThridCookie(HttpServletRequest request){
		String channelNo = (String) request.getSession().getAttribute(Constants.THRID_TOKEN_ChannelNo);
    	String channelId = (String) request.getSession().getAttribute(Constants.THRID_TOKEN_ChannelId);
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(channelNo)||StringUtils.isBlank(channelId)){
			String wm_source=null;
			String wm_campaign=null;
			String wm_param=null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null&&cookies.length>0){
				for (Cookie cookie : cookies) {
					if(wm_source==null ||wm_campaign==null ||wm_param==null){
						if(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE.equals(cookie.getName().trim())){
							wm_source=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE, wm_source);
						}else if(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN.equals(cookie.getName().trim())){
							wm_campaign=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN, wm_campaign);
						}else if(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM.equals(cookie.getName().trim())){
							wm_param=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM, wm_param);
						}
					}
				}
			}
			if(wm_source!=null && wm_campaign!=null){
				map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CHANNEL_TYPE, "1");
				return map;
			}
			
		}
		//提交订单接口不需要尚品客信息，尚品客信息在购物车控制
		/*else{
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE, channelNo);
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN, channelId);
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM, "");
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CHANNEL_TYPE, "0");//THRID_COOKIE_CHANNEL_TYPE 尚品客传 0 ;  网盟 传1
			return map;
		}*/
		
		return null;
	}
	/**
	 *
	 * getThridCookie:<br/>
	 * @param channelNo 对应网盟的参数为source
	 * @param channelId 对应网盟的参数为campaign
	 * @param request
	 * @return
	 */
	public static Map<String, String> getThridBothCookie(HttpServletRequest request){
		String channelNo = (String) request.getSession().getAttribute(Constants.THRID_TOKEN_ChannelNo);
		String channelId = (String) request.getSession().getAttribute(Constants.THRID_TOKEN_ChannelId);
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(channelNo)||StringUtils.isBlank(channelId)){
			String wm_source=null;
			String wm_campaign=null;
			String wm_param=null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null&&cookies.length>0){
				for (Cookie cookie : cookies) {
					if(wm_source==null ||wm_campaign==null ||wm_param==null){
						if(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE.equals(cookie.getName().trim())){
							wm_source=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE, wm_source);
						}else if(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN.equals(cookie.getName().trim())){
							wm_campaign=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN, wm_campaign);
						}else if(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM.equals(cookie.getName().trim())){
							wm_param=cookie.getValue();
							map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM, wm_param);
						}
					}
				}
			}
			if(wm_source!=null && wm_campaign!=null){
				map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CHANNEL_TYPE, "1");
				return map;
			}

		}else{
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE, channelNo);
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN, channelId);
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM, "");
			map.put(com.shangpin.biz.utils.Constants.THRID_COOKIE_CHANNEL_TYPE, "0");//THRID_COOKIE_CHANNEL_TYPE 尚品客传 0 ;  网盟 传1
			return map;
		}

		return null;
	}
}
