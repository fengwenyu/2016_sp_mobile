package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * @ClassName: SearchConditions
 * @Description:商品列表页查询条件
 * @author qinyingchun
 * @date 2014年10月30日
 * @version 1.0
 */
public class SearchConditions implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tagId;
    private String brandNo;
    private String brandName;
    private String categoryNo;
    private String categoryName;
    private String price;
    private String size;
    private String color;
    private String colorName;
    private String order;
    private String gender;
    private String start;
    private String num;
    private String userLv;
    private int pageNo;
    private String keyword;
    private String topicId;
    private String postArea;// 商品发货地

    public String getPostArea() {
//        // 如果区域为空，默认取全部
//        if (!StringUtil.isNotEmpty(postArea)) {
//            this.postArea = "0";
//        }
        return postArea;
    }

    public void setPostArea(String postArea) {
        this.postArea = postArea;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserLv() {
        return userLv;
    }

    public void setUserLv(String userLv) {
        this.userLv = userLv;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPrice() {
        if (price != null && !"".equals(price) && price.indexOf("以上") > -1) {
            price = price.substring(0, price.indexOf("以上")) + "~";
        }
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

}
