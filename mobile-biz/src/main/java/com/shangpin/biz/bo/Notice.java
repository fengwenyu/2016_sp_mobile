package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codFlag;
    private String dispatching;//配送信息
    private String acceptance;//承诺信息
    private String salesPromotion;//促销信息
    private RefContent salesPromotionNew;//促销消息，现在走通用规则
	private List<Pay> payment;//支付方式支持类型
	private List<Tag> tag;//尺码和颜色集合
	private String fastDispatch;
 
    public String getFastDispatch() {
		return fastDispatch;
	}
	public void setFastDispatch(String fastDispatch) {
		this.fastDispatch = fastDispatch;
	}

	public String getCodFlag() {
		return codFlag;
	}
	public void setCodFlag(String codFlag) {
		this.codFlag = codFlag;
	}
    public String getSalesPromotion() {
        return salesPromotion;
    }
    public void setSalesPromotion(String salesPromotion) {
        this.salesPromotion = salesPromotion;
    }
	public String getDispatching() {
		return dispatching;
	}
	public void setDispatching(String dispatching) {
		this.dispatching = dispatching;
	}
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	public List<Pay> getPayment() {
		return payment;
	}
	public void setPayment(List<Pay> payment) {
		this.payment = payment;
	}
	public List<Tag> getTag() {
		return tag;
	}
	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}
	public RefContent getSalesPromotionNew() {
		return salesPromotionNew;
	}
	public void setSalesPromotionNew(RefContent salesPromotionNew) {
		this.salesPromotionNew = salesPromotionNew;
	}
	
}
