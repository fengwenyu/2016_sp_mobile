package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class TimeLimitBuySale implements Serializable {
	private static final long serialVersionUID = 1L;
	private String phase;
	private String phaseTitle;
	private List<TimeLimitBuySaleList> list;

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPhaseTitle() {
		return phaseTitle;
	}

	public void setPhaseTitle(String phaseTitle) {
		this.phaseTitle = phaseTitle;
	}

	public List<TimeLimitBuySaleList> getList() {
		return list;
	}

	public void setList(List<TimeLimitBuySaleList> list) {
		this.list = list;
	}
}
