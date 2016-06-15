package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: QuickResult
 * @Description: 立即购买提交订单返回的实体类
 * @author qinyingchun
 * @date 2014年11月20日
 * @version 1.0
 */
public class QuickResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
