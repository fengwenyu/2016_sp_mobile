package com.shangpin.wireless.api.api2client.domain;

import java.util.HashMap;
import java.util.Map;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.OrderDetailUtil;
import com.shangpin.wireless.api.util.WebUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单合并详情
 * 
 * @author xupengcheng
 * 
 */
public class MergeOrderDetailResponse extends CommonAPIResponse {

	private JSONArray mainPayMode;
	private String userId;

	@Override
	public String obj2Json() {
		return super.obj2Json();
	}

	@Override
	public String obj2Json(String fromMethod, String data,String product,String version) {
		JSONObject result = new JSONObject();
		result = JSONObject.fromObject(data);
		// 主站返回的result结构
		JSONObject result0 = result.getJSONObject("result");
		if (result0 != null && result0.toString().equals("null")) {
			this.setContent(new JSONObject());
		} else {
			JSONObject returnRestul = OrderDetailUtil.getOrderInfo(result0, userId, mainPayMode,product,version, fromMethod);
			// 增加收货地址列表
			returnRestul.put("receiveList", getAddressList("0"));
			// 增加发票地址列表
			returnRestul.put("invoiceList", getAddressList("1"));
			this.setContent(returnRestul);
		}
		this.setCode(result.getString("code"));
		this.setCod(result.getString("code"));
		this.setMsg(result.getString("msg"));
		return this.toString();
	}
	
	public String getNewOrderToOldOrderDetail(String data){

		JSONObject result = new JSONObject();
		result = JSONObject.fromObject(data);
		// 主站返回的result结构
		JSONObject result0 = result.getJSONObject("result");
		if (result0 != null && result0.toString().equals("null")) {
			this.setContent(new JSONObject());
		} else {
			JSONObject returnRestul = OrderDetailUtil.getManagerOldInfo(result0, userId, mainPayMode);
			// 增加收货地址列表
			returnRestul.put("receiveList", getAddressList("0"));
			// 增加发票地址列表
			returnRestul.put("invoiceList", getAddressList("1"));
			this.setContent(returnRestul);
		}
		this.setCode(result.getString("code"));
		this.setMsg(result.getString("msg"));
		return this.toString();
	
	}
	
	/**
	 * 获取取消订单简要信息 适配老客户端
	 */
	public String getCancelOrderInfo(String data){
		JSONObject result = new JSONObject();
		result = JSONObject.fromObject(data);
		// 主站返回的result结构
		JSONObject result0 = result.getJSONObject("result");
		if (result0 != null && result0.toString().equals("null")) {
			this.setContent(new JSONObject());
		} else {
			JSONObject returnRestul = OrderDetailUtil.getCancelOrderInfo(result0);
			this.setContent(returnRestul);
		}
		this.setCode(result.getString("code"));
		this.setMsg(result.getString("msg"));
		return this.toString();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public JSONArray getMainPayMode() {
		return mainPayMode;
	}

	public void setMainPayMode(JSONArray mainPayMode) {
		this.mainPayMode = mainPayMode;
	}

	 
	
	/**
	 * 收货地址（0） 发票地址
	 * @return
	 */
	private JSONArray getAddressList(String type) {
		String url = Constants.BASE_URL_SP + "getconsigneeaddress/";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("isinvoice", type);
		JSONArray addressList = new JSONArray();
		try {
			String data = WebUtil.readContentFromGet(url, map);
			JSONObject obj = JSONObject.fromObject(data);
			if (obj != null) {
				JSONObject content = obj.getJSONObject("content");
				addressList = content.getJSONArray("list");
			}
		} catch (Exception e) {

		}
		return addressList;
	}

}
