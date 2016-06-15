package com.shangpin.biz.bo.orderUnion;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author liushaoqing
 * @version 1.0
 *
 * 国内海外合并 结算接口消息返回
 * 产品包装实体封装
 * wiki:http://wiki.sp.cn/pages/viewpage.action?pageId=5279841
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductWraper  implements Serializable {

    private String title;
    private String buyId;
    private List<Product> list;
    private ProductLabel carriage;
    private ProductLabel coupon;
    private ProductLabel discount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public ProductLabel getCarriage() {
        return carriage;
    }

    public void setCarriage(ProductLabel carriage) {
        this.carriage = carriage;
    }

    public ProductLabel getCoupon() {
        return coupon;
    }

    public void setCoupon(ProductLabel coupon) {
        this.coupon = coupon;
    }

    public ProductLabel getDiscount() {
        return discount;
    }

    public void setDiscount(ProductLabel discount) {
        this.discount = discount;
    }
}
