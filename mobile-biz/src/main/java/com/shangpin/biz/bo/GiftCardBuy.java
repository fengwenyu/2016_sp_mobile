package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 礼品卡立即购买
 *
 * @author zghw
 */
public class GiftCardBuy implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String buyId;
	private String totalAmount;
	private String payAmount;
	private String carriageAmount;
	private String lastInvoiceID;
	private String lastInvoiceFlag;
	private String lastInvoiceTitle;
	private String lastInvoiceContent;
	private String lastReceiveId;
	private String lastDeliveryType;
	private List<Receive> receive;
	private List<Receive> invoice;
	private PayType lastPayType;
	private Carriage carriage;
	private List<Pay> payment;
	private List<GiftCardBuyProduct> list;
	private List<DeliveryVo> delivery;
	private List<PriceShowVo> priceShow;
	private String totalSettlement;
	private String isInvoiceAddress;//是否显示发票地址，0不显示，显示
	private String invoiceDesc;//开发票的描述
	
	public String getIsInvoiceAddress() {
		return isInvoiceAddress;
	}

	public void setIsInvoiceAddress(String isInvoiceAddress) {
		this.isInvoiceAddress = isInvoiceAddress;
	}

	public String getInvoiceDesc() {
		return invoiceDesc;
	}

	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public String getLastInvoiceID() {
		return lastInvoiceID;
	}

	public void setLastInvoiceID(String lastInvoiceID) {
		this.lastInvoiceID = lastInvoiceID;
	}

	public String getLastInvoiceFlag() {
		return lastInvoiceFlag;
	}

	public void setLastInvoiceFlag(String lastInvoiceFlag) {
		this.lastInvoiceFlag = lastInvoiceFlag;
	}

	public String getLastInvoiceTitle() {
		return lastInvoiceTitle;
	}

	public void setLastInvoiceTitle(String lastInvoiceTitle) {
		this.lastInvoiceTitle = lastInvoiceTitle;
	}

	public String getLastInvoiceContent() {
		return lastInvoiceContent;
	}

	public void setLastInvoiceContent(String lastInvoiceContent) {
		this.lastInvoiceContent = lastInvoiceContent;
	}

	public String getLastReceiveId() {
		return lastReceiveId;
	}

	public void setLastReceiveId(String lastReceiveId) {
		this.lastReceiveId = lastReceiveId;
	}

	public String getLastDeliveryType() {
		return lastDeliveryType;
	}

	public void setLastDeliveryType(String lastDeliveryType) {
		this.lastDeliveryType = lastDeliveryType;
	}

	public List<Receive> getReceive() {
		return receive;
	}

	public void setReceive(List<Receive> receive) {
		this.receive = receive;
	}

	public List<Receive> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Receive> invoice) {
		this.invoice = invoice;
	}

	public PayType getLastPayType() {
		return lastPayType;
	}

	public void setLastPayType(PayType lastPayType) {
		this.lastPayType = lastPayType;
	}

	public Carriage getCarriage() {
		return carriage;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	public List<Pay> getPayment() {
		return payment;
	}

	public void setPayment(List<Pay> payment) {
		this.payment = payment;
	}

	public List<GiftCardBuyProduct> getList() {
		return list;
	}

	public void setList(List<GiftCardBuyProduct> list) {
		this.list = list;
	}

	public List<DeliveryVo> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<DeliveryVo> delivery) {
		this.delivery = delivery;
	}

	public List<PriceShowVo> getPriceShow() {
		return priceShow;
	}

	public void setPriceShow(List<PriceShowVo> priceShow) {
		this.priceShow = priceShow;
	}

	public String getCarriageAmount() {
		return carriageAmount;
	}

	public void setCarriageAmount(String carriageAmount) {
		this.carriageAmount = carriageAmount;
	}

    public String getTotalSettlement() {
        return totalSettlement;
    }

    public void setTotalSettlement(String totalSettlement) {
        this.totalSettlement = totalSettlement;
    }

}
