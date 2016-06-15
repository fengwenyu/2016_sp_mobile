package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Fashion;

/**
 * 客户端尙潮流接口
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizFashionService {

	/**
	 * 根据type返回尚潮流集合
	 * 
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Fashion> doFashion(String type, String pageIndex, String pageSize);

}
