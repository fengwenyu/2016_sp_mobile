package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class SizeInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ProductIndex> productindex;
	private ProductdetailSize productdetailsize;
	private String modelcloth;
	
	public List<ProductIndex> getProductindex() {
		return productindex;
	}
	public void setProductindex(List<ProductIndex> productindex) {
		this.productindex = productindex;
	}
	
	public ProductdetailSize getProductdetailsize() {
		return productdetailsize;
	}
	public void setProductdetailsize(ProductdetailSize productdetailsize) {
		this.productdetailsize = productdetailsize;
	}
	public String getModelcloth() {
		return modelcloth;
	}
	public void setModelcloth(String modelcloth) {
		this.modelcloth = modelcloth;
	}
	

}
