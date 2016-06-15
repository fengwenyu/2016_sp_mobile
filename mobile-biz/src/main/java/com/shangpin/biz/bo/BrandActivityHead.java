package com.shangpin.biz.bo;

import java.io.Serializable;
 
public class BrandActivityHead implements Serializable{
	private static final long serialVersionUID = 1L;
	private ActivityHead activity;
	private HeadInfo head;
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
}
