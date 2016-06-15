package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class BalanceServerResponse {
	private String code;
	private String msg;
	private String orderid;
	private String date;
	private String expiryDate;      //订单过期时间
	private String amount;
	private String onlinepayamount;	//	扣除礼品卡金额的在线支付金额
	private String giftcardamount;	//	礼品卡支付金额
	private String status;
	private String statusdesc;
	private String orderOrigin;
	private String orderOriginDesc;
	private String cancancel;
	private String canpay;
	private String canconfirm;
	@SuppressWarnings("unused")
    private String VERSION = Constants.VERSION;
	private String mainOrderId;//主支付订单号
	private String orderIdNew;//订单号v2.9.0以后用
	private String systime;//系统时间
	private String postArea;
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
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			this.setOrderid(obj.getString("orderid"));
			this.setDate(obj.getString("date"));
			this.setAmount(obj.getString("amount"));
			this.setOnlinepayamount(obj.getString("onlineamount"));
			this.setGiftcardamount(obj.getString("giftcardamount"));
			this.setStatus(obj.getString("status"));
			this.setStatusdesc(obj.getString("statusdesc"));
			this.setOrderOrigin(obj.getString("orderOrigin"));
			this.setOrderOriginDesc(obj.getString("orderOriginDesc"));
			this.setCancancel(obj.getString("cancancel"));
			this.setCanpay(obj.getString("canpay"));
			this.setCanconfirm(obj.getString("canconfirm"));
		}
		return this;
	}

	/**
	 * 用4.0.0版本 兼容4.0.0 以及以前的版本
	 * @param jsonStr
	 * @return
	 */
	public  BalanceServerResponse json2BeanHigh4(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			JSONObject json = obj.getJSONObject("result");
			this.setOrderid(json.getString("mainorderno"));
			this.setMainOrderId(json.getString("mainorderno"));
			this.setPostArea(json.getString("postArea"));
			JSONArray orderArray = json.getJSONArray("order");
			for (int i = 0; i < orderArray.size(); i++) {
			    JSONObject orderJsonObj = orderArray.getJSONObject(i);
                this.setOrderIdNew(orderJsonObj.getString("orderno"));                
            }
			this.setSystime(json.getString("systime"));
			this.setDate(json.getString("date"));
			this.setExpiryDate(json.getString("expirydate"));
			this.setAmount(String.valueOf(json.getInt("totalamount")));
			this.setOnlinepayamount(String.valueOf(json.getString("onlineamount")));
			this.setGiftcardamount(String.valueOf(json.getInt("giftcardamount")));
			this.setStatus(String.valueOf(json.getString("status")));
			this.setStatusdesc(json.getString("statusname"));
			this.setCancancel(checkTrueOrFalse(json.getString("cancancel")));
			this.setCanpay(checkTrueOrFalse(json.getString("canpay")));
			this.setCanconfirm(checkTrueOrFalse(json.getString("canconfirmgoods")));
		}
		return this;
	}

	@SuppressWarnings("unused")
    private static String checkNull(String value){
		if(value != null && !value.equals("null")){
			return value;
		}else{
			return "";
		}
	}
	
	private static String checkTrueOrFalse(String value){
		if(value != null && "true".equals(value)){
			return "1";
		}else{
			return "0";
		}
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

	public String getOnlinepayamount() {
		return onlinepayamount;
	}

	public void setOnlinepayamount(String onlinepayamount) {
		this.onlinepayamount = onlinepayamount;
	}

	public String getGiftcardamount() {
		return giftcardamount;
	}

	public void setGiftcardamount(String giftcardamount) {
		this.giftcardamount = giftcardamount;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

    public String getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(String mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public String getOrderIdNew() {
        return orderIdNew;
    }

    public void setOrderIdNew(String orderIdNew) {
        this.orderIdNew = orderIdNew;
    }

    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	
	
}
