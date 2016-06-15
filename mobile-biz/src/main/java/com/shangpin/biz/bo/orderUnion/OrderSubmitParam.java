package com.shangpin.biz.bo.orderUnion;

import com.shangpin.base.vo.SubmitOrderParam;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.IDCard;

import java.io.Serializable;

/**
 * @author liushaoqing
 * @version 1.0
 *  结算的时候参数封装
 */
public class OrderSubmitParam implements Serializable {

    private String userid;
    private String receivedId;	;//收货地址id
    private String invoiceFlag;//	是否开发票	String	必须	0：不开发票，1：
    private String invoiceType	;//发票类型	String	必须	0：个人，1：公司
    private String invoiceTitle;	//发票名称	String	可选
    private String invoiceContent;//发票内容	String	可选
    private String invoiceEmail;//发票邮箱	String	可选
    private String invoiceTel;//发票手机号	String	可选

    private String domesticCouponflag;//国内使用优惠券类型	int	必须	无：传空；1：优惠券；2：优惠码
    private String domesticCoupon;//国内优惠券或折扣码编号	String	必须	没有传”0“
    private String abroadCouponflag;	//国外使用优惠券类型	int	必须	无：传空；1：优惠券；2：优惠码
    private String abroadCoupon;//	国外优惠券或折扣码编号	String	必须	没有传”3“
    private String express;//	配送发送	String	必须	:1：工作日收货；2：工作日，节假日收货；3：双休，节假日收货
    private String orderfrom	;//订单来源	String	必须	9：ios；18：安卓；19：M站订单渠道
    private String buyId	;//购物车商品id	String	必须	多个用“|‘分开
    private String paytypeid	;//主支付方式编号	String	必须
    private String paytypechildid	;//子支付方式编号	String	必须
    private String ordertype	;//订单类型	String	必须	尚品1
    private String isUseGiftCardPay;//	是否使用礼品卡	String	必须	0：为不使用；1：使用
    private String orderSource;//	从何处来到提交订单	String	必须1：从购物车2：立即购买
    private String deliveryId;//配送方式编号	String	必须

    private String channelNo;//	第三方频道号	String	非必须
    private String channelId;//	第三方频道id	String	非必须k
    private String param; //网盟的参数
    private String channelType; //网盟的参数


    /**
     * 参数校验
     * @param orderSubmitParam
     * @return
     */
    public boolean validate(){
        boolean notEmpty = StringUtil.isNotEmpty(userid, receivedId, invoiceFlag, domesticCouponflag, domesticCoupon,
                abroadCoupon, abroadCouponflag, express, orderfrom, buyId, paytypeid, ordertype, isUseGiftCardPay,
                orderSource, deliveryId);
        boolean result = false;
        if(notEmpty){
            //发票校验
            if(invoiceFlag.equals("1")){
               if(StringUtil.isNotEmpty(invoiceType,invoiceTitle,invoiceContent)){
                   result = true;
               }
            }else{
                result = true;
            }
        }
        return result;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(String receivedId) {
        this.receivedId = receivedId;
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    public void setInvoiceEmail(String invoiceEmail) {
        this.invoiceEmail = invoiceEmail;
    }

    public String getInvoiceTel() {
        return invoiceTel;
    }

    public void setInvoiceTel(String invoiceTel) {
        this.invoiceTel = invoiceTel;
    }

    public String getDomesticCouponflag() {
        return domesticCouponflag;
    }

    public void setDomesticCouponflag(String domesticCouponflag) {
        this.domesticCouponflag = domesticCouponflag;
    }

    public String getDomesticCoupon() {
        return domesticCoupon;
    }

    public void setDomesticCoupon(String domesticCoupon) {
        this.domesticCoupon = domesticCoupon;
    }

    public String getAbroadCouponflag() {
        return abroadCouponflag;
    }

    public void setAbroadCouponflag(String abroadCouponflag) {
        this.abroadCouponflag = abroadCouponflag;
    }

    public String getAbroadCoupon() {
        return abroadCoupon;
    }

    public void setAbroadCoupon(String abroadCoupon) {
        this.abroadCoupon = abroadCoupon;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getOrderfrom() {
        return orderfrom;
    }

    public void setOrderfrom(String orderfrom) {
        this.orderfrom = orderfrom;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
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

    public String getIsUseGiftCardPay() {
        return isUseGiftCardPay;
    }

    public void setIsUseGiftCardPay(String isUseGiftCardPay) {
        this.isUseGiftCardPay = isUseGiftCardPay;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
