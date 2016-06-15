package com.shangpin.biz.bo.submitOrder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * <br/>
 * Date: 2016/4/26<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SubmitOrderResult implements Serializable {

    private String type;   //1为实物卡2为电子卡",
    private String rechargePasswd;   //23424324243sdfsdfsdwe23",
    private String pic;   //http: //pic14.shangpin.com/e/s/14/08/20/20140820191723534713-10-10.jpg",
    private String payamount;   //4000",
    private String carriage;   //20",
    private String cod;   //是否支持货到付款",
    private String codmsg;   //不支持问题描述",
    private String giftcard;   //1使用礼品卡支付，0不适用礼品卡支付",

    private SelectedPayment selectedpayment;
    private List<Payment> payments;
    private GiftcartInfo giftcartinfo;
    private PayInfo payinfo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRechargePasswd() {
        return rechargePasswd;
    }

    public void setRechargePasswd(String rechargePasswd) {
        this.rechargePasswd = rechargePasswd;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPayamount() {
        return payamount;
    }

    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
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

    public String getGiftcard() {
        return giftcard;
    }

    public void setGiftcard(String giftcard) {
        this.giftcard = giftcard;
    }

    public SelectedPayment getSelectedpayment() {
        return selectedpayment;
    }

    public void setSelectedpayment(SelectedPayment selectedpayment) {
        this.selectedpayment = selectedpayment;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public GiftcartInfo getGiftcartinfo() {
        return giftcartinfo;
    }

    public void setGiftcartinfo(GiftcartInfo giftcartinfo) {
        this.giftcartinfo = giftcartinfo;
    }

    public PayInfo getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(PayInfo payinfo) {
        this.payinfo = payinfo;
    }
}
