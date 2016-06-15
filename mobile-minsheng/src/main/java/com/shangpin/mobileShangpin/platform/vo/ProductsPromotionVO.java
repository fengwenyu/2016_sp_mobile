package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;

/**
 * 奥莱购物车列表信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class ProductsPromotionVO {

	private PromotionVO promotion;//促销信息
	private List<ProductCartVO> productCartVO;//购物车商品信息
	public PromotionVO getPromotion() {
		return promotion;
	}
	public void setPromotion(PromotionVO promotion) {
		this.promotion = promotion;
	}
	public List<ProductCartVO> getProductCartVO() {
		return productCartVO;
	}
	public void setProductCartVO(List<ProductCartVO> productCartVO) {
		this.productCartVO = productCartVO;
	}

	
	
	
}
