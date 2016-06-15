package com.shangpin.wireless.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 主页
 * @Author  zhouyu
 * @CreatDate  2012-07-12
 */
@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String index() throws Exception {
		return "index";
	}

	public String top() throws Exception {
		return "top";
	}

	public String left() throws Exception {
		return "left";
	}

	public String right() throws Exception {
		return "right";
	}

	public String bottom() throws Exception {
		return "bottom";
	}
}
