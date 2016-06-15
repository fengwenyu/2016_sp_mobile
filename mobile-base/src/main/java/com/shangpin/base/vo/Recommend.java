package com.shangpin.base.vo;

import java.io.Serializable;

public class Recommend  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String pic;
	
	private String link;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
