package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 
 * @author sunweiwei
 * 
 */
public class ListOfGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId; // 用户ID
    private String topicId; // 专题ID
    private String gender; // 性别：男（1）、女（0）
    private String pich; // 图片高度，参数固定格式：pich={h}
    private String picw; // 图片宽度，参数固定格式：picw={w}
    private String pageIndex; // 当前页数，如果不传，小于1或为空时，返回所有，并且忽略pagesize
    private String pageSize; // 每页显示条数
    private String filterSold; // 是否获取已售罄的商品，1为不获取；0、空或没传参数为获取

    private String picNos;// 图片文件编号，多个编号用逗号分割
    private String picType;// 图片类型(system，product，user)

    private String categoryId;// 品类ID
    private String brandId;// 品牌ID
    private String productId;// 商品ID
    private String chId;// 频道ID

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String status;
    /**
     * 礼品卡状态，0：全部；6：已激活（实物、电子）；7：已使用（实物、电子）； 8：已冻结（实物、电子）；9：已失效（实物、电子）
     * ；10：已作废（实物、电子）。
     */
    private String shopType;// 表示来自尚品1，奥莱2

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getChId() {
        return chId;
    }

    public void setChId(String chId) {
        this.chId = chId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicNos() {
        return picNos;
    }

    public void setPicNos(String picNos) {
        this.picNos = picNos;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPich() {
        return pich;
    }

    public void setPich(String pich) {
        this.pich = pich;
    }

    public String getPicw() {
        return picw;
    }

    public void setPicw(String picw) {
        this.picw = picw;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getFilterSold() {
        return filterSold;
    }

    public void setFilterSold(String filterSold) {
        this.filterSold = filterSold;
    }

}
