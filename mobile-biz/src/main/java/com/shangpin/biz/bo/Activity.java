package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 活动对象
 * 
 * @author cuibinqiang
 * 
 */
public class Activity  implements Serializable{
    private static final long serialVersionUID = 994358010403827310L;
    /** 活动ID  */
	private String activityid;
	/** 活动名称  */
	private String activityname;
	/** 活动的宣传图片路径  */
	private String pic;
	/** 活动的开始时间  */
	private String starttime;
	/** 活动的结束时间  */
	private String endtime;
	/** 活动的价格描述如：全场3折起  */
	private String t0;
	private String t1;
	private String t2;
	private String[] brands;
	private String brandno;
	private String brandcnname;
	private String brandenname;
	private String[] cates;
	private String brandpic;
	/** 是否为推荐商品，0非推荐，1推荐  */
	private String recommend;
	
	private String salesinfo;

	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getT0() {
		return t0;
	}

	public void setT0(String t0) {
		this.t0 = t0;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String[] getBrands() {
		return brands;
	}

	public void setBrands(String[] brands) {
		this.brands = brands;
	}

	public String[] getCates() {
		return cates;
	}

	public void setCates(String[] cates) {
		this.cates = cates;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = null == recommend ? "" : recommend;
	}

	public String getBrandno() {
		return brandno;
	}

	public void setBrandno(String brandno) {
		this.brandno = brandno;
	}

	public String getBrandcnname() {
		return brandcnname;
	}

	public void setBrandcnname(String brandcnname) {
		this.brandcnname = brandcnname;
	}

	public String getBrandenname() {
		return brandenname;
	}

	public void setBrandenname(String brandenname) {
		this.brandenname = brandenname;
	}

    public String getBrandpic() {
        return brandpic;
    }

    public void setBrandpic(String brandpic) {
        this.brandpic = brandpic;
    }

    public String getSalesinfo() {
        return salesinfo;
    }

    public void setSalesinfo(String salesinfo) {
        this.salesinfo = salesinfo;
    }

	
}
