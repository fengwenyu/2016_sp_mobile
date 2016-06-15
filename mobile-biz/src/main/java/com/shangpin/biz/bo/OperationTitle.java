package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class OperationTitle implements Serializable {

	private static final long serialVersionUID = -11472115503729002L;

	private String title;

	private List<Operation> operation;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Operation> getOperation() {
		return operation;
	}

	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}
}
