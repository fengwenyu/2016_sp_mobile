package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class CommentInit implements Serializable{

	private static final long serialVersionUID = -81411220728681877L;
	private CommentDetail orderdetails;
	private List<CommentItem> commentItemList;
	public CommentDetail getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(CommentDetail orderdetails) {
		this.orderdetails = orderdetails;
	}
	public List<CommentItem> getCommentItemList() {
		return commentItemList;
	}
	public void setCommentItemList(List<CommentItem> commentItemList) {
		this.commentItemList = commentItemList;
	}
	
	
}
