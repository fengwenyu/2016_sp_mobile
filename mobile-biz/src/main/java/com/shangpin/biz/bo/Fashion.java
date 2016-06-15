package com.shangpin.biz.bo;

import java.io.Serializable;

public class Fashion extends CommonRules implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pubTime;
	private String pic;
	private String summary;
	private String source;

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
