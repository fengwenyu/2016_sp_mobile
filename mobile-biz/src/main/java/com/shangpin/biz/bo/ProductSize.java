package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;


/**
 * @ClassName: ProductSize
 * @Description:商品尺码实体类
 * @author liling
 * @date 2015年3月16日
 * @version 1.0
 */
public class ProductSize implements Serializable{
	private static final long serialVersionUID = 2363502386829878146L;
	private List<ProductSizeProperty> property;
	private ProductDetailSizes productDetailSize;
	private String modelClothPic;
	private String sizeContent;
    public List<ProductSizeProperty> getProperty() {
		return property;
	}
	public void setProperty(List<ProductSizeProperty> property) {
		this.property = property;
	}
	
	public ProductDetailSizes getProductDetailSize() {
		return productDetailSize;
	}
	public void setProductDetailSize(ProductDetailSizes productDetailSize) {
		this.productDetailSize = productDetailSize;
	}
	public String getModelClothPic() {
		return modelClothPic;
	}
	public void setModelClothPic(String modelClothPic) {
		this.modelClothPic = modelClothPic;
	}
	/**
     * @return the sizeContent
     */
    public String getSizeContent() {
        return sizeContent;
    }
    /**
     * @param sizeContent the sizeContent to set
     */
    public void setSizeContent(String sizeContent) {
        this.sizeContent = sizeContent;
    }
	
}
