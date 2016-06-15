package com.shangpin.biz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.ProductType;
import com.shangpin.core.entity.FindManage;

/**
 * @ClassName: SubjectBizService
 * @Description:活动service
 * @author liling
 * @date 2014年11月27日
 * @version 1.0
 */
public interface SPBizSubjectService {
	/**
	 * 获取活动列表
	 * 
	 * @param dateTime当前时间
	 * @param type活动类型
	 * @return List<FindManage>
	 */
	public List<FindManage> findSubjectList(Date dateTime, String type);

	/**
	 * 获取活动商品列表
	 * 
	 * @param topicId活动id
	 * @param gender性别
	 * @param userId用户id
	 * @param pageIndex
	 * @param pageSize
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getNewTopicProducts(String topicId,
			String gender, String userId, String pageIndex, String pageSize);
	/**
	 * 获取活动列表静态模版
	 * 
	 * @param topicId活动id
	 * @param toLink 链接是否可用 0，不可用，1可用
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getTopAdver(String topicId, String toLink,ProductType source);

}
