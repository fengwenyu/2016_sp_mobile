package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.utils.AESUtil;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 
 * 发送手机验证码的接口，新接口，需要单独提供。 获取手机验证码的Servlet 前端需要传输手机号码，用户Id号
 * 
 * @author sunweiwei
 * 
 */
public class SendSMSCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(SendSMSCodeServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        final String imei = request.getHeader("imei");
        final String productNum = request.getHeader("p");
        final String version = request.getHeader("ver");
        
        String phoneNum = null;
        String type = null;
        String smsType = null;
        String sessionId = null;
        String userId = null;
        
        if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.9", version) == 1) {
        	Map<String, String> params = new ConcurrentHashMap<String, String>(8);

    		try {
    			params = AESUtil.base64ZipAES(request.getParameterMap().toString());
    		} catch (Exception e2) {
    			e2.printStackTrace();
    		}
    		
    		phoneNum = params.get("phonenum");
            type = params.get("type");
            smsType = params.get("smstype");
            sessionId = params.get("sessionid");
            userId = params.get("userid");
        } else {
            phoneNum = request.getParameter("phonenum");
            type = request.getParameter("type");
            smsType = request.getParameter("smstype");
            sessionId = request.getParameter("sessionid");
            userId = request.getParameter("userid");
        }
    	
        String url = Constants.BASE_URL_SP_AL + "SendRegMobileVerifyCode/";

        PrintWriter writer = response.getWriter();
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        if (StringUtil.isNotEmpty(smsType, type)) {
        	if ("1".equals(type) && "0".equals(smsType)) {
                // for only phone手机注册
        		url = Constants.BASE_URL_SP_AL + "SendRegMobileVerifyCode/";
        		sendSmsForOnlyPhone(request, response, url, userId, phoneNum, writer);
        		return;
        	}else if("3".equals(type)){
        		url = Constants.BASE_URL_SP_AL + "isBingPhone/";
       		 	Map<String, String> map = new HashMap<String, String>();
                map.put("UserId", userId);
                map.put("phone", phoneNum);
       		String data = WebUtil.readContentFromGet(url, map);
               JSONObject obj = JSONObject.fromObject(data);
               if(obj.get("code")!=null&&obj.get("code").equals("0")){
            	String newUrl = Constants.BASE_URL_SP_AL + "SendRegMobileVerifyCode/";
               	sendSmsPhone(request, response,newUrl,userId,phoneNum,writer);
           		return;
               }else{
               	response.getWriter().print(obj.toString());
               }
        	}else{
        		sendSmsPhone(request, response,url,userId,phoneNum,writer);
        		return;
        	}
        } else {
        	try {
        		WebUtil.sendErrorInvalidParams(response);
        	} catch (Exception e1) {
        		e1.printStackTrace();
        	}
        }

        if ("0".equals(type)) {
            // for login user
            sendSmsForLoginUser(request, response, url, userId, phoneNum, sessionId, imei, writer);
        } else if ("1".equals(type)) {
            // for only phone手机注册
            url = Constants.BASE_URL_SP_AL + "SendRegMobileVerifyCode/";
            sendSmsForOnlyPhone(request, response, url, userId, phoneNum, writer);
        } else if ("2".equals(type)) {
            sendSmsForNoLoginUser(request, response, url, phoneNum, writer);
        }
    }

    /**
     * 
     * @param request
     * @param response
     * @param url
     * @param phoneNum
     * @param writer
     */
    private void sendSmsPhone(HttpServletRequest request, HttpServletResponse response,String url,String userId,String phoneNum, PrintWriter writer) {
        final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        if (StringUtil.isNotEmpty(phoneNum)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userid", userId);
            map.put("phonenum", phoneNum);
            map.put("msgtemplate", "您的验证码是：{$verifyCode$}，请及时输入验证。");
            try {
                String data = WebUtil.readContentFromGet(url, map);
                JSONObject obj = JSONObject.fromObject(data);
                log.info("主站："+data+"返回给app"+obj.toString());
                response.getWriter().print(obj.toString());

                // 打印日志
                FileUtil.addLog(request, "sendverifycode", channelNo, "phonenum", phoneNum);
            } catch (Exception e) {
                log.error("sendsmscode：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("sendsmscode：" + e);
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
    /**
     * 
     * @param request
     * @param response
     * @param url
     * @param phoneNum
     * @param writer
     */
    private void sendSmsForOnlyPhone(HttpServletRequest request, HttpServletResponse response, String url, String userId, String phoneNum, PrintWriter writer) {
        final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        if (StringUtil.isNotEmpty(phoneNum)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userid", phoneNum);
            map.put("phonenum", phoneNum);
            map.put("msgtemplate", "尊敬的顾客，您正在注册尚品网，短信验证码为：{$verifyCode$}，请在页面内填写。如非本人操作，请联系客服4006-900-900。");
            try {
                String data = WebUtil.readContentFromGet(url, map);
                JSONObject obj = JSONObject.fromObject(data);
                response.getWriter().print(obj.toString());

                // 打印日志
                FileUtil.addLog(request, "sendverifycode", channelNo, "phonenum", phoneNum, "userId", userId);
            } catch (Exception e) {
                log.error("sendsmscode：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("sendsmscode：" + e);
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * 用于未登录的用户发送手机验证码
     * 
     * @param request
     * @param response
     * @param url
     * @param userId
     * @param phoneNum
     * @param writer
     */
    private void sendSmsForNoLoginUser(HttpServletRequest request, HttpServletResponse response, String url, String phoneNum, PrintWriter writer) {
        String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        if (StringUtil.isNotEmpty(phoneNum)) {
            // 检查用户是否存在
            String checkUrl = Constants.BASE_URL_SP_SP + "checkUsername/";

            String userId = checkUser(checkUrl, phoneNum);
            if (!StringUtil.isNotEmpty(userId)) {
                JSONObject respobj = new JSONObject();
                respobj.put("code", Constants.NO_BIND_PHONE);
                respobj.put("msg", "");
                respobj.put("content", "{}");
                writer.print(respobj.toString());
                writer.close();
                return;
            }

            Map<String, String> map = new HashMap<String, String>();
            map.put("userid", userId);
            map.put("phonenum", phoneNum);
            map.put("msgtemplate", "您的验证码是：{$verifyCode$}，请及时输入验证。");
            try {
                String data = WebUtil.readContentFromGet(url, map);
                JSONObject obj = JSONObject.fromObject(data);
                response.getWriter().print(obj.toString());

                // 打印日志
                FileUtil.addLog(request, "sendverifycode", channelNo, "phonenum", phoneNum);
            } catch (Exception e) {
                log.error("sendsmscode：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("sendsmscode：" + e);
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * 
     * @param request
     * @param response
     * @param url
     * @param userId
     * @param phoneNum
     * @param sessionId
     * @param imei
     * @param writer
     */
    private void sendSmsForLoginUser(HttpServletRequest request, HttpServletResponse response, String url, String userId, String phoneNum, final String sessionId,
            final String imei, PrintWriter writer) {
        String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        if (!SessionUtil.validate(userId, imei, sessionId)) {
            JSONObject respobj = new JSONObject();
            respobj.put("code", Constants.ERROR);
            respobj.put("msg", "");
            writer.print(respobj.toString());
            return;
        }

        // 验证用户是否绑定了手机
        String checkUrl = Constants.BASE_URL_SP_SP + "checkUsername/";
        String phone = checkUser(checkUrl, phoneNum, userId);
        if (StringUtil.isNotEmpty(phone)) {
            JSONObject respobj = new JSONObject();
            respobj.put("code", Constants.ERROR_PHONE_HAS_BIND);
            respobj.put("msg", Constants.PHONE_HAS_BIND_PROMPT);
            respobj.put("content", "{}");
            writer.print(respobj.toString());
            writer.close();
            return;
        }

        if (StringUtil.isNotEmpty(userId, phoneNum)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userid", userId);
            map.put("phonenum", phoneNum);
            map.put("msgtemplate", "您的验证码是：{$verifyCode$}，请及时输入验证。");
            try {
                String data = WebUtil.readContentFromGet(url, map);
                JSONObject obj = JSONObject.fromObject(data);
                response.getWriter().print(obj.toString());

                // 打印日志
                FileUtil.addLog(request, "sendverifycode", channelNo, "userid", userId, "phonenum", phoneNum);
            } catch (Exception e) {
                log.error("sendsmscode：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("sendsmscode：" + e);
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * 验证用户id是否绑定了手机号码
     * 
     * @param checkUrl
     * @param userName
     *            or phoneNum
     * @return
     */
    private String checkUser(String checkUrl, String userName, String userId) {
        if (StringUtil.isNotEmpty(userName)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", userName);
            try {
                String data = WebUtil.readContentFromGet(checkUrl, map);
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject content = null;
                String phone = null;
                String contentStr = obj.getString("content");
                if (contentStr != null && contentStr.length() > 2) {
                    content = obj.getJSONObject("content");
                    String oriUserId = content.getString("userid");
                    if (!userId.equals(oriUserId)) {
                        phone = content.getString("mobile");
                    }

                }
                return phone;
            } catch (Exception e) {
                log.error("sendsmscode：" + e);
            }
        }
        return null;
    }

    /**
     * 检查是否存在该用户、返回userid
     * 
     * @param checkUrl
     * @param userName
     *            or phoneNum
     * @return
     */
    private String checkUser(String checkUrl, String userName) {
        if (StringUtil.isNotEmpty(userName)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", userName);
            try {
                String data = WebUtil.readContentFromGet(checkUrl, map);
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject content = null;
                String userid = null;
                String contentStr = obj.getString("content");
                if (contentStr != null && contentStr.length() > 2) {
                    content = obj.getJSONObject("content");
                    userid = content.getString("userid");
                }

                if (StringUtil.isNotEmpty(userid)) {
                    return userid;
                }
            } catch (Exception e) {
                log.error("checkUsername：" + e);
            }
        }
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
