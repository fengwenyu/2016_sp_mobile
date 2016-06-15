package com.shangpin.wireless.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangpin.wechat.bo.base.ErrorInfo;
import com.shangpin.wechat.bo.base.Menu;
import com.shangpin.wechat.bo.base.MenuButton;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.base.action.BaseAction;

@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WeChatPublicService weChatPublicService;
	
	private String menuStr;
	private MenuButton menus;
	private ErrorInfo info;
	private String jsonMenu;
	
	/**
	 * 获取菜单
	 * @return
	 */
	public String list(){
		String token = weChatPublicService.getToken();
		menus = weChatPublicService.getMenuObj(token);
		return "list";
	}
	
	/**
	 * 创建菜单
	 * @param jsonMenu 菜单json结构
	 */
	public String create(){
		//String token = "TYNBUJRxneCHmcdtOCS31SUy5AVuqPCJoq0aYrtvSw_4C9figUxyub76EiRVFrn1PA9VMP-hnelcvPoQohx811Bx9IHrLlzp8HKA9IesfL8FGNaABAXYF";
		String token = weChatPublicService.getToken();
		info = weChatPublicService.createMenu(token, jsonMenu);
		return SUCCESS;
	}

	public String del(){
		String token = weChatPublicService.getToken();
		info = weChatPublicService.delMenu(token);
		return SUCCESS;
	}
	
	public ErrorInfo getInfo() {
		return info;
	}

	public void setInfo(ErrorInfo info) {
		this.info = info;
	}

	public MenuButton getMenus() {
		return menus;
	}


	public void setMenus(MenuButton menus) {
		this.menus = menus;
	}


	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public String getJsonMenu() {
		return jsonMenu;
	}

	public void setJsonMenu(String jsonMenu) {
		this.jsonMenu = jsonMenu;
	}
	
}
