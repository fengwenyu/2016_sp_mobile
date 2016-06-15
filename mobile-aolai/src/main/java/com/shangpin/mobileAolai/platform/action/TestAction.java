package com.shangpin.mobileAolai.platform.action;

import java.util.ResourceBundle;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.common.page.Page;
import com.shangpin.mobileAolai.platform.service.AccountService;

/**
 *测试action，连接数据库 
 * 
 * @author:
 * @date:
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/testAction"), results = {
		@Result(name = "list", location = "/WEB-INF/pages/account/index_test.jsp"),
		@Result(name = "save", type = "redirect", location = "testAction!list"),
		@Result(name = "index", location = "/WEB-INF/index.jsp") }) })
public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063317978913110795L;

	@Autowired
	AccountService accountService;

	Page page;

	// public String list() {
	// ServletActionContext.getRequest();
	// try {
	// page = accountService.getAllAccounts();
	// accountService.getSomething("demo", 1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "list";
	// }

	@SuppressWarnings("unused")
	public String index() {
		ResourceBundle rb = ResourceBundle.getBundle("sysParameters");
		return "index";
	}

	public String save() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
