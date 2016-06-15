package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class OperatHeader implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1020466533804783162L;
    private ActivityHead activity;
    private HeadInfo head;
    @JsonInclude(Include.NON_NULL) 
    private List<BrandActivityPromotions> promotion;
    private List<BrandActivityCoupon> coupon;
    
    private BrandActivityModelOne modelOne;
    
    private BrandActivityGallery gallery;
    
    public ActivityHead getActivity() {
        return activity;
    }
    public void setActivity(ActivityHead activity) {
        this.activity = activity;
    }
    public HeadInfo getHead() {
        return head;
    }
    public void setHead(HeadInfo head) {
        this.head = head;
    }
    public List<BrandActivityCoupon> getCoupon() {
        return coupon;
    }
    public void setCoupon(List<BrandActivityCoupon> coupon) {
        this.coupon = coupon;
    }
    public List<BrandActivityPromotions> getPromotion() {
        return promotion;
    }
    public void setPromotion(List<BrandActivityPromotions> promotion) {
        this.promotion = promotion;
    }
	public BrandActivityModelOne getModelOne() {
		return modelOne;
	}
	public void setModelOne(BrandActivityModelOne modelOne) {
		this.modelOne = modelOne;
	}
	public BrandActivityGallery getGallery() {
		return gallery;
	}
	public void setGallery(BrandActivityGallery gallery) {
		this.gallery = gallery;
	}


    	
	

}
