package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.Recommend;
import com.shangpin.biz.bo.WorthTitle;

/**
 * 推荐单品接口
 * 
 * @author huangxiaoliang
 *
 */
public interface SPBizRecommendService {

	/**
	 * 人气排行榜和今日值得买集合
	 * 
	 * @param type
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author zghw
	 */
	public String fromFindPopularityAndWorth(String type, String userId, String pageIndex, String pageSize);

	/**
	 * 首页人气排行榜和今日值得买集合
	 * 
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Recommend> doRecommendList(String userId) throws Exception;

	/**
	 * 为你推荐
	 * 
	 * @param type
	 * @param userId
	 * @param imei
	 * @param coord
	 * @param ip
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	String doRecProduct(String type, String userId, String imei, String coord, String ip, String pageIndex, String pageSize) throws Exception;

	/**
	 * 人气榜或值得买
	 * 
	 * @param type
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	WorthTitle doRecProduct(String type, String userId, String pageIndex, String pageSize) throws Exception;
	/**
     * 推荐商品
     * 
     * @param type
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @param productId
     * @return
     */
    List<Product> doRecProduct(String type, String userId, String shopType, String pageIndex, String pageSize, String productId) throws Exception;

}
