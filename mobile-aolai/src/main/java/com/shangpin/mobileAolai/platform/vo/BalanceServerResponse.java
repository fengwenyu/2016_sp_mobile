package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.StringUtil;

public class BalanceServerResponse implements Serializable{
    
    private static final long serialVersionUID = 6342083773591428781L;
    
    private String code;
	private String msg;
	private String orderid;
	private String date;
	private String amount;
	private String status;
	private String statusdesc;
	private String orderOrigin;
	private String orderOriginDesc;
	private String cancancel;
	private String canpay;
	private String canconfirm;

	/**
	 * 解析主站返回的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-12
	 * @param jsonStr
	 *            主站返回的json数据
	 * @Return
	 */
	public BalanceServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			JSONObject sourceOrder = JSONObject.fromObject(obj.getJSONObject("result"));
			this.setOrderid(StringUtil.checkNull(sourceOrder.getString("mainorderno")));
			this.setDate(StringUtil.checkNull(sourceOrder.getString("date")));
			this.setAmount(String.valueOf(sourceOrder.getInt("totalamount")));
			this.setStatus(StringUtil.checkNull(sourceOrder.getString("status")));
			this.setStatusdesc(StringUtil.checkNull(sourceOrder.getString("statusname")));
			this.setOrderOrigin("");
			this.setOrderOriginDesc("");
			this.setCancancel(StringUtil.checkTrueOrFalse(sourceOrder.getString("cancancel")));
			this.setCanpay(StringUtil.checkTrueOrFalse(sourceOrder.getString("canpay")));
			this.setCanconfirm(StringUtil.checkTrueOrFalse(sourceOrder.getString("canconfirmgoods")));
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusdesc() {
		return statusdesc;
	}

	public void setStatusdesc(String statusdesc) {
		this.statusdesc = statusdesc;
	}

	public String getOrderOrigin() {
		return orderOrigin;
	}

	public void setOrderOrigin(String orderOrigin) {
		this.orderOrigin = orderOrigin;
	}

	public String getOrderOriginDesc() {
		return orderOriginDesc;
	}

	public void setOrderOriginDesc(String orderOriginDesc) {
		this.orderOriginDesc = orderOriginDesc;
	}

	public String getCancancel() {
		return cancancel;
	}

	public void setCancancel(String cancancel) {
		this.cancancel = cancancel;
	}

	public String getCanpay() {
		return canpay;
	}

	public void setCanpay(String canpay) {
		this.canpay = canpay;
	}

	public String getCanconfirm() {
		return canconfirm;
	}

	public void setCanconfirm(String canconfirm) {
		this.canconfirm = canconfirm;
	}
}
