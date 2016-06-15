package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.UserDao;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.manage.service.UserService;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 用户管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@Service(UserService.SERVICE_NAME)
public class UserServiceImpl implements UserService {
	@Resource(name = UserDao.DAO_NAME)
	private UserDao userDao;

	/**
	 * 根据用户名与密码查询一个用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param loginName
	 * @param password
	 * @Return 返回一个游离态的User实体
	 */
	public User findByLoginNameAndPassword(String loginName, String password) {
		return userDao.findByLoginNameAndPassword(loginName, password);
	}

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
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) throws Exception {
		return userDao.getPageBean(pageNum, hqlHelper);
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public User getByCondition(HqlHelper hqlHelper) throws Exception {
		return userDao.getByCondition(hqlHelper);
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(User entity) throws Exception {
		userDao.save(entity);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return T
	 */
	public User getById(Long id) throws Exception {
		return userDao.getById(id);
	}
	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void update(User entity) throws Exception {
		userDao.update(entity);
	}
	/**
	 * 删除实体
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		userDao.delete(id);
	}
}
