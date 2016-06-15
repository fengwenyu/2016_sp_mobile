package com.shangpin.biz.service;

/**
 * 品类运营内容
 * 
 * @author wangfeng
 *
 */
public interface ASPBizCategoryOperationService {

	/**
	 * 品类运营内容
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	public String queryCategoryOperationToResult(String id, String userId);

	/**
	 * 品类运营内容（因图片尺寸兼容问题添加标识mark，区分版本。）
	 * 
	 * @param id
	 * @param userId
	 * @param mark
	 * @return
	 */
	public String queryCategoryOperationToResult(String id, String userId, String mark);
	
	String getCategoryOperations(String id, String userId) throws Exception;

}
