package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 
 * 商品传输数据对象，用于前台展示
 * 
 * @author zhanghongwei
 *
 */
public class Merchandise  implements Serializable{
	private static final long serialVersionUID = 1L;
	// -------------------商品列表---------------------
    /*** 商品id */
    private String productid;
    /*** 商品名称 */
    private String productname;
    /*** 商品列表图片 */
    private String pic;
    /*** 库存数量 或者购买数量 */
    private String count;
    /*** 商品价格列表；依次是正式、黄金、白金、钻石、市场 */
    private String[] prices;
    /*** 前端展示的价格索引从0开始 */
    private String priceindex;
    private String status;
    /*** 品牌id */
    private String brandid;
    /*** 品牌名称 */
    private String brandname;
    /*** 是否支持会员价格，支持会员价格为1，不支持为0 */
    private String issupportmember;
    /*** 特殊价格 */
    private Object[] specialprice;
    private String[] pics;
    // -------------------商品购物袋列表---------------------
    /*** 购物袋中skuid */
    private String sku;
    /*** 品牌 */
    private String brand;
    /*** 品类 */
    private String cate;
    /*** 可变属性名称 */
    private String firstpropname;
    /*** 可变属性名称对应的值 */
    private String firstpropvalue;
    /*** 可变属性名称 */
    private String secondpropname;
    /*** 可变属性名称对应的值 */
    private String secondpropvalue;
    /*** 购物袋中价格 */
    private String amount;

    private String exchange;
    /** 组合商品的组id，非组合商品此属性赋值为空字符串 */
    private String groupid;
    /** 组合商品的序号,同组的商品序号相同，如：1、2、3等，非组合商品此属性赋值为空字符串 */
    private String groupno;
    /** 组合商品总价格 */
    private String groupprice;

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

    public String[] getPrices() {
        return prices;
    }

    public void setPrices(String[] prices) {
        this.prices = prices;
    }

    public String getPriceindex() {
        return priceindex;
    }

    public void setPriceindex(String priceindex) {
        this.priceindex = priceindex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getIssupportmember() {
        return issupportmember;
    }

    public void setIssupportmember(String issupportmember) {
        this.issupportmember = issupportmember;
    }

    public Object[] getSpecialprice() {
        return specialprice;
    }

    public void setSpecialprice(Object[] specialprice) {
        this.specialprice = specialprice;
    }

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

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getFirstpropname() {
        return firstpropname;
    }

    public void setFirstpropname(String firstpropname) {
        this.firstpropname = firstpropname;
    }

    public String getFirstpropvalue() {
        return firstpropvalue;
    }

    public void setFirstpropvalue(String firstpropvalue) {
        this.firstpropvalue = firstpropvalue;
    }

    public String getSecondpropname() {
        return secondpropname;
    }

    public void setSecondpropname(String secondpropname) {
        this.secondpropname = secondpropname;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno;
    }

    public String getGroupprice() {
        return groupprice;
    }

    public void setGroupprice(String groupprice) {
        this.groupprice = groupprice;
    }

    /**
     * @return the pics
     */
    public String[] getPics() {
        return pics;
    }

    /**
     * @param pics the pics to set
     */
    public void setPics(String[] pics) {
        this.pics = pics;
    }
}
