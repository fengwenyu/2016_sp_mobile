package com.shangpin.biz.service;

import com.shangpin.biz.bo.ActivityLv2;
import com.shangpin.biz.bo.Merchandise;
import com.shangpin.biz.service.basic.IBizProductService;

public interface ALBizProductService extends IBizProductService {

	/**
	 * 二级活动列表
	 * 
	 * @param activityId
	 *            活动ID
	 * @param typeFlag
	 *            活动/专题入口标识：1 活动 ，0 专题
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页条数条数
	 * @return
	 */
	public ActivityLv2 getActivityLv2List(String activityId, String typeFlag, Integer pageIndex, Integer pageSize);

	/**
	 * 商品详情
	 * 
	 * @param categoryNo
	 *            活动子类编号
	 * @param goodsId
	 *            商品ID
	 * @param typeFlag
	 *            活动/专题入口标识：1 活动 ，0 专题
	 * @return
	 */
	public Merchandise getDetail(String categoryNo, String goodsId, String typeFlag);
}
