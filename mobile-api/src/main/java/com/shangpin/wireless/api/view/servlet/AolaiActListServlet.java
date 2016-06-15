package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.AolaiIndexAPIResponse;
import com.shangpin.wireless.api.util.AolaiIndexCacheUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 奥莱首页接口
 * 
 * @author liling
 */
public class AolaiActListServlet extends HttpServlet {
	private static final long serialVersionUID = -2773996041471611219L;
	protected final Log log = LogFactory.getLog(AolaiActListServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String tabid = request.getParameter("tabid");
			String userid = request.getParameter("userid");
			JSONObject resultJson = new JSONObject();
		
			JSONArray adActivity = new JSONArray();
			boolean first=true;
			if(tabid.equals("2")){
				tabid="1";
				first=false;
			}
			JSONArray topics = new JSONArray();
			if (StringUtil.isNotEmpty(tabid)) {
				JSONArray activity = AolaiIndexCacheUtil.getActivityIndex(tabid, "1", "2", "1", "1000");
				JSONArray ingActivity = new JSONArray();
				JSONArray groupTime = AolaiIndexCacheUtil.queryGroupTime("1", "2");
				adActivity = AolaiIndexCacheUtil.queryAdActivity("3", "1", "2");
				AolaiIndexAPIResponse aolaiIndexAPIResponse = new AolaiIndexAPIResponse();
				aolaiIndexAPIResponse.setCode("0");
				if (tabid.equals("1")){
					if(first){
						ingActivity = AolaiIndexCacheUtil.getActivityIndex("2", "1", "2", "1", "1000");
						if(ingActivity != null && ingActivity.size() > 0){
							aolaiIndexAPIResponse.setIngActivity(ingActivity);
						}
					}
				
					String toppicw = "1";
					String toppich = "2";
					topics= AolaiIndexCacheUtil.getNewTopic("0" + "_" + toppicw + "_" + toppich,false);
					if (topics != null && topics.size() > 0) {
						aolaiIndexAPIResponse.setTopics(topics);
					}
				}
				if (activity != null && activity.size() > 0) {
					if((topics != null && topics.size() > 0)){
						for(int i=0;i<topics.size();i++){
							JSONObject topic = (JSONObject) topics.get(i);
							for(int j=0;j<activity.size();j++){
								JSONObject ac = (JSONObject) activity.get(j);
								if(topic.getString("topicid").equals(ac.getString("activityid"))){
									activity.remove(j);
									break;
								}
							}
						}
					}
					aolaiIndexAPIResponse.setActivity(activity);
				}
				if (groupTime != null && groupTime.size() > 0) {
					aolaiIndexAPIResponse.setGroupTime(groupTime);
				}
				
				if (adActivity != null && adActivity.size() > 0) {
					aolaiIndexAPIResponse.setAdActivity(adActivity);
				}
			
				if (StringUtils.isNotEmpty(userid)) {
					JSONArray collection = AolaiIndexCacheUtil.queryCollection(userid, null, null, "1", "1", "2", "1", "1000");
					aolaiIndexAPIResponse.setCollection(collection);
				}	
				JSONObject result = new JSONObject();
				result = aolaiIndexAPIResponse.obj2Json(tabid, userid,first);
				resultJson.put("result", result);
				JSONObject aolaiIndexJson = (JSONObject) resultJson.get("result");
				response.getWriter().print(aolaiIndexJson.toString());
			} else {
				try {
					WebUtil.sendErrorInvalidParams(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("AolaiActListServlet：" + e);
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
	}
}
