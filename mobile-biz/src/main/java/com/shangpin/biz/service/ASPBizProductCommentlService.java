package com.shangpin.biz.service;

import com.shangpin.biz.bo.ProductComment;


/**
 * 商品评论
 * 
 * @author wangfeng
 *
 */
public interface ASPBizProductCommentlService {
	/**
	 * 商品评论列表
	 * 
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ProductComment queryProductComment(String productId, String pageIndex,String pageSize);
	/**
     * 商品评论列表
     * 
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String queryProductCommentToResult(String productId, String pageIndex,String pageSize,String tagId);

	
}
