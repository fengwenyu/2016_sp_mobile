package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wechat.bo.base.Menu;
import com.shangpin.wireless.dao.MenuDao;
import com.shangpin.wireless.manage.service.MenuService;

@Service(MenuService.SERVICE_NAME)
public class MenuServiceImpl implements MenuService{
	
	@Resource(name = MenuDao.DAO_NAME)
	private MenuDao menuDao;

	@Override
	public List<Menu> get() {
		try {
			return menuDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(Menu menu) {
		try {
			menuDao.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Menu menu) {
		try {
			menuDao.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(long id) {
		try {
			menuDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delAll() {
		
	}

}
