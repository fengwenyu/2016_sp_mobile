package com.shangpin.pay.bo;

/**
 * 海外支付宝支付请求参数业务对象 User: sunweiwei Date: 15-03-11 Time: 下午3:23
 */
public class OutsideAlipayBo extends AlipayBo {

    private static final long serialVersionUID = 7498236195247658353L;

    /**
	 * 
	 */
    private String productDetail;

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public OutsideAlipayBo() {
        super();
    }

    /**
     * 用于app调起支付宝客户端的构造函数
     * 
     * @param productName
     * @param orderId
     * @param totalFee
     * @param productDetail
     * @param notifyURL
     */
    public OutsideAlipayBo(String productName, String orderId, String totalFee, String productDetail, String notifyUrl) {
        super();
        this.productName = productName;
        this.orderId = orderId;
        this.totalFee = totalFee;
        this.productDetail = productDetail;
        this.notifyUrl = notifyUrl;
    }

    /**
     * 用于wap支付调用的构造函数
     * 
     * @param productName
     * @param orderId
     * @param totalFee
     * @param productDetail
     * @param merchantURL
     * @param returnURL
     * @param notifyURL
     */
    public OutsideAlipayBo(String productName, String orderId, String totalFee, String merchantUrl, String callbackUrl, String notifyUrl) {
        super();
        this.productName = productName;
        this.orderId = orderId;
        this.totalFee = totalFee;
        this.merchantUrl = merchantUrl;
        this.callbackUrl = callbackUrl;
        this.notifyUrl = notifyUrl;
    }

}
