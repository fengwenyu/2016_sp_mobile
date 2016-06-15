package com.shangpin.biz.bo;

import java.io.Serializable;

import com.shangpin.biz.utils.StringUtil;

/**
 * 订单提交成功主站返回数据
 * 
 * @author cuibinqiang
 *
 */
public class OrderReturn  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderid; // 订单编号
	private String date; // 运费对象
	private String amount; // 运费金额
	private String cod; // 是否支持货到付款（（1 支持，0不支持））
	private String codmsg; // 不支持货到付款原因
	private String skucounts;// 提交成功的sku数量
	private String objectcounts; // 礼品卡余额
	private String giftcardbalance; // 商品数量
	private String giftcard; // 是否使用礼品卡0：为不使用；1：使用
	private String carriage; // 运费
	private String systime;
	private String expiretime;
	private String postarea;
	private String orderno;
	private String supplierskuno;//供应商Id
	private String orderNo;//520买赠订单号
	
    private String msg;//主站返回信息
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

	private String code;//返回状态码
    public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getGiftcardbalance() {
		return StringUtil.removePoint(giftcardbalance);
	}

	public void setGiftcardbalance(String giftcardbalance) {
		this.giftcardbalance = giftcardbalance;
	}

	public String getGiftcard() {
		return giftcard;
	}

	public void setGiftcard(String giftcard) {
		this.giftcard = giftcard;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public String getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getPostarea() {
		return postarea;
	}

	public void setPostarea(String postarea) {
		this.postarea = postarea;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getSupplierskuno() {
		return supplierskuno;
	}

	public void setSupplierskuno(String supplierskuno) {
		this.supplierskuno = supplierskuno;
	}


	

}
