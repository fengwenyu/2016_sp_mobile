package com.shangpin.biz.bo;

import java.io.Serializable;

public class UserBuyInfo implements Serializable {
    private static final long serialVersionUID = -5586691257733348370L;
    private String cartgoodscount;
    private String waitpaycount;
    private String preparegoodscount;
    private String delivergoodscount;
    private String logisticsstatus;
    private String curlevel;
    private String curleveldesc;
    private String curleveldiscount;
    private String nextlevel;
    private String nextleveldesc;
    private String nextleveldiscount;
    private String cardcount;

    public String getCartgoodscount() {
        return cartgoodscount;
    }

    public void setCartgoodscount(String cartgoodscount) {
        this.cartgoodscount = cartgoodscount;
    }

    public String getWaitpaycount() {
        return waitpaycount;
    }

    public void setWaitpaycount(String waitpaycount) {
        this.waitpaycount = waitpaycount;
    }

    public String getPreparegoodscount() {
        return preparegoodscount;
    }

    public void setPreparegoodscount(String preparegoodscount) {
        this.preparegoodscount = preparegoodscount;
    }

    public String getDelivergoodscount() {
        return delivergoodscount;
    }

    public void setDelivergoodscount(String delivergoodscount) {
        this.delivergoodscount = delivergoodscount;
    }

    public String getLogisticsstatus() {
        return logisticsstatus;
    }

    public void setLogisticsstatus(String logisticsstatus) {
        this.logisticsstatus = logisticsstatus;
    }

    public String getCurlevel() {
        return curlevel;
    }

    public void setCurlevel(String curlevel) {
        this.curlevel = curlevel;
    }

    public String getCurleveldesc() {
        return curleveldesc;
    }

    public void setCurleveldesc(String curleveldesc) {
        this.curleveldesc = curleveldesc;
    }

    public String getCurleveldiscount() {
        return curleveldiscount;
    }

    public void setCurleveldiscount(String curleveldiscount) {
        this.curleveldiscount = curleveldiscount;
    }

    public String getNextlevel() {
        return nextlevel;
    }

    public void setNextlevel(String nextlevel) {
        this.nextlevel = nextlevel;
    }

    public String getNextleveldesc() {
        return nextleveldesc;
    }

    public void setNextleveldesc(String nextleveldesc) {
        this.nextleveldesc = nextleveldesc;
    }

    public String getNextleveldiscount() {
        return nextleveldiscount;
    }

    public void setNextleveldiscount(String nextleveldiscount) {
        this.nextleveldiscount = nextleveldiscount;
    }

    public String getCardcount() {
        return cardcount;
    }

    public void setCardcount(String cardcount) {
        this.cardcount = cardcount;
    }

}
