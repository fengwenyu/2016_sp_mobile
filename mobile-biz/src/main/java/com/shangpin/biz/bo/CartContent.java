package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CartContent
 * @Description: 购物车列表实体类
 * @author qinyingchun
 * @date 2014年11月6日
 * @version 1.0
 */
public class CartContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private String LastBuyUrl;
	private String TotalPrice;
	private String TotalQuantity;
	private String TotalPromotionPrice;
	private List<CartItems> SpList;
	private List<CartItems> AlList;

	public String getLastBuyUrl() {
		return LastBuyUrl;
	}

	public void setLastBuyUrl(String lastBuyUrl) {
		LastBuyUrl = lastBuyUrl;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getTotalQuantity() {
		return TotalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		TotalQuantity = totalQuantity;
	}

	public String getTotalPromotionPrice() {
		return TotalPromotionPrice;
	}

	public void setTotalPromotionPrice(String totalPromotionPrice) {
		TotalPromotionPrice = totalPromotionPrice;
	}

	public List<CartItems> getSpList() {
		return SpList;
	}

	public void setSpList(List<CartItems> spList) {
		SpList = spList;
	}

	public List<CartItems> getAlList() {
		return AlList;
	}

	public void setAlList(List<CartItems> alList) {
		AlList = alList;
	}

}
