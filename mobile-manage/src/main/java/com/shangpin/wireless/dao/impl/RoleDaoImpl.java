package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.RoleDao;
import com.shangpin.wireless.domain.Role;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(RoleDao.DAO_NAME)
public class RoleDaoImpl extends ManageBaseDaoImpl<Role> implements RoleDao {

}
