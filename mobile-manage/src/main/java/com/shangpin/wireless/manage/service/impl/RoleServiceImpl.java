package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.RoleDao;
import com.shangpin.wireless.domain.Role;
import com.shangpin.wireless.manage.service.RoleService;

/**
 * 角色管理
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-13
 */
@Service(RoleService.SERVICE_NAME)
public class RoleServiceImpl implements RoleService {
	@Resource(name = RoleDao.DAO_NAME)
	private RoleDao roleDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(Role entity) throws Exception {
		roleDao.save(entity);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void update(Role entity) throws Exception {
		roleDao.update(entity);
	}

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		roleDao.delete(id);
	}
}
