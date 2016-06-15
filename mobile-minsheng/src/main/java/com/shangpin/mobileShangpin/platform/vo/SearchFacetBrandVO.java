package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;



/**
 * 搜索品牌条件
 * 
 */
public class SearchFacetBrandVO {
	private String name;//条件名称
	private SearchBrandItemVO brandkey=new SearchBrandItemVO();//  品牌key类
	private List<SearchBrandItemVO> searchBrandItemVO=new ArrayList<SearchBrandItemVO>();//品牌搜索列表
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SearchBrandItemVO> getSearchBrandItemVO() {
		return searchBrandItemVO;
	}
	public void setSearchBrandItemVO(List<SearchBrandItemVO> searchBrandItemVO) {
		this.searchBrandItemVO = searchBrandItemVO;
	}
	public SearchBrandItemVO getBrandkey() {
		return brandkey;
	}
	public void setBrandkey(SearchBrandItemVO brandkey) {
		this.brandkey = brandkey;
	}
	

	
	
}
