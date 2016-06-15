package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wechat.bo.base.Menu;

/**微信菜单业务操作接口
 * @author qinyingchun
 *
 */
public interface MenuService {
	
	public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.MenuServiceImpl";
	
	/**
	 * 获取微信菜单列表
	 * @return
	 */
	public List<Menu> get();
	
	/**
	 * 创建菜单
	 * @param menu
	 */
	public void create(Menu menu);
	
	/**
	 * 修改菜单
	 * @param menu
	 */
	public void update(Menu menu);
	
	
	/**
	 * 删除某项菜单包括其子菜单
	 * @param id
	 */
	public void del(long id);
	
	/**
	 * 删除整个微信菜单
	 */
	public void delAll();

}
