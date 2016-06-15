package com.shangpin.wireless.api.api2client.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;



import com.shangpin.wireless.api.util.AolaiIndexCacheUtil;
import com.shangpin.wireless.api.util.DateUtil;
import com.shangpin.wireless.api.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AolaiIndexAPIResponse {
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

	private JSONArray activity;
	private JSONArray groupTime;
	private JSONArray adActivity;
	private JSONArray topics;
	private JSONArray collection;
	private JSONArray ingActivity;
	public JSONArray getActivity() {
		return activity;
	}

	public void setActivity(JSONArray activity) {
		this.activity = activity;
	}

	public JSONArray getGroupTime() {
		return groupTime;
	}

	public void setGroupTime(JSONArray groupTime) {
		this.groupTime = groupTime;
	}

	public JSONArray getAdActivity() {
		return adActivity;
	}

	public void setAdActivity(JSONArray adActivity) {
		this.adActivity = adActivity;
	}

	public JSONArray getTopics() {
		return topics;
	}

	public void setTopics(JSONArray topics) {
		this.topics = topics;
	}

	public JSONArray getCollection() {
		return collection;
	}

	public void setCollection(JSONArray collection) {
		this.collection = collection;
	}

	public JSONArray getIngActivity() {
		return ingActivity;
	}

	public void setIngActivity(JSONArray ingActivity) {
		this.ingActivity = ingActivity;
	}

	public JSONObject obj2Json(String tabid, String userid,boolean first) {
		try {
		
				final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONObject obj = new JSONObject();
				obj.put("code", getCode());
				obj.put("msg", getMsg() == null ? "" : getMsg());
				JSONObject aolaiIndexJson = new JSONObject();
				long now = System.currentTimeMillis();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				aolaiIndexJson.put("systime", String.valueOf(now));
				if (tabid.equals("1")&&groupTime!= null && groupTime.size() > 0) {
					JSONObject temp = (JSONObject) groupTime.get(0);
					aolaiIndexJson.put("tmrupdatetime", String.valueOf(sdfconfig.parse(DateUtil.getAfterAmountDay(date, 1, "yyyy-MM-dd")+ " "+ temp.getString("starttime") + ":00").getTime()));
				} else {
					aolaiIndexJson.put("tmrupdatetime", "");
				}
				// 活动图
				JSONArray groupArray = new JSONArray();
				JSONArray[] activityArray = null;
				if(groupTime!= null && groupTime.size() > 0){
					activityArray = new JSONArray[groupTime.size()];
					for (int j = 0; j < groupTime.size(); j++) {
						activityArray[j] = new JSONArray();
					}
				}	
				if (tabid.equals("1")) {
					if(groupTime!= null && groupTime.size() > 0){
						String dateStr = null;
						String hourStr = null;
						JSONObject secondGroup = new JSONObject();
						if (activity != null && activity.size() > 0) {
							String eveningShowTime = AolaiIndexCacheUtil.getDetail("eveningshowtime");
							for (int i = 0; i < activity.size(); i++) {
								JSONObject oj = (JSONObject) activity.get(i);
								if (!oj.isEmpty()) {
									String startDate = oj.getString("starttime");
									long startTime = Long.valueOf(startDate);
									for (int j = 0; j < groupTime.size(); j++) {
										JSONObject firstGroup = (JSONObject) groupTime.get(j);
										if (j + 1 >= groupTime.size()) {
											JSONObject temp = (JSONObject) groupTime.get(0);
											dateStr = DateUtil.getAfterAmountDay(date,1, "yyyy-MM-dd");
											hourStr = temp.getString("starttime");
										} else {
											secondGroup = (JSONObject) groupTime.get(j + 1);
											dateStr = DateUtil.getAfterAmountDay(date,0, "yyyy-MM-dd");
											hourStr = secondGroup.getString("starttime");
										}
										long eveningTime = 0;
										if (StringUtil.isNotEmpty(eveningShowTime)) {
											eveningTime = sdfconfig.parse(sdf.format(date) + " "+ eveningShowTime).getTime();
										} else {
											eveningTime = sdfconfig.parse(sdf.format(date) + " "+ "19:00:00").getTime();
										}
										long fristTime = sdfconfig.parse(sdf.format(date)+ " "+ firstGroup.getString("starttime")+ ":00").getTime();
										long secondTime = sdfconfig.parse(dateStr + " " + hourStr + ":00").getTime();
										if (startTime >= fristTime&& startTime < secondTime) {
											JSONObject child = new JSONObject();
											child = getChild(oj, tabid, fristTime,eveningTime);
											if (!child.isEmpty()) {
												activityArray[j].add(child);
												break;
											}
										}
									}
								}
							}
						}
						for (int j = 0; j < groupTime.size(); j++) {
							JSONObject list = new JSONObject();
							JSONObject firstGroup = (JSONObject) groupTime.get(j);
							long fristTime = 0;
							long secondTime = 0;
							if (j + 1 >= groupTime.size()) {
								JSONObject temp = (JSONObject) groupTime.get(0);
								dateStr = DateUtil.getAfterAmountDay(date, 1,"yyyy-MM-dd");
								hourStr = temp.getString("starttime");
							} else {
								secondGroup = (JSONObject) groupTime.get(j + 1);
								dateStr = DateUtil.getAfterAmountDay(date, 0,"yyyy-MM-dd");
								hourStr = secondGroup.getString("starttime");
							}
							fristTime = sdfconfig.parse(sdf.format(date) + " "+ firstGroup.getString("starttime")+ ":00").getTime();
							secondTime = sdfconfig.parse(dateStr + " " + hourStr + ":00").getTime();
							if (!activityArray[j].isEmpty()) {
								String bannerpic = firstGroup.getString("pic");
								if (StringUtil.isNotEmpty(bannerpic)) {
									list.put("bannerpic",bannerpic.replace("-1", "-{w}").replace("-2", "-{h}"));
								} else {
									list.put("bannerpic", "");
								}
								String name = firstGroup.getString("name");
								if (StringUtil.isNotEmpty(name)) {
									list.put("desc", firstGroup.getString("name"));
								} else {
									list.put("desc", "");
								}
	
								list.put("updatetime", String.valueOf(fristTime));
								long eveningTime = 0;
								String eveningShowTime = AolaiIndexCacheUtil.getDetail("eveningshowtime");
								if (StringUtil.isNotEmpty(eveningShowTime)) {
									eveningTime = sdfconfig.parse(sdf.format(date) + " " + eveningShowTime).getTime();
								} else {
									eveningTime = sdfconfig.parse(sdf.format(date) + " " + "19:00:00").getTime();
								}
								if (fristTime >= eveningTime) {
									list.put("eveningshow", "1");
								} else {
									list.put("eveningshow", "0");
								}
								list.put("list", activityArray[j]);
							}
							if (!list.isEmpty()) {
								if (new Date().getTime() >= fristTime && new Date().getTime() <= secondTime) {
									groupArray.add(0, list);
								} else {
									groupArray.add(list);
								}
							}
						}
						
						HashMap<Integer, JSONArray> map = new HashMap<Integer, JSONArray>();
						if (adActivity != null && adActivity.size() > 0) {
							for (int i = 0; i < adActivity.size(); i++) {
								JSONObject oj = (JSONObject) adActivity.get(i);
								String startDate = oj.getString("starttime");
								long startTime = Long.valueOf(startDate);
								String endDate = oj.getString("endtime");
								long endTime = Long.valueOf(endDate);
								long nowTime=new Date().getTime();
								
								Date adDate = sdf.parse(sdf
										.format(new Date(startTime)));
								Date nowdate = new Date();
								nowdate = sdf.parse(sdf.format(nowdate));
								long shengyu = nowdate.getTime() - adDate.getTime();
								int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
								if(marginDate!=0 || endTime<nowTime||nowTime<startTime){
									adActivity.remove(i);
									i=i-1;
								}
							}
						}
						
						if (adActivity != null && adActivity.size() > 0) {
							for (int i = 0; i < adActivity.size(); i++) {
								JSONObject oj = (JSONObject) adActivity.get(i);
								int position = Integer.valueOf(oj.getString("position"));
								JSONObject child = new JSONObject();
								child = getAdChild(oj, tabid, 0,0);
							
								if(map.containsKey(position)){
									JSONArray tempMap=map.get(position);
									tempMap.add(child);
									map.put(position, tempMap);
								}else{
									JSONArray temp=new JSONArray();
									temp.add(child);
									map.put(position, temp);
								}
								
							}	
						}
						for (Integer key : map.keySet()) {
							JSONArray objArray = map.get(key);
							int p = key - 1;
							for (int s = 0; s < objArray.size(); s++) {
								JSONObject tempobj = (JSONObject) objArray.get(s);
								String startDate = tempobj.getString("starttime");
								long startTime = Long.valueOf(startDate);
								long nowTime = new Date().getTime();
								if (nowTime >= startTime) {
									if(!groupArray.isEmpty()){
										for (int n = 0; n < groupArray.size(); n++) {
											JSONObject temp = (JSONObject) groupArray
													.get(n);
											JSONArray listtemp = temp.getJSONArray("list");
											if (listtemp.size() >= p) {
												if (p < 0) {
													p = 0;
												}
												listtemp.add(p, tempobj);
												break;
											} else {
												p = p - listtemp.size();
												continue;
											}

										}
										
									}else{
										JSONObject firstGroup = (JSONObject) groupTime.get(0);
										JSONObject list = new JSONObject();
										JSONArray group = new JSONArray();
										String eveningShowTime = AolaiIndexCacheUtil.getDetail("eveningshowtime");
										long fristTime = sdfconfig.parse(sdf.format(date) + " "+ firstGroup.getString("starttime")+ ":00").getTime();
										String bannerpic = firstGroup.getString("pic");
										if (StringUtil.isNotEmpty(bannerpic)) {
											list.put("bannerpic",bannerpic.replace("-1", "-{w}").replace("-2", "-{h}"));
										} else {
											list.put("bannerpic", "");
										}
										String name = firstGroup.getString("name");
										if (StringUtil.isNotEmpty(name)) {
											list.put("desc", firstGroup.getString("name"));
										} else {
											list.put("desc", "");
										}
										long eveningTime;
										if (StringUtil.isNotEmpty(eveningShowTime)) {
											eveningTime = sdfconfig.parse(sdf.format(date) + " " + eveningShowTime).getTime();
										} else {
											eveningTime = sdfconfig.parse(sdf.format(date) + " " + "19:00:00").getTime();
										}
										if (fristTime >= eveningTime) {
											list.put("eveningshow", "1");
										} else {
											list.put("eveningshow", "0");
										}
										list.put("updatetime", String.valueOf(fristTime));
										group.add(tempobj);
										list.put("list", group);
										groupArray.add(list);
										break;
									}
								}
							}
						}
					}
					if(first){
						JSONObject list = new JSONObject();
						JSONArray group = new JSONArray();
						if (ingActivity != null && ingActivity.size() > 0) {
							for (int i = 0; i < ingActivity.size(); i++) {
								JSONObject object = (JSONObject) ingActivity.get(i);
								JSONObject child = new JSONObject();
								child = getChild(object, tabid, 0, 0);
								group.add(child);
							}

						}
						list.put("bannerpic", "");
						list.put("desc", "");
						long eveningTime = 0;
						list.put("updatetime", "0");
						list.put("eveningshow", "0");
						list.put("list", group);
						groupArray.add(list);
						HashMap<Integer, JSONArray> map = new HashMap<Integer, JSONArray>();
						if (adActivity != null && adActivity.size() > 0) {
							for (int i = 0; i < adActivity.size(); i++) {
								JSONObject oj = (JSONObject) adActivity.get(i);
								String startDate = oj.getString("starttime");
								long startTime = Long.valueOf(startDate);
								String endDate = oj.getString("endtime");
								long endTime = Long.valueOf(endDate);
								long nowTime=new Date().getTime();
								
								Date adDate = sdf.parse(sdf
										.format(new Date(startTime)));
								Date nowdate = new Date();
								nowdate = sdf.parse(sdf.format(nowdate));
								long shengyu = nowdate.getTime() - adDate.getTime();
								int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
								if(marginDate<=0 || endTime<nowTime){
									adActivity.remove(i);
									i=i-1;
								}
							}
						}
						
						if (adActivity != null && adActivity.size() > 0) {
							for (int i = 0; i < adActivity.size(); i++) {
								JSONObject oj = (JSONObject) adActivity.get(i);
								int position = Integer.valueOf(oj.getString("position"));
								JSONObject child = new JSONObject();
								child = getAdChild(oj, tabid, 0,0);
							
								if(map.containsKey(position)){
									JSONArray tempMap=map.get(position);
									tempMap.add(child);
									map.put(position, tempMap);
								}else{
									JSONArray temp=new JSONArray();
									temp.add(child);
									map.put(position, temp);
								}
								
							}	
						}
						for (Integer key : map.keySet()) {
							JSONArray objArray = map.get(key);
							int p = key - 1;
							for (int s = 0; s < objArray.size(); s++) {
								JSONObject tempobj = (JSONObject) objArray.get(s);
								String startDate = tempobj.getString("starttime");
								long startTime = Long.valueOf(startDate);
								long nowTime = new Date().getTime();
								if (nowTime >= startTime) {
									if(!groupArray.isEmpty()){
										for (int n = 0; n < groupArray.size(); n++) {
											JSONObject temp = (JSONObject) groupArray
													.get(n);
											JSONArray listtemp = temp.getJSONArray("list");
											if (listtemp.size() > p) {
												if (p < 0) {
													p = 0;
												}
												listtemp.add(p, tempobj);
												break;
											} else {
												p = p - listtemp.size();
												continue;
											}

										}
										
									}else{
										list.put("bannerpic", "");
										list.put("desc", "");
										list.put("updatetime", "");
										list.put("eveningshow", "0");
										group.add(tempobj);
										list.put("list", group);
										groupArray.add(list);
										break;
									}
								}
							}
						}
					}
					
				} else if (tabid.equals("4")) {
					if(groupTime!= null && groupTime.size() > 0){
						JSONArray[] twoAfterActivity = null;
						twoAfterActivity = new JSONArray[groupTime.size()];
						JSONArray[] threeAfterActivity = null;
						threeAfterActivity = new JSONArray[groupTime.size()];
						for (int j = 0; j < groupTime.size(); j++) {
							twoAfterActivity[j] = new JSONArray();
							threeAfterActivity[j] = new JSONArray();
						}
						String dateStr = null;
						String twoDateStr = null;
						String threeDateStr = null;
						String hourStr = null;
						JSONObject secondGroup = new JSONObject();
						if (activity != null && activity.size() > 0) {
							for (int i = 0; i < activity.size(); i++) {
								JSONObject oj = (JSONObject) activity.get(i);
								if (!oj.isEmpty()) {
									String startDate = oj.getString("starttime");
									long startTime = Long.valueOf(startDate);
									for (int j = 0; j < groupTime.size(); j++) {
										JSONObject firstGroup = (JSONObject) groupTime.get(j);
										if (j + 1 >= groupTime.size()) {
											JSONObject temp = (JSONObject) groupTime.get(0);
											dateStr = DateUtil.getAfterAmountDay(date,2, "yyyy-MM-dd");
											twoDateStr = DateUtil.getAfterAmountDay(date, 3, "yyyy-MM-dd");
											threeDateStr = DateUtil.getAfterAmountDay(date, 4, "yyyy-MM-dd");
											hourStr = temp.getString("starttime");
										} else {
											secondGroup = (JSONObject) groupTime.get(j + 1);
											dateStr = DateUtil.getAfterAmountDay(date,1, "yyyy-MM-dd");
											twoDateStr = DateUtil.getAfterAmountDay(date, 2, "yyyy-MM-dd");
											threeDateStr = DateUtil.getAfterAmountDay(date, 3, "yyyy-MM-dd");
											hourStr = secondGroup.getString("starttime");
										}
										long fristTime = sdfconfig.parse(DateUtil.getAfterAmountDay(date, 1,"yyyy-MM-dd")+ " "+ firstGroup.getString("starttime")+ ":00").getTime();
										long secondTime = sdfconfig.parse(dateStr + " " + hourStr + ":00").getTime();
										long twoAfterFristTime = sdfconfig.parse(DateUtil.getAfterAmountDay(date, 2,"yyyy-MM-dd")+ " "+ firstGroup.getString("starttime")+ ":00").getTime();
										long twoAfterSecondTime = sdfconfig.parse(twoDateStr + " " + hourStr + ":00").getTime();
										long threeAfterFristTime = sdfconfig.parse(DateUtil.getAfterAmountDay(date, 3,"yyyy-MM-dd")+ " "+ firstGroup.getString("starttime")+ ":00").getTime();
										long threeAfterSecondTime = sdfconfig.parse(threeDateStr + " " + hourStr + ":00").getTime();
										if (startTime >= fristTime&& startTime < secondTime) {
											JSONObject child = new JSONObject();
											child = getChild(oj, tabid, 0, 0);
											if (!child.isEmpty()) {
												activityArray[j].add(child);
												break;
											}
										}
										if (startTime >= twoAfterFristTime && startTime < twoAfterSecondTime) {
											JSONObject child = new JSONObject();
											child = getChild(oj, tabid, 0, 0);
											if (!child.isEmpty()) {
												twoAfterActivity[j].add(child);
												break;
											}
										}
										if (startTime >= threeAfterFristTime && startTime < threeAfterSecondTime) {
											JSONObject child = new JSONObject();
											child = getChild(oj, tabid, 0, 0);
											if (!child.isEmpty()) {
												threeAfterActivity[j].add(child);
												break;
											}
										}
									}
								}
							}
	
							for(int i=0;i<activityArray.length;i++){
								if( activityArray[i].isEmpty()){
									continue;
								}
								JSONObject list = new JSONObject();
								JSONObject firstGroup = (JSONObject) groupTime.get(i);
								long fristTime = sdfconfig.parse(
										DateUtil.getAfterAmountDay(date, 1,
												"yyyy-MM-dd")
												+ " "
												+ firstGroup.getString("starttime")
												+ ":00").getTime();
								String desc = "";
								String detailDesc = AolaiIndexCacheUtil
										.getDetail("tomorrowdesc");
								String endDesc = AolaiIndexCacheUtil.getDetail("desc");
								if (StringUtil.isNotEmpty(detailDesc)
										&& StringUtil.isNotEmpty(endDesc)) {
									desc = detailDesc
											+ firstGroup.getString("starttime") + "点  "
											+ endDesc;
								}
								list.put("desc", desc);
								list.put("updatetime", String.valueOf(fristTime));
								list.put("eveningshow", "0");
								list.put("list", activityArray[i]);
								groupArray.add(list);
							}
							
							
							for(int i=0;i<twoAfterActivity.length;i++){
								if( twoAfterActivity[i].isEmpty()){
									continue;
								}
								JSONObject list = new JSONObject();
								JSONObject firstGroup = (JSONObject) groupTime.get(i);
								long fristTime = sdfconfig.parse(
										DateUtil.getAfterAmountDay(date, 2,
												"yyyy-MM-dd")
												+ " "
												+ firstGroup.getString("starttime")
												+ ":00").getTime();
								String desc = "";
								String detailDesc = AolaiIndexCacheUtil
										.getDetail("aftertomorrowdesc");
								String endDesc = AolaiIndexCacheUtil.getDetail("desc");
								if (StringUtil.isNotEmpty(detailDesc)
										&& StringUtil.isNotEmpty(endDesc)) {
									desc = detailDesc
											+ firstGroup.getString("starttime") + "点  "
											+ endDesc;
								}
								list.put("desc", desc);
								list.put("updatetime", String.valueOf(fristTime));
								list.put("eveningshow", "0");
								list.put("list", twoAfterActivity[i]);
								groupArray.add(list);
							}
							
							for(int i=0;i<threeAfterActivity.length;i++){
								if( threeAfterActivity[i].isEmpty()){
									continue;
								}
								JSONObject list = new JSONObject();
								JSONObject firstGroup = (JSONObject) groupTime.get(i);
								long fristTime = sdfconfig.parse(
										DateUtil.getAfterAmountDay(date, 2,
												"yyyy-MM-dd")
												+ " "
												+ firstGroup.getString("starttime")
												+ ":00").getTime();
								String desc = "";
							
								String endDesc = AolaiIndexCacheUtil.getDetail("desc");     
								if ( StringUtil.isNotEmpty(endDesc)) {
									desc =  DateUtil.getAfterAmountDay(date, 3,
											"yyyy-MM-dd")
											+ " "
											+ firstGroup.getString("starttime") + "点  "
											+ endDesc;
								}
								list.put("desc", desc);
								list.put("updatetime", String.valueOf(fristTime));
								list.put("eveningshow", "0");
								list.put("list", threeAfterActivity[i]);
								groupArray.add(list);
							}
							
						}
					}
					
				} else if (tabid.equals("2")) {// 昨日上新
					JSONObject list = new JSONObject();
					JSONArray group = new JSONArray();
					if (activity != null && activity.size() > 0) {
						for (int i = 0; i < activity.size(); i++) {
							JSONObject object = (JSONObject) activity.get(i);
							JSONObject child = new JSONObject();
							child = getChild(object, tabid, 0, 0);
							group.add(child);
						}

					}
					list.put("bannerpic", "");
					list.put("desc", "");
					list.put("updatetime", "");
					list.put("eveningshow", "0");
					list.put("list", group);
					groupArray.add(list);
					HashMap<Integer, JSONArray> map = new HashMap<Integer, JSONArray>();
					if (adActivity != null && adActivity.size() > 0) {
						for (int i = 0; i < adActivity.size(); i++) {
							JSONObject oj = (JSONObject) adActivity.get(i);
							String startDate = oj.getString("starttime");
							long startTime = Long.valueOf(startDate);
							String endDate = oj.getString("endtime");
							long endTime = Long.valueOf(endDate);
							long nowTime=new Date().getTime();
							
							Date adDate = sdf.parse(sdf
									.format(new Date(startTime)));
							Date nowdate = new Date();
							nowdate = sdf.parse(sdf.format(nowdate));
							long shengyu = nowdate.getTime() - adDate.getTime();
							int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
							if(marginDate<=0 || endTime<nowTime){
								adActivity.remove(i);
								i=i-1;
							}
						}
					}
					
					if (adActivity != null && adActivity.size() > 0) {
						for (int i = 0; i < adActivity.size(); i++) {
							JSONObject oj = (JSONObject) adActivity.get(i);
							int position = Integer.valueOf(oj.getString("position"));
							JSONObject child = new JSONObject();
							child = getAdChild(oj, tabid, 0,0);
						
							if(map.containsKey(position)){
								JSONArray tempMap=map.get(position);
								tempMap.add(child);
								map.put(position, tempMap);
							}else{
								JSONArray temp=new JSONArray();
								temp.add(child);
								map.put(position, temp);
							}
							
						}	
					}
					for (Integer key : map.keySet()) {
						JSONArray objArray = map.get(key);
						int p = key - 1;
						for (int s = 0; s < objArray.size(); s++) {
							JSONObject tempobj = (JSONObject) objArray.get(s);
							String startDate = tempobj.getString("starttime");
							long startTime = Long.valueOf(startDate);
							long nowTime = new Date().getTime();
							if (nowTime >= startTime) {
								if(!groupArray.isEmpty()){
									for (int n = 0; n < groupArray.size(); n++) {
										JSONObject temp = (JSONObject) groupArray
												.get(n);
										JSONArray listtemp = temp.getJSONArray("list");
										if (listtemp.size() > p) {
											if (p < 0) {
												p = 0;
											}
											listtemp.add(p, tempobj);
											break;
										} else {
											p = p - listtemp.size();
											continue;
										}

									}
									
								}else{
									list.put("bannerpic", "");
									list.put("desc", "");
									list.put("updatetime", "");
									list.put("eveningshow", "0");
									group.add(tempobj);
									list.put("list", group);
									groupArray.add(list);
									break;
								}
							}
						}
					}
					

				} else if (tabid.equals("3")) {// 最后疯抢
					JSONObject list = new JSONObject();
					JSONArray group = new JSONArray();
					if (activity != null && activity.size() > 0) {
						for (int i = 0; i < activity.size(); i++) {
							JSONObject object = (JSONObject) activity.get(i);
							JSONObject child = new JSONObject();
							child = getChild(object, tabid, 0, 0);
							group.add(child);
						}
						list.put("bannerpic", "");
						list.put("desc", "");
						list.put("updatetime", "");
						list.put("eveningshow", "0");
						list.put("list", group);
						groupArray.add(list);
					}
					HashMap<Integer, JSONArray> map = new HashMap<Integer, JSONArray>();
					if (adActivity != null && adActivity.size() > 0) {
						for (int i = 0; i < adActivity.size(); i++) {
							JSONObject oj = (JSONObject) adActivity.get(i);
							String startDate = oj.getString("starttime");
							long startTime = Long.valueOf(startDate);
							String endDate = oj.getString("endtime");
							long endTime = Long.valueOf(endDate);
							long nowTime=new Date().getTime();
							
							Date adDate = sdf.parse(sdf
									.format(new Date(endTime)));
							Date nowdate = new Date();
							nowdate = sdf.parse(sdf.format(nowdate));
							long shengyu = nowdate.getTime() - adDate.getTime();
							int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
							if(marginDate!=0 || endTime<nowTime||nowTime<startTime){
								adActivity.remove(i);
								i=i-1;
							}
						}
					}
					
					if (adActivity != null && adActivity.size() > 0) {
						for (int i = 0; i < adActivity.size(); i++) {
							JSONObject oj = (JSONObject) adActivity.get(i);
							int position = Integer.valueOf(oj.getString("position"));
							JSONObject child = new JSONObject();
							child = getAdChild(oj, tabid, 0,0);
						
							if(map.containsKey(position)){
								JSONArray tempMap=map.get(position);
								tempMap.add(child);
								map.put(position, tempMap);
							}else{
								JSONArray temp=new JSONArray();
								temp.add(child);
								map.put(position, temp);
							}
							
						}	
					}
					for (Integer key : map.keySet()) {
						JSONArray objArray = map.get(key);
						int p = key - 1;
						for (int s = 0; s < objArray.size(); s++) {
							JSONObject tempobj = (JSONObject) objArray.get(s);
							String startDate = tempobj.getString("starttime");
							long startTime = Long.valueOf(startDate);
							long nowTime = new Date().getTime();
							if (nowTime >= startTime) {
								if(!groupArray.isEmpty()){
									for (int n = 0; n < groupArray.size(); n++) {
										JSONObject temp = (JSONObject) groupArray
												.get(n);
										JSONArray listtemp = temp.getJSONArray("list");
										if (listtemp.size() > p) {
											if (p < 0) {
												p = 0;
											}
											listtemp.add(p, tempobj);
											break;
										} else {
											p = p - listtemp.size();
											continue;
										}

									}
									
								}else{
									list.put("bannerpic", "");
									list.put("desc", "");
									list.put("updatetime", "");
									list.put("eveningshow", "0");
									group.add(tempobj);
									list.put("list", group);
									groupArray.add(list);
									break;
								}
							}

						}
						
					}
				}
				
				JSONArray topicNoList = new JSONArray();
				if (topics != null && topics.size() > 0) {
					for (int i = 0; i < topics.size(); i++) {
						JSONObject topicsObj = (JSONObject) topics.get(i);
						JSONObject child = new JSONObject();
						child.put("activityid", topicsObj.getString("topicid"));
						child.put("activityname", topicsObj.getString("name"));
						child.put("starttime", topicsObj.getString("starttime"));
						child.put("endtime", topicsObj.getString("endtime"));
						String pic = topicsObj.getString("pic");
						if (StringUtil.isNotEmpty(pic)) {
							child.put("pic", pic.replace("-1-2", "-{w}-{h}"));
						} else {
							child.put("pic", "");
						}
						child.put("brandpic", "");
						child.put("t0", "");
						child.put("t1", "");
						child.put("t2", "");
						child.put("brandno", "");
						child.put("brandcnname", "");
						child.put("brandenname", "");
						child.put("brands", "");
						child.put("cates", "");
						child.put("recommend", "");
						child.put("iscollect", "0");
						child.put("eveningshow", "0");
						child.put("channelid", "");
						child.put("channelname", "");
						child.put("appurl", "");
						child.put("url", "");
						child.put("type", "0");
						topicNoList.add(child);
					}
				}
				
				aolaiIndexJson.put("activity", groupArray);
				aolaiIndexJson.put("topic", topicNoList == null ? "[]"
						: topicNoList);
				obj.put("content", aolaiIndexJson);
				return obj;
//			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getAdChild(JSONObject obj, String tabid, long fristTime,
			long eveningTime) {
		if (tabid.equals("4")) {
			JSONObject child = new JSONObject();
			child.put("activityid", "");
			child.put("activityname", obj.getString("adname"));
			child.put("starttime", obj.getString("starttime"));
			child.put("endtime", obj.getString("endtime"));
			String pic = obj.getString("adpic");
			if (StringUtil.isNotEmpty(pic)) {
				child.put("pic", pic.replace("-1-2", "-{w}-{h}"));
			} else {
				child.put("pic", "");
			}
			child.put("brandpic", "");
			child.put("t0", "");
			child.put("t1", "");
			child.put("t2", "");
			child.put("brandno", "");
			child.put("brandcnname", "");
			child.put("brandenname", "");
			child.put("brands", "");
			child.put("cates", "[]");
			child.put("recommend", "");
			child.put("iscollect", "0");
			child.put("eveningshow", "0");
			child.put("salesinfo", "");

			child.put("channelid", "");
			child.put("channelname", "");
			child.put("appurl", "");
			child.put("url", obj.getString("adurl"));
			child.put("type", "1");
			return child;
		} else {
			JSONObject child = new JSONObject();
			child.put("activityid", "");
			child.put("activityname", obj.getString("adname"));
			child.put("starttime", obj.getString("starttime"));
			child.put("endtime", obj.getString("endtime"));
			String pic = obj.getString("adpic");
			if (StringUtil.isNotEmpty(pic)) {
				child.put("pic", pic.replace("-1-2", "-{w}-{h}"));
			} else {
				child.put("pic", "");
			}
			child.put("brandpic", "");
			child.put("t0", "");
			child.put("t1", "");
			child.put("t2", "");
			child.put("brandno", "");
			child.put("brandcnname", "");
			child.put("brandenname", "");
			child.put("brands", "");
			child.put("cates", "[]");
			child.put("recommend", "");
			child.put("salesinfo",  "");
			child.put("iscollect", "0");
			if (tabid.equals("1")) {
				if (fristTime >= eveningTime) {
					child.put("eveningshow", "1");
				} else {
					child.put("eveningshow", "0");
				}
			} else {
				child.put("eveningshow", "0");
			}
			if (obj.getString("type").equals("2")) {
				String link = obj.getString("adurl");
				String channelname = AolaiIndexCacheUtil.getChannel(link);
				child.put("channelid", link);
				child.put("channelname", channelname == null ? "" : channelname);
				child.put("appurl", "");
				child.put("url", "");
				child.put("type", "2");
			} else if (obj.getString("type").equals("1")) {
				child.put("channelid", "");
				child.put("channelname", "");
				child.put("appurl",AolaiIndexCacheUtil.getDetail("appurl"));
				child.put("url", AolaiIndexCacheUtil.getDetail("url"));
				child.put("type", "3");
			} else if (obj.getString("type").equals("3")) {
				child.put("channelid", "");
				child.put("channelname", "");
				child.put("appurl", "");
				child.put("url", obj.getString("adurl"));
				child.put("type", "1");
			} else {
				child.put("channelid", "");
				child.put("channelname", "");
				child.put("appurl", "");
				child.put("url", "");
				child.put("type", "");
			}
			return child;

		}
	}

	public JSONObject getChild(JSONObject object, String tabid, long fristTime,
			long eveningTime) {
		JSONObject child = new JSONObject();
		String brandenname=object.getString("brandenname");
		String activityid = object.getString("activityid");
		child.put("activityid", activityid);
		if(StringUtil.isNotEmpty(brandenname)){
			child.put("activityname", brandenname+" "+object.getString("activityname"));
		}else{
			child.put("activityname", object.getString("activityname"));
		}
		
		child.put("starttime", object.getString("starttime"));
		child.put("endtime", object.getString("endtime"));
		String pic = object.getString("pic");
		String brandpic = object.getString("brandpic");
		if (StringUtil.isNotEmpty(pic)) {
			child.put("pic", pic.replace("-1-2", "-{w}-{h}"));
		} else {
			child.put("pic", "");
		}
		if (StringUtil.isNotEmpty(brandpic)) {
			child.put("brandpic", brandpic.replace("-0-0", "-{w}-{h}"));
		} else {
			child.put("brandpic", "");
		}
		String t1=object.getString("t1");
		String t2=object.getString("t2");
		if(StringUtil.isNotEmpty(t2)&&StringUtil.isNotEmpty(t1)){
			if(t2.indexOf("折")>-1){
				child.put("t0", object.getString("t0"));
			}else{
				String t0= object.getString("t0")+"¥";
				child.put("t0",t0);
			}
			
		}else{
			child.put("t0", object.getString("t0"));
			
		}
		child.put("t1", object.getString("t1"));
		if(StringUtil.isNotEmpty(t2)){
			if(t2.indexOf("元")>-1){
				child.put("t2","");
			}else{
				child.put("t2",t2);
			}
			
		}else{
			child.put("t2","");
		}
		
		
		String brandno=object.getString("brandno");
		child.put("brandno", brandno);
		String brandcnname=object.getString("brandcnname");
		child.put("brandcnname", brandcnname);
	
		child.put("brandenname", brandenname);
		child.put("brands", object.getJSONArray("brands"));
		child.put("cates", "[]");
		child.put("recommend", object.getString("recommend"));
		child.put("salesinfo",  object.getString("salesinfo"));
		String iscollect = "0";
		if (collection != null && collection.size() > 0) {
			for (int k = 0; k < collection.size(); k++) {
				JSONObject collectionObj = (JSONObject) collection.get(k);
				String collectActivityId = collectionObj
						.getString("activityid");
				if (activityid.equals(collectActivityId)) {
					iscollect = "1";
					break;
				}
			}
		}
		child.put("iscollect", iscollect);
		if (tabid.equals("1")) {
			if (fristTime >= eveningTime) {
				child.put("eveningshow", "1");
			} else {
				child.put("eveningshow", "0");
			}
		} else {
			child.put("eveningshow", "0");
		}
		child.put("channelid", "");
		child.put("channelname", "");
		child.put("appurl", "");
		child.put("url", "");
		child.put("type", "0");
		return child;

	}

	public Integer marginDate(Date startDate, Date endDate) {
		long shengyu = endDate.getTime() - startDate.getTime();
		int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
		return marginDate;
	}


}