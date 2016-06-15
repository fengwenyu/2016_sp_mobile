package com.shangpin.biz.service;

/**
 * 四级地址行政区信息接口
 * 
 * @author cuibinqiang
 *
 */
public interface ASPBizDistrictService {

	/**
	 * 获取省份行政区
	 * 
	   @return
	
	   @author cuibinqiang
	 */
	public String provinceList();
	
	/**
	 * 获取城市行政区
	 * 
	   @param id 省份ID
	   @return
	
	   @author cuibinqiang
	 */
	public String cityList(String id);
	
	/**
	 * 获取地区行政区
	 * 
	   @param id 城市ID
	   @return
	
	   @author cuibinqiang
	 */
	public String areaList(String id);
	
	/**
	 * 获取县镇行政区
	 * 
	   @param id 地区ID
	   @return
	
	   @author cuibinqiang
	 */
	public String townList(String id);
}
