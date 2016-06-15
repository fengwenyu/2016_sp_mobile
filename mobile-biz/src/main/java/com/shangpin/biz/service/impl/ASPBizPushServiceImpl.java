package com.shangpin.biz.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.stream.JsonReader;
import com.shangpin.biz.bo.PushInfo;
import com.shangpin.biz.service.ASPBizPushService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.core.entity.PushData;
import com.shangpin.core.entity.PushManageAndroid;
import com.shangpin.core.entity.PushManageIos;
import com.shangpin.core.entity.PushconfShangpin;
import com.shangpin.core.service.IPushDataService;
import com.shangpin.core.service.IPushManageAndroidService;
import com.shangpin.core.service.IPushManageIosService;
import com.shangpin.core.service.IPushconfShangpinService;

@Service
public class ASPBizPushServiceImpl implements ASPBizPushService{

	@Autowired
	private IPushManageIosService pushManageIosService;
	@Autowired
	private IPushconfShangpinService pushconfShangpinService;
	@Autowired
	private IPushManageAndroidService pushManageAndroidService;
	@Autowired
	private IPushDataService pushDataService;
	private JsonReader jsonReader;
	
	@Override
	public List<PushInfo> pushInfo(String userId, String pageIndex, String pageSize, String plateForm){
		List<PushInfo> pushInfos = new ArrayList<PushInfo>();
		if("IOS".equals(plateForm)){
			Set<Long> set = new HashSet<Long>();
			PushconfShangpin pushconfShangpin = pushconfShangpinService.findByUserId(userId);
			if(null != pushconfShangpin){
				String token = pushconfShangpin.getToken();
				List<PushData> pushDatas = pushDataService.findByToken(token);
				for(PushData pushData : pushDatas){
					set.add(pushData.getDictId());
				}
			}
			set.add(-1L);
			List<PushManageIos> pushManageIos = pushManageIosService.findByDictIds(set, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			for(PushManageIos pushManageIos2 : pushManageIos){
				PushInfo pushInfo = new PushInfo();
				pushInfo.setTitle(pushManageIos2.getTitle());
				pushInfo.setContent(pushManageIos2.getNotice());
				pushInfo.setIcon(Constants.MESSAGE_ICON_PUSH_IOS);
				pushInfo.setImageUrl("");
				String action = pushManageIos2.getAction();
				String actionArg = pushManageIos2.getActionarg();
				String actionObj = pushManageIos2.getActionobj();
				pushInfo.setType(getType(action));
				String conentRef = typeRefContent(actionArg, action, actionObj);
				pushInfo.setRefContent(!StringUtils.isEmpty(conentRef) ? conentRef : "");
				pushInfo.setCreateTime(String.valueOf(pushManageIos2.getShowTime().getTime()));
				pushInfos.add(pushInfo);
			}
		}else {
			List<PushManageAndroid> pushManageAndroids = pushManageAndroidService.findByPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			for(PushManageAndroid pushManageAndroid : pushManageAndroids){
				PushInfo pushInfo = new PushInfo();
				String pushContent = pushManageAndroid.getPushContent();
				String title = getTitle(pushContent);
				pushInfo.setTitle(StringUtils.isEmpty(title) ? "优惠活动" : title);
				pushInfo.setContent(pushManageAndroid.getNotice());
				pushInfo.setIcon(Constants.MESSAGE_ICON_PUSH_ANDORID);
				pushInfo.setImageUrl("");
				String action = pushManageAndroid.getAction();
				String actionArg = pushManageAndroid.getActionarg();
				String actionObj = pushManageAndroid.getActionobj();
				pushInfo.setType(getType(action));
				String conentRef = typeRefContent(actionArg, action, actionObj);
				pushInfo.setRefContent(!StringUtils.isEmpty(conentRef) ? conentRef : "");
				pushInfo.setCreateTime(String.valueOf(pushManageAndroid.getShowTime().getTime()));
				pushInfos.add(pushInfo);
			}
		}
		
		return pushInfos;
	}
	
	private String getType(String action){
		if("detail".equals(action)){
			return "3";
		}else if("link".equals(action)){
			return "1";
		}else if("topic".equals(action)){
			return "4";
		}else if("activity".equals(action)){
			return "2";
		}else{
			return "";
		}
	}
	
	
	private String typeRefContent(String actionarg, String action, String actionObj){
		if("detail".equals(action)){
			Map<String, String> detailMap = readJson(actionObj);
			return detailMap.get("detail");
		}else{
			return actionarg;
		}
	}
	
	private Map<String, String> readJson(String json){
		Map<String, String> map = new HashMap<String, String>();
		try {
			jsonReader = new JsonReader(new StringReader(json));
			jsonReader.beginObject();
			while(jsonReader.hasNext()){
				String name = jsonReader.nextName();
				String value = jsonReader.nextString();
				if("productid".equals(name)){
					map.put("detail", value);
				}
			}
			jsonReader.endObject();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getTitle(String json){
		try {
			jsonReader = new JsonReader(new StringReader(json));
			jsonReader.beginObject();
			while(jsonReader.hasNext()){
				String name = jsonReader.nextName();
				String value = jsonReader.nextString();
				if("title".equals(name)){
					return value;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		String json = "{\"productid\":\"300045\"}";
//		JsonReader jsonReader = new JsonReader(new StringReader(json));
//		try {
//			jsonReader.beginObject();
//			while(jsonReader.hasNext()){
//				String key = jsonReader.nextName();
//				System.out.println(key);
//				String value = jsonReader.nextString();
//				System.out.println(value);
//			}
//			jsonReader.endObject();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ASPBizPushServiceImpl service = new ASPBizPushServiceImpl();
		String json = "{\"id\":\"5135\",\"title\":\"尖货排行经典畅销男鞋\",\"aps\":{\"sound\":\"default\",\"alert\":\"舒适\u201C一脚蹬\u201D，BALLY 新品￥1530，查看更多品牌懒人鞋>>\",\"badge\":1},\"actionarg\":\"60328540\",\"action\":\"activity\",\"showTime\":\"2016-04-08 20:00:00\"}";
		System.out.println(service.getTitle(json));
	}
	
}
