package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索颜色条件
 * 
 */
public class SearchFacetColorsVO {
	private String name;//条件名称
	private SearchColorsItemVO colorsItemVO = new SearchColorsItemVO();//搜索条件
	private List<SearchColorsItemVO> searchColorsItemVO=new ArrayList<SearchColorsItemVO>();//颜色搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SearchColorsItemVO getColorsItemVO() {
		return colorsItemVO;
	}
	public void setColorsItemVO(SearchColorsItemVO colorsItemVO) {
		this.colorsItemVO = colorsItemVO;
	}
	public List<SearchColorsItemVO> getSearchColorsItemVO() {
		return searchColorsItemVO;
	}
	public void setSearchColorsItemVO(List<SearchColorsItemVO> searchColorsItemVO) {
		this.searchColorsItemVO = searchColorsItemVO;
	}
 
	
	

	
	
}
