package com.shangpin.wireless.api.api2server.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 订单数据传输对象，用于前台展示
 * 
 * @Author wangfeng
 * @CreateDate 2013-9-9
 */
public class OrderdetailServerResponse {

	/** 订单ID */
	private String orderid;
	/** 主支付方式，支付宝(20)、货到付款(2)、银联 */
	private String paytypeid;
	/** 子支付方式，银联:招行、工行... */
	private String paytypechildid;
	/** 表示订单来自尚品1，奥莱2 */
	private String ordertype;
	/** 订单金额 */
	private String amount;
	/** 订单支付金额 */
	private String payamount;
	/** 订单生成日期 */
	private String date;
	/** 是否支持货到付款，0不支持；1支持 */
	private String cod;
	/** 提交订单时错误信息，提交成功时为空 */
	private String onlinepayamount;	//	扣除礼品卡金额的在线支付金额
	private String giftcardamount;	//	礼品卡支付金额
	private String status;
	private String statusdesc;
	private String orderOrigin;
	private String orderOriginDesc;
	private String cancancel;
	private String canpay;
	private String canconfirm;
	private String msg;
	/** 提交订单后，服务器返回的错误码 */
	private String code;
	/** 商品列表 */
	private List<SPGoodsOrderServerResponse> spGoods;
	private String mainOrderId;//支付订单号
	private String orderIdNew;//订单号 v2.9.0版本之后用
	private String systime;//系统时间
	private String expirtime;//有效时间
	private String postArea;
	// **************************生成订单
	
	
	/**
	 * 大于4.0的版本
	 * @param jsonStr
	 * @return
	 */
	public static OrderdetailServerResponse json2BeanHigh4(String jsonStr) {
		OrderdetailServerResponse orderdetailServerResponse = new OrderdetailServerResponse();
		JSONObject obj = JSONObject.fromObject(jsonStr);
		orderdetailServerResponse.setCode(obj.getString("code"));
		orderdetailServerResponse.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(orderdetailServerResponse.code)) {
			JSONObject json = obj.getJSONObject("result");
			// 组装订单详情信息
			orderdetailServerResponse.setPostArea(json.getString("postArea"));
			orderdetailServerResponse.setOrderid(json.getString("mainorderno"));
			orderdetailServerResponse.setMainOrderId(json.getString("mainorderno"));
			orderdetailServerResponse.setDate(json.getString("date").replace("-", "/"));
			orderdetailServerResponse.setExpirtime(json.getString("expirydate").replace("-", "/"));
			orderdetailServerResponse.setSystime(json.getString("systime"));
			orderdetailServerResponse.setPayamount(String.valueOf(json.getInt("payamount")));
			orderdetailServerResponse.setAmount(String.valueOf(json.getInt("totalamount")));
			orderdetailServerResponse.setOnlinepayamount(String.valueOf(json.getString("onlineamount")));
			orderdetailServerResponse.setGiftcardamount(String.valueOf(json.getInt("giftcardamount")));
			orderdetailServerResponse.setStatus(String.valueOf(json.getString("status")));
			orderdetailServerResponse.setStatusdesc(json.getString("statusname"));
			orderdetailServerResponse.setCancancel(checkTrueOrFalse(json.getString("cancancel")));
			orderdetailServerResponse.setCanpay(checkTrueOrFalse(json.getString("canpay")));
			orderdetailServerResponse.setCanconfirm(checkTrueOrFalse(json.getString("canconfirmgoods")));
			JSONArray orderArray = json.getJSONArray("order");
			List <SPGoodsOrderServerResponse> list = new ArrayList<SPGoodsOrderServerResponse>();
			for (int i = 0; i < orderArray.size(); i++) {
				SPGoodsOrderServerResponse spGoodsOrderServerResponse = new SPGoodsOrderServerResponse();
				JSONObject goodsJsonObj = orderArray.getJSONObject(i);
				orderdetailServerResponse.setOrderIdNew(goodsJsonObj.getString("orderno"));
				JSONArray products = goodsJsonObj.getJSONArray("orderdetail");
				for(int j =0; j< products.size(); j++){
					JSONObject product = products.getJSONObject(j);
					spGoodsOrderServerResponse.setCount(product.getString("count"));
					spGoodsOrderServerResponse.setProductname(product.getString("productname"));	
					spGoodsOrderServerResponse.setDuty(product.getString("duty"));
					spGoodsOrderServerResponse.setGoodFees(product.getString("goodFees"));
					spGoodsOrderServerResponse.setTransportFee(product.getString("transportFee"));
					list.add(spGoodsOrderServerResponse);
				}
			}
			orderdetailServerResponse.setSpGoods(list);
		}

