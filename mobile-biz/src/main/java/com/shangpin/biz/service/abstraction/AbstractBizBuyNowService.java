package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.BuyNow;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

/**
 * @author qinyingchun
 *立即购买实体类
 */
public abstract class AbstractBizBuyNowService {
	
	@Autowired
	private ShangPinService shangPinService;
	
	public String buyNowStr(String userId, String skuId, String productId, String activityId, String amount, String region){
		return shangPinService.findBuyNow(userId, skuId, productId, activityId, amount, region);
	}
	public String buyNowStrThrid(String userId, String skuId, String productId, String activityId, String amount, String region,String chanelNo,String chanelId){
		return shangPinService.findBuyNowThrid(userId, skuId, productId, activityId, amount, region,chanelNo, chanelId);
	}
	public BuyNow buyNowObj(String userId, String skuId, String productId, String activityId, String amount, String region){
		String json = this.buyNowStr(userId, skuId, productId, activityId, amount, region);
		if(!StringUtils.isEmpty(json)){
			ResultObjOne<BuyNow> buyNowObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<BuyNow>>(){});
			if(Constants.SUCCESS.equals(buyNowObj.getCode())){
				return buyNowObj.getObj();
			}
		}
		return null;
	}
	
	public BuyNow buyNowObj(String userId, String skuId, String productId, String activityId, String amount, String region,String chanelNo,String chanelId){
		String json = this.buyNowStrThrid(userId, skuId, productId, activityId, amount, region,chanelNo,chanelId);
		if(!StringUtils.isEmpty(json)){
			ResultObjOne<BuyNow> buyNowObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<BuyNow>>(){});
			if(Constants.SUCCESS.equals(buyNowObj.getCode())){
				return buyNowObj.getObj();
			}
		}
		return null;
	}
	
	public String buyNowStr(String userId, String skuId, String productId, String activityId, String amount, String region, String type){
		return shangPinService.findBuyNow(userId, skuId, productId, activityId, amount, region, type);
	}
	
	
	public String buyNowStr(String userId, String skuId, String productId, String activityId, String amount, String region, String type,String isDefaultUseCoupon){
		return shangPinService.findBuyNow(userId, skuId, productId, activityId, amount, region, type,isDefaultUseCoupon);
	}
	
	
	public BuyNow buyNowObj(String userId, String skuId, String productId, String activityId, String amount, String region, String type){
		String json = this.buyNowStr(userId, skuId, productId, activityId, amount, region, type);
		if(!StringUtils.isEmpty(json)){
			ResultObjOne<BuyNow> buyNowObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<BuyNow>>(){});
			if(Constants.SUCCESS.equals(buyNowObj.getCode())){
				return buyNowObj.getObj();
			}
		}
		return null;
	}
	
	
}
