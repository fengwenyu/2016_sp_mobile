package com.shangpin.biz.bo.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class SearchCommon implements Serializable {

	private static final long serialVersionUID = -9088016116349683725L;

	private String sid;

	private String status;

	private String discription;

	private String qTime;

	@XmlElement(name = "SID")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@XmlElement(name = "Status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "Discription")
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@XmlElement(name = "QTime")
	public String getqTime() {
		return qTime;
	}

	public void setqTime(String qTime) {
		this.qTime = qTime;
	}

	// @XmlElement(name = "SID")
	// public String getSID() {
	// return SID;
	// }
	//
	// public void setSID(String sID) {
	// SID = sID;
	// }
	//
	// @XmlElement(name = "Status")
	// public String getStatus() {
	// return Status;
	// }
	//
	// public void setStatus(String status) {
	// Status = status;
	// }
	//
	// @XmlElement(name = "Discription")
	// public String getDiscription() {
	// return Discription;
	// }
	//
	// public void setDiscription(String discription) {
	// Discription = discription;
	// }
	//
	// @XmlElement(name = "QTime")
	// public String getQTime() {
	// return QTime;
	// }
	//
	// public void setQTime(String qTime) {
	// QTime = qTime;
	// }

}