		return orderdetailServerResponse;
	
	}

	/**
	 * 低于4.0的版本
	 * @param jsonStr
	 * @return
	 */
	public static OrderdetailServerResponse json2BeanOfLow4(String jsonStr){
		OrderdetailServerResponse orderdetailServerResponse = new OrderdetailServerResponse();
		JSONObject obj = JSONObject.fromObject(jsonStr);
		orderdetailServerResponse.setCode(obj.getString("code"));
		orderdetailServerResponse.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(orderdetailServerResponse.code)) {
			JSONObject json = JSONObject.fromObject(obj.getJSONObject("content"));
			// 订单json对象
			JSONObject detail = (JSONObject) json.get("detail");

			// 组装订单详情信息
			orderdetailServerResponse.setOrderid(json.getString("orderid"));
			orderdetailServerResponse.setDate(json.getString("date"));
			orderdetailServerResponse.setPayamount(json.getString("payamount"));
			orderdetailServerResponse.setAmount(json.getString("amount"));
			orderdetailServerResponse.setOnlinepayamount(json.getString("onlineamount"));
			orderdetailServerResponse.setGiftcardamount(json.getString("giftcardamount"));
			orderdetailServerResponse.setStatus(json.getString("status"));
			orderdetailServerResponse.setStatusdesc(json.getString("statusdesc"));
			orderdetailServerResponse.setOrderOrigin(json.getString("orderOrigin"));
			orderdetailServerResponse.setOrderOriginDesc(json.getString("orderOriginDesc"));
			orderdetailServerResponse.setCancancel(checkTrueOrFalse(json.getString("cancancel")));
			orderdetailServerResponse.setCanpay(json.getString("canpay"));
			orderdetailServerResponse.setCanconfirm(json.getString("canconfirm"));
			// 商品列表json对象
			JSONArray array = detail.getJSONArray("list");
			// orderVO.list = JSONArray.toList(array, new MerchandiseVO(),new
			// JsonConfig());
			List <SPGoodsOrderServerResponse> list = new ArrayList<SPGoodsOrderServerResponse>();
			for (int i = 0; i < array.size(); i++) {
				SPGoodsOrderServerResponse spGoodsOrderServerResponse = new SPGoodsOrderServerResponse();
				JSONObject goodsJsonObj = array.getJSONObject(i);
				spGoodsOrderServerResponse.setCount(goodsJsonObj.getString("count"));
				spGoodsOrderServerResponse.setProductname(goodsJsonObj.getString("productname"));													
				list.add(spGoodsOrderServerResponse);
			}
			orderdetailServerResponse.setSpGoods(list);
		}

		return orderdetailServerResponse;
	}
	
	@SuppressWarnings("unused")
    private String checkNull(String value){
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
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPaytypeid() {
		return paytypeid;
	}

	public void setPaytypeid(String paytypeid) {
		this.paytypeid = paytypeid;
	}

	public String getPaytypechildid() {
		return paytypechildid;
	}

	public void setPaytypechildid(String paytypechildid) {
		this.paytypechildid = paytypechildid;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SPGoodsOrderServerResponse> getSpGoods() {
		return spGoods;
	}

	public void setSpGoods(List<SPGoodsOrderServerResponse> spGoods) {
		this.spGoods = spGoods;
	}


    public String getOrderIdNew() {
        return orderIdNew;
    }

    public void setOrderIdNew(String orderIdNew) {
        this.orderIdNew = orderIdNew;
    }

    public String getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(String mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public String getExpirtime() {
        return expirtime;
    }

    public void setExpirtime(String expirtime) {
        this.expirtime = expirtime;
    }

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

   
    
	
	
}
