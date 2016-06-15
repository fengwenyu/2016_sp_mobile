package com.shangpin.mobileShangpin.platform.vo;


/**
 * 优惠券传输数据对象，用于前台展示
 */
public class CouponVO {
	/*** 优惠券编号 */
	private String couponno;
	
	/*** 优惠券名称 */
	private String name;
	private String desc;
	/*** 优惠券面值 */
	private String amount;
	/*** 优惠券有效期 */
	private String expiredate;
	/*** 优惠券使用规则 */
	private String rule;
	/*** 优惠券 状态码，0未使用；1已使用； 3已过期；-1全部 */
	private String statuscode;
	/*** 优惠券 状态 */
	private String status;
	/*** 优惠券 使用日期 */
	private String usedate;
	/*** 订单id */
	private String orderid;

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

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsedate() {
		return usedate;
	}

	public void setUsedate(String usedate) {
		this.usedate = usedate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
	
}
