package com.shangpin.wireless.api.api2client.domain;

import java.util.Map;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;

public class BalanceAPIResponse {
	
	private String code;
	private String cod;
	private String msg;
	private String orderid;
	private String date;
	private String amount;
	private String onlinepayamount;	//	扣除礼品卡金额的在线支付金额
	private String giftcardamount;	//	礼品卡支付金额
//	private String status;
	private String statusdesc;
	private String expiretime;
	private String mainpaymode;
	private String subpaymode;
	private String data;
	private Map<String,String> wechat;
	private String payarg1;
	private String payarg2;
	private String payarg3;
	private String cancancel;
	private String canpay;
	private String canconfirm;
	private String mainOrderId;//支付订单号
	private String orderIdNew;//订单号 v2.9.0之后用
	private String systime;//系统时间

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-09-12
	 * @param
	 * @Return
	 */
	public String obj2Json() {
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("cod", code);
		obj.put("msg", msg);
		JSONObject content = new JSONObject();
		if (Constants.SUCCESS.equals(code)) {
		    content.put("systime", systime);
			content.put("orderid", orderid);
			content.put("mainOrderId", mainOrderId);
			content.put("orderIdNew", orderIdNew);
			content.put("date", date);
			content.put("expiretime", expiretime);
			content.put("amount", StringUtil.cutOffDecimal(onlinepayamount));	//	扣除礼品卡金额的在线支付金额
//			content.put("status", status);
			content.put("statusdesc", statusdesc);
			content.put("mainpaymode", mainpaymode);
			content.put("subpaymode", subpaymode);
			content.put("data", data);
			content.put("wechat", wechat);
//			if (payarg1 != null) {
//				content.put("payarg1", payarg1);
//			}
//			if (payarg2 != null) {
//				content.put("payarg2", payarg2);
//			}
//			if (payarg3 != null) {
//				content.put("payarg3", payarg3);
//			}
			content.put("payarg1", payarg1==null?"":payarg1);
			content.put("payarg2", payarg2==null?"":payarg2);
			content.put("payarg3", payarg3==null?"":payarg3);
			content.put("cancancel", cancancel);
			content.put("canpay", canpay);
			content.put("canconfirm", canconfirm);
		}
		obj.put("content", content);
		return obj.toString();
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
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

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getStatusdesc() {
		return statusdesc;
	}

	public void setStatusdesc(String statusdesc) {
		this.statusdesc = statusdesc;
	}

	public String getExpireTime() {
		return expiretime;
	}

	public void setExpireTime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getMainpaymode() {
		return mainpaymode;
	}

	public void setMainpaymode(String mainpaymode) {
		this.mainpaymode = mainpaymode;
	}

	public String getSubpaymode() {
		return subpaymode;
	}

	public void setSubpaymode(String subpaymode) {
		this.subpaymode = subpaymode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public void setPayArg1(String payarg1) {
		this.payarg1 = payarg1;
	}
	public void setPayArg2(String payarg2) {
		this.payarg2 = payarg2;
	}
	public void setPayArg3(String payarg3) {
		this.payarg3 = payarg3;
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


	public Map<String, String> getWechat() {
		return wechat;
	}

	public void setWechat(Map<String, String> wechat) {
		this.wechat = wechat;
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
	

}
