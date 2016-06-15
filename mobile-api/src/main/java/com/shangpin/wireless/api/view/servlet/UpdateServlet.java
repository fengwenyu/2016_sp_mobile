package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.api2server.domain.CommonServerResponse;
import com.shangpin.wireless.api.domain.OnlineManage;
import com.shangpin.wireless.api.service.OnlineManageService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.DBType;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 产品下载
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-30
 */
public class UpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(UpdateServlet.class);
    private OnlineManageService onlineManageService;

    @Override
    public void init() throws ServletException {
        ServletContext sc = this.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        onlineManageService = (OnlineManageService) ctx.getBean(OnlineManageService.SERVICE_NAME);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String productNum = request.getHeader("p");
        final String channelNum = request.getHeader("ch");
        final String version = request.getParameter("ver");
        final String manual = request.getParameter("manual");
        final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
        if (StringUtil.isNotEmpty(productNum, channelNum, version)) {
            try {
                JSONObject content = new JSONObject();
                CommonServerResponse returnMsg = new CommonServerResponse();

                HqlHelper hqlHelper = new HqlHelper(OnlineManage.class, "o");
                hqlHelper.addWhereCondition("o.channelNum=?", Long.parseLong(channelNum.trim()));
                hqlHelper.addWhereCondition("o.productNum=?", Long.parseLong(productNum.trim()));
                hqlHelper.addWhereCondition("o.inuse=?", 1);
                OnlineManage onlineManage = onlineManageService.getByCondition(hqlHelper, DBType.dataSourceAPI.toString());
                if (onlineManage != null) {
                    if (compare(onlineManage.getVersionForceMax(), version) > 0) {
                        content.put("downurl", onlineManage.getDownloadPath());
                        content.put("isforce", "1");// 1表示强制升级，0表示非强制升级
                        content.put("prompt", onlineManage.getPrompt());
                        content.put("ver", onlineManage.getVersionLatest());
                    } else if (compare(onlineManage.getVersionLatest(), version) > 0) {
                        content.put("downurl", onlineManage.getDownloadPath());
                        content.put("isforce", "0");// 1表示强制升级，0表示非强制升级
                        content.put("prompt", onlineManage.getPrompt());
                        content.put("ver", onlineManage.getVersionLatest());
                    } else {
                        content.put("downurl", "");
                        content.put("isforce", "0");// 1表示强制升级，0表示非强制升级
                        content.put("prompt", "当前已经是最新版本");
                        content.put("ver", onlineManage.getVersionLatest());
                    }
                } else {
                    HqlHelper hqlHelperDefault = new HqlHelper(OnlineManage.class, "o");
                    hqlHelperDefault.addWhereCondition("o.channelNum=?", Long.parseLong("0"));
                    hqlHelperDefault.addWhereCondition("o.productNum=?", Long.parseLong(productNum.trim()));
                    hqlHelperDefault.addWhereCondition("o.inuse=?", 1);
                    OnlineManage onlineManageDefault = onlineManageService.getByCondition(hqlHelperDefault, DBType.dataSourceAPI.toString());
                    if (onlineManageDefault != null) {
                        if (compare(onlineManageDefault.getVersionForceMax(), version) > 0) {
                            content.put("downurl", onlineManageDefault.getDownloadPath());
                            content.put("isforce", "1");// 1表示强制升级，0表示非强制升级
                            content.put("prompt", onlineManageDefault.getPrompt());
                            content.put("ver", onlineManageDefault.getVersionLatest());
                        } else if (compare(onlineManageDefault.getVersionLatest(), version) > 0) {
                            content.put("downurl", onlineManageDefault.getDownloadPath());
                            content.put("isforce", "0");// 1表示强制升级，0表示非强制升级
                            content.put("prompt", onlineManageDefault.getPrompt());
                            content.put("ver", onlineManageDefault.getVersionLatest());
                        } else {
                            content.put("downurl", "");
                            content.put("isforce", "0");// 1表示强制升级，0表示非强制升级
                            content.put("prompt", "当前已经是最新版本");
                            content.put("ver", onlineManageDefault.getVersionLatest());
                        }
                    } else {
                        content.put("downurl", "");
                        content.put("isforce", "0");// 1表示强制升级，0表示非强制升级
                        content.put("prompt", "当前已经是最新的版本");
                        content.put("ver", version);
                    }
                }
                returnMsg.setMsg("");
                returnMsg.setCode("0");
                returnMsg.setContent(content);
                response.getWriter().print(JSONObject.fromObject(returnMsg));
            } catch (Exception e) {
                log.error("Update-doGet()" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            // 记录访问日志
            FileUtil.addLog(request, "update", channelNo, "ver", version, "manual", manual);
        } else {
            try {
                WebUtil.sendErrorInvalidParams(response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 比较版本号
     * 
     * @Author: zhouyu
     * @CreatDate: 2012-07-30
     * @param versionForceMax
     *            强制升级最大版本号
     * @param version
     *            现用软件版本号
     */
    public int compare(String versionForceMax, String version) {
        // 0：相等；1：大于；-1：小于
        String[] bigArr = versionForceMax.split("\\.");
        String[] smallArr = version.split("\\.");
        for (int i = 0; i < 3; i++) {
            if (Integer.parseInt(bigArr[i]) > Integer.parseInt(smallArr[i])) {
                return 1;
            } else if (Integer.parseInt(bigArr[i]) < Integer.parseInt(smallArr[i])) {
                return -1;
            }
        }
        return 0;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
