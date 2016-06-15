package com.shangpin.wechat.bo.base;

import java.io.Serializable;

public class MenuList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuButton menu;

	public MenuButton getMenu() {
		return menu;
	}

	public void setMenu(MenuButton menu) {
		this.menu = menu;
	}
	

}
