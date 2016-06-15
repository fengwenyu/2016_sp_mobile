package com.shangpin.wireless.api.api2client.domain;


import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.GiftCardListServerResponse.GiftCard;
import com.shangpin.wireless.api.domain.Constants;

public class GiftCardListAPIResponse extends CommonAPIResponse {

	private String balance;
	private ArrayList<GiftCard> list;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public ArrayList<GiftCard> getList() {
		return list;
	}

	public void setList(ArrayList<GiftCard> list) {
		this.list = list;
	}

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2014-01-24
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg());
		JSONObject content = new JSONObject();
		content.put("balance", balance);
		if (Constants.SUCCESS.equals(getCode()) && list != null) {
			JSONArray array = new JSONArray();
			for (int i=0;i<list.size();i++) {
				GiftCard giftcard = list.get(i);
				JSONObject item = new JSONObject();
				item.put("cardno", giftcard.getGiftCardNo());
				item.put("amount", String.valueOf(giftcard.getAmount()));
				item.put("balance", String.valueOf(giftcard.getCurrentAmount()));
				item.put("expiredate", giftcard.getDateEnd());
				item.put("buystatus", "7".equals(giftcard.getStatus())?"1":"0");
				
				array.add(item);
			}
			content.put("list", array);
		}
		obj.put("content", content);
		return obj.toString();
	}
}
