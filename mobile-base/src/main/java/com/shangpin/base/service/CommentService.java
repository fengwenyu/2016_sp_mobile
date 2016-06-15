package com.shangpin.base.service;

public interface CommentService {

	/**
	 * 评价初始化
	 * @param subOrderNo
	 * @param productId
	 * @param userId
	 * @return
	 */
	public String findCommentInitInfo(String subOrderNo,String productId,String userId,String orderDetailNo,String skuNo,String categoryNo);
	/**
	 * 根据订单号查询评价列表
	 * @param subOrderNo
	 * @param userId
	 * @return
	 */
	public String CommentListByOrderNo(String subOrderNo,String userId);
	/**
	 * 提交评论
	 * @param subOrderNo
	 * @param basicInfo
	 * @param descInfo
	 * @param imageList
	 * @param userId
	 * @param productId
	 * @return
	 */
	public String submitComment(String commentItemList, String commentContent, String imageList, String orderNo,String orderDetailNo,String skuNo,String productNo,String userId,String categoryNo);
	/**
     * 我的待评论
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
	public String commentProductList(String userId, String pageIndex, String pageSize);
}
