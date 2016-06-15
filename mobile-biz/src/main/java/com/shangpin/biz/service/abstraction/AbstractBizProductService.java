package com.shangpin.biz.service.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.biz.bo.ProductCount;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.SPmerchandise;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizProductService {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizProductService.class);
	@Autowired
	private ShangPinService shangPinService;

	public String fromProductDetail(ListOfGoods listOfGoods) {
		String json = shangPinService.findSPProductDetail(listOfGoods);
		return json;
	}

	public ResultObjOne<SPmerchandise> beProductDetail(ListOfGoods listOfGoods) {
		String json = fromProductDetail(listOfGoods);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<SPmerchandise> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<SPmerchandise>>() {
			});
			return result;
		}
		return null;
	}
	
	/**
	 * 
	 * @Title:findProductDetail
	 * @Description:商品详情页接口(5.4)
	 * @param activityId
	 *            活动编号
	 * @param productId
	 *            商品编号
	 * @param userId
	 *            用户id
	 * @return
	 * @author liling
	 * @date 2015年3月13日
	 */
	public ProductDetail fromFindProductDetail(String activityId, String productId, String userId,String picNo,String isNew) {
		try {
			String json = shangPinService.findProductDetailNew(activityId, productId, userId,picNo,isNew);;
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<ProductDetail> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductDetail>>() {
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
	
	public ProductCount productCount(String productNo){
		String json = shangPinService.productCount(productNo);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjOne<ProductCount> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductCount>>(){});
		if(Constants.SUCCESS.equals(obj.getCode()) && null != obj.getObj()){
			return obj.getObj();
		}
		return null;
	}
	
	
	
}
