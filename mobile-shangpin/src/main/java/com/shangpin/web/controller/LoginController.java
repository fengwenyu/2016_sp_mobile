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

package com.shangpin.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.CaptchaService;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.SendCoupon;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IAccountService;
import com.shangpin.core.service.IWeixinBindService;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.CookieUtil;
import com.shangpin.web.utils.UserUtil;

/**
 * 用户管理的controller
 * 
 * @author zhongchao
 */
@Controller
public class LoginController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /** 跳转到登录页面 */
    private static final String LOGIN = "user/login";
    /** 跳转到首页 */
    @SuppressWarnings("unused")
    private static final String INDEX_PAGE = "redirect:/index";
    /** 忘记密码页面index */
    private static final String FORGETPASSWORD_INDEX = "user/forget_password";
    /** 忘记密码页面next */
    private static final String FORGETPASSWORD_NEXT = "user/forget_password2";
    /** 忘记密码页面last */
    private static final String FORGETPASSWORD_LAST = "user/forget_password3";
    /** 跳转到注册页面index */
    private static final String REGISTER_INDEX = "user/sigin_1";
    /** 跳转到注册页面next */
    private static final String REGISTER_NEXT = "user/sigin_2";
    /** 跳转到注册页面last */
    private static final String REGISTER_LAST = "user/sigin_3";
    /** 个人中心 */
    private static final String USERINFO = "user/home";
    private static final String WXBINDSUCCESS = "weixin/bindSuccess";
    private static final String SENDCOUPON = "coupon/sendCoupon";
    private static final Object RETURN_URL = "back";

    @Autowired
    private SPBizUserService userService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IWeixinBindService weixinBindService;
    @Autowired
    private SPBizCouponService bizCouponService;

    /**
     * @Title: login
     * @Description: 跳转到登录界面
     * @param back
     *            回调地址
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map, @RequestParam(value = "back", required = false) String back, String way) {
        logger.debug("back URL :" + back);
        if (StringUtils.hasText(back)) {
            map.put("back", back);
        }
        if (StringUtils.hasText(way)) {
            map.put("way", way);
        }
        return LOGIN;
    }

    /**
     * @Title: wxLogin
     * @Description: 跳转到登录界面
     * @param back
     *            回调地址
     * @Create By liling
     * @Create Date 2014年12月18日
     */
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public String wxLogin(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "back", required = false) String back, String way) {
        HttpSession session = request.getSession();
        String useragent = request.getHeader("User-Agent");
        String wxId = (String) session.getAttribute(Constants.WX_ID_NAME);
        // 判断登陆操作是否来自微信，如果是需进行绑定账号操作
        if (!ClientUtil.CheckMircro(useragent) || StringUtils.isEmpty(wxId)) {
            return wexinLoginURL(back);
        }
        logger.debug("back URL :" + back);
        
        map.put("way", way);
        map.put("back", back);
        if (!StringUtils.hasText(wxId)) {
            return wexinLoginURL(back);
        }
        // 判断是否已经绑定
        AccountWeixinBind accountWeixinBind = weixinBindService.findByWXId(wxId);
        User user = getSessionUser(request);
        if (accountWeixinBind == null) {
            // 如果已经绑定，执行自动登录操作
            return wexinLoginURL(back);
        }
        if (!StringUtils.isEmpty(user)) {
            removeSessionUser(request);
        }
        user = userService.weixinAutoLogin(accountWeixinBind);
        if (!StringUtils.isEmpty(user)) {
            // 将user放入session中
            session.setAttribute(Constants.SESSION_USER, user);
            // 将userId存入session，便于日志查看
            session.setAttribute(Constants.SESSION_USERID, user.getUserid());
        }

        if (!StringUtils.isEmpty(back)) {
            map.remove("back");
            // 不为空就返回之前请求的地址
            logger.debug("backURL :" + back);
            return "redirect:" + back;
        }

        return wexinLoginURL(back);
    }

    /**
     * @Title: register_index
     * @Description: 跳转到注册第一个页面
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/toRegister", method = RequestMethod.GET)
    public String toRegister() {

        return REGISTER_INDEX;
    }

    /**
     * @Title: forgetpassword_index
     * @Description: 跳转到忘记密码第一个页面
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/findpwd", method = RequestMethod.GET)
    public String toForgetpassword() {

        return FORGETPASSWORD_INDEX;
    }

    /**
     * @Title: forgetpassword_last
     * @Description: 跳转到密码设置界面
     * @param mobi
     *            手机号
     * @param mobiCode
     *            手机验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/findpwLast", method = RequestMethod.POST)
    public String toForgetpasswordLast(Map<String, Object> map, @RequestParam("mobi") String mobi, @RequestParam("mobiCode") String mobiCode) {
        map.put("mobi", mobi);
        map.put("mobiCode", mobiCode);
        String msg = "";

        if(!checkPhoneCode(mobi,mobiCode)){
        	msg = "验证码错误，请重新输入...";
            map.put("msg", msg);
            return FORGETPASSWORD_NEXT;
        }
        logger.debug("mobi：" + mobi + "," + "mobiCode:" + mobiCode);
        return FORGETPASSWORD_LAST;
    }

    /**
     * @Title: register_last
     * @Description: 跳转到最后一个注册页面
     * @param mobi
     *            手机号
     * @param mobiCode
     *            手机验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/registerLast", method = RequestMethod.POST)
    public String toRegisterLast(Map<String, Object> map, @RequestParam("mobi") String mobi, @RequestParam("mobiCode") String mobiCode) {
        map.put("mobi", mobi);
        map.put("mobiCode", mobiCode);
        logger.debug("mobi：" + mobi + "," + "mobiCode:" + mobiCode);
        return REGISTER_LAST;
    }

    /**
     * 
     * @Title: login
     * @Description: 用户登录校验
     * @param captcha
     *            验证码
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @param back
     *            回调地址
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "captcha", required = false) String captcha, @RequestParam(value = "userName",required = true) String userName, @RequestParam(value="password",required = true) String password,
            @RequestParam(value = "back", required = false) String back, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, String way)
            throws Exception, IOException {
        // 获取渠道号
        // String channelNo = getChannelNo(request);
        // String ua = request.getHeader("User-Agent");
        // String ip =request.getRemoteAddr();

        String msg = "";
        // //限制用户频繁操作(从缓存里面取ip，校验是否是黑名单ip)
        // if (accountService.ipBlacklist(userName, ip)) {
        // msg = "您操作过于频繁，请稍后重试";
        // return LOGIN;
        // }
        map.put("back", back);
        Object logincount = request.getSession().getAttribute("logincount");
        int counter = 1;// 判断登录次数，第三次进行验证码输入
        if (logincount == null) {
            counter = 2;
            request.getSession().setAttribute("logincount", 2);
        } else {
            counter = ((Integer) logincount).intValue();
            ++counter;
            request.getSession().setAttribute("logincount", counter);
        }
        // 对验证码校验
        if (counter > 3) {
            boolean result = captchaService.verifyCaptcha(request.getSession(), captcha);
            if (!result) {
                msg = "验证码错误，请重新输入...";
                map.put("mobi", userName);
                map.put("msg", msg);
                return LOGIN;
            }
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (!StringUtils.isEmpty(user)) {
            session.removeAttribute(Constants.SESSION_USER);
        }
        // 调用主站接口校验登录
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            try {
                user = userService.login(userName, password);
                logger.info("主站user："+JsonUtil.toJson(user));
                if (!StringUtils.isEmpty(user)) {
                    // 将user放入session中
                	logger.info("放入session");
                    session.setAttribute(Constants.SESSION_USER, user);
                    logger.info("放入session成功");
                    // 将userId存入session，便于日志查看
                    session.setAttribute(Constants.SESSION_USERID, user.getUserid());
                    synchronized (user.getUserid().intern()) {
                        // 登录成功则清空登录次数
                        request.getSession().removeAttribute("logincount");
                        logger.debug("loginName:" + userName + "; " + Thread.currentThread().getName());

                    }
                    String useragent = request.getHeader("User-Agent");
                    Object weixinId = session.getAttribute(Constants.WX_ID_NAME);
                    logger.info("weixinId："+weixinId+"  back:"+back);
                    // 判断登陆操作是否来自微信，如果是需进行绑定账号操作
                    if (ClientUtil.CheckMircro(useragent) && !StringUtils.isEmpty(weixinId)) {

                        // 判断是否已经绑定，没有绑定则执行绑定操作
                        String wxId = weixinId.toString();
                        AccountWeixinBind accountWeixinBind = weixinBindService.findByWXId(wxId);
                        if (accountWeixinBind != null) {
                            // 是否来自微信修改绑定账号的登陆操作
                            WeixinBindModify(accountWeixinBind, userName, user);
                            return WXBINDSUCCESS;

                        }
                        logger.info("weixinid :wxId={}", wxId);
                        WeixinBind(wxId, userName, user);

                    }
                    logger.info("1111");
                    if (!StringUtils.isEmpty(back)) {
                    	logger.info("222");
                        map.remove("back");
                        // 不为空就返回之前请求的地址
                        logger.info("backURL :" + back);
                        return "redirect:" + back;
                    }
                    logger.info("333");
                    // 跳转到个人中心
                    logger.info("login success" + "userid :" + user.getUserid() + "username:" + user.getName());
                    return "redirect:user/home";
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

    /**
     * 
     * @Title: register
     * @Description: 手机注册
     * @param captcha
     *            验证码
     * @param mobi
     *            用户名
     * @param password
     *            密码
     * @param back
     *            回调地址
     * @param gender
     *            性别
     * @param mobiCode
     *            手机验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerVerify(@RequestParam(value = "captcha", required = false) String captcha, @RequestParam("mobi") String userName,
            @RequestParam("password") String password, @RequestParam(value = "back", required = false) String back, @RequestParam(value = "gender") String gender,
            @RequestParam(value = "mobiCode") String mobiCode, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        // 获取渠道号
        // String channelNo = getChannelNo(request);
        // String ua = request.getHeader("User-Agent");

        String msg = "";
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(gender)) {
            try {
            	//增加商品客的参数channelNo channelId
        		HttpSession session = request.getSession();
            	Map<String, String> cookieMap = UserUtil.getThridBothCookie(request);
            	String content = null;
            	if(cookieMap!=null){
            		// 调用主站接口注册：手机注册
            		content = userService.fromRegister("", userName, password, gender, mobiCode, "1", "",cookieMap.get(com.shangpin.biz.utils.Constants.THRID_COOKIE_SOURCE),cookieMap.get(com.shangpin.biz.utils.Constants.THRID_COOKIE_CAMPAIGN),cookieMap.get(com.shangpin.biz.utils.Constants.THRID_COOKIE_PARAM),cookieMap.get(com.shangpin.biz.utils.Constants.THRID_COOKIE_CHANNEL_TYPE));
            	}else{
            		content = userService.fromRegister("", userName, password, gender, mobiCode, "1", "");
            	}
                
                
                if (StringUtils.isEmpty(content)) {
                    // 跳转到注册页面
                    return REGISTER_INDEX;
                }
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(content);
                String code = jsonNode.path("code").asText();
                msg = jsonNode.path("msg").asText();
                if ("0".equals(code)) {
                    if (StringUtils.isEmpty(msg)) {
                        msg = "注册成功！";
                    }
                    map.put("msg", msg);
                    map.put("mobi", userName);
                    logger.debug("调用主站注册接口成功返回数据：" + content);
                    User user = userService.login(userName, password);
                    if (!StringUtils.isEmpty(user)) {
                        // 将user放入session中
                        session.setAttribute(Constants.SESSION_USER, user);
                        // 将userId存入session，便于日志查看
                        session.setAttribute(Constants.SESSION_USERID, user.getUserid());
                        synchronized (user.getUserid().intern()) {
                            // 登录成功则清空登录次数
                            request.getSession().removeAttribute("logincount");
                            logger.debug("loginName:" + userName + "; " + Thread.currentThread().getName());

                        }

                        Map<String, String> mapCoupon = new HashMap<String, String>();
                        mapCoupon = SendCoupon.isSendCouponAndGetCoupon();
                        if (mapCoupon != null) {
                            if (mapCoupon.get("isSendCoupon").equals("true")) {
                                bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + mapCoupon.get("coupon"));
                                map.put("sendcoupondesc", mapCoupon.get("sendcoupondesc"));
                                return SENDCOUPON;
                            }
                        }

                    }
                    if (!StringUtils.isEmpty(back)) {
                        map.remove("back");
                        // 不为空就返回之前请求的地址
                        logger.debug("backURL :" + back);
                        return "redirect:" + back;
                    }
                    // 跳转到个人中心
                    logger.debug("login success" + "userid :" + user.getUserid() + "username:" + user.getName());
                    return USERINFO;
                } else {
                    map.put("mobi", userName);
                    map.put("msg", msg);
                    logger.debug("调用主站注册接口失败返回数据：" + content);
                    return REGISTER_INDEX;// 跳转到注册页
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("异常：" + e);
            }

        }
        return REGISTER_INDEX;
    }

    /**
     * @Title: forgetpassword_last
     * @Description: 修改密码
     * @param mobi
     *            手机号
     * @param password
     *            新密码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public String changepwd(@RequestParam("mobi") String mobi, @RequestParam("mobiCode") String mobiCode, @RequestParam("password") String password, Map<String, Object> map) {
    	map.put("mobi", mobi);
        map.put("mobiCode", mobiCode);
        String msg = "";

        if(!checkPhoneCode(mobi,mobiCode)){
        	msg = "短信校验码错误，请重新输入...";
            map.put("msg", msg);
            return FORGETPASSWORD_LAST;
        }
        logger.debug("mobi：" + mobi + "," + "mobiCode:" + mobiCode);
        
        // 根据注册手机号调用主站接口返回User
        User user = userService.findUserByUserName(mobi);
        if (StringUtils.isEmpty(user)) {
            map.put("msg", "该用户名不存在！");
            return FORGETPASSWORD_LAST;
        }
        String userid = user.getUserid();
        JsonNode jsonNode = null;
        try {
            // 调用主站接口找回密码
            String jsonString = userService.fromChangePassword(userid, password);
            if (StringUtils.isEmpty(jsonString)) {
                return FORGETPASSWORD_INDEX;
            }
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != jsonNode) {
            msg = jsonNode.path("msg").asText();
            String code = jsonNode.path("code").asText();
            map.put("msg", msg);
            if ("0".equals(code)) {
                return LOGIN;
            }
        }
        
        return FORGETPASSWORD_LAST;
    }

    /**
     * @Title: register_next
     * @Description: 注册发送手机验证码
     * @param mobi
     *            手机号
     * @param captcha
     *            验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/sendRegistPhoneCode", method = RequestMethod.POST)
    public String sendRegistPhoneCode(@RequestParam("mobi") String mobi, HttpServletRequest request, Map<String, Object> map, @RequestParam("captcha") String captcha) {
        User user = getSessionUser(request);
        String userId = user == null ? mobi : user.getUserid();
        userService.fromSendVerifyCode(userId, mobi, "尊敬的顾客，您正在注册尚品网，短信验证码为：{$verifyCode$}，请在页面内填写。如非本人操作，请联系客服4006-900-900。");
        map.put("mobi", mobi);
        return REGISTER_NEXT;
    }

    /**
     * @Description: 注册时重复发送手机验证码
     * @param mobi
     *            手机号
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @ResponseBody
    @RequestMapping(value = "/repeatSendRegistPhoneCode", method = RequestMethod.POST)
    public String sendRegistPhoneCodeRepeat(@RequestParam("mobi") String mobi, HttpServletRequest request) {
        User user = getSessionUser(request);
        String userId = user == null ? mobi : user.getUserid();
        String jsonString = userService.fromSendVerifyCode(userId, mobi, "尊敬的顾客，您正在注册尚品网，短信验证码为：{$verifyCode$}，请在页面内填写。如非本人操作，请联系客服4006-900-900。");
        return jsonString;
    }
    /**
     * @Description: 注册时重复发送手机验证码
     * @param mobi
     *            手机号
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @ResponseBody
    @RequestMapping(value = "/repeatSendFindpwdCode", method = RequestMethod.POST)
    public String sendfindpwdCodeRepeat(@RequestParam("mobi") String mobi, HttpServletRequest request) {
        User user = getSessionUser(request);
        String userId = user == null ? mobi : user.getUserid();
        String jsonString = userService.fromSendVerifyCode(userId, mobi, "您的验证码是：{$verifyCode$}，请及时输入验证。");
        return jsonString;
    }
    /**
     * @Description: 验证验证码
     * @param captcha
     *            验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @ResponseBody
    @RequestMapping(value = "/checkCaptchaCode", method = RequestMethod.GET)
    public String checkCaptchaCode(@RequestParam("captcha") String captcha,@RequestParam("mobi") String mobi, HttpServletRequest request, Map<String, Object> map) {
    	//判断手机号是否注册过
    	QuickUser quickUser = userService.checkUser(mobi, "0");
        if(quickUser!=null&&"0".equals(quickUser.getIsNewUser())){
        	return "{\"msg\": \"该手机号已注册过尚品网！\"}";
        }
        // 判断验证码是否正确
        boolean flag = captchaService.verifyCaptcha(request.getSession(), captcha);
        // String msg = (flag == true ? null : "手机号或验证码输入错误！");
        if (!flag) {
            return "{\"msg\": \"手机号或验证码输入错误！\"}";
        }
        return null;
    }

    /**
     * @Description: 验证手机验证码
     * @param mobi
     *            手机号
     * @param verifycode
     *            手机验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @ResponseBody
    @RequestMapping(value = "/checkPhoneCode", method = RequestMethod.GET)
    public String checkPhoneCode(@RequestParam("mobi") String mobi, @RequestParam("verifycode") String verifycode, HttpServletRequest request) {
        User user = getSessionUser(request);

        String userId = user == null ? mobi : user.getUserid();
        // 调用主站接口对手机验证码进行验证
        String jsonString = userService.fromVerifyPhoneCode(userId, mobi, verifycode);
        return jsonString;
    }

    /**
     * @Title: forgetpassword_next
     * @Description: 手机忘记密码
     * @param mobi
     *            手机号
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/findpwdPhone", method = RequestMethod.POST)
    public String forgetPasswordPhone(@RequestParam("mobi") String mobi, HttpServletRequest request, Map<String, Object> map) {
        /*User user = getSessionUser(request);
        String userId = user == null ? mobi : user.getUserid();*/
        userService.fromSendVerifyCode(mobi, mobi, "您的验证码是：{$verifyCode$}，请及时输入验证。");
        map.put("mobi", mobi);
        return FORGETPASSWORD_LAST;
    }

    /**
     * @Title: forgetpassword_index
     * @Description: 邮箱忘记密码
     * @param mobi
     *            手机号
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/findpwdEmail", method = RequestMethod.POST)
    public String forgetPasswordEmail(@RequestParam("mobi") String email, HttpServletRequest request, Map<String, Object> map) {
        String jsonStr = userService.fromForgotPassword(email);
        if (!StringUtils.isEmpty(jsonStr)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(jsonStr);
                String msg = jsonNode.path("msg").asText();
                String code = jsonNode.path("code").asText();
                if ("1".equals(code)) {
                    map.put("mobi", email);
                    map.put("msg", msg);
                    return FORGETPASSWORD_INDEX;
                } else {
                    // 其他返回登录页
                    map.put("mobi", email);
                    map.put("msg", msg);
                    return LOGIN;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("forgetPasswordEmail Exception:" + e);
            }
        }
        map.put("msg", "请输入正确的邮箱地址或手机号码!");
        return FORGETPASSWORD_INDEX;
    }

    /**
     * @Title:
     * @Description: 退出登录状态
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        // 情况session中user
        try {
            String referer = request.getHeader("referer");
            String userId = getUserId(request);
            logger.debug("LoginController logout session id={} and sessionId={} ", userId,getSessionId(request));
            removeSessionUser(request);
            response.sendRedirect(referer);
        } catch (Exception e) {
            logger.error("注销异常：" + e);
        }
        return null;
    }

    /**
     * @Title:
     * @Description: 生成验证码
     * @param request
     * @param response
     * @Create By zhongchao
     * @Create Date 2014年10月22日
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
     * @Title:
     * @Description: 获取渠道号
     * @param request
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
    public String getChannelNo(HttpServletRequest request) {
        String channelNo = request.getParameter(Constants.CHANNEL_PARAM_NAME);
        if (StringUtils.isEmpty(channelNo)) {
            if (CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null) {
                channelNo = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
            } else {
                channelNo = Constants.SP_WAP_DEFAULT_CHANNELNO;
            }
        }
        return channelNo;
    }

    private void WeixinBind(String wxId, String userName, User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 绑定
        AccountWeixinBind accountWeixinBind = new AccountWeixinBind();
        accountWeixinBind.setWeixinId(wxId);
        accountWeixinBind.setCreateTime(new Date());
        accountWeixinBind.setLoginName(userName);
        accountWeixinBind.setUserId(user.getUserid());
        accountWeixinBind.setNickname(user.getName());
        accountWeixinBind.setGender(user.getGender());
        try {
            accountWeixinBind.setRegTime(sdf.parse(user.getRegTime()));
            accountWeixinBind.setRegOrigin(user.getRegOrigin());
            accountWeixinBind.setBindTime(new Date());
            accountWeixinBind.setMarkup("hand");
            weixinBindService.addOrModifyAccountWeixinBind(accountWeixinBind);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void WeixinBindModify(AccountWeixinBind accountWeixinBind, String userName, User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 绑定
        accountWeixinBind.setCreateTime(new Date());
        accountWeixinBind.setLoginName(userName);
        accountWeixinBind.setUserId(user.getUserid());
        accountWeixinBind.setNickname(user.getName());
        accountWeixinBind.setGender(user.getGender());
        try {
            accountWeixinBind.setRegTime(sdf.parse(user.getRegTime()));
            accountWeixinBind.setRegOrigin(user.getRegOrigin());
            accountWeixinBind.setBindTime(new Date());
            accountWeixinBind.setMarkup("hand");
            weixinBindService.addOrModifyAccountWeixinBind(accountWeixinBind);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @Title: wexinLoginURL
     * @Description: 微信定登陆请求并拼接back url
     * @param
     * @return String
     * @throws
     * @Create By liling
     * @Create Date 2014年11月17日
     */
    private String wexinLoginURL(String backUrl) {
        StringBuilder url = new StringBuilder("redirect:");

        url.append("/login");
        if (!StringUtils.isEmpty(backUrl)) {
            url.append("?");
            url.append(RETURN_URL).append("=");
            String encoderReturnURL;
            try {
                encoderReturnURL = URLEncoder.encode(backUrl, Constants.DEFAULT_ENCODE);
                url.append(encoderReturnURL);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return url.toString();
    }
    
    /**
     * 验证下发手机的验证码是否正确
     * @param mobi
     * @param verifycode
     * @return
     */
    private boolean checkPhoneCode(String mobi, String verifycode) {
        // 调用主站接口对手机验证码进行验证
    	ResultBase result = userService.beVerifyPhoneCode(mobi, mobi, verifycode);
        if(!result.isSuccess()){
        	return false; 
        }
        return true;
    }

}