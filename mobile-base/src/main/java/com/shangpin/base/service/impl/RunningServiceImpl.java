package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.RunningService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.product.service.intf.cbwfs.IspRunningService;

@Service
public class RunningServiceImpl extends BaseService implements RunningService{
	//跑步馆单品列表
	private StringBuilder runProductListRUL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("RunnerChannelProductList");
	//跑步馆分类列表
	private StringBuilder runCategoryListRUL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("RunnerChannelCategoryList");
	//频道分类商品列表
	private StringBuilder runCategoryProductListRUL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("RCCategoryProductList");
	
	@Autowired
	private IspRunningService runningService;
	
	@Override
	public String productList(String userId, String channelNo, String categoryNo, String start, String end) {
		Map<String, String> params = genParamMap();
		params.put("userID", userId);
		params.put("channelNO", channelNo);
		params.put("channelCategoryNO", categoryNo);
		params.put("start", start);
		params.put("end", end);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "RunnerChannelProductList", params, runProductListRUL.toString());
	}
	@Override
	public String categoryList(String userId, String categoryNo,String channelNO, String start, String end) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userID", userId);
		params.put("channelCategoryNO", categoryNo);
		params.put("channelNO", channelNO);
		if(!StringUtils.isEmpty(start)){
			params.put("start", start);
		}
		if(!StringUtils.isEmpty(end)){
			params.put("end", end);
		}
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "RunnerChannelCategoryList", params, runCategoryListRUL.toString());
	}
	
    @Override
    public String galleryList(String type, String frames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("frames", frames);
        String[] args = {type, frames};
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "runningGallery", params, runningService, "gallery", args);
    }
	
	@Override
	public String getRunBrandsHost(String type, String frames) {
	    Map<String,String> params=new HashMap<String,String>();
	    params.put("type",type);
	    params.put("frames", frames);
	    String[] args = {type, frames};
	    return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "runningBrandsHot", params, runningService, "brandsHot", args);
	}
	
	@Override
	public String categoryProductList(String start, String end, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea) {
		Map<String,String> params=new HashMap<String,String>();
		if(!isEmpty(start)){
			params.put("start", start);
		}
		if(!isEmpty(end)){
			params.put("end", end);
		}
	    params.put("channelCategoryNO",channelCategoryNO);
	    if(!isEmpty(categoryNO)){
	    	params.put("categoryNO",categoryNO);
	    }
	    if(!isEmpty(userType)){
	    	params.put("userType",userType);
	    }
	    if(!isEmpty(price)){
	    	params.put("price",price);
	    }
	    if(!isEmpty(productSize)){
	    	params.put("productSize",productSize);
	    }
	    if(!isEmpty(primaryColorId)){
	    	params.put("primaryColorId",primaryColorId);
	    }
	    if(!isEmpty(brandNO)){
	    	params.put("brandNO",brandNO);
	    }
	    if(!isEmpty(order)){
	    	params.put("order",order);
	    }
	    if(!isEmpty(postArea)){
	    	params.put("postArea",postArea);
	    }
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "RCCategoryProductList", params, runCategoryProductListRUL.toString());
	}
	
	private boolean isEmpty(String str){
		if(StringUtils.isEmpty(str) || "null".equals(str)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
        RunningServiceImpl run = new RunningServiceImpl();
        System.out.println(run.galleryList("SwitchPicture", "4"));
    }
	
}