package com.shangpin.base.vo;

import java.io.Serializable;

public class Product  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String postArea;//商品来源 1国内 2海外

	private String productId;

	private String productName;

	private String marketPrice;// 市场价

	private String limitedPrice;// 普通会员价

	private String goldPrice;// 黄金会员价

	private String platinumPrice;// 白金会员价

	private String diamondPrice;// 钻石会员价
	
	private String promotionPrice;// 促销价

	private String productPicFile;// 商品图片编号

	private String productModelPicFile;// 人模图片编号

	private String productModelPicUrl;// 人模图片路径

	private String brandNo;// 品牌编号

	private String brandNameEN;// 品牌中文名

	private String brandNameCN;// 品牌英文名

	private String isSupportDiscount;// 是否支持会员权益0，否；1，是

	private String count;// 有效库存数

	private String promotionDesc;

	private String pic;// 商品图片路径

	private String name;
	
	private String type;
	
	private String refContent;
	
	private String isModelPic;// 是否是模特图
	
	private String erpCategoryNo;//erp分类
	
	private String status;
	
	private String limitedVipPrice;
	
	private int strongPrice;
	
	private int delPrice;
	private String picNo;//显示的图片编号
	private String prefix;// 商品前缀
	private String suffix;// 商品后缀
	private String promotionNotice;// 商品促销语
	
	public int getStrongPrice() {
		return strongPrice;
	}

	public void setStrongPrice(int strongPrice) {
		this.strongPrice = strongPrice;
	}

	public int getDelPrice() {
		return delPrice;
	}

	public void setDelPrice(int delPrice) {
		this.delPrice = delPrice;
	}

	public String getIsModelPic() {
		return isModelPic;
	}

	public void setIsModelPic(String isModelPic) {
		this.isModelPic = isModelPic;
	}

	public String getErpCategoryNo() {
		return erpCategoryNo;
	}

	public void setErpCategoryNo(String erpCategoryNo) {
		this.erpCategoryNo = erpCategoryNo;
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

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		if (marketPrice != null && marketPrice.indexOf(".") > -1) {
			this.marketPrice = marketPrice.substring(0, marketPrice.indexOf("."));
		} else {
			this.marketPrice = marketPrice;
		}
	}

	public String getLimitedPrice() {
		return limitedPrice;
	}

	public void setLimitedPrice(String limitedPrice) {
		if (limitedPrice != null && limitedPrice.indexOf(".") > -1) {
			this.limitedPrice = limitedPrice.substring(0, limitedPrice.indexOf("."));
		} else {
			this.limitedPrice = limitedPrice;
		}
	}

	public String getGoldPrice() {
		return goldPrice;
	}

	public void setGoldPrice(String goldPrice) {
		if (goldPrice != null && goldPrice.indexOf(".") > -1) {
			this.goldPrice = goldPrice.substring(0, goldPrice.indexOf("."));
		} else {
			this.goldPrice = goldPrice;
		}
	}
	public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        if (promotionPrice != null && promotionPrice.indexOf(".") > -1) {
            this.promotionPrice = promotionPrice.substring(0, promotionPrice.indexOf("."));
        } else {
            this.promotionPrice = promotionPrice;
        }
    }
	public String getPlatinumPrice() {
		return platinumPrice;
	}

	public void setPlatinumPrice(String platinumPrice) {
		if (platinumPrice != null && platinumPrice.indexOf(".") > -1) {
			this.platinumPrice = platinumPrice.substring(0, platinumPrice.indexOf("."));
		} else {
			this.platinumPrice = platinumPrice;
		}
	}

	public String getDiamondPrice() {
		return diamondPrice;
	}

	public void setDiamondPrice(String diamondPrice) {
		if (diamondPrice != null && diamondPrice.indexOf(".") > -1) {
			this.diamondPrice = diamondPrice.substring(0, diamondPrice.indexOf("."));
		} else {
			this.diamondPrice = diamondPrice;
		}
	}

	public String getProductPicFile() {
		return productPicFile;
	}

	public void setProductPicFile(String productPicFile) {
		this.productPicFile = productPicFile;
	}

	public String getProductModelPicFile() {
		return productModelPicFile;
	}

	public void setProductModelPicFile(String productModelPicFile) {
		this.productModelPicFile = productModelPicFile;
	}

	public String getProductModelPicUrl() {
		return productModelPicUrl;
	}

	public void setProductModelPicUrl(String productModelPicUrl) {
		this.productModelPicUrl = productModelPicUrl;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getBrandNameEN() {
		return brandNameEN;
	}

	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}

	public String getBrandNameCN() {
		return brandNameCN;
	}

	public void setBrandNameCN(String brandNameCN) {
		this.brandNameCN = brandNameCN;
	}

	public String getIsSupportDiscount() {
		return isSupportDiscount;
	}

	public void setIsSupportDiscount(String isSupportDiscount) {
		this.isSupportDiscount = isSupportDiscount;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefContent() {
		return refContent;
	}

	public void setRefContent(String refContent) {
		this.refContent = refContent;
	}

	public String getStatus() {
		//status = "100000";//这个状态以后不用，所以默认设置为普通，兼容老版本客户端
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLimitedVipPrice() {
		return limitedVipPrice;
	}

	public void setLimitedVipPrice(String limitedVipPrice) {
		if (limitedVipPrice != null && limitedVipPrice.indexOf(".") > -1) {
			this.limitedVipPrice = limitedVipPrice.substring(0, limitedVipPrice.indexOf("."));
		} else {
			this.limitedVipPrice = limitedVipPrice;
		}
	}

    public String getPostArea() {
        return postArea;
    }

    public void setPostArea(String postArea) {
        this.postArea = postArea;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPromotionNotice() {
		return promotionNotice;
	}

	public void setPromotionNotice(String promotionNotice) {
		this.promotionNotice = promotionNotice;
	}
	
	
	
}
