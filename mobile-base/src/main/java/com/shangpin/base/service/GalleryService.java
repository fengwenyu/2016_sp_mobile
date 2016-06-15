package com.shangpin.base.service;

import java.util.List;
import java.util.Map;

import com.shangpin.product.model.cbwfs.MobileAlterPic;
import com.shangpin.product.model.common.ContentBuilder;

/**
 * 轮播图
 * @author qinyingchun
 *
 */
public interface GalleryService {
	
	/**
	 * 轮播图接口，移动端轮播图可以通用
	 * @param type 轮播图类型
	 * @return
	 */
	ContentBuilder<Map<String, List<MobileAlterPic>>> gallery(String type);

}
