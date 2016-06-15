package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.OrderDetailUtil;

/**
 * 订单合并列表
 * @author xupengcheng
 *
 */
public class OrderMergeManageResponse extends CommonAPIResponse{

	private JSONArray mainPayMode;
	private String orderType;//订单类别 1 尚品2 奥莱 兼容客户端老版本
	private String userId;
	@Override
	public String obj2Json(String dataStr,String product,String version) {
		JSONObject returnObj = new JSONObject();
		try{
			//主站服务器传的数据
			JSONObject data = JSONObject.fromObject(dataStr);
			String code = data.getString("code") == null ? "" : data.getString("code");
			String msg = data.getString("msg") == null ? "" : data.getString("msg");
			returnObj.put("code", code);
			returnObj.put("msg", msg);
			JSONObject reContent = new JSONObject();
			JSONArray list = new JSONArray();
			if(!"".equals(code) && code.equals(Constants.SUCCESS)){
				Object obj = data.get("result");
				if(obj == null || obj instanceof JSONNull){
					reContent.put("list", new JSONArray());
				}else{
					JSONObject dataObject = data.getJSONObject("result");
					if(dataObject != null && !"{}".equals(dataObject.toString())){
						JSONArray array = dataObject.getJSONArray("Items");
						if(array != null && !"".equals(array.toString())){
							for(int i = 0; i < array.size(); i++){
								JSONObject sourceOrder = array.getJSONObject(i);
								JSONObject targetOrder = OrderDetailUtil.getOrderInfo(sourceOrder, userId, mainPayMode,product,version, "");
								list.add(targetOrder);
								reContent.put("list", list);
							}
						}
					}else{
						reContent.put("list", list);
					}
				}
			}else{
				reContent.put("list", list);
			}
			returnObj.put("content", reContent);
		}catch (Exception e) {
			System.out.print(e.getStackTrace());
			returnObj.put("code", "-1");
			returnObj.put("msg", "订单查询出错");
		}
		return returnObj.toString().replace("null", "\"\"");
	}
	
	
	public JSONArray getMainPayMode() {
		return mainPayMode;
	}
	public void setMainPayMode(JSONArray mainPayMode) {
		this.mainPayMode = mainPayMode;
	}
	 
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
