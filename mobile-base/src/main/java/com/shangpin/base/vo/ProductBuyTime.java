package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 定义指定时间购买物品实体
 * 
 * @author qinyingchun
 *
 */
public class ProductBuyTime implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String productId;
    private String time;

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductBuyTime [productId=" + productId + ", time=" + time + "]";
    }

}
