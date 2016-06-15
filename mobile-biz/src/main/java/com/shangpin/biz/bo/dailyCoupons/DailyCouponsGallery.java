package com.shangpin.biz.bo.dailyCoupons;

import java.io.Serializable;

import com.shangpin.biz.bo.CommonRules;

/**
 * 天天抢券页面轮播图
 * @author zkj
 *
 */
public class DailyCouponsGallery extends CommonRules implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6023264187223934647L;
	private String pic; //图片编号

	    public String getPic() {
	        return pic;
	    }

	    public void setPic(String pic) {
	        this.pic = pic;
	    }
}
