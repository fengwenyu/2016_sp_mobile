/**
 * <pre>
 * Copyright:		Copyright(C) 2010-2014, shangpin.com
 * Filename:		com.shangpin.controller.UserController.java
 * Class:			UserController
 * Date:			2014-07-11
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0
 * Description:		
 *
 * </pre>
 **/

package com.aolai.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aolai.web.utils.ConfigContainer;
import com.aolai.web.utils.Constants;
import com.aolai.web.utils.CookieUtil;
import com.aolai.web.utils.MesConstants;
import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ALBizCouponService;
import com.shangpin.biz.service.ALBizUserService;
import com.shangpin.biz.service.CaptchaService;
import com.shangpin.utils.JsonUtil;

/**
 * 用户管理的controller
 * 
 * @author sunweiwei
 */
@Controller
public class LoginController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /** 跳转到登录页 */
    private static final String LOGIN = "user/login";
    /** 跳转到注册页 */
    private static final String REGISTER = "user/register";
    /** 跳转到首页 */
    private static final String INDEX_PAGE = "redirect:/index";
    /** 跳转到注册条款 */
    private static final String AGREEMENT = "user/agreement";
    /** 发券页面 */
    private static final String SEND_COUPON = "coupon/send_coupon";
    @Autowired
    private ALBizUserService userService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private ALBizCouponService couponService;

    /**
     * 跳转到登录页
     * 
     * @param map
     * @param back
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map, String back) {
        logger.debug("back URL :" + back);
        if (StringUtils.hasText(back)) {
            map.put("back", back);

        }
        System.out.println(back);
        return LOGIN;
    }

    /**
     * 跳转到注册页
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String toRegister() {

        return REGISTER;
    }

    /**
     * 跳转到注册条款
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/agreement", method = RequestMethod.GET)
    public String toGreement() {

        return AGREEMENT;
    }

    /**
     * 注册
     * 
     * @author zghw
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerVerify(String captcha, String userName, String password, String back, String gender, @RequestHeader(Constants.USER_AGENT) String userAgent,
            HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        if (isEmpty(userName, password, gender, captcha)) {
            map.put("msg", MesConstants.REG_FAIL_LACK);
            return REGISTER;
        }
        boolean result = captchaService.verifyCaptcha(request.getSession(), captcha);
        if (!result) {
            map.put("msg", MesConstants.REG_FAIL_CAPTCHA);
            return REGISTER;
        }
        String json = userService.register(userName, null, password, gender, null, Constants.TYPE_EMAIL, null);
        ResultObjOne<User> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>() {
        });
        String msg = obj.getMsg();
        if (!obj.isSuccess()) {
            map.put("msg", msg);
            return REGISTER;
        }
        User user = obj.getContent();
        if (user == null) {
            return LOGIN;
        }
        //String channelNo = getChannelNo(request);
        //userService.synchronizationAccount(user, channelNo, Constants.PRODUCT_10000, userAgent);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_USER, user);

        // TODO记录日志
        logger.info("register success:userId={},userName={},gender={}", user.getUserid(), user.getName(), user.getGender());
        if (isEmpty(msg)) {
            map.put("msg", MesConstants.SUCCESS);
        }
        // 发券
        CouponPeriod couponPeriod = ConfigContainer.getCouponPeriod();
        boolean isSendCoupon = couponService.isSendCoupon(couponPeriod);
        if (isSendCoupon) {
            ResultBase couponObj = couponService.sendActivation(user.getUserid(), Constants.SHOP_TYPE_AOLAI, Constants.COUPON + couponPeriod.getCouponNo());
            if (couponObj.isSuccess()) {
                map.put("couponDesc", couponPeriod.getCouponDesc());
                return SEND_COUPON;
            }
        }
        if (!StringUtils.isEmpty(back)) {
            // 回调URL
            logger.info("backURL :" + back);
            return "redirect:" + back;
        }
        return INDEX_PAGE;
    }

    /**
     * 登录
     * 
     * @param userName
     * @param password
     * @param request
     * @param map
     * @return
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String captcha, String userName, String password, String back, HttpServletRequest request, HttpServletResponse response, ModelMap map)
            throws Exception, IOException {
        // 从session中获取user
        User user = getSessionUser(request);
        if (!StringUtils.isEmpty(user)) {
            logger.info("Redirect to index");
            return INDEX_PAGE;
        }
        String msg = "";
        map.put("back", back);
        // 调用主站接口校验登录
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            try {
                boolean result = captchaService.verifyCaptcha(request.getSession(), captcha);
                if (!result) {
                    msg = "验证码错误，请重新输入...";
                    map.put("msg", msg);
                    return LOGIN;// 跳转到登录页
                }
                user = userService.login(userName, password);

                if (user != null && result) {
                    // 将user放入session中
                    HttpSession session = request.getSession();
                    session.setAttribute(Constants.SESSION_USER, user);
                    // 将userId放入session中
                    String userId = user.getUserid();
                    session.setAttribute(Constants.SESSION_USERID, userId);
                    if (!StringUtils.isEmpty(back)) {
                        map.remove("back");
                        // 不为空就返回之前请求的地址
                        logger.info("backURL :" + back);
                        return "redirect:" + back;
                    }
                    return INDEX_PAGE;
                } else {
                    msg = "用户名或密码错误，请重新输入...";
                    map.put("msg", msg);
                    return LOGIN;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("Exception:" + e);
            }

        }
        return LOGIN;
    }

    /**
     * 注销用户
     * 
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        try {
        	// 清除session中user
            request.getSession().removeAttribute(Constants.SESSION_USER);
            return INDEX_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Logout exception：" + e);
        }
        return null;
    }

    /**
     * 获取渠道号
     * 
     * @param request
     * @return
     */
    public String getChannelNo(HttpServletRequest request) {
        String channelNo = request.getParameter(Constants.CHANNEL_PARAM_NAME);
        if (StringUtils.isEmpty(channelNo)) {
            if (CookieUtil.getCookieByName(request, Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
                channelNo = CookieUtil.getCookieByName(request, Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
            } else {
                channelNo = StringUtils.isEmpty(channelNo) ? Constants.AOLAI_WAP_DEFAULT_CHANNELNO : channelNo;
            }

        }
        return channelNo;
    }

    /**
     * 生成验证码
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void writeImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            captchaService.writeImage(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("writeImage :" + e);
        }
    }

    /**
     * Check whether the given String list is empty.
     * 
     * @param 多个str参数列表
     * @return 如果任意一个参数string 是空的 则返回true
     */
    private boolean isEmpty(String... str) {
        for (String param : str) {
            if (StringUtils.isEmpty(param))
                return true;
        }
        return false;
    }
}
