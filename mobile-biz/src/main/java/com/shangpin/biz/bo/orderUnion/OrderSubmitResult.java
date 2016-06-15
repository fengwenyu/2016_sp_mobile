package com.shangpin.biz.bo.orderUnion;

/**
 * @author liushaoqing
 * @version 1.0
 * 结算的时候主站返回参数封装
 *
 * systime : 221213233系统时间
 * expiretime : 3233223233223有效时间
 * orderid : 20141112087962
 * date : 2014/11/12 12:02:16
 * amount : 5750
 * cod : 0
 * codmsg : 订单中的商品不支持货到付款
 * skucounts : 132
 * objectcounts : 132
 * giftcardbalance : 860.00
 * giftcard : 0
 * carriage : 0
 * postarea : 1国内2海外
 * skuandcount : aaa|2
 * supplierno : 供应商id
 * orderno : 订单号
 * internalAmount : 分帐时支付到国内的金额
 * payCategory : 1
 */
public class OrderSubmitResult {

    private String systime;
    private String expiretime;
    private String orderid;
    private String date;
    private String amount;
    private String cod;
    private String codmsg;
    private String skucounts;
    private String objectcounts;
    private String giftcardbalance;
    private String giftcard;
    private String carriage;
    private String postarea;
    private String skuandcount;
    private String supplierno;
    private String orderno;
    private String internalAmount;
    private String payCategory;

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
        return giftcardbalance;
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

    public String getPostarea() {
        return postarea;
    }

    public void setPostarea(String postarea) {
        this.postarea = postarea;
    }

    public String getSkuandcount() {
        return skuandcount;
    }

    public void setSkuandcount(String skuandcount) {
        this.skuandcount = skuandcount;
    }

    public String getSupplierno() {
        return supplierno;
    }

    public void setSupplierno(String supplierno) {
        this.supplierno = supplierno;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getInternalAmount() {
        return internalAmount;
    }

    public void setInternalAmount(String internalAmount) {
        this.internalAmount = internalAmount;
    }

    public String getPayCategory() {
        return payCategory;
    }

    public void setPayCategory(String payCategory) {
        this.payCategory = payCategory;
    }
}
