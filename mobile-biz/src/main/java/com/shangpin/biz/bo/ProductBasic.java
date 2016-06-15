package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductBasic
 * @Description:商品详情基本实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductBasic implements Serializable {

   
    /**
     * 
     */
    private static final long serialVersionUID = 812946510435627486L;
    
    
    private String productId;//商品编号
    private String productName;//商品名称
    private Brand brand;//品牌对象
    private String activityId;//活动编号
    private Collect collect;
    private String isPackage;//是否包邮 0不支持1支持
    private List<String> info;//详情基本信息
    private String recommend;//编辑推荐
    private String isAfterSale;//是否有售后保养 0没有 1有
    private String isSize;//是否有尺码 0没有 1有
    private String isBrandStyle;//是否有品牌风尚 0没有 1有
    private String commentCount;//评论数
    private String firstPropName;//第一属性名称
    private String secondPropName;//第二属性名称
    private String isSoldOut;//是否售罄 0 没有 1售罄
    private String[] allPics;// 全部要展示的图片
    private List<ProductFirstProps> firstProps;//sku第一属性对象
    private String prefix;//商品前缀
    private String suffix;//商品后缀
    private String advertWord;//广告语
    private String sales;//销量
    private String collections;//收藏数
    private List<AttributeInfo> attributeInfo;//属性对象
    private DefaultIndex defaultIndex;//默认选择sku索引
    
    
    /***  isShortSize 520活动临时所加参数，主站不传，自己封装 
     */
    private String isShortSize;//是否断码 0:没有断码 ，1:断码
    
    public String getIsShortSize() {
		return isShortSize;
	}
	public void setIsShortSize(String isShortSize) {
		this.isShortSize = isShortSize;
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
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public String getActivityId() {
        return activityId;
    }
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    public String getIsPackage() {
        return isPackage;
    }
    public void setIsPackage(String isPackage) {
        this.isPackage = isPackage;
    }
    
    public List<String> getInfo() {
		return info;
	}
	public void setInfo(List<String> info) {
		this.info = info;
	}
	public String getRecommend() {
        return recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    public String getFirstPropName() {
        return firstPropName;
    }
    public void setFirstPropName(String firstPropName) {
        this.firstPropName = firstPropName;
    }
    public String getSecondPropName() {
        return secondPropName;
    }
    public void setSecondPropName(String secondPropName) {
        this.secondPropName = secondPropName;
    }
    
    public List<ProductFirstProps> getFirstProps() {
		return firstProps;
	}
	public void setFirstProps(List<ProductFirstProps> firstProps) {
		this.firstProps = firstProps;
	}
    public Collect getCollect() {
        return collect;
    }
    public void setCollect(Collect collect) {
        this.collect = collect;
    }
    public String getIsAfterSale() {
        return isAfterSale;
    }
    public void setIsAfterSale(String isAfterSale) {
        this.isAfterSale = isAfterSale;
    }
    public String getIsSize() {
        return isSize;
    }
    public void setIsSize(String isSize) {
        this.isSize = isSize;
    }
    public String getIsBrandStyle() {
        return isBrandStyle;
    }
    public void setIsBrandStyle(String isBrandStyle) {
        this.isBrandStyle = isBrandStyle;
    }
    public String getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
    public String getIsSoldOut() {
        return isSoldOut;
    }
    public void setIsSoldOut(String isSoldOut) {
        this.isSoldOut = isSoldOut;
    }
    public String[] getAllPics() {
        return allPics;
    }
    public void setAllPics(String[] allPics) {
        this.allPics = allPics;
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
    public String getSales() {
        return sales;
    }
    public void setSales(String sales) {
        this.sales = sales;
    }
    public String getCollections() {
        return collections;
    }
    public void setCollections(String collections) {
        this.collections = collections;
    }
    public List<AttributeInfo> getAttributeInfo() {
        return attributeInfo;
    }
    public void setAttributeInfo(List<AttributeInfo> attributeInfo) {
        this.attributeInfo = attributeInfo;
    }
    public DefaultIndex getDefaultIndex() {
        return defaultIndex;
    }
    public void setDefaultIndex(DefaultIndex defaultIndex) {
        this.defaultIndex = defaultIndex;
    }
    public String getAdvertWord() {
        return advertWord;
    }
    public void setAdvertWord(String advertWord) {
        this.advertWord = advertWord;
    }
    
    
}