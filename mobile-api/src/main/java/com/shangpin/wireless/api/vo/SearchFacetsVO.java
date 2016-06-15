package com.shangpin.wireless.api.vo;




/**
 * 搜索条件
 * 
 */
public class SearchFacetsVO {
	private SearchFacetBrandVO searchFacetBrandVO;// 品牌
	private SearchFacetColorVO searchFacetColorVO;// 颜色
	private SearchFacetSizeVO searchFacetSizeVO;// 尺码
	private SearchFacetPriceVO searchFacetPriceVO;// 价格
	private SearchFacetCategoryVO searchFacetCategoryVO;// 商品直属分类
	private SearchFacetCategoryVO searchFacetCategoryL2VO;// 商品所属2级分类
	private SearchFacetCategoryVO searchFacetCategoryL3VO;// 商品所属3级分类
	private SearchFacetCategoryVO searchFacetCategoryL4VO;// 商品所属4级分类
	private SearchFacetCategoryVO searchFacetNavigationVO;// 商品所属导航


	public SearchFacetCategoryVO getSearchFacetCategoryVO() {
		return searchFacetCategoryVO;
	}

	public void setSearchFacetCategoryVO(SearchFacetCategoryVO searchFacetCategoryVO) {
		this.searchFacetCategoryVO = searchFacetCategoryVO;
	}

	public SearchFacetCategoryVO getSearchFacetCategoryL2VO() {
		return searchFacetCategoryL2VO;
	}

	public void setSearchFacetCategoryL2VO(SearchFacetCategoryVO searchFacetCategoryL2VO) {
		this.searchFacetCategoryL2VO = searchFacetCategoryL2VO;
	}

	public SearchFacetCategoryVO getSearchFacetCategoryL3VO() {
		return searchFacetCategoryL3VO;
	}

	public void setSearchFacetCategoryL3VO(SearchFacetCategoryVO searchFacetCategoryL3VO) {
		this.searchFacetCategoryL3VO = searchFacetCategoryL3VO;
	}

	public SearchFacetCategoryVO getSearchFacetCategoryL4VO() {
		return searchFacetCategoryL4VO;
	}

	public void setSearchFacetCategoryL4VO(SearchFacetCategoryVO searchFacetCategoryL4VO) {
		this.searchFacetCategoryL4VO = searchFacetCategoryL4VO;
	}

	public SearchFacetCategoryVO getSearchFacetNavigationVO() {
		return searchFacetNavigationVO;
	}

	public void setSearchFacetNavigationVO(SearchFacetCategoryVO searchFacetNavigationVO) {
		this.searchFacetNavigationVO = searchFacetNavigationVO;
	}

	public SearchFacetBrandVO getSearchFacetBrandVO() {
		return searchFacetBrandVO;
	}

	public void setSearchFacetBrandVO(SearchFacetBrandVO searchFacetBrandVO) {
		this.searchFacetBrandVO = searchFacetBrandVO;
	}

	public SearchFacetColorVO getSearchFacetColorVO() {
		return searchFacetColorVO;
	}

	public void setSearchFacetColorVO(SearchFacetColorVO searchFacetColorVO) {
		this.searchFacetColorVO = searchFacetColorVO;
	}

	public SearchFacetSizeVO getSearchFacetSizeVO() {
		return searchFacetSizeVO;
	}

	public void setSearchFacetSizeVO(SearchFacetSizeVO searchFacetSizeVO) {
		this.searchFacetSizeVO = searchFacetSizeVO;
	}

	public SearchFacetPriceVO getSearchFacetPriceVO() {
		return searchFacetPriceVO;
	}

	public void setSearchFacetPriceVO(SearchFacetPriceVO searchFacetPriceVO) {
		this.searchFacetPriceVO = searchFacetPriceVO;
	}

}
