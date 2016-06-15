package com.shangpin.wireless.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.PrivilegeDao;
import com.shangpin.wireless.domain.Privilege;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(PrivilegeDao.DAO_NAME)
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl extends ManageBaseDaoImpl<Privilege> implements PrivilegeDao {
	/**
	 *  查询所有顶级权限的列表
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	/**
	 *  获取数据库中所有权限URL的集合（这就是要控制的功能集合，这个集合是不重复的）
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	public List<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
