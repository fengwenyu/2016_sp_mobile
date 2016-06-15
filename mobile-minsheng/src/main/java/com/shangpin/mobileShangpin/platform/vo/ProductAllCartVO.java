package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;



/**
 * 购物车商品信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class ProductAllCartVO{

    private String shopIds;
	private String totalPrice ;//总金额
	private String totalQuantity;//购买商品总数
    private String totalPromotionPrice;//总优惠金额
    private String practicalPrice;//优惠后总价格    
    private List<ProductsPromotionVO> productsPromotionVOList;
 
   
	
	
	
   
    
    public List<ProductsPromotionVO> getProductsPromotionVOList() {
        return productsPromotionVOList;
    }
    public void setProductsPromotionVOList(List<ProductsPromotionVO> productsPromotionVOList) {
        this.productsPromotionVOList = productsPromotionVOList;
    }
    public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getTotalPromotionPrice() {
		return totalPromotionPrice;
	}
	public void setTotalPromotionPrice(String totalPromotionPrice) {
		this.totalPromotionPrice = totalPromotionPrice;
	}
	public String getPracticalPrice() {
		return practicalPrice;
	}
	public void setPracticalPrice(String practicalPrice) {
		this.practicalPrice = practicalPrice;
	}
    public String getShopIds() {
        return shopIds;
    }
    public void setShopIds(String shopIds) {
        this.shopIds = shopIds;
    }
    
    
	
    
}
