package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.PriceShowVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SettlementUnion implements Serializable{

    private AddressWrapper received;
    private ProductWraper domesticProduct;//国内
    private ProductWraper abroadProduct;
    private InvoiceWrapper invoice;
    private GiftCardWrapper giftCard;
    private List<Payment> payments;
    private List<PriceShowVo> priceShow;
    private String buyId;
    private String totalSettlement;//结算数量
    private String totalAmount;// 总金额
    private String prompt;//页面提示

    private String isCod;//   是否支持货到付款。0:否，1是。
    private String payCategory;//  "1:国内，2：国外，3：支付宝分帐

    public static final String ISCOD_TRUE ="1" ;//支持货到付款
    public static final String ISCOD_FALSE = "0" ;//支持货到付款

    public static final String DOMESTIC_PAY ="1";//国内支付
    public static final String ABOROAD_PAY = "2";//国外支付
    public static final String ALIPART_PAY = "3";//阿里分账


    public AddressWrapper getReceived() {
        return received;
    }

    public void setReceived(AddressWrapper received) {
        this.received = received;
    }

    public ProductWraper getDomesticProduct() {
        return domesticProduct;
    }

    public void setDomesticProduct(ProductWraper domesticProduct) {
        this.domesticProduct = domesticProduct;
    }

    public ProductWraper getAbroadProduct() {
        return abroadProduct;
    }

    public void setAbroadProduct(ProductWraper abroadProduct) {
        this.abroadProduct = abroadProduct;
    }

    public InvoiceWrapper getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceWrapper invoice) {
        this.invoice = invoice;
    }

    public GiftCardWrapper getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(GiftCardWrapper giftCard) {
        this.giftCard = giftCard;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<PriceShowVo> getPriceShow() {
        return priceShow;
    }

    public void setPriceShow(List<PriceShowVo> priceShow) {
        this.priceShow = priceShow;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getTotalSettlement() {
        return totalSettlement;
    }

    public void setTotalSettlement(String totalSettlement) {
        this.totalSettlement = totalSettlement;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getIsCod() {
        return isCod;
    }

    public void setIsCod(String isCod) {
        this.isCod = isCod;
    }

    public String getPayCategory() {
        return payCategory;
    }

    public void setPayCategory(String payCategory) {
        this.payCategory = payCategory;
    }
}
