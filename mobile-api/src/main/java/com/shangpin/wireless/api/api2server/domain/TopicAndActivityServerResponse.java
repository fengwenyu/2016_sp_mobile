package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 用于封装主站接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class TopicAndActivityServerResponse {

	private String code;
	private String msg;
	private JSONArray content;

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

	public JSONArray getContent() {
		return content;
	}

	public void setContent(JSONArray content) {
		this.content = content;
	}

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public TopicAndActivityServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
		    content = JSONArray.fromObject(obj.getJSONArray("content"));
		    if(content!=null&&!"".equals(content)){
		        JSONArray lastContent=new JSONArray();;
		        for(int i=0;i<content.size();i++){
		            lastContent.add(content.getJSONObject(i));
		            if(lastContent.size()==3){
		                break;
		            }
		            
		        }		        
		        content=lastContent;
		    }
		}
		return this;
	}
	
	/**
	 * 解析主站返回的json数据针对奥莱活动
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-05-29
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public TopicAndActivityServerResponse activeJsonToObj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			content = JSONArray.fromObject(obj.getJSONArray("content"));
			if(content!=null&&!"".equals(content)){
				JSONArray contentArray;
				for(int i=0;i<content.size();i++){
					contentArray=content.getJSONArray(i);
					if(contentArray!=null&&!"".equals(contentArray)){
						JSONObject contentObj;
						for(int j=0;j<contentArray.size();j++){
							StringBuffer name=new StringBuffer();
							contentObj=contentArray.getJSONObject(j);
							name.append(contentObj.getString("brandenname"));
							name.append(" ");
							name.append(contentObj.getString("activityname"));
							contentObj.put("activityname", name.toString());
						}
					}
					
				}
			}
		}
		return this;
	}

}
