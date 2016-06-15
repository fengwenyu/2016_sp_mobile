package com.shangpin.web.vo;

/**
 * 前端页面支付方式封装
 * @author liushaoqing
 */
public class Payment extends com.shangpin.biz.bo.orderUnion.Payment {

    private String clazz;//添加各个支付方式的样式名

    private String url;

    private String way;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
