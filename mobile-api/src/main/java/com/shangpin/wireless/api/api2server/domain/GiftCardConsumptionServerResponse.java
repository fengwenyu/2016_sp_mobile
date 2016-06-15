package com.shangpin.wireless.api.api2server.domain;


import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class GiftCardConsumptionServerResponse extends CommonServerResponse {

	public static class GiftCard {
		private String giftCardNo;
		private String cardType;
		private int amount;
		private String status;
		private int currentAmount;
		private String dateEnd;
		public String getGiftCardNo() {
			return giftCardNo;
		}
		public void setGiftCardNo(String giftCardNo) {
			this.giftCardNo = giftCardNo;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getCurrentAmount() {
			return currentAmount;
		}
		public void setCurrentAmount(int currentAmount) {
			this.currentAmount = currentAmount;
		}
		public String getDateEnd() {
			return dateEnd;
		}
		public void setDateEnd(String dateEnd) {
			this.dateEnd = dateEnd;
		}
	}
	
	public static class Consumption {
		private String cardChangeAmountId;
		private String orderNo;
		private String changeType;
		private int lastAmount;
		private int currentAmount;
		private String dateChange;
		public String getCardChangeAmountId() {
			return cardChangeAmountId;
		}
		public void setCardChangeAmountId(String cardChangeAmountId) {
			this.cardChangeAmountId = cardChangeAmountId;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getChangeType() {
			return changeType;
		}
		public void setChangeType(String changeType) {
			this.changeType = changeType;
		}
		public int getLastAmount() {
			return lastAmount;
		}
		public void setLastAmount(int lastAmount) {
			this.lastAmount = lastAmount;
		}
		public int getCurrentAmount() {
			return currentAmount;
		}
		public void setCurrentAmount(int currentAmount) {
			this.currentAmount = currentAmount;
		}
		public String getDateChange() {
			return dateChange;
		}
		public void setDateChange(String dateChange) {
			this.dateChange = dateChange;
		}
	}

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
	public GiftCardConsumptionServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(getCode())) {
			obj = obj.getJSONObject("content");
			JSONObject cardobj = obj.getJSONObject("giftcard");
			GiftCard giftcard = new GiftCard();
			giftcard.setGiftCardNo(cardobj.getString("GiftCardNo"));
			giftcard.setCardType(cardobj.getString("CardType"));
			giftcard.setStatus(cardobj.getString("Status"));
			giftcard.setAmount(cardobj.getInt("Amount"));
			giftcard.setCurrentAmount(cardobj.getInt("CurrentAmount"));
			giftcard.setDateEnd(cardobj.getString("DateEnd"));
			setGiftcard(giftcard);
			
			JSONArray array = obj.getJSONArray("list");
			ArrayList<Consumption> list = new ArrayList<Consumption>();
			for (int i=0;i<array.size();i++) {
				obj = array.getJSONObject(i);
				Consumption consumption = new Consumption();
				consumption.setCardChangeAmountId(obj.getString("CardChangeAmountId"));
				consumption.setOrderNo(obj.getString("RelationNo"));
				consumption.setChangeType(obj.getString("ChangeType"));
				consumption.setLastAmount(obj.getInt("LastAmount"));
				consumption.setCurrentAmount(obj.getInt("CurrentAmount"));
				consumption.setDateChange(obj.getString("DateChange"));
				
				list.add(consumption);
			}
			setList(list);
		}
		return this;
	}
}
