package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 主站搜索结果对象
 * 
 * @author wangfeng
 * 
 */
public class SearchResultApp implements Serializable {
	private static final long serialVersionUID = 1L;
	private String labelId;
	private String count; // 结果总数
	private List<SearchHotWords> conditionList;
	private List<SearchAttribute> attributes;
	private List<SearchAttribute> filtrates;
	private List<Product> productList;
	private SearchCorrect correct;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<SearchHotWords> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<SearchHotWords> conditionList) {
		this.conditionList = conditionList;
	}

	public List<SearchAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SearchAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<SearchAttribute> getFiltrates() {
		return filtrates;
	}

	public void setFiltrates(List<SearchAttribute> filtrates) {
		this.filtrates = filtrates;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

    public SearchCorrect getCorrect() {
        return correct;
    }

    public void setCorrect(SearchCorrect correct) {
        this.correct = correct;
    }

	
}
