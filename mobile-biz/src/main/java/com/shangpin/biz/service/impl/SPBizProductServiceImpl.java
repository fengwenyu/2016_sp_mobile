package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.SPmerchandise;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizProductService;
import com.shangpin.biz.service.abstraction.AbstractBizProductService;

/**
 * @ClassName: SPProductServiceImpl
 * @Description:商品详情接口实现类
 * @author qinyingchun
 * @date 2014年11月3日
 * @version 1.0
 */
@Service
public class SPBizProductServiceImpl extends AbstractBizProductService implements SPBizProductService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizProductServiceImpl.class);

	@Autowired
	private ShangPinService shangPinService;

	@Override
	public SPmerchandise detail(String productNo, String topicId, String userId, String width, String height) {
		try {
			ListOfGoods listOfGoods = new ListOfGoods();
			listOfGoods.setProductId(productNo);
			listOfGoods.setPicw(width);
			listOfGoods.setPich(height);
			listOfGoods.setTopicId(topicId == null ? "" : topicId);
			listOfGoods.setUserId(userId == null ? "" : userId);
			ResultObjOne<SPmerchandise> obj = beProductDetail(listOfGoods);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ProductDetail findProductDetail(String activityId, String productId, String userId,String picNo){
		ProductDetail productDetail = new ProductDetail();
		productDetail = fromFindProductDetail(activityId, productId, userId,picNo,"1");
		if (productDetail == null) {
			return null;
		}
		return productDetail;
		
	}
	
}
