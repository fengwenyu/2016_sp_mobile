package com.shangpin.web.vo;

import com.shangpin.utils.StringUtil;

/**
 * @author liushaoqing
 * 礼品卡购买新接口实体
 */
public class GiftCardSubmit  {

    String userid	;//用户ID	String	必须
    String type;	//礼品卡类型	String	必须	1.电子卡 2为实物卡
    String addrid	;//收货地址	String	非必须	如果是电子卡可不传 实物卡需要传
    String invoiceflag;//	是否开发票	String	必须	0不开发票 1开发票
    String invoiceaddrid;//k	发票包含的收货地址	String	非必须	依赖开发票
    String invoicetype	;//发票类型 0：个人 1公司	String	非必须	依赖开发票
    String invoicetitle	;//发票抬头	String	非必须	依赖开发票
    String invoicecontent	;//发票内容	String	非必须	依赖开发票
    String invoiceEmail;//
    String invoiceTel;//
    String invoiceStyle; //String	可选	1电子发票，2纸制发票
    String express;//	配送方式	String	非必须	如果是电子卡可不传 实物卡需要传 1：工作日收货；2：工作日，节假日收货；3：双休，节假日收货
    String orderfrom;//	订单来源	String	必须	9：ios；18：安卓；19：M站订单渠道
    String buysIds	;//购物车ID	String	必须
    String paytypeid	;//主支付方式编号	String	必须
    String paytypechildid	;//子支付方式编号	String	必须
    String ordertype;//	订单来源站点	String	必须	表示订单来自尚品1，奥莱2
    String chanelNo	;//第三方频道号	String	非必须
    String chanelId	;//第三方频道id	String	非必

    /**
     * 参数后端校验
     * @return
     */
    public boolean validate(){

        boolean validate = false;
        if(StringUtil.isNotEmpty(userid,type,invoiceflag,invoiceStyle,orderfrom,buysIds,paytypeid,paytypechildid,ordertype)){

            //如果是开发票
            if(invoiceflag.equals("1")){

                //如果是实体礼品卡
                if(type.equals("2")){
                    if(StringUtil.isNotEmpty(addrid,express)){
                        if(StringUtil.isNotEmpty(invoicetype,invoicetitle,invoicecontent,invoiceTel,invoiceaddrid)){
                            validate = true;
                        }
                    }
                }else{
                    if(StringUtil.isNotEmpty(invoicetype,invoicetitle,invoicecontent,invoiceTel)){
                        validate = true;
                    }
                }

            }else{
                //如果是实体礼品卡
                if(type.equals("2")){
                    if(StringUtil.isNotEmpty(addrid,express)){
                        validate = true;
                    }
                }else{
                    validate = true;
                }
            }
        }
        return validate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddrid() {
        return addrid;
    }

    public void setAddrid(String addrid) {
        this.addrid = addrid;
    }

    public String getInvoiceflag() {
        return invoiceflag;
    }

    public void setInvoiceflag(String invoiceflag) {
        this.invoiceflag = invoiceflag;
    }

    public String getInvoiceaddrid() {
        return invoiceaddrid;
    }

    public void setInvoiceaddrid(String invoiceaddrid) {
        this.invoiceaddrid = invoiceaddrid;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    public String getInvoicecontent() {
        return invoicecontent;
    }

    public void setInvoicecontent(String invoicecontent) {
        this.invoicecontent = invoicecontent;
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

    public String getInvoiceStyle() {
        return invoiceStyle;
    }

    public void setInvoiceStyle(String invoiceStyle) {
        this.invoiceStyle = invoiceStyle;
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

    public String getBuysIds() {
        return buysIds;
    }

    public void setBuysIds(String buysIds) {
        this.buysIds = buysIds;
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

    public String getChanelNo() {
        return chanelNo;
    }

    public void setChanelNo(String chanelNo) {
        this.chanelNo = chanelNo;
    }

    public String getChanelId() {
        return chanelId;
    }

    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }
}
