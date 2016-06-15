package com.shangpin.base.vo;

import java.io.Serializable;

public class Coupon  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String rule;
	private String couponno;//编号
	private String name;//使用人
	private String amount;//优惠券面值
	private String expirydate;//有效期	
	private String status;//状态
	private String statuscode;//使用次数
	private String type;
	private String orderid;
	private String usedate;
	private String haveMore;
	private String pageindex;
	
	 public String getHaveMore() {
        return haveMore;
    }
    public void setHaveMore(String haveMore) {
        this.haveMore = haveMore;
    }
    public String getPageindex() {
        return pageindex;
    }
    public void setPageindex(String pageindex) {
        this.pageindex = pageindex;
    }
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public String getUsedate() {
        return usedate;
    }
    public void setUsedate(String usedate) {
        this.usedate = usedate;
    }
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getCouponno() {
		return couponno;
	}
	public void setCouponno(String couponno) {
		this.couponno = couponno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	
	
}
