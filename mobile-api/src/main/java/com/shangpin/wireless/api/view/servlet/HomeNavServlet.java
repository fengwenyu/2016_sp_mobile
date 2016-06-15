package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.service.FindManageService;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 尚品客户端导航接口
 * 
 * @author liling
 * @date 2014-8-25
 */
public class HomeNavServlet extends BaseServlet {

	private static final long serialVersionUID = 8916085796721777528L;
	protected final Log log = LogFactory.getLog(HomeNavServlet.class);
	private static final int APPNAVIGATION_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static HashMap<String, JSONArray> HomeNavJson = new HashMap<String, JSONArray>();
	private static long HomeNavTime;
	private FindManageService findManageService;
	
	@Override
	public void init() throws ServletException {
		findManageService = (FindManageService) getBean(FindManageService.class);
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FindManage staticActivity= findManageService.findStaticActivity();
			JSONObject content = new JSONObject();

			long now = System.currentTimeMillis();
			if (now > HomeNavTime + APPNAVIGATION_KEEP_TIME) {
				HomeNavJson.clear();

				Properties props = PropertiesUtil.getInstance("/config/homenav/homenav.properties");
				for (Enumeration<Object> e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					String propvalue;
					propvalue = new String(props.getProperty(propkey));
					JSONArray json = JSONArray.fromObject(propvalue);
					HomeNavJson.put(propkey, json);

				}

				final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONArray homeNavArray = HomeNavJson.get("homenav");
				JSONArray list = new JSONArray();
				if (null != homeNavArray && !"".equals(homeNavArray)) {
					for (int i = 0; i < homeNavArray.size(); i++) {
						JSONObject obj = (JSONObject) homeNavArray.get(i);
						if (!obj.isEmpty()) {
							if (obj.has("startTime") && obj.has("endTime")){
									Date starttime = sdfconfig.parse(obj.getString("startDate"));
									Date endtime = sdfconfig.parse(obj.getString("endDate"));
									Date date = new Date(now);
									if (!date.after(starttime)|| date.before(endtime)) {
										homeNavArray.remove(i);
										i=i-1;
									}else{
										JSONObject childObj=new JSONObject();
										childObj.put("name", obj.getString("name"));
										childObj.put("type", obj.getString("type"));
										childObj.put("pic", obj.getString("pic"));
										childObj.put("refContent", obj.getString("refContent"));
										list.add(childObj);
									}
							}else{
								list.add(obj);
							}	
						
						}
				
						content.put("list", list);
					}
				}
			}
			JSONObject staticatc = new JSONObject();
			if (staticActivity != null) {
				if(staticActivity.getShowStartDate()!=null&&staticActivity.getShowEndDate()!=null){
					staticatc.put("startTime", String.valueOf(sdf.parse(sdf.format(staticActivity.getShowStartDate())).getTime()));
					staticatc.put("endTime", String.valueOf(sdf.parse(sdf.format(staticActivity.getShowEndDate())).getTime()));
					staticatc.put("isDisplay", String.valueOf(staticActivity.getDisplay().value));
					staticatc.put("url", staticActivity.getGetUrl());
				}
			
			}
			content.put("staticAct", staticatc);
			JSONObject obj = new JSONObject();
			obj.put("code", "0");
			obj.put("msg", "");
			obj.put("content", content);
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("HomeNavServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}



}
