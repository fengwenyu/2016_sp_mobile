package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 物流
 * 
 * @author zghw
 *
 */
public class LogisticsInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 订单id */
    private String orderid;
    /** 物流单最新状态日期 */
    private String date;
    /** 金额 */
    private String amount;
    /** 订单物品数量 */
    private String totalcount;
    /** 订单对应的产品信息 */
    private List<LogisticsList> list;

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

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public List<LogisticsList> getList() {
        return list;
    }

    public void setList(List<LogisticsList> list) {
        this.list = list;
    }

}
