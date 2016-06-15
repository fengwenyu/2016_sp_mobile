package com.shangpin.wireless.dao;

import java.util.List;

import com.shangpin.wireless.base.dao.ManageBaseDao;
import com.shangpin.wireless.domain.Privilege;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-11
 */
public interface PrivilegeDao extends ManageBaseDao<Privilege> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.PrivilegeDaoImpl";

	/**
	 *  查询所有顶级权限的列表
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param
	 * @Return  List
	 */
	public List<Privilege> findTopList();

	/**
	 *  获取数据库中所有权限URL的集合（这就是要控制的功能集合，这个集合是不重复的）
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param
	 * @Return  List
	 */
	public List<String> getAllPrivilegeUrls();
	
}
