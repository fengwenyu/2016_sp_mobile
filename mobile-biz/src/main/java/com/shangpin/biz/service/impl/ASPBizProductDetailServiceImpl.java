package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizProductDetailService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizProductDetailServiceImpl implements ASPBizProductDetailService {
	private static final Logger logger = LoggerFactory.getLogger(ASPBizProductDetailServiceImpl.class);
	@Autowired
	ShangPinService shangPinService;

	@Override
	public ProductDetail queryProductDetail(String activityId, String productId, String userId, String picNo, boolean isNew) {
		try {
			String json = null;
			if (isNew) {
				json = shangPinService.findProductDetailNew(activityId, productId, userId, picNo);
			} else {
				json = shangPinService.findProductDetail(activityId, productId, userId, picNo);
			}
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<ProductDetail> productDetailObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductDetail>>() {
				});
				if (Constants.SUCCESS.equals(productDetailObj.getCode())) {
					return productDetailObj.getObj();
				}
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductDetail queryProductDetail(String activityId, String productId, String userId, String picNo, String isNew) throws Exception {
		String json = shangPinService.findProductDetailNew(activityId, productId, userId, picNo, isNew);
		ProductDetail proDetail = null;
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<ProductDetail> productDetailObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductDetail>>() {
			});
			if (Constants.SUCCESS.equals(productDetailObj.getCode())) {
				proDetail = productDetailObj.getObj();
			}
		}
		return proDetail;
	}
}
