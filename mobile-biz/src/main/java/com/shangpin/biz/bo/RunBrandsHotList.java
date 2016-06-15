package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 返回的热卖品牌数据结构
 * @author Twl
 *
 */
public class RunBrandsHotList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String moduleName;
	private List<RunBrandsHot> list;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<RunBrandsHot> getList() {
		return list;
	}

	public void setList(List<RunBrandsHot> list) {
		this.list = list;
	}
	
	
}
