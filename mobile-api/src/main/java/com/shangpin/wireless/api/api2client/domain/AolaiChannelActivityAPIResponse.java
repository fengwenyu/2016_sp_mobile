package com.shangpin.wireless.api.api2client.domain;


import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AolaiChannelActivityAPIResponse {
	
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
	/**
	 * 返给客户端的json数据
	 */
	public JSONObject obj2Json(String data) {
		JSONObject obj= new JSONObject(); 
		JSONObject channelActivityJson = new JSONObject();
		channelActivityJson.put("code", getCode());
		channelActivityJson.put("msg", getMsg() == null ? "" : getMsg());
		if (Constants.SUCCESS.equals(getCode())) {
			JSONObject object = JSONObject.fromObject(data);
			JSONObject content = object.getJSONObject("content");
			JSONArray array=new JSONArray();
			JSONArray contentArray = JSONArray.fromObject(content.getJSONArray("list"));
			for (int i = 0; i < contentArray.size(); i++) {
				JSONObject objectArray=(JSONObject) contentArray.get(i);
				String t1=objectArray.getString("t1");
				String t2=objectArray.getString("t2");
				if(StringUtil.isNotEmpty(t2)&&StringUtil.isNotEmpty(t1)){
					if(t2.indexOf("折")>-1){
						objectArray.put("t0", objectArray.getString("t0"));
					}else{
						String t0= objectArray.getString("t0")+"¥";
						objectArray.put("t0",t0);
					}
					
				}else{
					objectArray.put("t0", objectArray.getString("t0"));
					
				}
				objectArray.put("t1", objectArray.getString("t1"));
				if(StringUtil.isNotEmpty(t2)){
					if(t2.indexOf("元")>-1){
						objectArray.put("t2","");
					}else{
						objectArray.put("t2",t2);
					}
					
				}else{
					objectArray.put("t2","");
				}
				
				objectArray.put("cates", "[]");
				objectArray.put("iscollect", "0");
				objectArray.put("eveningshow", "0");
				objectArray.put("channelid", "");
				objectArray.put("channelname", "");
				objectArray.put("appurl", "");
				objectArray.put("type", "0");
				
				
				
				
				array.add(objectArray);
			}
			obj.put("chid",content.getString("chid"));
			obj.put("channel",content.getString("channel"));
			obj.put("systime",content.getString("systime"));
			obj.put("Isnextpage",content.getString("Isnextpage"));
			obj.put("list",array);
		}
	
		channelActivityJson.put("content", obj);
		return channelActivityJson;
	}

}
