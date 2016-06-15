package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author qinyingchun
 * 商品尺码信息的原始数据
 *
 */
public class SizeInfoII implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<String> productindex = new ArrayList<String>();
    private Modelcloth modelcloth;
    private ProductDetailSize productdetailsize;

    /**
     * @return the productindex
     */
    public List<String> getProductindex() {
        return productindex;
    }

    /**
     * @param productindex
     *            the productindex to set
     */
    public void setProductindex(List<String> productindex) {
        this.productindex = productindex;
    }

    /**
     * @return the modelcloth
     */
    public Modelcloth getModelcloth() {
        return modelcloth;
    }

    /**
     * @param modelcloth
     *            the modelcloth to set
     */
    public void setModelcloth(Modelcloth modelcloth) {
        this.modelcloth = modelcloth;
    }

    /**
     * @return the productdetailsize
     */
    public ProductDetailSize getProductdetailsize() {
        return productdetailsize;
    }

    /**
     * @param productdetailsize
     *            the productdetailsize to set
     */
    public void setProductdetailsize(ProductDetailSize productdetailsize) {
        this.productdetailsize = productdetailsize;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SizeInfoII [productindex=" + productindex + ", modelcloth=" + modelcloth + ", productdetailsize=" + productdetailsize + "]";
    }
}
