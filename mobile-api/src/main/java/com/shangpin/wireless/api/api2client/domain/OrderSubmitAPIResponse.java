package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 用于封装提交订单接口的返回数据
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-04
 */
public class OrderSubmitAPIResponse {

	private String code;
	private String msg;
	private JSONArray mainpaymode;
	private String orderid;
	private String date;
	private String amount;
	private String carriage;	//	运费
	private String cod;// 是否支持货到付款
	private String codmsg;// 不支持货到付款的原因
	private String giftcard;//是否有可用的礼品卡，0没有；1有

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-04
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
			content.put("orderid", orderid);
			content.put("date", date);
//			content.put("amount", amount);
			content.put("amount", String.valueOf(Integer.parseInt(amount) + Integer.parseInt(carriage)));
			content.put("orderamount", amount);
			content.put("carriage", carriage);
			content.put("codmsg", codmsg);
			content.put("giftcard", giftcard);
			content.put("mainpaymode", mainpaymode==null?"[]":mainpaymode);
		}
		obj.put("content", content);
		return obj.toString();
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

	public JSONArray getMainpaymode() {
		return mainpaymode;
	}

	public void setMainpaymode(JSONArray mainpaymode) {
		this.mainpaymode = mainpaymode;
	}

	public String getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(String giftcard) {
		this.giftcard = giftcard;
	}

}
