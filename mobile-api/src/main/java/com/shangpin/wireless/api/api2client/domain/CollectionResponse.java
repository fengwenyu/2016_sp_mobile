package com.shangpin.wireless.api.api2client.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;

/**
 * 收藏活动
 * 
 * @author xupengcheng
 * 
 */
public class CollectionResponse extends CommonAPIResponse {
	JSONArray list;
	private AoLaiService aoLaiService;

	public CollectionResponse() {
		this.aoLaiService = ApplicationContextUtil.get("ac").getBean(AoLaiService.class);
		list = getGroupTime();
	}

	@Override
	public String obj2Json(String data) {
		TreeMap<String, JSONObject> map = new TreeMap<String, JSONObject>();
		JSONObject result = new JSONObject();
		JSONObject obj = JSONObject.fromObject(data);
		if (obj != null) {
			String code = obj.getString("code");
			String msg = obj.getString("msg");
			result.put("code", code);
			result.put("msg", msg);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			Object contentObj = obj.get("content");
			JSONObject resultObj = new JSONObject();
			if (contentObj != null && contentObj instanceof JSONArray) {
				JSONArray content = (JSONArray) contentObj;
				if (content != null && !"[]".equals(content) && content.size() > 0) {
					for (int i = 0; i < content.size(); i++) {
						JSONObject temp = content.getJSONObject(i);
						temp.put("iscollect", "1");
						String startTime = temp.getString("starttime");
						Long startTimeL = Long.parseLong(startTime);
						calendar.setTimeInMillis(startTimeL);
						Date date = calendar.getTime();
						String timeStr = df.format(date);
						String day = timeStr.substring(0, timeStr.indexOf(" "));
						String hms = timeStr.substring(timeStr.indexOf(" ") + 1);
						String groupTime = getSubjectGroupName(hms);
						String key = day + " " + groupTime + "开售";
						if (groupTime != null) {
							if (map.containsKey(key)) {
								JSONObject resultObj1 = map.get(key);
								resultObj1.getJSONArray("list").add(temp);
							} else {
								JSONObject title = new JSONObject();
								title.put("desc", key);
								JSONArray array = new JSONArray();
								array.add(temp);
								title.put("list", array);
								map.put(key, title);
							}
						}
					}
				}
			}
			if (map.size() > 0) {
				String[] keys = map.descendingKeySet().toArray(new String[0]);
				JSONArray array = new JSONArray();
				for (String key : keys) {
					array.add(map.get(key));
				}
				resultObj.put("group", array);
			} else {
				resultObj.put("group", new JSONArray());
			}
			result.put("content", resultObj);
		}
		return result.toString();
	}

	/**
	 * 返回几点开售
	 * 
	 * @param startTime
	 * @return
	 */
	private String getSubjectGroupName(String startTime) {
		String resultTime = "";
		if (list != null && list.size() > 0) {
			float min = 0f;
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = list.getJSONObject(i);
				float startTime0 = Float.parseFloat(obj.getString("starttime").replace(":", "."));
				float endTime = Float.parseFloat(obj.getString("endtime").replace(":", "."));
				if (endTime < 1) {
					endTime = 24 + endTime;
				}
				float startTime1 = Float.parseFloat(startTime.replace(":", "."));
				if (startTime1 >= startTime0 && startTime1 < endTime) {
					float tempMin = startTime1 - startTime0;
					if (min == 0f && tempMin >= 0f) {
						min = tempMin;
						resultTime = obj.getString("starttime");
					} else if (min > 0f && tempMin >= 0f && tempMin < min) {
						min = tempMin;
						resultTime = obj.getString("starttime");
					}
				}

			}
		}
		return resultTime;
	}

	/**
	 * 活动分组时间
	 * 
	 * @return
	 */
	private JSONArray getGroupTime() {
		JSONArray list = new JSONArray();
		String data = null;
		try {
			data = aoLaiService.findGroupTime("100", "100");
			JSONObject obj = JSONObject.fromObject(data);
			list = obj.getJSONArray("content");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
