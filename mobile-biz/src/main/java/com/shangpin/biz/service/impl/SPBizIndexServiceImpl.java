package com.shangpin.biz.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;











import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.CategoryList;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.bo.IndexHotBrands;
import com.shangpin.biz.bo.Promotions;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizIndexService;
import com.shangpin.utils.JsonUtil;

/** 
* @ClassName: BizIndexServiceImpl 
* @Description:商城首页各部分调用的方法 
* @author 秦迎春
* @date 2014年10月22日 下午3:31:55 
* @version 1.0 
*/
@Service
public class SPBizIndexServiceImpl implements SPBizIndexService{
	
	private static final Logger logger = LoggerFactory.getLogger(SPBizIndexService.class);
	
	@Autowired
	private ShangPinService shangPinService;

	@Override
	public GalleryList galleryList(String type,String frames) {
		try {
			String json = shangPinService.queryGalleryList(type,frames);
			logger.debug("调用base轮播图接口返回数据：" + json);
			if(StringUtils.isEmpty(json)){
				return null;
			}
			ResultObjOne<GalleryList> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GalleryList>>(){});
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base轮播图接口返回数据发生错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryList categoryList() {
		try {
			String json = shangPinService.queryMallCategoryList();
			logger.debug("调用base品类列表接口返回数据：" + json);
			if(StringUtils.isEmpty(json)){
				return null;
			}
			ResultObjOne<CategoryList> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<CategoryList>>(){});
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base品类列表接口返回数据发生错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IndexHotBrands brandLists(String pageIndex, String pageSize) {
		try {
			String json = shangPinService.queryBrandList(pageIndex, pageSize);
			logger.debug("调用base品牌列表接口返回数据：" + json);
			if(StringUtils.isEmpty(json)){
				return null;
			}
			ResultObjOne<IndexHotBrands> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<IndexHotBrands>>(){});
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base品牌列表接口返回数据发生错误!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Promotions promotions() {
		try {
			String json = shangPinService.queryMallPromotionList();
			logger.debug("调用base推广位接口返回数据：" + json);
			if(StringUtils.isEmpty(json)){
				return null;
			}
			ResultObjOne<Promotions> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Promotions>>(){});
			return obj.getObj();
		} catch (Exception e) {
			logger.error("调用base推广位接口返回数据发生错误!");
			e.printStackTrace();
		}
		return null;
	}

}
