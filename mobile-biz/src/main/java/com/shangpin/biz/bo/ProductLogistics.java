package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: ProductLogistics 
* @Description: 新版物流中的商品
* @author 李灵
* @date 2014年11月29日 
* @version 1.0 
*/
public class ProductLogistics implements Serializable{

	private static final long serialVersionUID = 1L;
	private String sku;
	private String brand;
	private String productId;
	private String productName;
	private String count;
	private String price;
	private String amount;
	private String pic;
	private List<LogisticSkuProp> prop;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public List<LogisticSkuProp> getProp() {
		return prop;
	}
	public void setProp(List<LogisticSkuProp> prop) {
		this.prop = prop;
	}
	
}
