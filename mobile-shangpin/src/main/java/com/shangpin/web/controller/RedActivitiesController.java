package com.shangpin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ActivityUserDb;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizStarPacketService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.core.entity.AccountWeixinBind;
import com.shangpin.core.service.IWeixinBindService;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.wechat.service.WeChatPublicService;

/**
 * @author wh
 * 发红包和现金卷
 *
 */
@Controller
public class RedActivitiesController extends BaseController{
	 
	private static final Logger logger = LoggerFactory.getLogger(RedActivitiesController.class);
	private static final String RESULT = "/star/226/result";//结果页
	
	private static final String  RED_PASSWORD="Baby生日快乐";
	
	private static final String  RED_PASSWORD_NVSHEN="女神节爱自己";
	
	private static final String  ERROR_MSG="抱歉，该优惠券充值码已充值过，不能重复充值！";
	
	private static final String  RED_PASSWORD_CONPON="8924795278";
	
	private static final String  RED_PASSWORD_CONPON_NV="9253751881";
	//为红包CEO发红包配置
    private static final String INDEX = "/star/226/index";//现金券领取页面
    
    private static final String ERROR = "/star/red/error";//错误页面
    
    private static final String RED_INDEX= "/star/red/index";
   //神舟专车入口的激活码
    private static final String BDCOUPON = "0162791032";
    //和合信激活码
    private static final String BD_COUPON = "1640146810";
    
    private static final String DBRED="1";
    
    private static final String RED_RESULT="/star/red/result";
    
    private static final String NMB_END_TIME="2016-04-02-09:59:59";
    
	@Autowired
	private SPBizStarPacketService starPacketService;
	@Autowired
    private SPBizUserService userService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private WeChatPublicService weChatPublicService;
	@Autowired
    private IWeixinBindService weixinBindService;
	@Autowired
    private SPBizCouponService bizCouponService;
	
    /**
     * 跳转到领取红包页面
     * @param batchCode 
     * @param model
     * @return
     */
    @RequestMapping(value = "/star/red/index")
    public String indexPage(Model model, HttpServletRequest request){   
        model.addAttribute("star", null);
        return INDEX;
    }
    /**
     * 跳转到领取红包页面
     * @param batchCode 
     * @param model
     * @return
     */
    @RequestMapping(value = "/star/red")
    public String index(Model model, HttpServletRequest request){        
        model.addAttribute("star", "1");
        return INDEX;
    }
    
    /**
     * 抽奖方法
     * 
     * @param phoneNum
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/red/coupons")
    @ResponseBody
    public Map<String, Object> receiveCoupons(String verifyPassword, HttpServletRequest request) {
        Map<String, Object> map =new HashMap<>();
        User user = getSessionUser(request);
        if (user==null) {
            map.put("code", Constants.UNLOGIN); 
        }
        String userId =user.getUserid();
        if (StringUtil.isNotEmpty(verifyPassword)&& (verifyPassword.equalsIgnoreCase(RED_PASSWORD)||verifyPassword.equalsIgnoreCase(RED_PASSWORD_NVSHEN))) {
            Map<String, String>  pMap=new HashMap<>();
            pMap.put("baby生日快乐", RED_PASSWORD_CONPON);
            pMap.put("女神节爱自己", RED_PASSWORD_CONPON_NV);
            // 4用户优惠劵接口(addPacket)
            map = bizCouponService.sendCoupon(userId, "1", "coupon:" + pMap.get(verifyPassword.toLowerCase()), "30");
            logger.debug("return data==============={}", map.toString());
            if (ERROR_MSG.equals(map.get("msg"))) {
                map.put("msg", "红包已领过，不可重复领取哦");
                map.put("code", "4"); 
            }
            return map;
        }
        map.put("code", "3"); 
        map.put("msg", "口令输入有误");
        return map;
    }
    /**
     * 结果页面
     * @return
     */
    @RequestMapping(value = "/red/star/black_result")
    public String Share(String star,Model model, HttpServletRequest request){
        model.addAttribute("star", star);
        return RESULT;
        
    }
    
    
    /**
     * 跳转到领取红包页面
     * @param batchCode 红包批次号
     * @param model
     * @return
     */
    @RequestMapping(value = "/red/index", method = RequestMethod.GET)
    public String index(String star,Model model, HttpServletRequest request){
        if (star==null || !isUrl(star)) {
            return ERROR;
        }  
        model.addAttribute("star", star);
        return RED_INDEX;
       
    }


