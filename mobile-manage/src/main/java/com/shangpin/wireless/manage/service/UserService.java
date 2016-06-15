package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 用户管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
public interface UserService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.UserServiceImpl";

	/**
	 * 根据用户名与密码查询一个用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param loginName
	 * @param password
	 * @Return 返回一个游离态的User实体
	 */
	User findByLoginNameAndPassword(String loginName, String password) throws Exception;

	/**
	 * 获取分页数据对象
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param pageNum
	 *            页码
	 * @param：hqlHelper 查询对象
	 * @Return PageBean
	 */
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) throws Exception;

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	User getByCondition(HqlHelper hqlHelper) throws Exception;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void save(User entity) throws Exception;

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	User getById(Long id) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	void update(User entity) throws Exception;
	/**
	 * 删除实体
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return
	 */
	void delete(Long id) throws Exception;
}
