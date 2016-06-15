package com.shangpin.mobileShangpin.common.util;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238567377151210814L;

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		arg0.remove("pager.offset");
	}

}
