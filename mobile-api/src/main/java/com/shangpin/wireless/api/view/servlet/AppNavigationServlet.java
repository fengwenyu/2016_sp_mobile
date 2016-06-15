package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.shangpin.wireless.api.api2client.domain.AppNavigationAPIResponse;
import com.shangpin.wireless.api.domain.AolaiActivity;
import com.shangpin.wireless.api.domain.AppNavigation;
import com.shangpin.wireless.api.domain.ShowTypeEnum;
import com.shangpin.wireless.api.service.AolaiActivityService;
import com.shangpin.wireless.api.service.AppNavigationService;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * APP导航接口
 */
public class AppNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = -7006761440891887218L;
	protected final Log log = LogFactory.getLog(AppNavigationServlet.class);
	private AppNavigationService appNavigationService;
	private AolaiActivityService aolaiActivityService;
	private static final int APPNAVIGATION_KEEP_TIME = 0 * 60 * 1000; // 30分钟
	private static HashMap<String, JSONObject> AppNavigationJson = new HashMap<String, JSONObject>();
	private static long AppNavigationTime;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long now = System.currentTimeMillis();
			if (now > AppNavigationTime + APPNAVIGATION_KEEP_TIME) {
				AppNavigationJson.clear();
				List<AppNavigation> appNavigationList = new ArrayList<AppNavigation>();
				appNavigationList = appNavigationService.findAll();
				AolaiActivity aolaiActivity= aolaiActivityService.findAolaiActivity();
				AppNavigationAPIResponse appNavigationListAPIResponse = new AppNavigationAPIResponse();
				appNavigationListAPIResponse.setCode("0");
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (appNavigationList != null && appNavigationList.size() > 0) {
					for(int i=0;i<appNavigationList.size();i++){
						AppNavigation appNavigation =  appNavigationList.get(i);
						if(appNavigation.getShowType().equals(ShowTypeEnum.HTML)){
							if(appNavigation.getEndDate() != null){
								if(sdf.parse(appNavigation.getEndDate().toString()).before(new Date())){
									appNavigationList.remove(i);
								}
							}
						}
					}
					appNavigationListAPIResponse.setAppNavigationList(appNavigationList);
				}
				if (aolaiActivity != null) {
					appNavigationListAPIResponse.setAolaiActivity(aolaiActivity);
				}
				JSONObject result = appNavigationListAPIResponse.obj2Json();
				AppNavigationJson.put("result", result);
				AppNavigationTime = now;
			}
			JSONObject appNavigationJson = AppNavigationJson.get("result");
			
			JSONObject obj = new JSONObject();
			obj.put("code", "0");
			obj.put("msg", "");
			appNavigationJson.put("systime", String.valueOf(now));
			obj.put("content", appNavigationJson);
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AppNavigationServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext sc = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		appNavigationService = (AppNavigationService) ctx.getBean(AppNavigationService.SERVICE_NAME);
		aolaiActivityService = (AolaiActivityService) ctx.getBean(AolaiActivityService.SERVICE_NAME);
	}
}
