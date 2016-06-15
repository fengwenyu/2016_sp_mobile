package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 用于封装提交订单接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class OrderSubmitServerResponse {

	private String code;
	private String msg;
	private String orderid;
	private String date;
	private String amount;
	private String carriage;	//	运费
	private String cod;// 是否支持货到付款
	private String codmsg;// 不支持货到付款的原因
	private String skucounts;
	private String objectcounts;
	private String giftcard;//是否有可用的礼品卡，0没有；1有

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
			this.setCarriage(obj.getString("carriage"));
			this.setCod(obj.getString("cod"));
			this.setCodmsg(obj.getString("codmsg"));
			this.setSkucounts(obj.getString("skucounts"));
			this.setObjectcounts(obj.getString("objectcounts"));
			this.setGiftcard(obj.getString("giftcard"));
		}
		return this;
	}
	public OrderSubmitServerResponse jsonV2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setOrderid(obj.getString("orderid"));
			this.setDate(obj.getString("date"));
			this.setAmount(obj.getString("amount"));
			this.setCarriage(obj.getString("carriage"));
			this.setCod(obj.getString("cod"));
			this.setCodmsg(obj.getString("codmsg"));
			this.setSkucounts(obj.getString("skucounts"));
			this.setObjectcounts(obj.getString("objectcounts"));
			if(Float.valueOf(obj.getString("giftcardbalance"))>0){
				this.setGiftcard("1");
			}else{
				this.setGiftcard("0");
			}
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

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCodmsg() {
		return codmsg;
	}

	public void setCodmsg(String codmsg) {
		this.codmsg = codmsg;
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
