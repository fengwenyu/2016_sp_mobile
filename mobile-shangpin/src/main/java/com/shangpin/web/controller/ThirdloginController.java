package com.shangpin.web.controller;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * m站第三方登录controller
 * @author fengwenyu
 *
 */
@Controller
@RequestMapping("/thirdLogin")
public class ThirdloginController extends BaseController{
    /**
     * qq和微信的公众号id及access_token
     */
    private static final Logger logger  = LoggerFactory.getLogger(ThirdloginController.class);
    private final String QQ_APPID= PropertyUtil.getString("third.qq.app.id");
    private final String QQ_APP_ACCESS_KEY= PropertyUtil.getString("third.qq.app.access.key");
    private final String WX_APPID= PropertyUtil.getString("third.wx.app.id");
    private final String WX_APP_ACCESS_KEY= PropertyUtil.getString("third.wx.app.access.key");

    @Autowired
    SPBizUserService userService;


    /**
     * 第三方qq登录
     * @param code
     * @param state
     * @param back 回跳地址
     * @return
     */
    @RequestMapping(value = "/qqlogin")
    public String thirdQQLogin(String code ,String state,String back,HttpServletRequest request) {
        String uuid;
        if(state!=null){
            uuid = state;
        }else{
            uuid = UUID.randomUUID().toString();
        }
        String session_key = "third_"+uuid;
        if(back!=null){
            request.getSession().setAttribute(session_key,back);
        }
        String qq_back_url = userService.getShangpinDomain()+"thirdLogin/qqlogin";
        if(StringUtils.isBlank(code)){
            //用户未登录状态，拼装参数去登录
            return "redirect:https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="+QQ_APPID+"&state="+uuid+"&g_ut=1&which=ConfirmPage&redirect_uri="+qq_back_url;
        }
        //获取Access Token
        String accessUrl = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+QQ_APPID+"&client_secret="+QQ_APP_ACCESS_KEY+"&code="+code+"&redirect_uri="+qq_back_url;
        String accessData = HttpClientUtil.doGet(accessUrl);
        String access_token=null;
        String expires_in=null;
        String refresh_token=null;
        if(StringUtils.isNotBlank(accessData) && accessData.length()>0){
            String[] accesss = accessData.trim().split("&");
            if(accesss!=null && accesss.length>0){
                for (String access : accesss) {
                    String[] k_v = access.split("=");
                    if(k_v.length==2){
                        switch (k_v[0].trim()){
                            case "access_token":
                                access_token = k_v[1];break;
                            case "expires_in":
                                expires_in = k_v[1];break;
                            case "refresh_token":
                                refresh_token = k_v[1];break;
                        }
                    }
                }
            }
        }
        if(access_token==null || expires_in ==null){
            logger.info("获取qq的accesstoken失败，返回值："+accessData);
            return null;
        }
        //拿到用户的qq号
        String qq_url="https://graph.qq.com/oauth2.0/me?access_token="+access_token;
        String qq_num = HttpClientUtil.doGet(qq_url);
        if(qq_num!=null){
            qq_num = qq_num.replace("callback(","").replace(")","").replace("\n","").replace(";","");
        }
        JSONObject qqnum_obj = JSONObject.fromObject(qq_num);
        String openid = qqnum_obj.getString("openid");
        if(openid==null){
            logger.info("获取qq用户info信息失败,返回值："+qqnum_obj);
            return null;
        }
        //获取用户信息
        String user_info_url="https://graph.qq.com/user/get_user_info?access_token="+access_token+"&oauth_consumer_key="+QQ_APPID+"&openid="+openid;
        String qq_info_data = HttpClientUtil.doGet(user_info_url);
        JSONObject info_obj = JSONObject.fromObject(qq_info_data);
        String nickName = info_obj.getString("nickname");
        String qq_gender = info_obj.getString("gender");
        String gender = null;
        if("男".equals(qq_gender)){
            gender="1";
        }else if ("女".equals(qq_gender)){
            gender="0";
        }else {
            gender ="2";
        }
        String user_icon = info_obj.getString("figureurl");//头像 暂时未使用  剩余可选值figureurl_1，figureurl_2，figureurl_qq_1，figureurl_qq_2
        //调用登录
        String login_data = userService.thirdLogin("qq", null, openid, gender, nickName, "");
        String back_old = (String)request.getSession().getAttribute(session_key);
        //登录成功
        String result = loginToSession(login_data,back_old,request);
        return result;
    }


    /**
     * 第三方微信登录
     * @param code
     * @param state
     * @param back
     * @return
     */
    @RequestMapping(value = "/wxlogin")
    public String thirdWXLogin(String code ,String state,String back,HttpServletRequest request) {
        String uuid ;
        if(state!=null){
            uuid = state;
        }else{
            uuid = UUID.randomUUID().toString();
        }
        String session_key = "third_"+uuid;
        if(back!=null){
            request.getSession().setAttribute(session_key,back);
        }
        String wx_back_url = userService.getShangpinDomain()+"thirdLogin/wxlogin";
       if(StringUtils.isBlank(code)){
           return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WX_APPID+"&redirect_uri="+wx_back_url+"&response_type=code&scope=snsapi_userinfo&state="+uuid+"#wechat_redirect";
       }

        //获取access_token
        String access_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WX_APPID+"&secret="+WX_APP_ACCESS_KEY+"&code="+code+"&grant_type=authorization_code";
        String access_data = HttpClientUtil.doGet(access_url);
        JSONObject access_obj = JSONObject.fromObject(access_data);
        String access_token = access_obj.getString("access_token");
        String openid = access_obj.getString("openid");
        if(access_token==null|| openid==null){
            logger.info("获取微信accessid、openid失败，返回值："+access_data);
            return null;
        }

        //获取用户信息
        String userinfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        String userinfo_data = HttpClientUtil.doGet(userinfo_url);
        if(userinfo_data==null || userinfo_data.contains("errcode")){
            logger.info("获取微信用户info信息失败，返回值："+userinfo_data);
            return null;
        }
        JSONObject userinfo_obj = JSONObject.fromObject(userinfo_data);
        String nickName = userinfo_obj.getString("nickname");
        String icon = userinfo_obj.getString("headimgurl");
        String sex = userinfo_obj.getString("sex");//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        String gender;
        if("1".equals(sex)){
            gender = "1";//男
        }else if("2".equals(sex)){
            gender = "0";//女
        }else {
            gender = "2";
        }
        String login_data = userService.thirdLogin("weixin", null, openid, gender, nickName, "");
        String back_old = (String)request.getSession().getAttribute(session_key);
        String result = loginToSession(login_data,back_old,request);
        return result;
    }

    /**
     * 将用户放入session
     * @param data
     * @return
     */
    private String loginToSession(String data,String back,HttpServletRequest request){
        ResultObjOne<User> result = JsonUtil.fromJson(data, new TypeToken<ResultObjOne<User>>() {
        });
        if("0".equals(result.getCode())){
            User user = result.getContent();
            logger.info("放入session");
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_USER, user);
            logger.info("放入session成功");
            session.setAttribute(Constants.SESSION_USERID, user.getUserid());
            if(StringUtils.isNotBlank(back)){
                back = back.replace("9uuuuu9","&");
                return "redirect:"+back;
            }else{
                return "redirect:/user/home";
            }
        }
        logger.info("调取主站的第三方登录失败，返回值为："+data);
        return null;
    }
}
