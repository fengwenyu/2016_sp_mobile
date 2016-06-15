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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.ALBizUserService;

/**
 * 用户管理的controller
 * 
 * @author sunweiwei
 */
@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(UserController.class);
    /** 个人中心页 */
    public static final String HOME = "home/index";

    @Autowired
    private ALBizUserService userService;

    /**
     * 个人中心首页
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toHome(Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        UserBuyInfo buyInfo = userService.getUserBuyInfoObj(userId);
        map.put("buyInfo", buyInfo);
        return HOME;
    }

}
