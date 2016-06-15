package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Gallery;


public interface ASPBizGalleryService {
   
    /**
     *风格主题
     *
     * @param type
     *               类型 1：新品轮播；2：商城轮播；3：首页轮播           
     * @return
     * @author wangfeng
     */
    public List<Gallery> queryGalleryList(String type,String frames);

    
	

}
