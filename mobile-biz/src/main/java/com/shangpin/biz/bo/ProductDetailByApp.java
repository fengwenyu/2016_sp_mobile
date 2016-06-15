package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductDetailByApp
 * @Description:客户端商品详情实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductDetailByApp implements Serializable {

	private static final long serialVersionUID = -1038664650191088665L;

	private ProductDetail product;
	private List<Product> recommend;
	private List<ProductBiz> biz;

	public ProductDetail getProduct() {
		return product;
	}

	public void setProduct(ProductDetail product) {
		this.product = product;
	}

	public List<ProductBiz> getBiz() {
		return biz;
	}

	public void setBiz(List<ProductBiz> biz) {
		this.biz = biz;
	}

	public List<Product> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<Product> recommend) {
		this.recommend = recommend;
	}
}
