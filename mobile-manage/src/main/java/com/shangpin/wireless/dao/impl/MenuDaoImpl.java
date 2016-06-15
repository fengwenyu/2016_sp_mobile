package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wechat.bo.base.Menu;
import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.MenuDao;

@Repository(MenuDao.DAO_NAME)
public class MenuDaoImpl extends ApiBaseDaoImpl<Menu> implements MenuDao{

}
