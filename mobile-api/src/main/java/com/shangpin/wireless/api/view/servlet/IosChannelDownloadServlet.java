package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.api2client.domain.YouMiMessageCode;
import com.shangpin.wireless.api.domain.IOSDownloadActive;
import com.shangpin.wireless.api.service.IOSDownloadService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * IOS渠道用户下载记录接口
 * 
 * @author xupengcheng
 * 
 */
public class IosChannelDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270416351126194854L;
	private IOSDownloadService iosDownloadService;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac = ApplicationContextUtil.get("ac");
		iosDownloadService = (IOSDownloadService) ac.getBean("iosDownloadService");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * ifa mac callback_url
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommonAPIResponse apiResponse = new CommonAPIResponse();
		String ifa = StringUtil.StringFilter(req.getParameter("ifa")); // identifier for advertisers
		String mac = StringUtil.StringFilter(req.getParameter("mac")); // 设备标识
		String callbackUrl = req.getParameter("callback_url"); // 回调地址
		String appType = StringUtil.StringFilter(req.getParameter("appType")); // app类型 尚品 2、奥莱 1
		String channel = StringUtil.StringFilter(req.getParameter("channel"));
		String channelName=StringUtil.StringFilter(req.getParameter("channelName"));
		String version = StringUtil.StringFilter(req.getParameter("version"));//版本号
		String appid=StringUtil.StringFilter(req.getParameter("appid"));//针对哇霸单独处理
		if (!(StringUtil.isNotEmpty(ifa))&&!(StringUtil.isNotEmpty(mac))) {
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			if (StringUtil.isNotEmpty(appType, channel, version)) {
				IOSDownloadActive iosDownloadActive = new IOSDownloadActive();
				iosDownloadActive.setAppType(appType);
				if (StringUtil.isNotEmpty(ifa)) {
					iosDownloadActive.setIfa(ifa);
				}
				if (StringUtil.isNotEmpty(mac)) {
					iosDownloadActive.setMac(mac);
				}
				iosDownloadActive.setVersion(version);
				if("109".equals(channel)){            //哇霸渠道单独处理
				    String url="http://api.wooboo.com.cn/services/cpa/callback?appid="+appid+"&idfa="+ifa+"&mac="+mac;
				    iosDownloadActive.setCallbackUrl(url);
				}else{				    
				    iosDownloadActive.setCallbackUrl(callbackUrl);
				}
				iosDownloadActive.setChannel(channel);
				iosDownloadActive.setChannelName(channelName);
				iosDownloadActive.setAppid(appid);
				boolean flag = false;
				try {
					flag = iosDownloadService.saveOrUpdate(iosDownloadActive);
					if (flag) {
						apiResponse.setCode(YouMiMessageCode.Normal.id);
						apiResponse.setMsg(YouMiMessageCode.Normal.name);
						resp.getWriter().print(apiResponse.obj2Json());
					}else{
						try {
							WebUtil.sendApiException(resp);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
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
	}
}
