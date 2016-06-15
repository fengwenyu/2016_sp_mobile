package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.RecProductItem;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizRecProductService {
	@Autowired
	private CommonService commonService;
	public ResultObjMapList<RecProduct> beRecProduct(String userId, String type, String shopType,String productId, String pageIndex,
			String pageSize) {
		String json = fromRecProduct(userId, type, shopType,productId, pageIndex, pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<RecProduct> result = JsonUtil.fromJson(json,
					new TypeToken<ResultObjMapList<RecProduct>>() {
					});
			return result;
		}
		return null;
	}
	
	public String fromRecProduct(String userId, String type, String shopType,String productId, String pageIndex, String pageSize) {
		String json = commonService.recProduct(userId, type, shopType,productId, pageIndex, pageSize);
		return json;
	}
	public RecProductItem fromQueryRecProduct(String userId, String type, String dynParam, String pageIndex, String pageSize) {
		String json = commonService.recProduct(userId, type, dynParam, pageIndex, pageSize);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<RecProductItem> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<RecProductItem>>() {
			});
			return obj.getObj();
		}
		return null;
	}
}
