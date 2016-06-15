package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ProductsBizTopic implements Serializable{
	private static final long serialVersionUID = 1L;
	private String pic;
	private String count;
	private String productid;
	private String productname;
	private List<String> prices;
	private String priceindex;
	private String issupportmember;
	private String status;
	private String brandname;
	private String brandid;
	private List<String> specialprice;
	private List<String> pics;
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public List<String> getPrices() {
		return prices;
	}
	public void setPrices(List<String> prices) {
		this.prices = prices;
	}
	public String getPriceindex() {
		return priceindex;
	}
	public void setPriceindex(String priceindex) {
		this.priceindex = priceindex;
	}
	public String getIssupportmember() {
		return issupportmember;
	}
	public void setIssupportmember(String issupportmember) {
		this.issupportmember = issupportmember;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public List<String> getSpecialprice() {
		return specialprice;
	}
	public void setSpecialprice(List<String> specialprice) {
		this.specialprice = specialprice;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}

}
