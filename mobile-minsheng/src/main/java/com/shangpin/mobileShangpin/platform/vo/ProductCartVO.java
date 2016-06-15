package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;



/**
 * 购物车商品信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class ProductCartVO{

	 //购物车详情Id
	private String shopDetailId ;
    //商品名称
	private String productName ;
    //购买价格
	private String price ;
    //优惠后价格
	private String promotionPrice ;
    //收藏价格
	private String favoritePrice;
    //购买数量
	private String quantity ;
	 //品牌编号
	private String brandNo ;
    //品牌名称
	private String brandName ;
    //商品Url
	private String productUrl;
    //图片路径
	private String img;
    //商品编号
	private String productNo ;
    //Sku编号
	private String skuNo;
    //活动号
	private String categoryNo ;
	 //奥莱活动子类编号
	private String alCategoryNo ;
    //购买时间
	private String dateTime ;
    //颜色尺码属性值
	private String colorSize ;
    //颜色尺码属性值
	private List<ProductPropVO> skuAttrText ;
	//颜色尺码属性值(字符串便于获取快照)
	private String skuAttrTextStr;
    //奥莱分类编号（奥莱不为空，尚品一般为空）
    //public String aolaiCategoryNo { get; set; }
    //商品所属站点（1、尚品 2、奥莱）
	private String islimitedOutlet ;
    //报错类型（1:售罄 2：剩余量 3：活动过期 4:商品已下架）
	private String msgType ;
    //报错信息（1、商品已售罄3、活动已过期，4、商品已下架）
	private String msg ;
	private String totalQuantityAmount;//购物车单个商品总额
	private String stock;//库存
	public String getShopDetailId() {
		return shopDetailId;
	}
	public void setShopDetailId(String shopDetailId) {
		this.shopDetailId = shopDetailId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getFavoritePrice() {
		return favoritePrice;
	}
	public void setFavoritePrice(String favoritePrice) {
		this.favoritePrice = favoritePrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getColorSize() {
		return colorSize;
	}
	public void setColorSize(String colorSize) {
		this.colorSize = colorSize;
	}

	public String getIslimitedOutlet() {
		return islimitedOutlet;
	}
	public void setIslimitedOutlet(String islimitedOutlet) {
		this.islimitedOutlet = islimitedOutlet;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ProductPropVO> getSkuAttrText() {
		return skuAttrText;
	}
	public void setSkuAttrText(List<ProductPropVO> skuAttrText) {
		this.skuAttrText = skuAttrText;
	}
	public String getSkuAttrTextStr() {
		return skuAttrTextStr;
	}
	public void setSkuAttrTextStr(String skuAttrTextStr) {
		this.skuAttrTextStr = skuAttrTextStr;
	}
	public String getAlCategoryNo() {
		return alCategoryNo;
	}
	public void setAlCategoryNo(String alCategoryNo) {
		this.alCategoryNo = alCategoryNo;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getTotalQuantityAmount() {
		return totalQuantityAmount;
	}
	public void setTotalQuantityAmount(String totalQuantityAmount) {
		this.totalQuantityAmount = totalQuantityAmount;
	}
    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
	

	
    
}
