package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 物流信息
 * 
 * @author zhanghongwei
 *
 */
public class Logistics  implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 订单ID */
    private String orderId;
    /** 物流单号 */
    private String ticketno;
    /** 物流单状态 */
    private String status;
    /** 物流单状态描述 */
    private String statusdesc;
    /** 物流公司名称 */
    private String express;
    /** 物流单最新状态日期 */
    private String date;
    /** 最新信息内容 */
    private String desc;
    /** 物流地址 */
    private String address;
    /** 物流数量 */
    private String totalcount;
    
    private List<Logistics> logistics;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Logistics> getLogistics() {
        return logistics;
    }

    public void setLogistics(List<Logistics> logistics) {
        this.logistics = logistics;
    }

}
