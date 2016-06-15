/**
 * <pre>
 * Copyright:       Copyright(C) 2010-2014, shangpin.com
 * Filename:        com.shangpin.controller.UserController.java
 * Class:           UserController
 * Date:            2014-07-11
 * Author:          <a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0
 * Description:     
 *
 * </pre>
 **/

package com.shangpin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.web.utils.Constants;

/**
 * 用户管理的controller
 * 
 * @author sunweiwei
 */
@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /** 个人中心 */
    private static final String USERINFO = "user/home";
    /** 跳转到登录页面 */
    private static final String LOGIN = "user/login";

    @Autowired
    private SPBizOrderService bizOrderService;
    @Autowired
    private SPBizUserService bizUserService;

    /**
     * @Title: userinfo
     * @Description: 跳转到个人中心
     * @author zghw
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toUserInfo(HttpServletRequest request, ModelMap model) {
        try {
            User user = getSessionUser(request);
            if (user != null) {
                String statusType = "2";
                boolean hasWaitPayOrder = bizOrderService.hasWaitPayOrder(user.getUserid(), statusType);
                String userId = user.getUserid();
                UserBuyInfo buyInfo = bizUserService.getUserBuyInfoObj(userId, Constants.SITE_NO_SP);
                model.addAttribute("hasWaitPayOrder", hasWaitPayOrder);
                model.addAttribute("buyIfo", buyInfo);
                return USERINFO;
            }
        } catch (Exception e) {
            logger.error("error:", e);
        }
        return LOGIN;
    }
}