package com.shangpin.biz.service.basic;

import java.util.List;

import com.shangpin.biz.bo.Advert;

/**
 * 首页相关业务
 * 
 * @author huangxiaoliang
 *
 */
public interface IBizIndexService {

	/**
	 * 获取首页广告
	 * 
	 * @return
	 */
	String getBaseAdvert();

	/**
	 * 获取首页广告集合
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Advert> doBaseAdvert() throws Exception;

	/**
	 * 获取首页标签
	 * 
	 * @return
	 */
	String getBaseLabel(String pageIndex, String pageSize);

}
