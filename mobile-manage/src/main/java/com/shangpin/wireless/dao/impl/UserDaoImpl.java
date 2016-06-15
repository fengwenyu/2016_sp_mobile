package com.shangpin.wireless.dao.impl;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.UserDao;
import com.shangpin.wireless.domain.PushconfigAolai;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.util.HqlHelper;

/**
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@Repository(UserDao.DAO_NAME)
public class UserDaoImpl extends ManageBaseDaoImpl<User> implements UserDao {
	/**
	 * 根据用户名与密码查询一个用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-12
	 * @param loginName
	 * @param password
	 * @Return 返回一个游离态的User实体
	 */
	public User findByLoginNameAndPassword(final String loginName, final String password) {
		// 密码要使用MD5摘要
		final String passwordMd5 = DigestUtils.md5Hex(password);
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session) throws SQLException {
//				Object obj = session.createQuery(//
//						"FROM User u WHERE u.loginName=? AND u.pwd=?")//
//						.setParameter(0, loginName)//
//						.setParameter(1, passwordMd5)//
//						.uniqueResult();
//				if (obj != null) {
//					User user = (User) obj;
//					Hibernate.initialize(user.getRole());
//					return user;
//				}
//				return null;
//			}
//		};
//		Object obj = getHibernateTemplate().execute(callback);
		HqlHelper hqlHelper = new HqlHelper(User.class, "u");
		hqlHelper.addWhereCondition("u.loginName=?", loginName);
		hqlHelper.addWhereCondition("u.pwd=?", passwordMd5);
		Object obj = null;
		try {
			obj = super.getByCondition(hqlHelper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (obj != null) {
			return (User) obj;
		} else {
			return null;
		}
	}

	public User getById(Long id) throws Exception {
		if (id == null) {
			return null;
		} else {
			User user = (User) super.getById(id);//getHibernateTemplate().get(User.class, id);
			Hibernate.initialize(user.getRole());
			return user;
		}
	}
}
