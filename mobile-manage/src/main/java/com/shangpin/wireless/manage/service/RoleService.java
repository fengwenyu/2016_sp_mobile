package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.Role;

/**
 * 角色管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-13
 */
public interface RoleService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.RoleServiceImpl";

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(Role entity) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(Role entity) throws Exception;
	/**
	 * 删除实体
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param id
	 * @Return 
	 */
	public void delete(Long id) throws Exception;
}
