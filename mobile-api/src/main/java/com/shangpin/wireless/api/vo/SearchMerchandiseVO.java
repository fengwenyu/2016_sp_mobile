package com.shangpin.wireless.api.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果对象
 * 
 */
public class SearchMerchandiseVO {
	private String sid;//搜索ID
	private String status;//搜索请求状态
	private String discription;//描述
	private String keyword;//关键字
	private String total;//结果总数
	private String start;//分页起始
	private String end;//分页末页
	private String qtime;//分页耗时
	private SearchFacetsVO searchFacetVO = new SearchFacetsVO();//搜索条目对象
	private List<SearchProductVO> searchProductVO=new ArrayList<SearchProductVO>();//搜索商品对象
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getQtime() {
		return qtime;
	}
	public void setQtime(String qtime) {
		this.qtime = qtime;
	}
	public SearchFacetsVO getSearchFacetVO() {
		return searchFacetVO;
	}
	public void setSearchFacetVO(SearchFacetsVO searchFaceVO) {
		this.searchFacetVO = searchFaceVO;
	}
	public List<SearchProductVO> getSearchProductVO() {
		return searchProductVO;
	}
	public void setSearchProductVO(List<SearchProductVO> searchProductVO) {
		this.searchProductVO = searchProductVO;
	}
	
	
}
