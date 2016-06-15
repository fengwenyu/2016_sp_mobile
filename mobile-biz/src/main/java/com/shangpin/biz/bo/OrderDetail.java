package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 订单详细信息
 * @author Administrator
 *
 */
public class OrderDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	/*** 可变属性名称 */
	private String firstpropname = "颜色";
	/*** 可变属性名称 */
	private String secondpropname = "尺码";
	private String firstpropvalue;
	private String secondpropvalue;
	private String amount;
	private String count;
	private String brand;
	private String pic;
	private String productname;
	/**单件商品购买总价*/
	private Integer totalAmout;
	/**商品sku*/
	private String sku;
	private String productno;
	/**1电子卡2实物卡*/
	private String type;
	
	//订单详情 v8.4
	private String giftType;            
	private String id;            
	private String name;         
	private String brandNameCN;         
	private String brandNameEN;                
	private String countryPic;          
	private List<Attribute> attribute;           
	private String price;               
	private String priceTitle;          
	private String quantity;            
	private String quantityTitle;
	private String postArea;
	private String isClick;
	private String goodFees;
	private String transportFee;
	private String duty;
	public String getFirstpropname() {
		return firstpropname;
	}
	public void setFirstpropname(String firstpropname) {
		this.firstpropname = firstpropname;
	}
	public String getSecondpropname() {
		return secondpropname;
	}
	public void setSecondpropname(String secondpropname) {
		this.secondpropname = secondpropname;
	}
	public void setTotalAmout(Integer totalAmout) {
		this.totalAmout = totalAmout;
	}
	public Integer getTotalAmout() {
		if(StringUtils.isEmpty(getAmount()) || StringUtils.isEmpty(getCount())){
			return 0;
		}
		totalAmout = Integer.parseInt(this.getAmount())*Integer.parseInt(this.getCount());
		return totalAmout;
	}
	public String getFirstpropvalue() {
		return firstpropvalue;
	}
	public void setFirstpropvalue(String firstpropvalue) {
		this.firstpropvalue = firstpropvalue;
	}
	public String getSecondpropvalue() {
		return secondpropvalue;
	}
	public void setSecondpropvalue(String secondpropvalue) {
		this.secondpropvalue = secondpropvalue;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandNameCN() {
		return brandNameCN;
	}
	public void setBrandNameCN(String brandNameCN) {
		this.brandNameCN = brandNameCN;
	}
	public String getBrandNameEN() {
		return brandNameEN;
	}
	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}
	public String getCountryPic() {
		return countryPic;
	}
	public void setCountryPic(String countryPic) {
		this.countryPic = countryPic;
	}
	public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceTitle() {
		return priceTitle;
	}
	public void setPriceTitle(String priceTitle) {
		this.priceTitle = priceTitle;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getQuantityTitle() {
		return quantityTitle;
	}
	public void setQuantityTitle(String quantityTitle) {
		this.quantityTitle = quantityTitle;
	}
	public String getPostArea() {
		return postArea;
	}
	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}
	public String getIsClick() {
		return isClick;
	}
	public void setIsClick(String isClick) {
		this.isClick = isClick;
	}
	public String getGoodFees() {
		return goodFees;
	}
	public void setGoodFees(String goodFees) {
		this.goodFees = goodFees;
	}
	public String getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(String transportFee) {
		this.transportFee = transportFee;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
}
