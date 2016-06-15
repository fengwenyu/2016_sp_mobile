package com.shangpin.biz.service;

import com.shangpin.biz.bo.MallCategory;

/**
 * 品类
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizCategoryService {

	/**
	 * 客户端品类
	 * 
	 * @return
	 */
	MallCategory doCategory() throws Exception;

}
