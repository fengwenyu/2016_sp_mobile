package com.aolai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.aolai.web.utils.Constants;
import com.aolai.web.utils.CookieUtil;
import com.shangpin.biz.bo.User;

public class BaseController {

    /**
     * 获得当前session中的用户信息
     * 
     * @param request
     *            HttpServletRequest
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        return user;
    }
    
    /**
     * 获取渠道号
     * @return
     */
    public String getChannelNo(HttpServletRequest request){
    	String channelNo = request.getParameter(Constants.CHANNEL_PARAM_NAME);
    	if (StringUtils.isEmpty(channelNo)) {
    		if(CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null){
    			channelNo = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
    		}else{
    			channelNo = Constants.AOLAI_WAP_DEFAULT_CHANNELNO;
    		}
    	}
    	return channelNo;
    }

    /**
     * 得到userId
     * @param request
     * @return
     */
    public String getUserId(HttpServletRequest request){
        User user = getSessionUser(request);
        if(user != null){
        	 return user.getUserid();
        }
        return null;
    }
}
