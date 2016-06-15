package com.shangpin.biz.service.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.ProductTemplate;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizProductTemplateService {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizProductTemplateService.class);
	@Autowired
	ShangPinService shangPinService;

	/**
	 * 获取商品模板
	 * 
	 * @param type
	 *            1：品牌风尚；2：保养及售后
	 * @param id
	 *            productId
	 * @return
	 * @author liling
	 */
	public ProductTemplate fromFindProductTemplates(String type, String productId) {
		try {
			String json = shangPinService.findProductTemplate(type, productId);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<ProductTemplate> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductTemplate>>() {
				});
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