    /***
     * 根据条件判断是否发送验证码
     * 
     * @param phoneNum
     *            手机号
     * @param model
     * @param request
     * @return map
     */
    @ResponseBody
    @RequestMapping(value = "/red/isSendInfo", method = { RequestMethod.GET, RequestMethod.POST })
    public Map<String, Object> sendInfoLogic(String phoneNum,String star, Model model, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        int channel = 0;
        if (star.equals("1")) {
            channel = 81;
        } else {
            channel = 81 + (Integer.parseInt(star) - 1);
        }
        // 一、点击获取验证码时判断该手机号是否已经注册尚品网
        QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, String.valueOf(channel));
        String userId = quickUser.getUserId();
        User user = userService.findUserByUserId(userId);
        request.getSession().setAttribute(Constants.SESSION_USER, user);
        // 新用户下发验证码和注册信息
        if (Constants.IS_NEW_USER.equals(quickUser.getIsNewUser())) {
            String msgTemplate = "验证码:{$verifyCode$}，立即输入抢红包！" + phoneNum.substring(0, 3) + "XXXX" + phoneNum.substring(7, 11) +"已注册为尚品网会员，密码是手机号后6位，抢的红包将直接放入该账户";
            return bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
        } else {
            // 二、已注册的用户点击获取验证码时需判断该手机号是否已经抢过红包
            // 获得是否抢过优惠劵。
            ResultBase rBase = bizCouponService.checkActiveCode(phoneNum, getDBCoupon(star));
            if (rBase.isSuccess()) {
                String msgTemplate = "验证码:{$verifyCode$}，立即输入抢红包！抢到的红包将会放入" + phoneNum.substring(0, 3) + "XXXX" + phoneNum.substring(7, 11) +"的账户中，登录即可查看使用";
                return bizSendInfoService.sendPhoneCode(phoneNum, phoneNum, msgTemplate);
            }else {
                map.put("code", "3");
                map.put("phone", phoneNum);
                map.put("star", star);
            }
           
        }
        return map;
    }
    
    
    
    /**
     * 获得不同的DB的编码
     * @param star
     * 
     * 
     * 
     * @return
     */
    private String getDBCoupon(String star) {
        if (!star.isEmpty()&& star.equals("1")) {
            return BDCOUPON;
        }else if(star.equals("2")){
            return BD_COUPON;
        }
        return null;
        
    }
    
    /**
     * 抽奖方法
     * 
     * @param phoneNum
     * @param verifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/red/receive/Coupons", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> receiveCoupons(String phoneNum, String star,String verifyCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        ResultBase result = userService.beVerifyPhoneCode(phoneNum, phoneNum, verifyCode);
        if (result == null || Constants.FAILE.equals(result.getCode())) {
            map.put("code", "4");
            map.put("msg", "短信验证码错误");
            return map;
        }
        // 2.判断手机号是否为新老用户
        String userId = null;
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (user == null) {
            int channel = 0;
            if (star.equals("1")) {
                channel = 81;
            } else {
                channel = 81 + (Integer.parseInt(star) - 1);
            }
            QuickUser quickUser = userService.checkUser(phoneNum, Constants.CREATE_NEW_USER, String.valueOf(channel));
            userId = quickUser.getUserId();
            User users = userService.findUserByUserId(userId);
            request.getSession().setAttribute(Constants.SESSION_USER, users);
        } else {
            userId = user.getUserid();
        }
        Map<String, Object> resultBase =new HashMap<>();
        if (StringUtil.isNotEmpty(star) && isUrl(star)) {
            // 4用户优惠劵接口(addPacket)
            resultBase = bizCouponService.sendCoupon(userId, "1", "coupon:" + getDBCoupon(star), "30");
            resultBase.put("star", star);
            return resultBase;
        }else {
            map.put("code", Constants.FAILE);
            map.put("msg", "页面错误");
            return resultBase;
        }
    } 
    
    
    /**
     * 用户与微信进行绑定
     * @param openId 微信用户openId
     * @param 
     * @param User 用户信息
     */
    @SuppressWarnings("unused")
    private void bind(String openId, String userName, User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 绑定
        AccountWeixinBind accountWeixinBind = new AccountWeixinBind();
        accountWeixinBind.setWeixinId(openId);
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
     * 结果页面
     * @return
     */
    @RequestMapping(value = "/red/black_result", method = {RequestMethod.GET, RequestMethod.POST})
    public String Share(String phoneNum,String star,Model model, HttpServletRequest request){
        if (star==null || !isUrl(star)) {
            return ERROR;
        } 
        model.addAttribute("star",star);
        model.addAttribute("phoneNum",phoneNum.substring(0, 3) + "XXXX" + phoneNum.substring(7, 11) );
        return RED_RESULT;
        
    }
    
    /***
     * 判断url是否正确
     * @param str
     * @return
     */
    private Boolean isUrl(String str) {
       // String regex = "^([1-9]|10)$";  
        int num = Integer.parseInt(str);
        if (!str.isEmpty() && 0<num && num<3) {
            return true;
        }
        return false;
    }
    /////////////////////////////////////////////
    /**
     * 市场部NMD鞋子赠送活动
     * @return page
     */
    @RequestMapping(value="/activity/index")
    public String initPage() {
        return "/activity/index";
    }
    /***
     * 查询活动详情
     * @return page
     * @throws ParseException 
     */
    @RequestMapping(value="/activity/details")
    public String findActivityDetail(Model model) throws ParseException {
        Date endTime = DateUtils.strToDate(NMB_END_TIME, "yyyy-MM-dd-HH:mm:ss");
        long nowTime = System.currentTimeMillis();
        if(nowTime>=endTime.getTime()){//1459509569900 1459509659000
         model.addAttribute("isEnd", "1");   
        }
        return "/activity/detail";
    }
    
    /***
     * 验证是否到编辑页面
     * @return
     */
    @RequestMapping(value="/activity/edit")
    public String edit(HttpServletRequest request,Model model) {
        User user = getSessionUser(request);
        String userId =user.getUserid();
        // 获得是否领取过优惠劵
        String date = bizCouponService.checkActiveUserId(userId);
        if ((StringUtil.isNotEmpty(date)) && (!date.equals("000000"))) {
            Date endTime = DateUtils.strToDate(NMB_END_TIME, "yyyy-MM-dd-HH:mm:ss");
            long nowTime = System.currentTimeMillis();
            if(nowTime>=endTime.getTime()){
                model.addAttribute("isEnd", "1");   
            }
            model.addAttribute("code",date);
            return "/activity/result";
        }
        return "/activity/edit";
    }
    
    /***
     * 保存用户信息
     * @param request
     * @return
     */
    @RequestMapping(value="/activity/saveInfo")
    @ResponseBody
    public Map<String, Object> saveUserInfo(HttpServletRequest request,ActivityUserDb activityUserDb) {
        Map<String, Object> map =new HashMap<String,Object>();
        User user = getSessionUser(request);
        if (user==null) {
            map.put("code", Constants.UNLOGIN); 
        }
        String userId =user.getUserid();
        activityUserDb.setUserId(userId);
        //保存用户信息
        String code= bizCouponService.saveActivityUserDetail(activityUserDb);
        if(StringUtil.isEmpty(code)){
            map.put("code", "1");
            map.put("msg", "已领取过！");
            return map;
        }
        if(!code.equals("000000")){
         //发放优惠劵检查用户是否领取过。
          map = bizCouponService.sendCoupon(userId, "1", "coupon:0098246372", "30");
          map.put("date", code);
        }
       return map;
    }
    
    /***
     * 查询活动结果页面
     * @return page
     */
    @RequestMapping(value="/activity/result")
    public String result(String code,Model model) {
        model.addAttribute("code", code);
        return "/activity/result";
    }
}
