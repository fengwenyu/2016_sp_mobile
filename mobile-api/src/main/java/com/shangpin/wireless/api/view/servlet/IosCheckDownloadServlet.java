package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.IOSDownloadActive;
import com.shangpin.wireless.api.service.IOSDownloadService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * IOS是否有下载接口
 * 
 * @author wangfeng
 * 
 */
public class IosCheckDownloadServlet extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(IosCheckDownloadServlet.class);
    private IOSDownloadService iosDownloadService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ac = ApplicationContextUtil.get("ac");
        iosDownloadService = (IOSDownloadService) ac.getBean("iosDownloadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ifas = req.getParameter("ifas"); // identifier for advertisers 7
       // String appid = req.getParameter("appid"); // 针对哇霸
        String appType = req.getParameter("appType");// app类型 尚品、奥莱
        String version = req.getParameter("version");// 版本号
        if (StringUtil.isNotEmpty(appType,ifas)) {
            IOSDownloadActive iosDownloadActive = new IOSDownloadActive();
            iosDownloadActive.setIfas(ifas);
          //  iosDownloadActive.setAppType(appType);
           // iosDownloadActive.setVersion(version);
            try {
                JSONObject apiResult = new JSONObject(); 
                List<IOSDownloadActive> iosDownloadActiveList = iosDownloadService.getIOSDownloadActiveList(iosDownloadActive);
                String isDownload="0";
                String [] ifaArray=ifas.split(",");
                if (iosDownloadActiveList != null&&iosDownloadActiveList.size()>0) {                                        
                    for (int i = 0; i < ifaArray.length; i++) {
                        for (int j = 0; j < iosDownloadActiveList.size(); j++) {                           
                            if(ifaArray[i].equals(iosDownloadActiveList.get(j).getIfa())){
                                isDownload="1";
                            }else{
                                isDownload="0";
                            }
                            apiResult.put(ifaArray[i], isDownload);
                        }
                    }                    
                } else {
                    for (int i = 0; i < ifaArray.length; i++) {
                        apiResult.put(ifaArray[i], isDownload);
                    } 
                }
                resp.getWriter().print(apiResult);
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

}
