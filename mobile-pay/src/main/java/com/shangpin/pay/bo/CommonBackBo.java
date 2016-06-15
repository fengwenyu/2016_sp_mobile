package com.shangpin.pay.bo;

import java.io.Serializable;

/**
 * 支付请求返回业务对象 User: liling Date: 14-11-10 Time: 下午3:23
 */
public class CommonBackBo implements Serializable {
    private static final long serialVersionUID = -1274078732568602121L;

    /** 验签是否成功 */
    private boolean verifySign;
    /** 订单号 */
    private String orderId;
    /** 订单金额 */
    private String totalFee;
    /** 交易流水号 */ 
    private String tradeNo;
    

    public boolean isVerifySign() {
        return verifySign;
    }
    
    public boolean getVerifySign(){
        return verifySign;
    }

    public void setVerifySign(boolean verifySign) {
        this.verifySign = verifySign;
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

    public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public CommonBackBo() {
        super();
    }

	public CommonBackBo(boolean verifySign, String orderId, String totalFee,
			String tradeNo) {
		super();
		this.verifySign = verifySign;
		this.orderId = orderId;
		this.totalFee = totalFee;
		this.tradeNo = tradeNo;
	}
	public CommonBackBo(boolean verifySign, String orderId, String totalFee) {
		super();
		this.verifySign = verifySign;
		this.orderId = orderId;
		this.totalFee = totalFee;
	}
}
