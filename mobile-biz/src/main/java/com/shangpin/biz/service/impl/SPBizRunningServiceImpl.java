package com.shangpin.biz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.Category;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RunBrandsHot;
import com.shangpin.biz.bo.RunBrandsHotList;
import com.shangpin.biz.bo.RunCategory;
import com.shangpin.biz.bo.RunGallery;
import com.shangpin.biz.bo.RunGalleryList;
import com.shangpin.biz.bo.RunProduct;
import com.shangpin.biz.bo.base.ResultBaseWy;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizRunningService;
import com.shangpin.biz.service.abstraction.AbstractBizRunningService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.CDNHash;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizRunningServiceImpl extends AbstractBizRunningService implements SPBizRunningService {
    
    private static final Logger logger = LoggerFactory.getLogger(SPBizRunningService.class);

	@Override
	public List<Product> runProducts(String userId, String channelNo, String categoryNo, String pageIndex, String pageSize) {
		String start = null;
		String end= null;
		if(!StringUtils.isEmpty(pageIndex)&&!StringUtils.isEmpty(pageSize)){
			start = String.valueOf((Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1);
			end = String.valueOf(Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
		}
		String json = runningService.productList(userId, channelNo, categoryNo, start, end);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBaseWy<RunProduct> result  = JsonUtil.fromJson(json, new TypeToken<ResultBaseWy<RunProduct>>(){});
		RunProduct product = result.getResult();
		List<Product> products = product.getDocs();
		if(Constants.SUCCESS.equals(product.getStatus()) && null != products && products.size() > 0){
			for(Product product2 : products){
				product2.setPic(getPicUrl(product2.getProductPicFile(), "1"));
			}
			return products;
		}
		return null;
	}

	@Override
	public List<Category> runCategorys(String userId, String categoryNo,String channelNO, String pageIndex, String pageSize) {
		String start = null;
		String end= null;
		if(!StringUtils.isEmpty(pageIndex)&&!StringUtils.isEmpty(pageSize)){
			start = String.valueOf((Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1);
			end = String.valueOf(Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
		}
		String json = runningService.categoryList(userId, categoryNo,channelNO, start, end);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBaseWy<RunCategory> result = JsonUtil.fromJson(json, new TypeToken<ResultBaseWy<RunCategory>>(){});
		RunCategory category = result.getResult();
		List<Category> categories = category.getChannelCategorys();
		if(Constants.SUCCESS.equals(category.getStatus()) && null != categories && categories.size() > 0){
			for(Category category2 : categories){
				if(!StringUtils.isEmpty(category2.getChannelCategoryPic())){
					category2.setChannelCategoryPic(getPicUrl(category2.getChannelCategoryPic(), "2"));
				}
			}
			return categories;
		}
		return null;
	}
    
    /**
     * 获取运动馆首页轮播图
     */
    @Override
    public RunGalleryList getRunGalleryList(String type, String frames) {
        
        try {

            String resultJson = runningService.galleryList(type, frames);
            logger.debug("调用base运动馆轮播图接口返回数据：" + resultJson);
            
            if(StringUtils.isEmpty(resultJson)){
                return null;
            }
            
            ResultObjOne<RunGalleryList> obj = JsonUtil.fromJson(resultJson, new TypeToken<ResultObjOne<RunGalleryList>>(){});
            RunGalleryList runGalleryList = obj.getObj();
            List<RunGallery> runGalleries = runGalleryList.getGallery();
            
            for(RunGallery runGallery : runGalleries) {
                runGallery.setPic(getPicUrl(runGallery.getPic(), "2"));
            }
            
            return runGalleryList;
            
        } catch (Exception e) {
            logger.error("调用base运动馆轮播图接口返回数据发生错误!", e);
            return null;
        }
    }
    
    /**
     * 获取跑步馆品牌热卖
     */
    @Override
	public RunBrandsHotList getRunBrandsHost(String type, String frames) {
		
	    try {
			String json = runningService.getRunBrandsHost(type,frames);
			logger.debug("调用base轮播图接口返回数据：" + json);
			if(StringUtils.isEmpty(json)){
				return null;
			}
			ResultObjOne<RunBrandsHotList> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<RunBrandsHotList>>(){});
			RunBrandsHotList runBrandsHotList =obj.getObj();
			if(runBrandsHotList!=null){
				List<RunBrandsHot> brandList= runBrandsHotList.getList();
				for (RunBrandsHot runBrandsHot : brandList) {
					runBrandsHot.setPic(getPicUrl(runBrandsHot.getPic(), "2"));
				}
				return runBrandsHotList;
			}
			
		} catch (Exception e) {
			logger.error("调用base轮播图接口返回数据发生错误!");
			e.printStackTrace();
		}
		return null;
	}
    
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
