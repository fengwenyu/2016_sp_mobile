package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ManageBaseDao;
import com.shangpin.wireless.domain.User;

/**
 * @Author zhouyu
 * @CreatDate 2012-07-11
 */
public interface UserDao extends ManageBaseDao<User> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.UserDaoImpl";

	/**
	 * 根据用户名与密码查询一个用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param loginName
	 * @param password
	 * @Return 返回一个游离态的User实体
	 */
	public User findByLoginNameAndPassword(String loginName, String password);

	public User getById(Long id) throws Exception;
}
