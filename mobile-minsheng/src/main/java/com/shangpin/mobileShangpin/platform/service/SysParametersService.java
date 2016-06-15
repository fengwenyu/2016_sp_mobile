package com.shangpin.mobileShangpin.platform.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.shangpin.mobileShangpin.platform.vo.ProvinceVO;

/**
 * 系统参数业务逻辑接口，用于获取系统数据，如：省、市、区
 * 
 * @author yumeng
 * @date:2012-11-9
 */
public interface SysParametersService {

	/**
	 * 获取省级数据列表
	 * 
	 * @return 返回省级数据列表
	 */
	public List<ProvinceVO> getProvinceList();

	/**
	 * 通过省级ID，获取市级数据列表
	 * 
	 * @return 返回市级数据列表
	 */
	public List<ProvinceVO> getCityList(String id);

	/**
	 * 通过市级ID，获取区级数据列表
	 * 
	 * @return 返回市级数据列表
	 */
	public List<ProvinceVO> getAreaList(String id);

	/**
	 * 通过省级ID，获取市级json数据列表
	 * 
	 * @return 返回市级数据列表
	 */
	public JSONObject getCityJson(String id);

	/**
	 * 通过市级ID，获取区级json数据列表
	 * 
	 * @return 返回市级数据列表
	 */
	public JSONObject getAreaJson(String id);
}
