package com.shangpin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.User;
import com.shangpin.web.utils.Constants;

public class BaseController {
    
    protected int MAX_VERSION = 290; //最大版本号

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
     * 移除当前session中的用户信息
     * 
     * @param request
     *            HttpServletRequest
     * @return
     */
    protected void removeSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_USER);
        session.removeAttribute(Constants.SESSION_USERID);
        session.removeAttribute(Constants.WX_ID_NAME);
        session.invalidate();
    }

    /**
     * 
     * @Title: isUser
     * @Description:检查用户是否登录
     * @param
     * @return boolean
     * @throws
     * @Create By qinyingchun
     * @Create Date 2014年11月10日
     */
    protected boolean isUser(HttpServletRequest request) {
        User user = getSessionUser(request);
        if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getUserid())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param request
     * @return
     */
    protected String getUserId(HttpServletRequest request) {
        if (isUser(request)) {
            return getSessionUser(request).getUserid();
        } else {
            return null;
        }
    }

    /**
     * 
     * @param request
     * @return
     */
    protected String getSessionId(HttpServletRequest request) {
        return request.getSession().getId();
    }

}
