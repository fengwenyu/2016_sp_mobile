package com.shangpin.biz.bo;

import java.io.Serializable;


public class ReturnInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String returnId;//退货申请号
	private String returnReason;//退货原因
	private String timestamp;//退货申请时间
	private String returnAmount;//退款金额
	private String returnType;//退货方式
	private String returnAddress;//退货地址
	private String zipCode;//邮编
	private String receiver;//收货人
	//Add 2016/03/28
    private String returnGiftCards;//￥50礼品卡
	private String returnCoupon;//100减50优惠劵
	private String invoiceAddress;//发票寄回地址
	private String invoiceCode;//发票寄回的邮编
	private String logisticsCpy;//物理公司
	private String logisticsNo;//物流单号
    private String receiverPhone; //收货人电话
    /**
     * @return the receiverPhone
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }
    /**
     * @param receiverPhone the receiverPhone to set
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

    /**
     * @return the returnGiftCards
     */
    public String getReturnGiftCards() {
        return returnGiftCards;
    }
    /**
     * @param returnGiftCards the returnGiftCards to set
     */
    public void setReturnGiftCards(String returnGiftCards) {
        this.returnGiftCards = returnGiftCards;
    }
    /**
     * @return the returnCoupon
     */
    public String getReturnCoupon() {
        return returnCoupon;
    }
    /**
     * @param returnCoupon the returnCoupon to set
     */
    public void setReturnCoupon(String returnCoupon) {
        this.returnCoupon = returnCoupon;
    }
    /**
     * @return the invoiceAddress
     */
    public String getInvoiceAddress() {
        return invoiceAddress;
    }
    /**
     * @param invoiceAddress the invoiceAddress to set
     */
    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }
    /**
     * @return the invoiceCode
     */
    public String getInvoiceCode() {
        return invoiceCode;
    }
    /**
     * @param invoiceCode the invoiceCode to set
     */
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
    /**
     * @return the logisticsCpy
     */
    public String getLogisticsCpy() {
        return logisticsCpy;
    }
    /**
     * @param logisticsCpy the logisticsCpy to set
     */
    public void setLogisticsCpy(String logisticsCpy) {
        this.logisticsCpy = logisticsCpy;
    }
    /**
     * @return the logisticsNo
     */
    public String getLogisticsNo() {
        return logisticsNo;
    }
    /**
     * @param logisticsNo the logisticsNo to set
     */
    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }
}
