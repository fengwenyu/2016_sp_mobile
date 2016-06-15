package com.shangpin.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.OverSeasService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.product.model.custom.AdverList;
import com.shangpin.product.service.intf.cbwfs.IspCommonAdverService;
import com.shangpin.product.service.intf.cbwfs.IspOverSeasService;
import com.shangpin.utils.HttpClientUtil;

@Service
public class OverSeasServiceImpl extends BaseService implements OverSeasService {

    //分类列表
    private StringBuilder categoryListURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("OverSeasChannelCategoryList");
    //海外购商品列表
    private StringBuilder productListURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("OverSeasChannelProductList");
    
    @Autowired
    private IspOverSeasService overSeasService;
    
    @Autowired
    private IspCommonAdverService commonAdverService;
    
    /**
     * 海外购轮播图接口
     * 
     * findData方法前三个参数是为了计算缓存所需要的key，后三个参数是为了通过反射获得相应的方法来调取相应的
     * 接口获得所需数据
     */
    @Override
    public String galleryList(String type, String frames) {
        Map<String, String> params = genParamMap();
        params.put("type", type);
        params.put("frames", frames);
        String[] args = {type, frames};
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "overSeasGallery", params, overSeasService, "gallery", args);
    }
    
    @Override
	public ContentBuilder<List<AdverList>> adver() {
		ContentBuilder<List<AdverList>> advers = commonAdverService.advers();
		return advers;
	}
    
	@Override
	public String categoryList(String categoryNo, String postArea) {
		Map<String, String> params = genParamMap();
		if(!isEmpty(categoryNo)){
			params.put("categoryNO", categoryNo);
		}
		if(!isEmpty(postArea)){
			params.put("postAreaNO", postArea);
		}
		return HttpClientUtil.doGet(categoryListURL.toString(), params);
	}
	
	@Override
	public String productList(String start, String end, String categoryNo, String postArea, String userType) {
		Map<String, String> params = genParamMap();
		params.put("start", start);
		params.put("end", end);
		if(!isEmpty(categoryNo)){
			params.put("categoryNO", categoryNo);
		}
		if(!isEmpty(postArea)){
			params.put("postAreaNO", postArea);
		}
		if(!isEmpty(userType)){
			params.put("userType", userType);
		}
		return HttpClientUtil.doGet(productListURL.toString(), params);
	}
	
	private boolean isEmpty(String str){
		if(StringUtils.isEmpty(str) || "null".equals(str)){
			return true;
		}
		return false;
	}

}
