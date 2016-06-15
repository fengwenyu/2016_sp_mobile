
package com.shangpin.biz.service.abstraction;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OverSeasService;
import com.shangpin.biz.bo.CategoryProductList;
import com.shangpin.biz.bo.OverSeaCategories;
import com.shangpin.biz.bo.OverSeaCategory;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.base.ResultBaseWy;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.product.model.custom.Adver;
import com.shangpin.product.model.custom.AdverList;
import com.shangpin.utils.CDNHash;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JsonUtil;


public abstract class AbstractBizOverSeasService {
    @Autowired
    protected OverSeasService overSeasService;
    
    public List<OverSeaCategory> categories(String categoryNo, String postArea){
    	String json = overSeasService.categoryList(categoryNo, postArea);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultBaseWy<OverSeaCategories> result = JsonUtil.fromJson(json, new TypeToken<ResultBaseWy<OverSeaCategories>>() {});
    	OverSeaCategories overSeaCategories = result.getResult();
    	if(null != overSeaCategories && Constants.SUCCESS.equals(overSeaCategories.getStatus())){
    		List<OverSeaCategory> category = overSeaCategories.getShopCategorys();
    		if(null != category && category.size() > 0){
    			return category;
    		}
    	}
    	return null;
    }
    
    public List<AdverList> adverLists(){
    	ContentBuilder<List<AdverList>> builder = overSeasService.adver();
    	if(Constants.SUCCESS.equals(builder.getCode())){
    		List<AdverList> adverLists = builder.getContent();
    		for(AdverList adverList : adverLists){
    			List<Adver> advers = adverList.getList();
    			for(Adver adver : advers){
    				String picNo = adver.getPicNo();
    				String picUrl = this.getPicUrl(picNo, "2").replace("-10-10", "-" + adver.getPicWidth() + "-" + adver.getPicHeight());
    				adver.setPicUrl(picUrl);
    			}
    		}
    		return adverLists;
    	}
    	return null;
    }
    
    public List<Product> productList(String pageIndex, String pageSize, String categoryNo, String postArea, String userLv){
    	String start = null;
		String end= null;
		if(!StringUtils.isEmpty(pageIndex)&&!StringUtils.isEmpty(pageSize)){
			start = String.valueOf((Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1);
			end = String.valueOf(Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
		}
		String userType = null;
		if(StringUtils.isEmpty(userLv)){
			if("0002".equals(userLv)){
				userType ="gold";
			}else if("0003".equals(userLv)){
				userType ="platinum";
			}else if("0004".equals(userLv)){
				userType ="diamond";
			}else {
				userType = "";
			}
		}
		String json = overSeasService.productList(start, end, categoryNo, postArea, userType);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBaseWy<CategoryProductList> result = JsonUtil.fromJson(json, new TypeToken<ResultBaseWy<CategoryProductList>>(){});
		CategoryProductList categoryProductList = result.getResult();
		if(null != categoryProductList && Constants.SUCCESS.equals(categoryProductList.getStatus())){
			List<Product> products = categoryProductList.getDocs();
			if(null != products && products.size() > 0){
				for(Product product : products){
					product.setPic(this.getPicUrl(product.getProductPicFile(), "1"));
					product.setCountryPic(this.getCountryPic(product.getPostAreaPic()));
					// 2015.11.24-黑色星期五实时变价，6小时阶段，过后可删除
					if (StringUtil.compareDate(Constants.ACTIVITY_READY_END, Constants.ACTIVITY_OPEN_START, DateUtils.dateToStr(new Date())) == 0) {
						product.setLimitedPrice(product.getNewLimitedPrice());
						product.setSellPrice(product.getNewSellPrice());
						product.setPlatinumPrice(product.getNewPlatinumPrice());
						product.setDiamondPrice(product.getNewDiamondPrice());
					}
				}
				return products;
			}
		}
		return null;
    }
    
    private String getCountryPic(String pic){
    	//http://pic13.shangpin.com/shangpin/images/public/areaflag/meiguo.png
    	return "http://pic13.shangpin.com/shangpin/images/public/areaflag/" + pic;
    }
    /**
     * 获取图片url
     * 
     * @author wangfeng
     * @createDate 2014-12-22
     * @param picNo
     *            ,type 1 商品，2 品牌
     */
	private String getPicUrl(String picNo, String type) {
        StringBuffer picBuffer = new StringBuffer("");
        picBuffer.append(CDNHash.getUrlHash(picNo));
        if ("1".equals(type)) {
            picBuffer.append("/f/p/");
        } else if ("2".equals(type)) {
            picBuffer.append("/e/s/");
        }
        picBuffer.append(picNo.substring(2, 4));
        picBuffer.append("/");
        picBuffer.append(picNo.substring(4, 6));
        picBuffer.append("/");
        picBuffer.append(picNo.substring(6, 8));
        picBuffer.append("/");
        picBuffer.append(picNo);
        picBuffer.append("-10-10.jpg");
        return picBuffer.toString();
    }
    
}
