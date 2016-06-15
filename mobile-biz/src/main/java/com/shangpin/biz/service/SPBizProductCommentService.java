package com.shangpin.biz.service;

import com.shangpin.biz.bo.ProductComment;

public interface SPBizProductCommentService {
	 public ProductComment queryProductComment(String productId, String pageIndex, String pageSize,String tagId);
}
