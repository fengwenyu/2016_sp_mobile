package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单类
 *
 */
public class Order implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 private String addr ;
	 private String area ;
	 private String city ;
	 private String date ;
	 private String discountamount ;
	 private String expirydate ;
	 private String express ;
	 private String giftcardamount ;
	 private String invoice ;
	 private String invoiceaddress ;
	 private String invoiceareaname ;
	 private String invoicecityname ;
	 private String invoicemobile ;
	 private String invoicename ;
	 private String invoicephone ;
	 private String	invoicepostcode;
	 private String invoiceprovincename;
	 private String invoicetownname;
	 private String logisticscount;
	 private String name;
	 //订单详情[]
	 private List<OrderDetail> orderdetail ;
	 private String orderno;
	 private String ordertype;
     private String phone;
     private String postcode;
     private String province;
     private String status;
     private String statusname;
     private String tel;
     private String title;
     private String town;
     private String userid;
     /**获取某个订单简单数据*/
     private String orderid;
     private String amount;
     private String onlineamount;
     private String statusdesc;
     private String orderOrigin;
     private String orderOriginDesc;
     private String cancancel;
     private String canpay;
     private String canconfirm;
     private String carriage;
     private String paytime;
     private String paytypeid;
     private String paytypechildid;
     private String postArea;
     private String cardID;
     
    //订单详情 V8.4
    private String orderId;            
	private String orderDesc;          
	private String orderAmount;        
	private String orderAmountDesc;    
	private String statusName;         
	private String canConfirm;         
	private String canLogistics;       
	private String isSplitOrder;       
	private String orderType;          
	private String commentStatus;                      
	private List<OrderDetail> detail;
     
     
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOnlineamount() {
		return onlineamount;
	}
	public void setOnlineamount(String onlineamount) {
		this.onlineamount = onlineamount;
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
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getGiftcardamount() {
		return giftcardamount;
	}
	public void setGiftcardamount(String giftcardamount) {
		this.giftcardamount = giftcardamount;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getInvoiceaddress() {
		return invoiceaddress;
	}
	public void setInvoiceaddress(String invoiceaddress) {
		this.invoiceaddress = invoiceaddress;
	}
	public String getInvoiceareaname() {
		return invoiceareaname;
	}
	public void setInvoiceareaname(String invoiceareaname) {
		this.invoiceareaname = invoiceareaname;
	}
	public String getInvoicecityname() {
		return invoicecityname;
	}
	public void setInvoicecityname(String invoicecityname) {
		this.invoicecityname = invoicecityname;
	}
	public String getInvoicemobile() {
		return invoicemobile;
	}
	public void setInvoicemobile(String invoicemobile) {
		this.invoicemobile = invoicemobile;
	}
	public String getInvoicename() {
		return invoicename;
	}
	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
	}
	public String getInvoicephone() {
		return invoicephone;
	}
	public void setInvoicephone(String invoicephone) {
		this.invoicephone = invoicephone;
	}
	public String getInvoicepostcode() {
		return invoicepostcode;
	}
	public void setInvoicepostcode(String invoicepostcode) {
		this.invoicepostcode = invoicepostcode;
	}
	public String getInvoiceprovincename() {
		return invoiceprovincename;
	}
	public void setInvoiceprovincename(String invoiceprovincename) {
		this.invoiceprovincename = invoiceprovincename;
	}
	public String getInvoicetownname() {
		return invoicetownname;
	}
	public void setInvoicetownname(String invoicetownname) {
		this.invoicetownname = invoicetownname;
	}
	public String getLogisticscount() {
		return logisticscount;
	}
	public void setLogisticscount(String logisticscount) {
		this.logisticscount = logisticscount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<OrderDetail> getOrderdetail() {
		return orderdetail;
	}
	
	public void setOrderdetail(List<OrderDetail> orderdetail) {
		this.orderdetail = orderdetail;
	}
	
	
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderAmountDesc() {
		return orderAmountDesc;
	}
	public void setOrderAmountDesc(String orderAmountDesc) {
		this.orderAmountDesc = orderAmountDesc;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCanConfirm() {
		return canConfirm;
	}
	public void setCanConfirm(String canConfirm) {
		this.canConfirm = canConfirm;
	}
	public String getCanLogistics() {
		return canLogistics;
	}
	public void setCanLogistics(String canLogistics) {
		this.canLogistics = canLogistics;
	}
	public String getIsSplitOrder() {
		return isSplitOrder;
	}
	public void setIsSplitOrder(String isSplitOrder) {
		this.isSplitOrder = isSplitOrder;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public List<OrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<OrderDetail> detail) {
		this.detail = detail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
     
}
