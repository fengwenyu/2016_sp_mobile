package com.shangpin.biz.bo.submitOrder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * <br/>
 * Date: 2016/4/26<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PayInfo implements Serializable {

    private String date;
    private String mainpaymode;
    private String subpaymode ;
    private String orderid;
    private String orderIdNew;
    private String mainOrderId;
    private String payarg1;
    private String payarg2;
    private String expiretime;
    private String payarg3;
    private String amount;
    private String prompt;
    private String data;
    private Wechat wechat;
    private String status;
    private String statusdesc;
    private String cancancel;
    private String canpay;
    private String canconfirm;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMainpaymode() {
        return mainpaymode;
    }

    public void setMainpaymode(String mainpaymode) {
        this.mainpaymode = mainpaymode;
    }

    public String getSubpaymode() {
        return subpaymode;
    }

    public void setSubpaymode(String subpaymode) {
        this.subpaymode = subpaymode;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderIdNew() {
        return orderIdNew;
    }

    public void setOrderIdNew(String orderIdNew) {
        this.orderIdNew = orderIdNew;
    }

    public String getMainOrderId() {
        return mainOrderId;
    }

    public void setMainOrderId(String mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    public String getPayarg1() {
        return payarg1;
    }

    public void setPayarg1(String payarg1) {
        this.payarg1 = payarg1;
    }

    public String getPayarg2() {
        return payarg2;
    }

    public void setPayarg2(String payarg2) {
        this.payarg2 = payarg2;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
    }

    public String getPayarg3() {
        return payarg3;
    }

    public void setPayarg3(String payarg3) {
        this.payarg3 = payarg3;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Wechat getWechat() {
        return wechat;
    }

    public void setWechat(Wechat wechat) {
        this.wechat = wechat;
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
}
