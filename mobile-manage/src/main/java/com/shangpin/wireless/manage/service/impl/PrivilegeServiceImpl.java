package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.PrivilegeDao;
import com.shangpin.wireless.domain.Privilege;
import com.shangpin.wireless.manage.service.PrivilegeService;

/**
 * 权限操作
 * 
 * @Author  zhouyu
 * @CreatDate  2012-07-12
 */
@Service(PrivilegeService.SERVICE_NAME)
public class PrivilegeServiceImpl implements PrivilegeService {

	@Resource(name = PrivilegeDao.DAO_NAME)
	private PrivilegeDao privilegeDao;

	/**
	 * 查询所有顶级权限的列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	public List<Privilege> findTopList() throws Exception {
		return privilegeDao.findTopList();
	}

	/**
	 * 查询所有顶级权限的列表
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-12
	 * @param
	 * @Return  List
	 */
	public List<String> getAllPrivilegeUrls() throws Exception {
		return privilegeDao.getAllPrivilegeUrls();
	}
	/**
	 * 根据id数组获取实体集合，如果ids为null或是没有元素，则返回一个空的集合
	 * 
	 * @Author  zhouyu
	 * @CreatDate  2012-07-11
	 * @param ids
	 * @Return  List
	 */
	public List<Privilege> getByIds(Long[] ids) throws Exception {
		return privilegeDao.getByIds(ids);
	}
}
