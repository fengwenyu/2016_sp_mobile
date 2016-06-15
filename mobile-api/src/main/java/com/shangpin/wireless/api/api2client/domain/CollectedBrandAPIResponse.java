package com.shangpin.wireless.api.api2client.domain;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class CollectedBrandAPIResponse {

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
	 * @param data 
	 */
	public JSONObject obj2Json(String data) {

		JSONObject collectedBrandJson = new JSONObject();
		collectedBrandJson.put("code", getCode());
		collectedBrandJson.put("msg", getMsg() == null ? "" : getMsg());
		if (Constants.SUCCESS.equals(getCode())) {
			JSONArray list = new JSONArray();
			JSONObject obj = JSONObject.fromObject(data);
			if (obj != null && !obj.equals("{}")) {
				String contentObject = obj.getString("content");
				if (contentObject != null && !contentObject.equals("{}")) {
					JSONArray contentArray = JSONArray.fromObject(obj.getJSONObject("content").getJSONArray("collectedBrandlist"));
					for (int i = 0; i < contentArray.size(); i++) {
						JSONObject object = (JSONObject) contentArray.get(i);
						JSONObject brand=new JSONObject();
						brand.put("nameEN", object.get("nameEN"));
	                    brand.put("nameCN", object.get("nameCN"));
	                    brand.put("name", object.get("nameCN"));
	                    brand.put("isFlagship", object.get("isFlagship"));
	                    brand.put("pic", object.get("pic"));
	                    brand.put("type", "3");
	                    brand.put("refContent", object.get("id"));
	                    brand.put("id", object.get("id"));//兼容2.6.0以前版本
						list.add(brand);
					}
				}
			}
			
			JSONObject content = new JSONObject();
			if(!list.isEmpty()){
				content.put("collectedBrandlist", list);
			}
			
			collectedBrandJson.put("content", content);
		}

		return collectedBrandJson;
	}
}
