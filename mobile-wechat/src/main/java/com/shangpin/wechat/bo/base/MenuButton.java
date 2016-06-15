package com.shangpin.wechat.bo.base;

import java.io.Serializable;
import java.util.List;

public class MenuButton implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Menu> button;

	public List<Menu> getButton() {
		return button;
	}

	public void setButton(List<Menu> button) {
		this.button = button;
	}
	

}
