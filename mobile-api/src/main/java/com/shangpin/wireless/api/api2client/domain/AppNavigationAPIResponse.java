package com.shangpin.wireless.api.api2client.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import com.shangpin.wireless.api.domain.AolaiActivity;
import com.shangpin.wireless.api.domain.AppNavigation;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.ShowTypeEnum;

public class AppNavigationAPIResponse {
	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private List<AppNavigation> appNavigationList;
	private AolaiActivity aolaiActivity;

	public List<AppNavigation> getAppNavigationList() {
		return appNavigationList;
	}

	public void setAppNavigationList(List<AppNavigation> appNavigationList) {
		this.appNavigationList = appNavigationList;
	}

	public AolaiActivity getAolaiActivity() {
		return aolaiActivity;
	}

	public void setAolaiActivity(AolaiActivity aolaiActivity) {
		this.aolaiActivity = aolaiActivity;
	}

	/**
	 * 返给客户端的json数据
	 */
	public JSONObject obj2Json() {
		
		JSONObject appNavigationJson = new JSONObject();
		
		if (Constants.SUCCESS.equals(getCode())) {  
			JSONArray list = new JSONArray();
			if (appNavigationList != null && appNavigationList.size() > 0) {
				for (int i = 0; i < appNavigationList.size(); i++) {
					AppNavigation appNavigation = appNavigationList.get(i);
					JSONObject object = new JSONObject();
					object.put("id",String.valueOf(appNavigation.getId()) );
					object.put("name", appNavigation.getName());
					object.put("type", String.valueOf(appNavigation.getShowType().value));
					String link=appNavigation.getLink();
					String tabId=appNavigation.getTabId();
					if(appNavigation.getShowType().equals(ShowTypeEnum.HTML)){
						object.put("tabId", "");
						object.put("link", link);
					}else{
						if(StringUtils.isNotEmpty(tabId)){
							object.put("tabId", tabId);
							object.put("link", "");
						}else{
							object.put("tabId", "");
							object.put("link", "");
						}
						
					}
					
					
					list.add(object);
				}
			}
			JSONObject staticatc = new JSONObject();
			if (aolaiActivity != null) {
				Date startTime=aolaiActivity.getStartTime();
				if(startTime!=null){
					staticatc.put("startTime", String.valueOf(startTime.getTime()));
				}else{
					staticatc.put("startTime", "");
				}
				Date endTime=aolaiActivity.getEndTime();
				if(endTime!=null){
					staticatc.put("endTime", String.valueOf(endTime.getTime()));
				}else{
					staticatc.put("endTime", "");
				}
				staticatc.put("isDisplay", String.valueOf(aolaiActivity.getDisplay().value));
				staticatc.put("url", aolaiActivity.getGetUrl());
			}
			appNavigationJson.put("staticAct", staticatc);
			appNavigationJson.put("list", list);
		
		}
		
		return appNavigationJson;
	}
}
