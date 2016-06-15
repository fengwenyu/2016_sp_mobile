package com.shangpin.biz.bo;

import java.io.Serializable;

public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String couponno;
    private String expirydate;
    private String rule;
    private String status;
    private String statuscode;
    private String type;
    private String discount;//优惠 金额
    private String expiredate;//截止日期
    private String usedate;
    private String orderid;
    
    //提交订单使用优惠码返回信息
    private String no;//优惠码
    private String name;//优惠码名称
    private String desc;//优惠码描述
    private String validitydate;//优惠码有效期
    private String amount;//购物袋商品总金额
    private String payamount;//购物袋商品需支付的金额(包括礼品卡)
    private String discountamount;//购物袋商品总优惠金额
    private String carriage;//运费
    private String isSelected;//优惠券是否被选中

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getValiditydate() {
		return validitydate;
	}

	public void setValiditydate(String validitydate) {
		this.validitydate = validitydate;
	}

	public String getPayamount() {
		return payamount;
	}

	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
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

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

}
