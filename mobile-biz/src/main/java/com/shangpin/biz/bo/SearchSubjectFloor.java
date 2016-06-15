package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 活动列表页楼层数据展示结果
 * 
 * @author fenwenyu
 * 
 */
public class SearchSubjectFloor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String title;//楼层title
	private String pic;//楼层titled的pic
	private List<ProductSreach> searchList;//搜索商品列表
	private List<Product> productList;//展示的商品列表
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ProductSreach> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<ProductSreach> searchList) {
		this.searchList = searchList;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
}
