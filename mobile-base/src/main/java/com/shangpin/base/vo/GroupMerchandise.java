package com.shangpin.base.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分组产品
 * 
 * @author zghw
 *
 */
public class GroupMerchandise   implements Serializable{
	private static final long serialVersionUID = 1L;
	private String groupname; // 分组名称
    private String grouporder; // 分组排序
    private String grouplogo; // 分组图片地址
    private String groupurl; // 分组图片指向的跳转链接
    private List<Merchandise> products; // 商品集

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGrouporder() {
        return grouporder;
    }

    public void setGrouporder(String grouporder) {
        this.grouporder = grouporder;
    }

    public String getGrouplogo() {
        return grouplogo;
    }

    public void setGrouplogo(String grouplogo) {
        this.grouplogo = grouplogo;
    }

    public String getGroupurl() {
        return groupurl;
    }

    public void setGroupurl(String groupurl) {
        this.groupurl = groupurl;
    }

    public List<Merchandise> getProducts() {
        return products;
    }

    public void setProducts(List<Merchandise> products) {
        this.products = products;
    }
}
