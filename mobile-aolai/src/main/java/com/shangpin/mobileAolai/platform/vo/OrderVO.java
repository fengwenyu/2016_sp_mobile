package com.shangpin.mobileAolai.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.StringUtil;

/**
 * 订单数据传输对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-11-9
 */
@SuppressWarnings("unchecked")
public class OrderVO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -5865219677753476747L;
    /** 订单ID */
	private String orderid;
	/** 用户ID */
	private String userid;
	/** 收货人地址ID */
	private String addrid;
	/** 快递信息 id */
	private String express;
	/** 优惠券id，不用为0 */
	private String coupon;
	/** 主支付方式，支付宝(20)、货到付款(2)、银联 */
	private String paytypeid;
	/** 子支付方式，银联:招行、工行... */
	private String paytypechildid;
	/** 是否开发票 0不开 1开 */
	private String invoiceflag;
	/** 发票抬头 */
	private String invoicetitle;
	/** 发票内容：商品全称，箱包，饰品，钟表，化妆品，服装，鞋帽，眼镜 */
	private String invoicecontent;
	/** 发票类型 0 单位，1个人 */
	private String invoicetype;
	/** 发票收货地址id */
	private String invoiceaddrid;
	/** 订单来源，手机、网站 */
	private String orderfrom;
	/** 表示订单来自尚品1，奥莱2 */
	private String ordertype;
	/** 购物袋中有错误提示的商品sku，多个使用|分割 */
	private String errorskus;
	/** 订单金额 */
	private String amount;
	/** 订单支付金额 */
	private String payamount;
	/** 订单生成日期 */
	private String date;
	/** 订单状态码 */
	private String status;
	/** 订单状态码描述 */
	private String statusdesc;
	/** 取消订单标识，0不可取消；1可以取消 */
	private String cancancel;
	/** 支付订单标识，0不可支付；1可以支付 */
	private String canpay;
	/** 确认订单标识(是否收货)，0不可确认；1可以确认 */
	private String canconfirm;
	/** 是否支持货到付款，0不支持；1支持 */
	private String cod;
	/** 提交订单时错误信息，提交成功时为空 */
	private String msg;
	/** 提交订单后，服务器返回的错误码 */
	private String code;
	// **************************订单详情
	/** 是否支持货到付款，0不支持；1支持 */
	private ConsigneeAddressVO cavo;
	/** 支付方式，支付宝、无支付方式 */
	private String paymode;
	/** 商品列表 */
	private List<MerchandiseVO> merchandiseList;
	// **************************生成订单
	/** 运费 */
	private String carriage;
	/** @see cod */
	private String codincart;
	private String onlineamount;
	private String codmsg;
	private String canlogistics;
	private Object detail;
	private String paydata;
	/** 1使用优惠券； 2使用优惠码；未使用传空字符串 */
	private String couponflag;
	/** 购买商品的gid串，多个用“|”分割*/
	private String buysids;
	/** 总优惠金额*/
	private String discount;
	/**使用的礼品卡总金额 */
	private String giftcardamount;
	/**订单来源*/
	private String orderOrigin;
	/**订单文字描述*/
	private String orderOriginDesc;
	/**0帐号中没有礼品卡；1有礼品卡（可用的礼品卡）*/
	private String giftcard;
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAddrid() {
		return addrid;
	}

	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
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

	public String getInvoiceflag() {
		return invoiceflag;
	}

	public void setInvoiceflag(String invoiceflag) {
		this.invoiceflag = invoiceflag;
	}

	public String getInvoicetitle() {
		return invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public String getInvoicecontent() {
		return invoicecontent;
	}

	public void setInvoicecontent(String invoicecontent) {
		this.invoicecontent = invoicecontent;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public String getInvoiceaddrid() {
		return invoiceaddrid;
	}

	public void setInvoiceaddrid(String invoiceaddrid) {
		this.invoiceaddrid = invoiceaddrid;
	}

	public String getOrderfrom() {
		return orderfrom;
	}

	public void setOrderfrom(String orderfrom) {
		this.orderfrom = orderfrom;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getErrorskus() {
		return errorskus;
	}

	public void setErrorskus(String errorskus) {
		this.errorskus = errorskus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ConsigneeAddressVO getCavo() {
		return cavo;
	}

	public void setCavo(ConsigneeAddressVO cavo) {
		this.cavo = cavo;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public List<MerchandiseVO> getMerchandiseList() {
		return merchandiseList;
	}

	public void setMerchandiseList(List<MerchandiseVO> merchandiseList) {
		this.merchandiseList = merchandiseList;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getCodincart() {
		return codincart;
	}

	public void setCodincart(String codincart) {
		this.codincart = codincart;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOnlineamount() {
		return onlineamount;
	}

	public void setOnlineamount(String onlineamount) {
		this.onlineamount = onlineamount;
	}

	public String getCodmsg() {
		return codmsg;
	}

	public void setCodmsg(String codmsg) {
		this.codmsg = codmsg;
	}

	public String getCanlogistics() {
		return canlogistics;
	}

	public void setCanlogistics(String canlogistics) {
		this.canlogistics = canlogistics;
	}

	/**
	 * 将json对象转化为OrderVO对象
	 * 
	 * @param json
	 *            json对象
	 * 
	 * @return OrderVO对象
	 */
	public static OrderVO json2Bean(JSONObject sourceOrder) {
		OrderVO orderVO = new OrderVO();
		JSONArray orderArray = sourceOrder.getJSONArray("order");
		List<MerchandiseVO> productAllList = new ArrayList<MerchandiseVO>();
		if (orderArray != null && orderArray.size() > 0) {
			for(int i = 0; i < orderArray.size(); i++){
				JSONObject orderDetail = orderArray.getJSONObject(i);
				if(i == 0){
					// 组装收货人地址
					ConsigneeAddressVO consigneeAddressVO = new ConsigneeAddressVO();
					consigneeAddressVO.setName(StringUtil.checkNull(orderDetail.getString("name")));
					consigneeAddressVO.setProvince(StringUtil.checkNull(orderDetail.getString("province")));
					consigneeAddressVO.setCity(StringUtil.checkNull(orderDetail.getString("city")));
					consigneeAddressVO.setArea(StringUtil.checkNull(orderDetail.getString("area")));
					consigneeAddressVO.setAddr(StringUtil.checkNull(orderDetail.getString("addr")));
					consigneeAddressVO.setTel(StringUtil.checkNull(orderDetail.getString("tel")));
					orderVO.setCavo(consigneeAddressVO);
					String expressDesc = StringUtil.checkNull(orderDetail.getString("express"));
					orderVO.setExpress(expressDesc);
					orderVO.setInvoicetitle(StringUtil.checkNull(orderDetail.getString("title")));
					orderVO.setInvoicecontent(StringUtil.checkNull(orderDetail.getString("invoice")));
				}
				JSONArray orderList = orderDetail.getJSONArray("orderdetail");
				if(!(orderList.isEmpty() || !orderList.isArray())){
					for(int j =0 ;j < orderList.size(); j++){
						JSONObject product = orderList.getJSONObject(j);
						MerchandiseVO merchandiseVO = new MerchandiseVO();
						merchandiseVO.setFirstpropname("颜色");
						merchandiseVO.setFirstpropvalue(StringUtil.checkNull(product.getString("firstpropvalue")));
						merchandiseVO.setSecondpropname("尺码");
						merchandiseVO.setSecondpropvalue(StringUtil.checkNull(product.getString("secondpropvalue")));
						merchandiseVO.setAmount(StringUtil.checkNull(product.getString("amount")));
						merchandiseVO.setCount(StringUtil.checkNull(product.getString("count")));
						merchandiseVO.setBrand(StringUtil.checkNull(product.getString("brand")));
						merchandiseVO.setPic(StringUtil.checkNull(product.getString("pic")));
						merchandiseVO.setProductname(StringUtil.checkNull(product.getString("productname")));
						productAllList.add(merchandiseVO);
					}
				}
			}
		}
		orderVO.setMerchandiseList(productAllList);
		// 组装订单详情信息
		orderVO.setOrderid(StringUtil.checkNull(sourceOrder.getString("mainorderno")));
		orderVO.setStatusdesc(StringUtil.checkNull(sourceOrder.getString("statusname")));
		orderVO.setDate(StringUtil.checkNull(sourceOrder.getString("date")));
		orderVO.setCarriage(String.valueOf(sourceOrder.getInt("freight")));
		orderVO.setPaymode(StringUtil.checkNull(sourceOrder.getString("paytypename")));
		orderVO.setAmount(String.valueOf(sourceOrder.getInt("totalamount")));
		orderVO.setPayamount(String.valueOf(sourceOrder.getInt("payamount")));
		orderVO.setCanpay(StringUtil.checkTrueOrFalse(sourceOrder.getString("canpay")));
		orderVO.setCancancel(StringUtil.checkTrueOrFalse(sourceOrder.getString("cancancel")));
		orderVO.setOnlineamount(String.valueOf(sourceOrder.getInt("onlineamount")));
		orderVO.setDiscount(String.valueOf(sourceOrder.getInt("discountamount")));
		
		String giftCardAmount = StringUtil.checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		orderVO.setGiftcardamount(giftCardAmount);
		return orderVO;
	}
	/**
	 * 将json对象转化为OrderVO对象
	 * 
	 * @param json
	 *            json对象
	 * 
	 * @return OrderVO对象
	 */
	public static OrderVO json2NewBean(JSONObject sourceOrder) {
		OrderVO orderVO = new OrderVO();
		JSONArray orderArray = sourceOrder.getJSONArray("order");
		JSONArray productAllList = new JSONArray();
		if (orderArray != null && orderArray.size() > 0) {
			for(int i = 0; i < orderArray.size(); i++){
				JSONObject orderDetail = orderArray.getJSONObject(i);
				if(i == 0){
					// 组装收货人地址
					ConsigneeAddressVO consigneeAddressVO = new ConsigneeAddressVO();
					consigneeAddressVO.setName(StringUtil.checkNull(orderDetail.getString("name")));
					consigneeAddressVO.setProvince(StringUtil.checkNull(orderDetail.getString("province")));
					consigneeAddressVO.setCity(StringUtil.checkNull(orderDetail.getString("city")));
					consigneeAddressVO.setArea(StringUtil.checkNull(orderDetail.getString("area")));
					consigneeAddressVO.setAddr(StringUtil.checkNull(orderDetail.getString("addr")));
					consigneeAddressVO.setTel(StringUtil.checkNull(orderDetail.getString("tel")));
					orderVO.setCavo(consigneeAddressVO);
					String expressDesc = StringUtil.checkNull(orderDetail.getString("express"));
					orderVO.setExpress(expressDesc);
					orderVO.setInvoicetitle(StringUtil.checkNull(orderDetail.getString("title")));
					orderVO.setInvoicecontent(StringUtil.checkNull(orderDetail.getString("invoice")));
				}
				JSONArray orderList = orderDetail.getJSONArray("orderdetail");
				if(!(orderList.isEmpty() || !orderList.isArray())){
					for(int j =0 ;j < orderList.size(); j++){
						JSONObject product = orderList.getJSONObject(j);
						product.put("firstpropname", "颜色");
						product.put("secondpropname", "尺码");
					}
					productAllList.addAll(orderList);
				}
			}
		}
		orderVO.setMerchandiseList(productAllList);
		// 组装订单详情信息
		orderVO.setOrderid(StringUtil.checkNull(sourceOrder.getString("mainorderno")));
		orderVO.setStatusdesc(StringUtil.checkNull(sourceOrder.getString("statusname")));
		orderVO.setDate(StringUtil.checkNull(sourceOrder.getString("date")));
		orderVO.setCarriage(String.valueOf(sourceOrder.getInt("freight")));
		orderVO.setPaymode(StringUtil.checkNull(sourceOrder.getString("paytypename")));
		orderVO.setAmount(String.valueOf(sourceOrder.getInt("totalamount")));
		orderVO.setPayamount(String.valueOf(sourceOrder.getInt("payamount")));
		orderVO.setCanpay(StringUtil.checkTrueOrFalse(sourceOrder.getString("canpay")));
		orderVO.setCancancel(StringUtil.checkTrueOrFalse(sourceOrder.getString("cancancel")));
		orderVO.setOnlineamount(String.valueOf(sourceOrder.getInt("onlineamount")));
		orderVO.setDiscount(String.valueOf(sourceOrder.getInt("discountamount")));
		
		String giftCardAmount = StringUtil.checkNull(sourceOrder.getString("giftcardamount"));
		if (giftCardAmount.indexOf(".") > 0) {
			giftCardAmount = giftCardAmount.substring(0, giftCardAmount.indexOf("."));
		}
		orderVO.setGiftcardamount(giftCardAmount);
		return orderVO;
	}
	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

	public String getPaydata() {
		return paydata;
	}

	public void setPaydata(String paydata) {
		this.paydata = paydata;
	}

	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getCouponflag() {
		return couponflag;
	}

	public void setCouponflag(String couponflag) {
		this.couponflag = couponflag;
	}

	public String getBuysids() {
		return buysids;
	}

	public void setBuysids(String buysids) {
		this.buysids = buysids;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGiftcardamount() {
		return giftcardamount;
	}

	public void setGiftcardamount(String giftcardamount) {
		this.giftcardamount = giftcardamount;
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

	public String getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(String giftcard) {
		this.giftcard = giftcard;
	}
	
	
}
