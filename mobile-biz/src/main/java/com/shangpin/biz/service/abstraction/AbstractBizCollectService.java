package com.shangpin.biz.service.abstraction;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Collect;
import com.shangpin.biz.bo.CollectProduct;
import com.shangpin.biz.bo.CollectProductList;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizCollectService {
	@Autowired
	private ShangPinService shangPinService;
	@Autowired 
	CommonService commonService;
	public String fromCollect(String userId, String pageIndex, String pageSize, String type) {
		String json = shangPinService.findCollectActivityList(userId, pageIndex, pageSize, type);
		return json;
	}
	
	public List<CollectProduct> collectProductList(String userId, String pageIndex, String pageSize, String type, String postArea){
		String json = shangPinService.findCollectProductList(userId, pageIndex, pageSize, type, postArea);
		if(!StringUtils.isEmpty(json)){
			ResultObjMapList<CollectProduct> resultObj = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<CollectProduct>>() {
			});
			if(Constants.SUCCESS.equals(resultObj.getCode())){
				List<CollectProduct> products = resultObj.getList("list");
				return products;
			}
		}
		return null;
		
	}

	public ResultObjMapList<Collect> beCollect(String userId, String pageIndex, String pageSize, String type) {
		String json = fromCollect(userId, pageIndex, pageSize, type);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Collect> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Collect>>() {
			});
			return result;
		}
		return null;
	}
	
	public ResultBase fromCollectBrand(String userId, String brandId) {
		String json = commonService.collectBrandObj(userId, brandId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	public ResultBase fromCancleCollectBrand(String userId, String brandId) {
		String json = commonService.cancelCollectBrandObj(userId, brandId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	public String fromCollectProduct(String shopType, String skuId, String userId, String picw, String pich, String detailPicw, String detailPich) {
		String json = shangPinService.collectProduct(shopType, skuId, userId, picw, pich, detailPicw, detailPich);
		return json;
	}
	public ResultBase fromCancleCollectProduct(String shopType, String collectId, String userId) {
		String json = shangPinService.cancleCollectProduct(shopType, collectId, userId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}
	
	public String collectBrandList(String userId, String pageIndex, String pageSize){
		String json = shangPinService.collectBrandList(userId, pageIndex, pageSize);
		return json;
	}
	
	public List<Collect> collectBrands(String userId, String pageIndex, String pageSize){
		String json = collectBrandList(userId, pageIndex, pageSize);
		if(!StringUtils.isEmpty(json)){
			ResultObjMapList<Collect> brandCollect = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Collect>>(){});
			if(Constants.SUCCESS.equals(brandCollect.getCode())){
				return brandCollect.getList("collectedBrandlist");
			}
		}
		return null;
	}
	
	public String fromCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea){
		String json=commonService.collectedProductListObj(userId, shopType, pageIndex, pageSize, postArea);
		return json;
	}
	/**
	 * 收藏商品列表
	 * 
	 * @param userId
	 *            用户id
	 * @param shopType
	 *            1来自尚品，2来自奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<CollectProductList> beCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea){
		String json = this.fromCollectProductList(userId, shopType, pageIndex, pageSize, postArea);
		if(!StringUtils.isEmpty(json)){
			ResultObjMapList<CollectProductList> collectProductList=JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<CollectProductList>>() {
			});
			return collectProductList;
		}
		return null;
	}
}
