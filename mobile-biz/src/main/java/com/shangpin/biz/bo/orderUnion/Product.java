package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.Property;

import java.io.Serializable;
import java.util.List;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 产品实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product  implements Serializable {

    private String id;
    private String name;
    private String sku;
    private String price;
    private String brandNameCN;
    private String brandNameEN;
    private String pic;
    private String quantity;
    private String priceTitle;
    private String priceDesc;
    private String countryPic;
    private String countryDesc;
    private List<Property> attribute;
    private Carriage carriage;

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceTitle() {
        return priceTitle;
    }

    public void setPriceTitle(String priceTitle) {
        this.priceTitle = priceTitle;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }

    public String getCountryPic() {
        return countryPic;
    }

    public void setCountryPic(String countryPic) {
        this.countryPic = countryPic;
    }

    public List<Property> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Property> attribute) {
        this.attribute = attribute;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }
}
