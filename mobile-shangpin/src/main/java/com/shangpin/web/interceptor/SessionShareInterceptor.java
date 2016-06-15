package com.shangpin.web.interceptor;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 本拦截器只判断当前访问的是否是客户端，如果是客户端且可以拿到客户端的用户，M站自动登录
 *
 * @author sunweiwei
 *
 */
public class SessionShareInterceptor extends HandlerInterceptorAdapter {

    public static final Logger logger = LoggerFactory.getLogger(SessionShareInterceptor.class);

    @Autowired
    private SPBizUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /** 如果是app登录，直接登录 */
        String userAgent = request.getHeader(Constants.APP_COOKIE_NAME_UA);
        String origin = request.getHeader(Constants.APP_COOKIE_NAME_ORIGIN);

        if (ClientUtil.CheckOrigin(origin) || ClientUtil.CheckApp(userAgent)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_USER);

            /** 如果用户存在，直接跳转 */
            if (!StringUtils.isEmpty(user)) {
                return true;
            }

            String token = request.getHeader(Constants.APP_COOKIE_NAME_TOKEN);
            String userId = request.getHeader(Constants.APP_COOKIE_NAME_UID);
            /** 给app用户登录 */
            SessionUtil.loginFromApp(token, userId, session, userService);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
