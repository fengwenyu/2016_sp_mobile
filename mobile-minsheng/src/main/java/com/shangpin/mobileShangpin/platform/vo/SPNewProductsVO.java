package com.shangpin.mobileShangpin.platform.vo;

public class SPNewProductsVO {
	/**
	 * 返回错误码，0为返回成功
	 * 
	 */
	private String code;
	/**
	 * 错误信息
	 * 
	 */
	private String msg;
	/**
	 * 商品ID
	 * 
	 */
	private String productid;
	/**
	 * 商品名称
	 * 
	 */
	private String productname;
	/**
	 * 商品图片URL链接
	 * 
	 */
	private String pic;
	/**
	 * 库存数量
	 * 
	 */
	private String count;
	/**
	 * 商品价格列表，依次是正式、黄金、白金、砖石、市场
	 * 
	 */
	private String prices;
	/**
	 * 前端暂时的价格索引从0开始
	 * 
	 */
	private String priceindex;
	/**
	 * 是否支持会员价格，支持会员价格为1，不支持为0
	 * 
	 */
	private String issupportmember;
	/**
	 * 商品状态码,共6位，依次为普通、新品、特价、促销、预售、预留（次位暂时为0）， 只能单选，即只能有一位是1； 按位存储，0表示否，1表示是； 如‘100000’表示普通，‘010000’表示新品，‘000100’表示促销，‘000010’表示预售 类型为：普通、新品时，前台显示尚品价格； 类型为促销时，前台显示尚品、市场价格
	 * 
	 */
	private String status;
	/**
	 * 品牌名称
	 * 
	 */
	private String brandname;
	/**
	 * 品牌id
	 * 
	 */
	private String brandid;
	/**
	 * 特殊价格数组，0位为促销价格， 当商品状态码为000100促销时，显示此数组中0位的价格；
	 * 
	 */
	private String specialprice;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getPrices() {
		return prices;
	}
	public void setPrices(String prices) {
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
	public String getSpecialprice() {
		return specialprice;
	}
	public void setSpecialprice(String specialprice) {
		this.specialprice = specialprice;
	}
	
	
	
}
