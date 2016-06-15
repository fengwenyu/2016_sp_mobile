package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.api2client.domain.LoginAndRegisterAPIResponse;
import com.shangpin.wireless.api.api2server.domain.LoginAndRegisterServerResponse;
import com.shangpin.wireless.api.domain.Account;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.LoginLimitUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 注册接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-27
 */
public class RegistServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(RegistServlet.class);
    private AccountService accountService;

    private static final int COUPON_KEEP_TIME = 30 * 60 * 1000; // 30分钟
    private static HashMap<String, JSONObject> CouponCache = new HashMap<String, JSONObject>();
    private static long CouponTime;
    
    private ImageCaptchaService imageCaptchaService;

    @Override
    public void init() throws ServletException {
        ServletContext sc = this.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        accountService = (AccountService) ctx.getBean(AccountService.SERVICE_NAME);
        imageCaptchaService = (ImageCaptchaService) getBean(ImageCaptchaService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String productNo = request.getHeader("p");// 产品号
        String channelNo = request.getHeader("ch");// 渠道号
        String imei = request.getHeader("imei");
        String invitecode = request.getParameter("invitecode");
        String type = request.getParameter("type");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userId = request.getParameter("userid");
        String imgcode = request.getParameter("imgcode");
        String verifycode = request.getParameter("smscode");
        final String ver = request.getHeader("ver");
        Date date = new Date();
        LoginAndRegisterServerResponse serverResponse = new LoginAndRegisterServerResponse();
        // type="1";
        // 兼容老版本的注册，造成有人绕过不传值的漏洞，目前去掉邮箱注册。
        if (type == null) {
            type = "1";
        }
        if (StringUtil.isNotEmpty(type, username, password, productNo, channelNo, imei)) {
        	
        	// 添加imei和ip注册限制
        				boolean addImeiLoginLimit = LoginLimitUtil.addImeiLoginCache(imei, request.getRemoteAddr());
        				if (!addImeiLoginLimit) {
        					try {
        						WebUtil.sendLimitException(response);
        						return;
        					} catch (Exception e) {
        						e.printStackTrace();
        					}
        				}
        	
        	
            if (((("1".equals(productNo) || "101".equals(productNo)) && (StringUtil.compareVer(ver, "4.0.0") >= 0)) || ("2".equals(productNo))||("102".equals(productNo) && (StringUtil.compareVer(ver, "2.5.6")> 0)))) {
                if ("1".equals(type)) {
                    if (StringUtil.isNotEmpty(verifycode)) {
                        // 手机验证码校验
                        String url = Constants.BASE_URL_SP_AL + "verifyphoneandcode/";
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("userid", username);
                        map.put("phonenum", username);
                        map.put("verifycode", verifycode);
                        try {
                            String data = WebUtil.readContentFromGet(url, map);
                            JSONObject obj = JSONObject.fromObject(data);
                            String code = obj.getString("code");

                            if (!"0".equals(code)) {
                                obj.put("msg", "您输入验证码不正确，请重新输入");
                                response.getWriter().print(obj.toString());
                                return;
                            }
                            // 打印日志
                            FileUtil.addLog(request, "verifyphoneandcode", channelNo, "userid", userId, "phonenum", username, "verifycode", verifycode, "gender", gender, "code",
                                    code);
                        } catch (Exception e) {
                            log.error("verifyphoneandcode：" + e);
                            try {
                                WebUtil.sendApiException(response);
                            } catch (Exception e1) {
                            }
                        }
                    } else {
                        try {
                            WebUtil.sendErrorInvalidParams(response);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                }
            }
            final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long now = System.currentTimeMillis();
            // 促销信息
            if (now > CouponTime + COUPON_KEEP_TIME) {
                Properties props = PropertiesUtil.getInstance("/coupon/config.properties");
                CouponCache.clear();
                for (Enumeration e = props.keys(); e.hasMoreElements();) {
                    String propkey = (String) e.nextElement();
                    final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
                    JSONObject json = JSONObject.fromObject(propvalue);
                    CouponCache.put(propkey, json);
                }
                CouponTime = now;
            }
            final String url = Constants.BASE_URL_AL_AL + "register/";
            Map<String, String> map = new HashMap<String, String>();
            if ("1".equals(type)) {
                map.put("type", type);
                map.put("smscode", verifycode);
                map.put("phonenum", username);
            }else{
                if("102".equals(productNo) || "2".equals(productNo)){
                    if (StringUtil.compareVer(ver, "2.5.6") > 0) {
                        Boolean isResponseCorrect = Boolean.FALSE;
//                        isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(imei, imgcode.toUpperCase());
                        isResponseCorrect = imageCaptchaService.validateResponseForID(imei, imgcode.toUpperCase());
                        if (isResponseCorrect) {
                            map.put("type", type);
                            map.put("mailaddress", username);
                        } else {
                            JSONObject obj = new JSONObject();
                            obj.put("code", "1");
                            obj.put("msg", "您输入验证码不正确，请重新输入");
                            obj.put("content", new JSONObject());
                            response.getWriter().print(obj.toString());
                            return;
                        }
                      
                    }
                }
            }
            // else {
            // map.put("type", type);
            // map.put("mailaddress", username);
            // }
            map.put("password", password);
            map.put("gender", gender);

            String isuser = "1";
            if (null == invitecode || invitecode.length() == 0) {
                isuser = "0";
                boolean usedefault = false;
                if (null != channelNo && channelNo.length() > 0) {
                    // 查询数据库 channelNo对应的invitecode
                    invitecode = "";
                    if (null == invitecode || invitecode.length() == 0) {
                        usedefault = true;
                    }
                } else {
                    usedefault = true;
                }
                if (usedefault) {
                    if ("1".equals(productNo)) { // 奥莱iPhone
                        invitecode = "50062560";
                    } else if ("2".equals(productNo)) {// 尚品iPhone客户端
                        invitecode = "16065598";
                    } else if ("101".equals(productNo)) {// 奥莱Android客户端
                        invitecode = "18090568";
                    } else if ("102".equals(productNo)) {// 尚品Android客户端
                        invitecode = "Q3YBFPWX";
                    } else if ("10000".equals(productNo)) { // m.aolai.com
                        invitecode = "26501690";
                    }
                }
            }
            // //: TEST
            // map.put("invitecode", "UYTUTSYG");
            try {
                String data = WebUtil.readContentFromPost(url, map);
                log.info("后台返回数据："+data);
                serverResponse.json2ObjNew(data, "");// json2obj
                LoginAndRegisterAPIResponse apiResponse = new LoginAndRegisterAPIResponse();
                BeanUtils.copyProperties(apiResponse, serverResponse);
                apiResponse.setSessionid(SessionUtil.addUser(apiResponse.getUserid(), imei));
                apiResponse.setIsopen("1");
                apiResponse.setMsgtype("2");  
                log.info("处理好数据："+JsonUtil.toJson(apiResponse));
                response.flushBuffer(); // 先返给客户端数据，再往库里修改数据
                if ("0".equals(apiResponse.getCode())) {
                    response.getWriter().print(apiResponse.obj2Json());// obj2json
                    final String key = "p" + productNo + "regist";
                    JSONObject couponcache = CouponCache.get(key);
                    if (couponcache != null) {
                        try {
                            Date starttime = sdfconfig.parse(couponcache.getString("starttime"));
                            Date endtime = sdfconfig.parse(couponcache.getString("endtime"));
                            if (date.after(starttime) && date.before(endtime)) {
                                new GiveOutCoupon(serverResponse.getUserid(), couponcache.getString("activatecode")).start();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    synchronized (serverResponse.getUserid().intern()) {
                        Account account = new Account();
                        account.setChannel(Long.parseLong(channelNo));
                        account.setProduct(Long.parseLong(productNo));
                        account.setGender(gender);
                        account.setLoginName(username);
                        account.setCreateTime(date);
                        account.setLoginTime(date);
                        account.setRegTime(sdf.parse(serverResponse.getRegTime()));
                        account.setRegOrigin(serverResponse.getRegOrigin());
                        account.setUserId(apiResponse.getUserid());
                        account.setPlatform(request.getHeader("os"));
                        account.setPhoneType(request.getHeader("mt"));
                        account.setPhoneModel(request.getHeader("model"));
                        accountService.save(account, DBType.dataSourceAPI.toString());
                    }
                }else {
                    JSONObject apiResult=new JSONObject();
                    apiResult.put("code", "1");
                    if ("1".equals(type)) {
                        apiResult.put("msg", "手机号已被注册，请更换手机号");
                    }else{                        
                        apiResult.put("msg", "注册失败,邮箱已被注册");
                    }
                    apiResult.put("content", new JSONObject());
                    response.getWriter().print(apiResult.toString());
                }
            } catch (Exception e) {
                // 记录访问日志
                FileUtil.addLog(request, "regist", channelNo, "username", username, "CurrentThread", Thread.currentThread().getName());
                e.printStackTrace();
                log.error("RegistServlet：" + e);
            }
            // 记录操作日志
            FileUtil.addLog(request, "regist", channelNo, "username", username,//
                    "code", serverResponse.getCode(),//
                    "userId", serverResponse.getUserid(),//
                    "regOrigin", serverResponse.getRegOrigin(),//
                    "gender", serverResponse.getGender(),//
                    "regTime", serverResponse.getRegTime(),//
                    "createTime", sdf.format(date),//
                    "loginTime", sdf.format(date), "invitecode", invitecode, "isuser", isuser);
        } else {
            try {
                WebUtil.sendErrorInvalidParams(response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    class GiveOutCoupon extends Thread {
        private String userid;
        private String activateCode;

        public GiveOutCoupon(String userid, String activateCode) {
            this.userid = userid;
            this.activateCode = activateCode;
        }

        @Override
        public void run() {
            try {
                String url = Constants.BASE_URL_AL_AL + "codemactchedphone/";
                // 组装参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userid);
                map.put("type", "coupon:" + activateCode);

                String data = WebUtil.readContentFromGet(url, map);
                // System.out.println("注册发放优惠券：userid = " + userid);
                // System.out.println(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
