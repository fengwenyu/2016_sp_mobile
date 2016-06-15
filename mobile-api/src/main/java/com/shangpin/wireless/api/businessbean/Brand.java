package com.shangpin.wireless.api.businessbean;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 用于将更多品类XML文件转换为javabean实体类（品牌单一实体）
 * 
 * @author yumeng
 *
 */
@XObject
public class Brand {
	/** 品牌ID */
	@XNode("id")
	private String id;
	/** 品牌名称 */
	@XNode("name")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}