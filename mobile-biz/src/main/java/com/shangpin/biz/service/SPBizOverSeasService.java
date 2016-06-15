package com.shangpin.biz.service;
import java.util.List;

import com.shangpin.biz.bo.OverSeaCategory;
import com.shangpin.biz.bo.OverSeasGalleryList;
import com.shangpin.biz.bo.Product;
import com.shangpin.product.model.custom.AdverList;
/**
 * @author qinyingchun
 *海外购业务处理接口
 */
public interface SPBizOverSeasService {
	
    /**
     * 跑步馆轮播图接口
     * @param type  类型
     * @param frames    所需帧数
     * @return
     */
    public OverSeasGalleryList getOverSeasGalleryList(String type, String frames);
    
    /**
     * 广告位
     * @return
     */
    public List<AdverList> adverLists();
    
    /**
     * 
     * @param categoryNo 商城分类编号
     * @param postArea 发货地区
     * @return
     */
    public List<OverSeaCategory> categories(String categoryNo, String postArea);
    
    /**
     * 
     * @param pageIndex 页码
     * @param pageSize 一页显示多少条
     * @param categoryNo 商城分类编号
     * @param postArea 发货地区
     * @param userLv  用户级别
     * @return
     */
    public List<Product> productList(String pageIndex, String pageSize, String categoryNo, String postArea, String userLv);

    
    
}
