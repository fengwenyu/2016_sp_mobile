package com.shangpin.pay.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 支付宝支付请求参数业务对象 User: liling Date: 14-11-10 Time: 下午3:23
 */
public class AlipayBo implements Serializable {

    private static final long serialVersionUID = 7377541473094842750L;

    /** 商品名称,类型精度String(256)(必填,代码中默认) */
    protected String productName;

    /** 订单号，类型精度String(64)。(必填) */
    protected String orderId;

    /** 用户购买或或者服务的的价格（必须为金额格式，单位元,必填) */
    protected String totalFee;

    /** 交易关闭时间（非必填）买家如未能在该设定时间内支付成功，交易将被关闭，单位'分钟'，默认值21600（15天），最终关闭时间误差1-2分钟 */
    protected String orderTimeout;

    /** 买家在商户系统中的唯一标识（非必填） */
    protected String userId;

    /** callback同步通知url（必填） */
    protected String callbackUrl;

    /** notify异步通知url（必填） */
    protected String notifyUrl;

    /** 用户在支付宝页面可返回商户的链接 */
    protected String merchantUrl;

    protected String gateWay;

    protected String internalAmount;

    protected List<PayOrderDetailBo> payOrderDetailBo;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getOrderTimeout() {
        return orderTimeout;
    }

    public void setOrderTimeout(String orderTimeout) {
        this.orderTimeout = orderTimeout;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }

    public void setMerchantUrl(String merchantUrl) {
        this.merchantUrl = merchantUrl;
    }

	public String getGateWay() {
		return gateWay;
	}

	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}

    public List<PayOrderDetailBo> getPayOrderDetailBo() {
        return payOrderDetailBo;
    }

    public void setPayOrderDetailBo(List<PayOrderDetailBo> payOrderDetailBo) {
        this.payOrderDetailBo = payOrderDetailBo;
    }

    public String getInternalAmount() {
        return internalAmount;
    }

    public void setInternalAmount(String internalAmount) {
        this.internalAmount = internalAmount;
    }
}
