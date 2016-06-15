package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 定义不能使用优惠券的商品
 * 
 * @author qinyingchun
 *
 */
public class ProductCoupon implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String brandName;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName
     *            the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductCoupon [id=" + id + ", brandName=" + brandName + "]";
    }
}
