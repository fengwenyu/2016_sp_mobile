package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.shangpin.wireless.api.domain.Constants;


public class ProductSnapAPIResponse {
	
	
	/**
	 * 返回简要商品的json数据(购物袋快照)
	 */
	public String objSpJson(String data,String productid,String amount,String count,String alcategoryno,String propStr) {
		JSONObject dataObj=JSONObject.fromObject(data);
		JSONObject contentObj=JSONObject.fromObject(dataObj.getString("content"));
		JSONObject obj = new JSONObject();
		obj.put("code", dataObj.getString("code"));
		obj.put("msg", dataObj.getString("msg"));
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(dataObj.getString("code"))) {
			content.put("brandname", contentObj.getString("brand"));
			JSONArray prop = new JSONArray();
			JSONObject propObj;
			if(propStr!=null&&!"".equals(propStr)){
				String [] propArray=propStr.split("\\|");
				if(propArray!=null&&propArray.length>0){
					for(int z=0;z<propArray.length;z++){
						propObj=new JSONObject();
						propObj.put("name",propArray[z].substring(0,propArray[z].indexOf(":")));
						propObj.put("value",propArray[z].substring(propArray[z].indexOf(":")+1));	
						prop.add(propObj);
					}
				}
			}
			content.put("prop", prop);
			content.put("count", count);
			content.put("specialinfo", contentObj.getString("specialinfo"));
			content.put("now", amount);
			content.put("past", amount);
			content.put("rebate", contentObj.getString("rebate"));
			content.put("productid", contentObj.getString("goodscode"));
			content.put("productname", contentObj.getString("name"));
			content.put("pics", contentObj.getJSONArray("pics"));
			content.put("info", contentObj.getJSONArray("info"));
			content.put("buyer", contentObj.getString("buyer"));
		}
		obj.put("content", content);
		return obj.toString();
	}
	
	/**
	 * 返回简要奥莱的json数据(购物袋快照)
	 */
	public String objAlJson(String data,String productid,String amount,String count,String alcategoryno,String propStr) {
		JSONObject dataObj=JSONObject.fromObject(data);
		JSONObject contentObj=JSONObject.fromObject(dataObj.getString("content"));
		JSONObject obj = new JSONObject();
		obj.put("code", dataObj.getString("code"));
		obj.put("msg", dataObj.getString("msg"));
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(dataObj.getString("code"))) {
			content.put("brandname", contentObj.getString("brand"));
			JSONArray prop = new JSONArray();
			JSONObject propObj;
			if(propStr!=null&&!"".equals(propStr)){
				String [] propArray=propStr.split("\\|");
				if(propArray!=null&&propArray.length>0){
					for(int z=0;z<propArray.length;z++){
						propObj=new JSONObject();
						propObj.put("name",propArray[z].substring(0,propArray[z].indexOf(":")));
						propObj.put("value",propArray[z].substring(propArray[z].indexOf(":")+1));	
						prop.add(propObj);
					}
				}
			}
			content.put("prop", prop);
			content.put("count", count);
			content.put("specialinfo", contentObj.getString("specialinfo"));
			content.put("now", amount);
			content.put("past", contentObj.getString("past"));
			content.put("rebate", contentObj.getString("rebate"));
			content.put("productid", contentObj.getString("goodscode"));
			content.put("productname", contentObj.getString("name"));
			content.put("pics", contentObj.getJSONArray("pics"));
			content.put("info", contentObj.getJSONArray("info"));
			content.put("buyer", contentObj.getString("buyer"));
		}
		obj.put("content", content);
		return obj.toString();
	}
	
	/**
	 * 返回简要商品的json数据(兼容老版本购物袋快照)
	 */
	public String objSpJson2(String data,String count) {
		JSONObject dataObj=JSONObject.fromObject(data);
		JSONObject contentObj=JSONObject.fromObject(dataObj.getString("content"));
		JSONObject detail = new JSONObject();
		if (Constants.SUCCESS.equals(dataObj.getString("code"))) {
			detail.put("specialinfo", contentObj.getString("specialinfo"));
			detail.put("pics", contentObj.getJSONArray("pics"));
			detail.put("buyer", contentObj.getString("buyer"));
			detail.put("count", count);
			detail.put("now", "0");
			detail.put("past", "0");
			detail.put("productname", contentObj.getString("name"));
			detail.put("brandname", contentObj.getString("brand"));
			detail.put("rebate", contentObj.getString("rebate"));
			detail.put("info", contentObj.getJSONArray("info"));
			detail.put("exchange", contentObj.getString("exchange"));
			detail.put("buyer", contentObj.getString("buyer"));
			detail.put("propicon", "");
		}
		return detail.toString();
	}
	
	/**
	 * 返回简要奥莱的json数据(兼容老版本购物袋快照)
	 */
	public String objAlJson2(String data,String count) {
		JSONObject dataObj=JSONObject.fromObject(data);
		JSONObject contentObj=JSONObject.fromObject(dataObj.getString("content"));
		JSONObject detail = new JSONObject();
		if (Constants.SUCCESS.equals(dataObj.getString("code"))) {
			detail.put("specialinfo", contentObj.getString("specialinfo"));
			detail.put("count", count);
			detail.put("pics", contentObj.getJSONArray("pics"));
			detail.put("buyer", contentObj.getString("buyer"));
			detail.put("now", contentObj.getString("now"));
			detail.put("past", contentObj.getString("past"));
			detail.put("productname", contentObj.getString("name"));
			detail.put("brandname", contentObj.getString("brand"));
			detail.put("rebate", contentObj.getString("rebate"));
			detail.put("info", contentObj.getJSONArray("info"));
			detail.put("exchange", contentObj.getString("exchange"));
			detail.put("buyer", contentObj.getString("buyer"));
			detail.put("propicon", "");
		}
		return detail.toString();
	}
	
	
	
}
