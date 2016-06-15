package com.shangpin.base.vo;

import java.util.List;

/**
 * 内容vo
 * 
 * @author zhanghongwei
 *
 * @param <T>
 * 不建议使用具体内容看@see com.shangpin.base.vo.ResultObj
 * 
 */

@Deprecated 
public class Content<T> {
    
	private List<T> list;
	
	private List<T> gallery;
    
    private List<T> latestProductList;
    
    private List<T> promotion;
    
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

	public List<T> getGallery() {
		return gallery;
	}

	public void setGallery(List<T> gallery) {
		this.gallery = gallery;
	}

	public List<T> getLatestProductList() {
		return latestProductList;
	}

	public void setLatestProductList(List<T> latestProductList) {
		this.latestProductList = latestProductList;
	}

	public List<T> getPromotion() {
		return this.promotion;
	}

	public void setPromotion(List<T> promotion) {
		this.promotion = promotion;
	}
	

}
