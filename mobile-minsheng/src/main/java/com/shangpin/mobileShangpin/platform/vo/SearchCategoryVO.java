package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 * @author Administrator
 *
 */
public class SearchCategoryVO {
	private String name;//分类名称
	private SearchCategoryItemVO cItemVO = new SearchCategoryItemVO();//查询条件
	private List<SearchCategoryItemVO> searchCategoryItemVO = new ArrayList<SearchCategoryItemVO>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SearchCategoryItemVO getcItemVO() {
		return cItemVO;
	}
	public void setcItemVO(SearchCategoryItemVO cItemVO) {
		this.cItemVO = cItemVO;
	}
	public List<SearchCategoryItemVO> getSearchCategoryItemVO() {
		return searchCategoryItemVO;
	}
	public void setSearchCategoryItemVO(
			List<SearchCategoryItemVO> searchCategoryItemVO) {
		this.searchCategoryItemVO = searchCategoryItemVO;
	}
	 

}
