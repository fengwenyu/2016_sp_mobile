package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class TimeLimitBuy implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<TimeLimitBuySale> sale;

	public List<TimeLimitBuySale> getSale() {
		return sale;
	}

	public void setSale(List<TimeLimitBuySale> sale) {
		this.sale = sale;
	}

}
