package com.shangpin.mobileAolai.platform.service;

import java.util.List;

import com.shangpin.mobileAolai.platform.vo.ActivitiesVO;
import com.shangpin.mobileAolai.platform.vo.TopicVO;

/**
 * 活动、专题业务逻辑接口，用于获取活动、专题列表
 * 
 * @author yumeng
 * @date:2012-10-29
 */
public interface ActivitiesService {

	/**
	 * 根据性别，获取24小时以内结束的活动列表
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * 
	 * @return 返回活动列表
	 */
	public List<ActivitiesVO> get24ActivitiesList(String gender);

	/**
	 * 根据性别、活动起始时间，获取活动列表
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * @param startTime
	 *            活动开始时间
	 * @param endTime
	 *            活动结束时间
	 * 
	 * @return 返回活动列表
	 */
	public List<ActivitiesVO> getActivitiesList(String gender,
			String startTime, String endTime);

	/**
	 * 根据性别，获取专题轮播广告列表
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * 
	 * @return 返回专题轮播广告列表
	 */
	public List<TopicVO> getTopicCarouselListt(String gender);

	/**
	 * 根据性别，获取所有最新活动列表
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * 
	 * @return 返回活动列表
	 */
	public List<ActivitiesVO> getAllActivitiesList(String gender);

	/**
	 * 根据性别，获取所有即将结束活动列表
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * 
	 * @return 返回活动列表
	 */
	public List<ActivitiesVO> getAllEndingActivitiesList(String gender);

	/**
	 * 根据性别、活动起始时间，获取活动列表，如果今日活动不足6个，在全部活动中补足6个；
	 * 
	 * @param gender
	 *            男士、女士(女士=0)
	 * @param startTime
	 *            活动开始时间
	 * @param endTime
	 *            活动结束时间
	 * 
	 * @return 返回活动列表
	 */
	public List<ActivitiesVO> getNewActivitiesList(String gender,
			String startTime, String endTime);
}
