package com.shangpin.biz.service;

import com.shangpin.biz.bo.ProductCount;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.SPmerchandise;
import com.shangpin.biz.service.basic.IBizProductService;



/** 
* @ClassName: SPProductService 
* @Description:商品详情页接口 
* @author qinyingchun
* @date 2014年11月3日
* @version 1.0 
*/
public interface SPBizProductService extends IBizProductService{
	
	/**
	 * 
	* @Title: detail 
	* @Description:商品详情数据
	* @paramp roductNo 商品编号
	* @return Merchandise
	* @throws 
	* @Create By qinyingchun
	* @Create Date 2014年11月3日
	 */
	public SPmerchandise detail(String productNo, String topicId, String userId, String width, String height);
	
	/**
	 * 
	 * @Title:findProductDetail
	 * @Description:商品详情页接口(5.4)
	 * @param activityId
	 *            活动编号
	 * @param productId
	 *            商品编号
	 * @param userId
	 *            用户id
	 * @return
	 * @author liling
	 * @date 2015年3月13日
	 */
	public ProductDetail findProductDetail(String activityId, String productId, String userId,String picNo) ;
	
	/**
	 * 查询商品库存数量
	 * @param productNo
	 * @return
	 */
	public ProductCount productCount(String productNo);

}
