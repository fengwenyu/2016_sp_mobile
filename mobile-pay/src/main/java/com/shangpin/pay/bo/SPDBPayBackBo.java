package com.shangpin.pay.bo;

import java.io.Serializable;

public class SPDBPayBackBo implements Serializable {

	private static final long serialVersionUID = 980015119798669795L;
	private SPDBPayBackValBo val;
	private String callId;
	private String err;

	public SPDBPayBackValBo getVal() {
		return val;
	}

	public void setVal(SPDBPayBackValBo val) {
		this.val = val;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

}
