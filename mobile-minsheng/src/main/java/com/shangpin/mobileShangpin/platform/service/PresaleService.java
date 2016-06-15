package com.shangpin.mobileShangpin.platform.service;

import java.util.Date;
import java.util.List;

import com.shangpin.mobileShangpin.platform.vo.ActivitiesVO;
import com.shangpin.mobileShangpin.platform.vo.PresaleVO;

public interface PresaleService {
	/**
	 * 活动列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @Return List 活动集合
	 */
	public List<ActivitiesVO> activityList(String startTime, String endTime) throws Exception;

	/**
	 * 日期列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-31
	 * @param date
	 *            当前时间
	 * @param num
	 *            天数
	 * @param startTime
	 *            起始时间
	 * @param endTime
	 *            结束时间
	 * @Return
	 */
	public List<PresaleVO> dateList(Date date, String id, String startTime, String endTime) throws Exception;
}
