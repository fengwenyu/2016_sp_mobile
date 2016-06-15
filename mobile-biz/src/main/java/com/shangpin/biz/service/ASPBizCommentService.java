package com.shangpin.biz.service;

import com.shangpin.biz.bo.Picture;


public interface ASPBizCommentService {

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
	 * 上传评论图片
	 * @param extension
	 * @param picturetype
	 * @param picFileContent
	 * @return
	 */
	public Picture uploadPic(String extension, String picturetype, String picFileContent);
	/**
     * 我的待评论
     * @param extension
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String commentProductList(String userId, String pageIndex, String pageSize);
}
