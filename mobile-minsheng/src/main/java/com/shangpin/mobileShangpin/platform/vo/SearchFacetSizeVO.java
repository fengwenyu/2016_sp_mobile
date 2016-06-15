package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索尺码条件
 * 
 */
public class SearchFacetSizeVO {
	private String name;//条件名称
	private  SearchSizeItemVO sizeItemVO = new SearchSizeItemVO();
	private List<SearchSizeItemVO> searchSizeItemVO=new ArrayList<SearchSizeItemVO>();//价格搜索列表
	public SearchSizeItemVO getSizeItemVO() {
		return sizeItemVO;
	}
	public void setSizeItemVO(SearchSizeItemVO sizeItemVO) {
		this.sizeItemVO = sizeItemVO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchSizeItemVO> getSearchSizeItemVO() {
		return searchSizeItemVO;
	}
	public void setSearchSizeItemVO(List<SearchSizeItemVO> searchSizeItemVO) {
		this.searchSizeItemVO = searchSizeItemVO;
	}
	

	

	
	
}
