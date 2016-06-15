package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.Privilege;

/**
 * 权限操作
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-12
 */
public interface PrivilegeService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.PrivilegeServiceImpl";

	/**
	 * 查询所有顶级权限的列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	List<Privilege> findTopList() throws Exception;

	/**
	 * 查询所有顶级权限的列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	List<String> getAllPrivilegeUrls() throws Exception;
	
	/**
	 * 根据id数组获取实体集合，如果ids为null或是没有元素，则返回一个空的集合
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param ids
	 * @Return  List
	 */
	public List<Privilege> getByIds(Long[] ids) throws Exception;

}
