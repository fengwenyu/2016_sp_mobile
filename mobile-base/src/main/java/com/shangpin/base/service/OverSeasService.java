package com.shangpin.base.service;

import java.util.List;

import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.product.model.custom.AdverList;

/**
 * 
 * @author chenshouqin
 *海外购功能相关接口
 */
public interface OverSeasService {
    
    /**
     * 跑步馆轮播图接口
     * @param type 类型
     * @param frames 所需帧数
     * @return
     */
    public String galleryList(String type, String frames);
    
    /**
     * 广告位
     * @return
     */
    public ContentBuilder<List<AdverList>> adver();
    
    /**
     * 
     * @param categoryNo 商城分类编号
     * @param postArea 发货地区
     * @return
     */
    public String categoryList(String categoryNo, String postArea);

    /**
     * 
     * @param start 开始索引
     * @param end 结束索引
     * @param categoryNo 商城分类编号
     * @param postArea 发货地区
     * @param userType  用户类型
     * @return
     */
    public String productList(String start, String end, String categoryNo, String postArea, String userType);

}
