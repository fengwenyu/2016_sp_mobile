package com.shangpin.biz.bo.dailyCoupons;

import java.io.Serializable;
import java.util.List;

/**
 * 天天抢券页面轮播图数据机构
 * @author zkj
 *
 */
public class DailyCouponsGalleryList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3891706455428258910L;
	private String type;
    private List<DailyCouponsGallery> gallery;  //轮播图列表
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<DailyCouponsGallery> getGallery() {
        return gallery;
    }
    public void setGallery(List<DailyCouponsGallery> gallery) {
        this.gallery = gallery;
    }
}
