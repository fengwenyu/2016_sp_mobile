package com.shangpin.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.service.SPBizWeixinService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;

public class UserInterceptor extends HandlerInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    private static final String LOGIN = "/login";
    private static final String WXLOGIN = "/wxLogin";
    private static final String RETURN_URL = "back";
    /** ajax请求响应头会有，x-requested-with */
    private static final String X_REQUESTED_WITH = "x-requested-with";
    /** 异步请求标准 */
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    @Autowired
    private SPBizUserService userService;
    @Autowired
    private SPBizWeixinService weixinService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String processURL = "";
        String type = request.getMethod();
        if ("POST".equals(type)) {
            processURL = getRequestUrl(request);
        } else if ("GET".equals(type)) {
            processURL = getProcessURL(request);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        logger.info("interceptor中session的user："+JsonUtil.toJson(user));
        if (StringUtils.isEmpty(user)) {
            String useragent = request.getHeader("User-Agent");
            String origin = request.getHeader(Constants.APP_COOKIE_NAME_ORIGIN);// 暂时不用此值判断
            // 用户来自微信
            String code = request.getParameter("code"); // 微信code
            if (!StringUtils.isEmpty(code)) {
                if (ClientUtil.CheckMircro(useragent) && processURL.indexOf(LOGIN) <= -1) {
                    String wxId = weixinService.getWeixinId(Constants.WX_OAUTH_URL, Constants.WX_APP_ID, Constants.WX_SECRET, code, Constants.GRANT_TYPE);
                    if (!StringUtils.isEmpty(wxId)) {
                        logger.debug("UserInterceptor wxId={}", wxId);
                        session.setAttribute(Constants.WX_ID_NAME, wxId);
                        response.sendRedirect(wxLoginURL(request, processURL));
                        return false;
                    }
                }
            }

            // 用户来自APP
            if (ClientUtil.CheckOrigin(origin) || ClientUtil.CheckApp(useragent)) {// 判断用户来源
                String paramsStr = request.getQueryString();
                String token = request.getHeader(Constants.APP_COOKIE_NAME_TOKEN);
                String userId = request.getHeader(Constants.APP_COOKIE_NAME_UID);
                boolean flag = loginFromApp(token, userId, session);
                logger.debug("===> app login token={},userId={},flag={}", token, userId, flag);
                if (!flag) {// 调用API判断是否登录，如果登录写入session
                /*	if(paramsStr.indexOf("isLogin=1")>-1){
                		return true;
                	}*/
                    String redirectUrl = appRedirectUrl(paramsStr, processURL);
                    logger.debug("===> app login url={}", redirectUrl);
                    response.sendRedirect(redirectUrl);
                    return false;
                }
                return true;
            }
            // 用户来自m站
            String xRequestedWith = request.getHeader(X_REQUESTED_WITH);
            if (xRequestedWith != null && xRequestedWith.equalsIgnoreCase(XML_HTTP_REQUEST)) {
                // 如果是ajax请求 timeout表示了未登录 或者session超时
                response.setHeader("sessionstatus", "timeout");
                // 把返回地址交给ajax处理
                response.setHeader("locationURL", loginURL(request, ""));
                response.setHeader("locationURLAll", loginURL(request, processURL));
                return false;
            }
            // 被拦截，重定向到login界面
            response.sendRedirect(loginURL(request, processURL));
            return false;

        }
        return true;
    }

    private String appRedirectUrl(String paramsStr, String callbackUrl) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(Constants.APP_NOT_LOGIN_URL);
        url.append("?");
        if (!StringUtils.isEmpty(paramsStr)) {
            url.append(paramsStr);
            url.append("&");

        }
        url.append("title=login&");
        url.append(Constants.APP_NAME_CALLBACK_URL);
        url.append("=");
        url.append(Constants.SHANGPIN_URL);
        url.append(URLEncoder.encode(callbackUrl, Constants.DEFAULT_ENCODE).replaceAll("%26", "8uuuuu8"));
        String redirectUrl = url.toString();
        logger.debug("Else AppAuthAspect 1 callbackUrl={},redirectUrl={} ", callbackUrl.replaceAll("%26", "8uuuuu8"), redirectUrl);
        return redirectUrl;
    }

    /**
     * 
     * 根据token自动登陆
     * 
     * @param request
     *            request请求
     * @param returnURL
     *            登录后返回的URL
     * @return boolean
     * @author liling
     * @throws Exception
     */
    private boolean loginFromApp(String token, String userId, HttpSession session) throws Exception {
        User user = null;
        String mtoken = AESUtil.decrypt(token, Constants.APP_TOKEN_KEY);
        if (StringUtils.hasText(mtoken)) {
            String[] arr = mtoken.split("[|]");
            if (arr.length == 2) {
                String userName = arr[0];
                String password = arr[1];
                user = userService.login(userName, password);
                if (addUserToSession(user, session)) {
                    return true;
                }
            }
        }
        if (StringUtils.isEmpty(userId)||"null".equals(userId)) {// 拿到APP登录的userId
            return false;
        }
        user = userService.findUserByUserId(userId);
        return addUserToSession(user, session);
    }

    /**
     * 
     * @param user
     * @param session
     * @return
     */
    private boolean addUserToSession(User user, HttpSession session) {
        if (!StringUtils.isEmpty(user)) {
            session.setAttribute(Constants.SESSION_USER, user);
            return true;
        }
        return false;
    }

    /**
     * 
     * 取得微信绑定登录URL地址
     * 
     * @param request
     *            request请求
     * @param returnURL
     *            登录后返回的URL
     * @return 登录对应的url并携带返回url参数
     * @author liling
     * @throws UnsupportedEncodingException
     */
    private String wxLoginURL(HttpServletRequest request, String returnURL) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();
        String ctx = request.getContextPath();
        if (!StringUtils.isEmpty(ctx)) {
            url.append(ctx);
        }
        url.append(WXLOGIN).append("?");
        url.append(RETURN_URL).append("=");
        if (!StringUtils.isEmpty(returnURL)) {
            String encoderReturnURL = URLEncoder.encode(returnURL, Constants.DEFAULT_ENCODE);
            url.append(encoderReturnURL);
        }
        return url.toString();
    }

    /**
     * 
     * 取得登录URL地址
     * 
     * @param request
     *            request请求
     * @param returnURL
     *            登录后返回的URL
     * @return 登录对应的url并携带返回url参数
     * @author zghw
     * @throws UnsupportedEncodingException
     */
    private String loginURL(HttpServletRequest request, String returnURL) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();
        String ctx = request.getContextPath();
        if (!StringUtils.isEmpty(ctx)) {
            url.append(ctx);
        }
        url.append(LOGIN).append("?");
        url.append(RETURN_URL).append("=");
        if (!StringUtils.isEmpty(returnURL)) {
            String encoderReturnURL = URLEncoder.encode(returnURL, Constants.DEFAULT_ENCODE);
            url.append(encoderReturnURL);
        }
        return url.toString();
    }

    /**
     * 取得当前运行相对的url路径(暂不支持对Post方式请求中的参数进行处理)
     * 
     * @param request
     * @return
     * @author zghw
     */
    private String getProcessURL(HttpServletRequest request) {
        String sp = request.getServletPath();
        StringBuilder url = new StringBuilder();
        url.append(sp);
        if (!StringUtils.isEmpty(request.getQueryString())) {
            url.append("?").append(request.getQueryString());
        }
        logger.debug("ProcessURL :" + url);
        return url.toString();
    }

    /**
     * 
     * @Title: getRequestUrl
     * @Description:获取拦截地址的请求地址（包含参数名和参数值）
     * @param
     * @return String
     * @throws
     * @Create By qinyingchun
     * @Create Date 2014年12月18日
     */
    public String getRequestUrl(HttpServletRequest request) {
        String request_url = request.getServletPath();
        logger.debug(request_url);
        // StringBuffer sbf = request.getRequestURL();
        Map<String, String[]> parameters = request.getParameterMap();
        if (0 == parameters.size()) {
            return request_url;
        }
        Set<Entry<String, String[]>> keys = parameters.entrySet();
        Iterator<Entry<String, String[]>> iterator = keys.iterator();
        StringBuffer sb = new StringBuffer();
        Entry<String, String[]> entry;
        while (iterator.hasNext()) {
            String value = "";
            entry = iterator.next();
            String keyName = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            logger.debug(keyName + ":" + value);
            sb.append(keyName + "=" + value + "&");
        }
        logger.debug("requestUrl:" + sb.toString());
        return request_url + "?" + sb.substring(0, sb.lastIndexOf("&"));
    }
}
