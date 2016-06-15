package com.shangpin.biz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.OverSeasGallery;
import com.shangpin.biz.bo.OverSeasGalleryList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizOverSeasService;
import com.shangpin.biz.service.SPBizRunningService;
import com.shangpin.biz.service.abstraction.AbstractBizOverSeasService;
import com.shangpin.utils.CDNHash;
import com.shangpin.utils.JsonUtil;

@Service
public class SPBizOverSeasServiceImpl extends AbstractBizOverSeasService implements SPBizOverSeasService {
    
    private static final Logger logger = LoggerFactory.getLogger(SPBizRunningService.class);

    /**
     * 获取海外购首页轮播图
     */
    @Override
    public OverSeasGalleryList getOverSeasGalleryList(String type, String frames) {
        
        try {

            String resultJson = overSeasService.galleryList(type, frames);
            logger.debug("调用base海外购轮播图接口返回数据：" + resultJson);
            
            if(StringUtils.isEmpty(resultJson)){
                return null;
            }
            
            ResultObjOne<OverSeasGalleryList> obj = JsonUtil.fromJson(resultJson, new TypeToken<ResultObjOne<OverSeasGalleryList>>(){});
            OverSeasGalleryList overSeasGalleryList = obj.getObj();
            List<OverSeasGallery> overSeasGalleries = overSeasGalleryList.getGallery();
            
            for(OverSeasGallery overSeasGallery : overSeasGalleries) {
                overSeasGallery.setPic(getPicUrl(overSeasGallery.getPic(), "2"));
            }
            
            return overSeasGalleryList ;
            
        } catch (Exception e) {
            logger.error("调用base海外购轮播图接口返回数据发生错误!", e);
            return null;
        }
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
