package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.OrderDetailUtil;
import com.shangpin.wireless.api.util.StringUtil;

public class OrderManageResponse extends CommonAPIResponse {

	private JSONArray mainPayMode;
	private String userId;
	private String orderType;//订单类别 1 尚品 2 奥莱 
	@Override
	public String obj2Json(String dataStr){
		try{
			JSONObject data = JSONObject.fromObject(dataStr);
			String code = data.getString("code") == null ? "1" : data.getString("code");
			String msg = data.getString("msg") == null ? "查询出错" : data.getString("msg");
			this.setCode(code);
			this.setMsg(msg);
			JSONObject reContent = new JSONObject();
			JSONArray list = new JSONArray();
			
			if(!"".equals(code) && code.equals(Constants.SUCCESS)){
				if (mainPayMode != null && mainPayMode.size() > 0) {
					reContent.put("mainpaymode", mainPayMode);
				} else {
					reContent.put("mainpaymode", new JSONArray());
				}
				Object obj = data.get("result");
				if(obj == null || obj instanceof JSONNull){
					reContent.put("list", new JSONArray());
					reContent.put("giftcard", "0");
					reContent.put("giftcarderror", "卡账户不存在");
				}else{
					JSONObject dataObject = data.getJSONObject("result");
					if(dataObject != null && !dataObject.isEmpty()){
						JSONArray array = dataObject.getJSONArray("Items");
						if(array != null && !array.isEmpty()){
							for(int i = 0; i < array.size(); i++){
								JSONObject sourceOrder = array.getJSONObject(i);
								JSONObject targetOrder = OrderDetailUtil.getManagerOldInfo(sourceOrder, userId, mainPayMode);
								list.add(targetOrder);
								reContent.put("list", list);
								String cardBalance = sourceOrder.getString("giftbardbalance");
								if(StringUtil.isNotEmpty(cardBalance) && !"0".equals(cardBalance)){
									// 老版本无法兼容，暂时去除礼品卡支付
									reContent.put("giftcard", "1");
									reContent.put("giftcarderror", "");
								}else{
									reContent.put("giftcard", "0");
									reContent.put("giftcarderror", "卡账户不存在");
								}
							}
						}
					}else{
						reContent.put("list", list);
						reContent.put("giftcard", "0");
						reContent.put("giftcarderror", "卡账户不存在");
					}
				}
			}else{
				reContent.put("list", list);
				reContent.put("giftcard", "0");
				reContent.put("giftcarderror", "卡账户不存在");
			}
			this.setContent(reContent);
		}catch (Exception e) {
			this.setCode("-1");
			this.setMsg("订单查询出错");
			this.setContent(new JSONObject());
		}
		return this.obj2Json().replace("null", "\"\"");
	}

	public JSONArray getMainPayMode() {
		return mainPayMode;
	}

	public void setMainPayMode(JSONArray mainPayMode) {
		this.mainPayMode = mainPayMode;
	}

	@SuppressWarnings("unused")
    private String checkNull(String value) {
		if (value != null) {
			return value;
		} else {
			return "";
		}
	}

	@SuppressWarnings("unused")
    private String checkTrueOrFalse(String value) {
		if (value != null && "true".equals(value)) {
			return "1";
		} else {
			return "0";
		}
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
