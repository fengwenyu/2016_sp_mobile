package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.Collect;
import com.shangpin.biz.bo.CollectProduct;
import com.shangpin.biz.service.basic.IBizCollectService;

/** 
* @ClassName: SPBizeCollectService 
* @Description: 个人中心收藏接口
* @author qinyingchun
* @date 2014年11月22日
* @version 1.0 
*/
public interface SPBizCollectService extends  IBizCollectService{
	
	/**
	 * 
	* @Title: collectList 
	* @Description:收藏列表
	* @param userId 用户标识
	* @param pageIndex 页码
	* @param pageSize 每页显示条数
	* @param type 来源，1尚品，2奥莱
	* @return List<Collect>
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月22日
	 */
	public List<Collect> collectList(String userId, String pageIndex, String pageSize, String type);
	/**
	 * 
	* @Title: collectList 
	* @Description:收藏商品列表
	* @param userId 用户标识
	* @param pageIndex 页码
	* @param pageSize 每页显示条数
	* @param type 来源，1尚品，2奥莱
	* @param postArea 商品来源，0全部，1国内，2海外
	* @return List<Collect>
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2015年3月26日
	 */
	public List<CollectProduct> collectProductList(String userId, String pageIndex, String pageSize, String type, String postArea);
	
	/**
	 * 收藏品牌列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Collect> collectBrands(String userId, String pageIndex, String pageSize);

	Map<String, Object> collectBrand(String userId, String brandId);

	Map<String, Object> cancleCollectBrand(String userid, String brandId);

	Map<String, Object> collectProduct(String shopType, String skuId, String userId, String picw, String pich, String detailPicw, String detailPich);

	Map<String, Object> cancleCollectProduct(String shopType, String collectId, String userId);

}
