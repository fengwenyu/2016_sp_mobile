package com.shangpin.biz.service.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.ProductComment;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;

public abstract class AbstractBizProductCommentService {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizProductCommentService.class);
	@Autowired
	ShangPinService shangPinService;

	/**
	 * 获取商品评论
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * 
	 * @param productId
	 *            商品id
	 * @return
	 * @author liling
	 */
	public ProductComment queryProductComment(String productId, String pageIndex, String pageSize) {
		try {
			String json = shangPinService.findCommentList(productId, pageIndex, pageSize,"");
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