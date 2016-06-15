package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.shangpin.utils.HttpClientUtil;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.IOSDownloadActive;
import com.shangpin.wireless.api.service.IOSDownloadService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * IOS下载激活回调接口
 * 
 * @author xupengcheng
 * 
 */
public class IOSDownloadActiveServlet extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(IOSDownloadActiveServlet.class);
    private IOSDownloadService iosDownloadService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ac = ApplicationContextUtil.get("ac");
        iosDownloadService = (IOSDownloadService) ac.getBean("iosDownloadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ifa = req.getParameter("ifa"); // identifier for advertisers 7
        String mac = req.getParameter("mac"); // 设备标识 6
        String appType = req.getParameter("p");// app类型 尚品、奥莱
        String imei = req.getParameter("imei");//
        String version = req.getParameter("version");// 版本号
        String channel="";
        String channelName="";
        log.info("ifa:" + ifa + "mac:" + mac + "imei:" + imei + "appType:" + appType + "version:" + version + "appType:" + appType);
        if (StringUtil.isNotEmpty(appType, imei)) {
            IOSDownloadActive iosDownloadActive = new IOSDownloadActive();
            // 先查询库中是否有imei，已经有的话 说明激活过
            iosDownloadActive.setImei(imei);
            //iosDownloadActive.setAppType(appType);
            //iosDownloadActive.setVersion(version);
            try {// 先用imei去找，如果找到了，其实应该是已经激活过了，不然不会记录imei
                IOSDownloadActive result = iosDownloadService.getIOSDownloadActive(iosDownloadActive);
                if (result != null) {
                    String isActive = result.getIsActive();
                    if (isActive == null || isActive.equals("0")) {
                        String callbackUrl = result.getCallbackUrl();
                        if (isActive(result.getChannel())) {
                            result.setIsActive("2");
                            callbackUrl = "";
                        } else {
                            result.setIsActive("1");
                        }
                        channel=result.getChannel();
                        channelName=result.getChannelName();
                        result.setActiveTime(new Date());
                        iosDownloadService.saveOrUpdate(result);
                        HttpClientUtil.doGet(callbackUrl, null);
                    }
                } else {
                    iosDownloadActive.setImei(null);
                    if (ifa != null && !"".equals(ifa)) {
                        iosDownloadActive.setIfa(ifa);
                    } else if (mac != null && !"".equals(mac)) {
                        iosDownloadActive.setMac(mac);
                    }
                    result = iosDownloadService.getIOSDownloadActive(iosDownloadActive);
                    if (result != null) {// 没找到，不激活,找到了随便取一条，更新imei，并激活
                        String callbackUrl = result.getCallbackUrl();
                        result.setImei(imei);
                        result.setActiveTime(new Date());
                        if (isActive(result.getChannel())) {
                            result.setIsActive("2");
                            callbackUrl = "";
                        } else {
                            result.setIsActive("1");
                        }
                        channel=result.getChannel();
                        channelName=result.getChannelName();
                        iosDownloadService.saveOrUpdate(result);
                        HttpClientUtil.doGet(callbackUrl, null);
                    }
                }                
                JSONObject apiResult = new JSONObject();
                JSONObject contentObject=new JSONObject();
                contentObject.put("channel", channel);
                contentObject.put("channelName", channelName);
                apiResult.put("code", Constants.SUCCESS);
                apiResult.put("msg", "");
                apiResult.put("content",contentObject);
                resp.getWriter().print(apiResult);
                FileUtil.addLog(null, "iosDownloadActive", "channel", result != null ? result.getChannel() : "", "isActive", result != null ? result.getIsActive() : "", // 1:为激活
                        "appType", appType, // 0为尚品，1为奥莱
                        "imei", imei, "mac", mac, "version", version, "ifa", ifa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                WebUtil.sendErrorInvalidParams(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public boolean isActive(String channel) {
        boolean flag = false; 
       /* boolean flag = true;        
       if ("57".equals(channel)) {
            int min = 0;
            int max = 100000;
            Random ran = new Random();
            int probability = ran.nextInt(100000);
            if (probability >= min && probability < max) {
                flag=false;
            }
        } else if ("55".equals(channel)) {
            int min = 0;
            int max = 20000;
            Random ran = new Random();
            int probability = ran.nextInt(100000);
            if (probability >= min && probability < max) {
                flag=false;
            }
        }else if ("98".equals(channel)) {
            int min = 0;
            int max = 20000;
            Random ran = new Random();
            int probability = ran.nextInt(100000);
            if (probability >= min && probability < max) {
                flag=false;
            }
        }else if ("97".equals(channel)) {
            int min = 0;
            int max = 40000;
            Random ran = new Random();
            int probability = ran.nextInt(100000);
            if (probability >= min && probability < max) {
                flag=false;
            }
        }else if ("101".equals(channel)) {
            int min = 0;
            int max = 45000;
            Random ran = new Random();
            int probability = ran.nextInt(100000);
            if (probability >= min && probability < max) {
                flag=false;
            }
        }
        else {
            flag=false;
        }*/
        return flag;
    }
}
