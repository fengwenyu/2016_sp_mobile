package com.shangpin.biz.bo;

public enum CodeMsgEnum {
	// 公共的成功code
	CODE_SUCCESS("0"),
	// 公共的错误code
	CODE_ERROR("1"),
	// 成功msg
	MSG_SUCCESS(""),
	// 立即购买错误msg
	MSG_BUY_ERROR("立即购买失败！"),
	// 预留的错误msg
	MSG_ERROR("");

	private String info;

	CodeMsgEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
