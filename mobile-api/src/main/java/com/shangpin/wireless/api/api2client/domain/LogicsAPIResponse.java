package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.util.StringUtil;

/**
 * 物流
 * 
 * @author xupengcheng
 * 
 */
public class LogicsAPIResponse extends CommonAPIResponse {

	@Override
	public String obj2Json(String data) {
		JSONObject dataObj = JSONObject.fromObject(data);
		this.setCode(dataObj.getString("code"));
		this.setMsg(dataObj.getString("msg"));
		JSONObject content = dataObj.getJSONObject("content");
		JSONObject contentObj = new JSONObject();
		if (!(content.isNullObject() || content.isEmpty())) {
			String orderId = content.getString("orderId");
			String date = content.getString("date").replace("-", "/");
			contentObj.put("orderid", orderId);
			contentObj.put("date", date);
			JSONArray list = new JSONArray();
			JSONArray oldList = content.getJSONArray("list");
			try {
				if (oldList.isArray() && oldList.size() > 0) {
					for (int i = 0; i < oldList.size(); i++) {
						JSONObject oldObj = oldList.getJSONObject(i);
						JSONObject productLogics = new JSONObject();
						productLogics.put("logistics", changeToOldLogistics(oldObj));
						productLogics.put("product", changeToOldProducts(oldObj.getJSONArray("products")));
						list.add(productLogics);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			contentObj.put("list", list);
		}

		this.setContent(contentObj);
		return this.toString().replace("orderId", "orderid").replace("productName", "productname");
	}

	/**
	 * 
	 * @param products
	 * @return
	 */
	private JSONArray changeToOldProducts(JSONArray products) {
		JSONArray list = new JSONArray();
		if (products.isArray() && !products.isEmpty()) {
			for (int i = 0; i < products.size(); i++) {
				JSONObject product = products.getJSONObject(i);
				JSONArray props = product.getJSONArray("prop");
				if (props.isArray() && !props.isEmpty()) {
					for (int j = 0; j < props.size(); j++) {
						JSONObject prop = props.getJSONObject(j);
						String name = prop.getString("name");
						String value = prop.getString("value");
						if (StringUtil.isNotEmpty(name) && name.equals("颜色")) {
							product.put("firstpropvalue", value);
							product.put("firstpropname", "颜色");
						} else if (StringUtil.isNotEmpty(name) && name.equals("尺寸")) {
							product.put("secondpropvalue", value);
							product.put("secondpropname", "尺码");
						}
					}
					product.remove("prop");
					list.add(product);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param jsonArray
	 * @return
	 */
    @SuppressWarnings("unused")
    private JSONArray changeToOldLogistics(JSONObject oldObj) {
		JSONArray list = new JSONArray();
		JSONArray logisticsList = oldObj.getJSONArray("logistics");
		String express = oldObj.getString("express");
		String ticketNo = oldObj.getString("ticketno");
		String status = oldObj.getString("status");
		String statusDesc = oldObj.getString("statusdesc");
		if (logisticsList.isArray() && !logisticsList.isEmpty()) {
			for (int i = 0; i < logisticsList.size(); i++) {
				JSONObject logics = logisticsList.getJSONObject(i);
				// 这个obj，desc，address有用么？
				JSONObject obj = new JSONObject();
				String date = logics.getString("date").replace("-", "/");
				String desc = logics.getString("desc");
				String address = logics.getString("address");
				logics.put("date", date);
				logics.put("ticketno", ticketNo);
				logics.put("express", express);
				logics.put("status", status);
				logics.put("statusdesc", statusDesc);
				list.add(logics);
			}
		}
		return list;
	}
}
