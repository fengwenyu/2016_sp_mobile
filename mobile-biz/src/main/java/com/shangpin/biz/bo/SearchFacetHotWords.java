package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: SearchHotWords
* @Description:搜索热词实体类 
* @author qinyingchun
* @date 2014年10月28日
* @version 1.0 
*/
public class SearchFacetHotWords implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;//名称
	private List<SearchHotWords> searchHotWords;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<SearchHotWords> getSearchHotWords() {
        return searchHotWords;
    }
    public void setSearchHotWords(List<SearchHotWords> searchHotWords) {
        this.searchHotWords = searchHotWords;
    }
   
	

}
