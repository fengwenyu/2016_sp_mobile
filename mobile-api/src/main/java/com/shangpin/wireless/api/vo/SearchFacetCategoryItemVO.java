package com.shangpin.wireless.api.vo;

/**
 * 商品分类
 * @author Administrator
 *
 */
public class SearchFacetCategoryItemVO {
	private String categoryNo;//分类编号
	private String categoryName;//分类名称
	private String parentNo;//父分类编号
	private String status;//分类有效状态
	private String sortType;//分类的排序
	private String count;//条数
	
	public boolean  parse (String attrstr, String text) {
		String[] itemarr = attrstr.split("\\|");
		setCategoryNo(itemarr[0]);
		setCategoryName(itemarr[1]);
		setParentNo(itemarr[2]);
		setStatus(itemarr[3]);
		setSortType(itemarr[4]);
		setCount(text);
		if(itemarr[3] != null && itemarr[3].equals("1")){
			return true;
		}else{
			return false;
		}
	}
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
