package com.shangpin.wireless.api.api2server.domain;


import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class GiftCardListServerResponse extends CommonServerResponse {

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

	private ArrayList<GiftCard> list;

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
	public GiftCardListServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(getCode())) {
			JSONArray array = obj.getJSONArray("content");
			ArrayList<GiftCard> list = new ArrayList<GiftCard>();
			for (int i=0;i<array.size();i++) {
				obj = array.getJSONObject(i);
				GiftCard giftcard = new GiftCard();
				giftcard.setGiftCardNo(obj.getString("GiftCardNo"));
				giftcard.setCardType(obj.getString("CardType"));
				giftcard.setStatus(obj.getString("Status"));
				giftcard.setAmount(obj.getInt("Amount"));
				giftcard.setCurrentAmount(obj.getInt("CurrentAmount"));
				giftcard.setDateEnd(obj.getString("DateEnd"));
				
				list.add(giftcard);
			}
			setList(list);
		}
		return this;
	}
}
