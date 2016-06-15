package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.Constants;

/**
 * 用于封装提交订单接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class OrderSubmitServerResponse implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -293663526929247520L;
    private String code;
	private String msg;
	private String orderid;
	private String date;
	private String amount;
	private String cod;// 是否支持货到付款
	private String skucounts;
	private String objectcounts;
	private String giftcard;//0没有礼品卡；1有礼品卡

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public OrderSubmitServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setOrderid(obj.getString("orderid"));
			this.setDate(obj.getString("date"));
			this.setAmount(obj.getString("amount"));
			this.setCod(obj.getString("cod"));
			this.setSkucounts(obj.getString("skucounts"));
			this.setObjectcounts(obj.getString("objectcounts"));
			this.setGiftcard(obj.getString("giftcard"));
		}
		return this;
	}

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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getSkucounts() {
		return skucounts;
	}

	public void setSkucounts(String skucounts) {
		this.skucounts = skucounts;
	}

	public String getObjectcounts() {
		return objectcounts;
	}

	public void setObjectcounts(String objectcounts) {
		this.objectcounts = objectcounts;
	}

	public String getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(String giftcard) {
		this.giftcard = giftcard;
	}
	
	
}
