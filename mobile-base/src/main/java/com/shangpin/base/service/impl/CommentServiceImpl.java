package com.shangpin.base.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.CommentService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;
@Service
public class CommentServiceImpl implements CommentService {

	//评论列表
	private StringBuilder commentListRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/orderCommentList");
	//评论初始化
	private StringBuilder commentInitRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/CommentInit");
	//提交评论
	private StringBuilder submitCommentRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/submitComment");
	//我的待评论
    private StringBuilder commentProductListRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/commentProductList");
    
	@Override
	public String findCommentInitInfo(String subOrderNo, String productNo,
			String userId,String orderDetailNo,String skuNo,String categoryNo) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("subOrderNo", subOrderNo);
		params.put("productNo", productNo);
		params.put("userId", userId);
		params.put("orderDetailNo", orderDetailNo);
		params.put("skuNo", skuNo);
		params.put("categoryNo", categoryNo);
		return HttpClientUtil.doGet(commentInitRUL.toString(), params);
	}

	@Override
	public String CommentListByOrderNo(String subOrderNo, String userId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("subOrderNo", subOrderNo);
		params.put("userId", userId);
		return HttpClientUtil.doGet(commentListRUL.toString(), params);
	}

	@Override
	public String submitComment(String commentItemList, String commentContent, String imageList, String orderNo,String orderDetailNo,String skuNo,String productNo,String userId,String categoryNo) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("commentItemList", commentItemList);
		params.put("commentContent", commentContent);
		params.put("imageList", imageList);
		params.put("orderNo", orderNo);
		params.put("orderDetailNo", orderDetailNo);
		params.put("skuNo", skuNo);
		params.put("productNo", productNo);
		params.put("userId", userId);
		params.put("categoryNo", categoryNo);
		return HttpClientUtil.doGet(submitCommentRUL.toString(), params);
	}

    @Override
    public String commentProductList(String userId, String pageIndex, String pageSize) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return HttpClientUtil.doGet(commentProductListRUL.toString(), params);
    }

}
