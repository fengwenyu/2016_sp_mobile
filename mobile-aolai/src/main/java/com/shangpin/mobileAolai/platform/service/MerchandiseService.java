package com.shangpin.mobileAolai.platform.service;

import com.shangpin.mobileAolai.platform.vo.ActivityVO;

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
}
