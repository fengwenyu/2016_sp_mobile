package com.shangpin.biz.service;

import java.util.Date;
import java.util.List;

import com.shangpin.biz.bo.Activity;
import com.shangpin.biz.bo.PreSell;

public interface ALBizIndexService {
	
	/**
	 * 获取最新热卖列表（首页）
	 * 
	 * @param gender 性别
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param picw 图片宽度
	 * @param pich 图片高度
	 * @return
	 * @author cuibinqiang
	 */
	public List<Activity> getNewHotProduct(String gender, String startTime, String endTime, String picw, String pich);

	/**
	 * 获取限时特卖列表（首页）
	 * 
	 * @param gender 性别
	 * @param picw 图片宽度
	 * @param pich 图片高度
	 * @return
	 * @author cuibinqiang
	 */
	public List<Activity> getLimitedProduct(String gender,String picw, String pich);
	
    /**
     * 全部最新热卖（首页）
     * 
     * @param gender 性别
	 * @param picw 图片宽度
	 * @param pich 图片高度
     * @return
     * @author cuibinqiang
     */
	public List<Activity> getAllNewHotProduct(String gender,String picw, String pich);
	
	/**
	 * 获取预售产品列表
	 * 
	 * @param gender 性别
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param picw 图片宽度
	 * @param pich 图片高度
	 * @return
	 * @author cuibinqiang
	 */
	public List<Activity> getPreSellProduct(String startTime, String endTime, String picw, String pich);
	
	/**
	 * 获取预售日期列表
	 * @param date 选中预售日期
	 * @param id 标识ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public List<PreSell> getPreDateList(Date date, String id, String startTime, String endTime);
}
