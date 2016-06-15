package com.shangpin.pay.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class SPDBPayParamsBo implements Serializable {
	private static final long serialVersionUID = 498996176085120334L;
	private SPDBPayValBo val;
	private String err;
	private String callId;

	public SPDBPayValBo getVal() {
		return val;
	}

	public void setVal(SPDBPayValBo val) {
		this.val = val;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

}
