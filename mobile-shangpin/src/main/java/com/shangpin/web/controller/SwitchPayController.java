package com.shangpin.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shangpin.biz.bo.User;
import com.shangpin.utils.GetPayGatewayUtil;
import com.shangpin.web.utils.Constants;

@Controller
@RequestMapping("/switch/pay")
public class SwitchPayController {
	public static final Logger logger = LoggerFactory.getLogger(SwitchPayController.class);
	private static String LOGIN="switch_pay/login";
	private static String INDEX="switch_pay/index";
	public static final String SESSION_USER = "switchpay_user";

	    /** userID */
	public static final String SESSION_USERID = "switchpay_userid";
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String face(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        if (StringUtils.isEmpty(user)) {
            return LOGIN;
        }
        model.addAttribute("key",  GetPayGatewayUtil.getGateway());
		return INDEX;

	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(required=true,value="payType")String type,Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        if (StringUtils.isEmpty(user)) {
            return LOGIN;
        }
        GetPayGatewayUtil.setGateway(type);
        return "redirect:/switch/pay/index";
	}
	/**
     * 
     * @Title: login
     * @Description: 用户登录校验
     * @Create By liling
     * @Create Date 2016年2月3日
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "userName",required = true) String userName, @RequestParam(value="password",required = true) String password,
           HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
            throws Exception, IOException {
     
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        if (!StringUtils.isEmpty(user)) {
            session.removeAttribute(SESSION_USER);
        }
        String msg = "";
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            try {
            	
                if (userName.equals(Constants.SWITCH_PAY_NAME)&&password.equals(Constants.SWITCH_PAY_PWD)) {
                	User fashionUser=new User();
            		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            		fashionUser.setUserid(dateFormat.format(new Date()));
            		fashionUser.setName(userName);
                    // 将user放入session中
                    session.setAttribute(SESSION_USER, fashionUser);
                    // 将userId存入session，便于日志查看
                    session.setAttribute(SESSION_USERID, fashionUser.getUserid());
                    return  "redirect:/switch/pay/index";
                } else {
                    msg = "用户名或密码错误，请重新输入...";
                    map.put("msg", msg);
                    return LOGIN;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception:" + e);
            }

        }
        return LOGIN;
    }
}
