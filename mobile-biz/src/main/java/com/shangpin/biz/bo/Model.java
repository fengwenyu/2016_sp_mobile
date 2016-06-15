package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable {

	private static final long serialVersionUID = -1193481064521526910L;

	private String type;

	private List<ModelOne> model;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ModelOne> getModel() {
		return model;
	}

	public void setModel(List<ModelOne> model) {
		this.model = model;
	}

}
