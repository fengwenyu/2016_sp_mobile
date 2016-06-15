package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.Collect;
import com.shangpin.biz.bo.CollectProductList;
import com.shangpin.biz.bo.base.ResultObjMapList;
/**
 * 收藏基础接口
 * 
 * @author zghw
 *
 */
public interface IBizCollectService {
	/**
	 * 收藏列表
	 * 
	 * @param userId
	 *            用户标识
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示条数
	 * @param type
	 *            来源，1尚品，2奥莱
	 * @return
	 * @author zghw
	 */
	public String fromCollect(String userId, String pageIndex, String pageSize, String type);

	/**
	 * 收藏列表
	 * 
	 * @param userId
	 *            用户标识
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页显示条数
	 * @param type
	 *            来源，1尚品，2奥莱
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<Collect> beCollect(String userId, String pageIndex, String pageSize, String type);
	
	public String fromCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea);
	/**
	 * 收藏商品列表
	 * 
	 * @param userId
	 *            用户id
	 * @param shopType
	 *            1来自尚品，2来自奥莱
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<CollectProductList> beCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea);
}
