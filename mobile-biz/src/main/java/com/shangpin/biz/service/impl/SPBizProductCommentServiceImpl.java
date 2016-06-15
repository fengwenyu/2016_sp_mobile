package com.shangpin.biz.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.ProductComment;
import com.shangpin.biz.service.SPBizProductCommentService;
import com.shangpin.biz.service.abstraction.AbstractBizProductCommentService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;
@Service
public class SPBizProductCommentServiceImpl  extends AbstractBizProductCommentService implements SPBizProductCommentService {
	@Autowired
	ShangPinService shangPinService;

	@Override
	public ProductComment queryProductComment(String productId, String pageIndex, String pageSize,String tagId) {
		try {
			String json = shangPinService.findCommentList(productId, pageIndex, pageSize,tagId);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<ProductComment> obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, ProductComment.class);
				String code = obj.getCode();
				if (!Constants.SUCCESS.equals(code) || null == obj) {
					return null;
				}
				return obj.getObj();
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}

}
