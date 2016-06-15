package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 商品传输数据对象，用于前台展示
 * 
 * @author cuibinqiang
 * 
 */
public class SPMerchandise  implements Serializable{
	private static final long serialVersionUID = 1L;
	// 商品id
    private String productid;
    // 商品名称
    private String productname;
    // 商品列表图片
    private String pic;
    // 库存数量
    private String count;
    // 商品价格列表；依次是正式、黄金、白金、钻石、市场
    private String[] prices;
    // 前端展示的价格索引从0开始
    private String priceindex;
    // 商品状态码
    private String status;
    // 品牌id
    private String brandid;
    // 品牌名称
    private String brandname;
    // 是否支持会员价格，支持会员价格为1，不支持为0
    private String issupportmember;
    // 特殊价格数组
    private String[] specialprice;

    private String[] pics;

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
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

    public String[] getSpecialprice() {
        return specialprice;
    }

    public void setSpecialprice(String[] specialprice) {
        this.specialprice = specialprice;
    }

}
