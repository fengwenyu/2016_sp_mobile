package com.shangpin.wireless.api.api2client.domain;


import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.GiftCardConsumptionServerResponse.Consumption;
import com.shangpin.wireless.api.api2server.domain.GiftCardConsumptionServerResponse.GiftCard;
import com.shangpin.wireless.api.domain.Constants;

public class GiftCardConsumptionAPIResponse extends CommonAPIResponse {


	private GiftCard giftcard;
	private ArrayList<Consumption> list;

	public GiftCard getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(GiftCard giftcard) {
		this.giftcard = giftcard;
	}

	public ArrayList<Consumption> getList() {
		return list;
	}

	public void setList(ArrayList<Consumption> list) {
		this.list = list;
	}

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2014-01-26
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg());
		JSONObject content = new JSONObject();
		content.put("cardno", giftcard.getGiftCardNo());
		content.put("amount", String.valueOf(giftcard.getAmount()));
		content.put("balance", String.valueOf(giftcard.getCurrentAmount()));
		if (Constants.SUCCESS.equals(getCode()) && list != null) {
			JSONArray array = new JSONArray();
			for (int i=0;i<list.size();i++) {
				Consumption consumption = list.get(i);
				JSONObject item = new JSONObject();
				item.put("orderid", consumption.getOrderNo());
				item.put("ordertime", "");
				item.put("type", ("1".equals(consumption.getChangeType()) || "2".equals(consumption.getChangeType()) ? consumption.getChangeType() : "0"));
				item.put("lastamount", String.valueOf(consumption.getLastAmount()));
				item.put("giftcardamount", String.valueOf(Math.abs(consumption.getCurrentAmount() - consumption.getLastAmount())));
				item.put("currentamount", String.valueOf(consumption.getCurrentAmount()));
				item.put("usedtime", consumption.getDateChange());
				
				array.add(item);
			}
			content.put("list", array);
		}
		obj.put("content", content);
		return obj.toString();
	}
}
