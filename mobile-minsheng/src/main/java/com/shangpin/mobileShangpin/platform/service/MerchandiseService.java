package com.shangpin.mobileShangpin.platform.service;

import java.util.List;
import com.shangpin.mobileShangpin.platform.vo.ActivityVO;
import com.shangpin.mobileShangpin.platform.vo.CmsTopVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;
import com.shangpin.mobileShangpin.platform.vo.SPTopicVO;

public interface MerchandiseService {
	
	/**
	 * 商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param activityId
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return ActivityVO 活动传输对象
	 */
	public ActivityVO activityVO(String activityId, String typeFlag, Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 尚品专题商品列表
	 * 
	 * @param topicID
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return SPTopicVO 尚品专题传输对象
	 */
	public SPTopicVO getTopicProducts(String topicID, String gender, String userid, Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 进入商品详情页
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-06
	 * @param goodsid
	 *            商品id
	 * @param typeFlag
	 *            代表来自专题还是活动
	 * @param categoryno
	 *            商品所在活动或者专题的id
	 * @Return List 商品集合
	 */
	public void merchandiseDetail(String goodsid, String typeFlag, String categoryno) throws Exception;

	/**
	 * 进入尚品商品详情页
	 * 
	 * @param productid
	 *            商品id
	 * @param userid
	 *            用户id
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	public SPMerchandiseVO spMerchandiseDetail(String productid, String userid, String topicid) throws Exception;
	/**
	 * 根据类别获取商品列表
	 * 
	 * @param category
	 *            类别id
	 * 
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	public List<SPMerchandiseVO> getCategoryByProducts(String category, String gender,String userId) throws Exception;

	/**
	 * 获取新品商品列表
	 * 
	 * @param gender
	 *            性别
	 * @param pageSize 
	 * 
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	public List<SPMerchandiseVO> getNewProducts(String categoryid,String gender, String userid,
			Integer pageIndex, Integer pageSize);
	
	/**
	 * 尚品新专题商品列表
	 * 
	 * @param topicID
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return SPTopicVO 尚品专题传输对象
	 */
	public CmsTopVO getNewTopicProducts(String topicID, String gender, String userid, Integer pageIndex, Integer pageSize) throws Exception;
}
