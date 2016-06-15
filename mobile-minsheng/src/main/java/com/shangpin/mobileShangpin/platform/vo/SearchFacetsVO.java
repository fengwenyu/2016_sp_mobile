package com.shangpin.mobileShangpin.platform.vo;

/**
 * 搜索条件
 * 
 */
public class SearchFacetsVO {
	private SearchFacetBrandVO searchFacetBrandVO;// 品牌
	private SearchFacetColorsVO searchFacetColorsVO;// 颜色
	private SearchFacetSizeVO searchFacetSizeVO;// 尺码
	private SearchFacetPriceVO searchPriceVO;
	private SearchCategoryVO searchClv2VO;// 商品二级分类
	private SearchCategoryVO searchClv3VO;// 商品三级分类
	private SearchCategoryVO searchClv4VO;// 商品四级分类
	private SearchCategoryVO manCategoryVO;  //男士分类
	private SearchCategoryVO womanCategoryVO; //女士分类
	public SearchFacetBrandVO getSearchFacetBrandVO() {
		return searchFacetBrandVO;
	}
	public void setSearchFacetBrandVO(SearchFacetBrandVO searchFacetBrandVO) {
		this.searchFacetBrandVO = searchFacetBrandVO;
	}
	public SearchFacetColorsVO getSearchFacetColorsVO() {
		return searchFacetColorsVO;
	}
	public void setSearchFacetColorsVO(SearchFacetColorsVO searchFacetColorsVO) {
		this.searchFacetColorsVO = searchFacetColorsVO;
	}
	public SearchFacetSizeVO getSearchFacetSizeVO() {
		return searchFacetSizeVO;
	}
	public void setSearchFacetSizeVO(SearchFacetSizeVO searchFacetSizeVO) {
		this.searchFacetSizeVO = searchFacetSizeVO;
	}
	public SearchCategoryVO getSearchClv2VO() {
		return searchClv2VO;
	}
	public void setSearchClv2VO(SearchCategoryVO searchClv2VO) {
		this.searchClv2VO = searchClv2VO;
	}
	public SearchCategoryVO getSearchClv3VO() {
		return searchClv3VO;
	}
	public void setSearchClv3VO(SearchCategoryVO searchClv3VO) {
		this.searchClv3VO = searchClv3VO;
	}
	public SearchCategoryVO getSearchClv4VO() {
		return searchClv4VO;
	}
	public void setSearchClv4VO(SearchCategoryVO searchClv4VO) {
		this.searchClv4VO = searchClv4VO;
	}
	public SearchCategoryVO getManCategoryVO() {
		return manCategoryVO;
	}
	public void setManCategoryVO(SearchCategoryVO manCategoryVO) {
		this.manCategoryVO = manCategoryVO;
	}
	public SearchCategoryVO getWomanCategoryVO() {
		return womanCategoryVO;
	}
	public void setWomanCategoryVO(SearchCategoryVO womanCategoryVO) {
		this.womanCategoryVO = womanCategoryVO;
	}
	public SearchFacetPriceVO getSearchPriceVO() {
		return searchPriceVO;
	}
	public void setSearchPriceVO(SearchFacetPriceVO searchPriceVO) {
		this.searchPriceVO = searchPriceVO;
	}

}
