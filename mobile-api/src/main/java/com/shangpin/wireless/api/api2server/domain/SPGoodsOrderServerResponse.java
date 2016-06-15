package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class SPGoodsOrderServerResponse {
	private String code;
	private String msg;
	
	private String count;// 商品购买数量
	private String productname;// 商品名称
	private String goodFees;
	private String transportFee;
	private String duty;

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2012-11-2
	 * @param
	 * @Return
	 */
	public SPGoodsOrderServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			setCount(obj.getString("count"));
			setProductname(obj.getString("productname"));
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getGoodFees() {
		return goodFees;
	}

	public void setGoodFees(String goodFees) {
		this.goodFees = goodFees;
	}

	public String getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
}
