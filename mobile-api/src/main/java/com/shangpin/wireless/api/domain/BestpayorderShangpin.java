package com.shangpin.wireless.api.domain;

/**
 * 翼支付生成订单表
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-09-08
 */

public class BestpayorderShangpin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String partnerid;
	private String partnername;
	private String supplyorgcode1;
	private String supplyorgcode2;
	private String supplyorgcode3;
	private String productno;
	private String partnerorderid;
	private String orderid;
	private String txnamount;
	private String rating;
	private String goodsname;
	private String goodscount;
	private String sig;

	// Constructors

	/** default constructor */
	public BestpayorderShangpin() {
	}

	/** full constructor */
	public BestpayorderShangpin(String partnerid, String partnername, String supplyorgcode1, String supplyorgcode2, String supplyorgcode3, String productno, String partnerorderid, String orderid, String txnamount, String rating, String goodsname, String goodscount, String sig) {
		this.partnerid = partnerid;
		this.partnername = partnername;
		this.supplyorgcode1 = supplyorgcode1;
		this.supplyorgcode2 = supplyorgcode2;
		this.supplyorgcode3 = supplyorgcode3;
		this.productno = productno;
		this.partnerorderid = partnerorderid;
		this.orderid = orderid;
		this.txnamount = txnamount;
		this.rating = rating;
		this.goodsname = goodsname;
		this.goodscount = goodscount;
		this.sig = sig;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartnerid() {
		return this.partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPartnername() {
		return this.partnername;
	}

	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}

	public String getSupplyorgcode1() {
		return this.supplyorgcode1;
	}

	public void setSupplyorgcode1(String supplyorgcode1) {
		this.supplyorgcode1 = supplyorgcode1;
	}

	public String getSupplyorgcode2() {
		return this.supplyorgcode2;
	}

	public void setSupplyorgcode2(String supplyorgcode2) {
		this.supplyorgcode2 = supplyorgcode2;
	}

	public String getSupplyorgcode3() {
		return this.supplyorgcode3;
	}

	public void setSupplyorgcode3(String supplyorgcode3) {
		this.supplyorgcode3 = supplyorgcode3;
	}

	public String getProductno() {
		return this.productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public String getPartnerorderid() {
		return this.partnerorderid;
	}

	public void setPartnerorderid(String partnerorderid) {
		this.partnerorderid = partnerorderid;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTxnamount() {
		return this.txnamount;
	}

	public void setTxnamount(String txnamount) {
		this.txnamount = txnamount;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodscount() {
		return this.goodscount;
	}

	public void setGoodscount(String goodscount) {
		this.goodscount = goodscount;
	}

	public String getSig() {
		return this.sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

}