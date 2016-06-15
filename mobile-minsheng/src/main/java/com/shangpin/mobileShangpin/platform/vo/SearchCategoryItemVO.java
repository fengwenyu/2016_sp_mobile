package com.shangpin.mobileShangpin.platform.vo;

/**
 * 商品分类
 * @author Administrator
 *
 */
public class SearchCategoryItemVO {
	private String code;//分类编号
	private String name;//分类名称
	private String parentCode;//父分类编号
	private String status;//分类有效状态
	private String sortType;//分类的排序
	private String count;//条数
	 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	public boolean parseItemData(String count, String itemData){
		String[] itemarr=itemData.split("\\|");
		this.setCode(itemarr[0]);
		this.setName(itemarr[1]);
		this.setParentCode(itemarr[2]);
		this.setStatus(itemarr[3]);
		this.setSortType(itemarr[4]);
		this.setCount(count);
		if(itemarr[3] != null && itemarr[3].equals("1")){
			return true;
		}else{
			return false;
		}
	}
}
