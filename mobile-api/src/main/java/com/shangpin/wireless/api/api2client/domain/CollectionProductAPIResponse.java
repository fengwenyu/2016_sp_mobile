package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class CollectionProductAPIResponse extends CommonAPIResponse{
	private String code;
	private String msg;
	private String data;
	private String skuId;
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
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	@Override
	public String obj2Json() {
		JSONObject returnObj = new JSONObject();
		if(data != null){
			JSONObject obj = JSONObject.fromObject(data);
			if(obj != null){
				String code = String.valueOf(obj.get("code"));
				returnObj.put("code", code);
				JSONObject contentObj = new JSONObject();
				if(Constants.SUCCESS.equals(code)){
					returnObj.put("msg", obj.get("msg"));
					Object content = obj.get("content");
					if(content != null){
						JSONObject listObj = (JSONObject) content;
						if(listObj.get("list") != null){
							JSONArray array = (JSONArray) listObj.get("list");
							for(int i =0; i < array.size() ; i ++){
								JSONObject product = (JSONObject) array.get(i);
								String fproductId = product.getString("favoriteproductid");
								String sku = product.getString("sku");
								if(fproductId != null && skuId != null && !"".equals(skuId)){
									if(skuId.equals(sku)){
										contentObj.put("favoriteproductid", fproductId);
										returnObj.put("content", contentObj);
										return returnObj.toString();
									}
								}
							}
						}
					}
				}else{
					returnObj.put("msg", msg);
					contentObj.put("favoriteproductid", new Object());
					returnObj.put("content", contentObj);
				}
			}
		}else{
			
		}
		return returnObj.toString();
	}
}
