package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: CategoryList 
* @Description: 商城首页类别列表调用主站返回的数据结构 
* @author qinyingchun
* @date 2014年10月22日
* @version 1.0 
*/
public class CategoryList implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryImage;
	private List<Category> categoryList;

	/**
	 * @return the categoryImage
	 */
	public String getCategoryImage() {
		return categoryImage;
	}

	/**
	 * @param categoryImage
	 *            the categoryImage to set
	 */
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	/**
	 * @return the categoryList
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            the categoryList to set
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
