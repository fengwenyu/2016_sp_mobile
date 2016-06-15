package com.shangpin.wireless.domain;


public class WechatCreateCardBaseInfoDateInfo implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;
	
	
	private String type;//   是   string  DATE_TYPE_FIX_TIME_RANGE 表示固定日期区间，DATE_TYPE_FIX_TERM表示固定时长（自领取后按天算。   使用时间的类型，旧文档采用的1和2依然生效。
	private String begin_timestamp;//   否   unsigned int  14300000  type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间，单位为秒）
	private String end_timestamp;//    否   unsigned int  15300000  type为DATE_TYPE_FIX_TIME_RANGE时专用，表示结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
	private String fixed_term;//   否   int   15  type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。
	private String fixed_begin_term;//   否   int   0   type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBegin_timestamp() {
		return begin_timestamp;
	}
	public void setBegin_timestamp(String begin_timestamp) {
		this.begin_timestamp = begin_timestamp;
	}
	public String getEnd_timestamp() {
		return end_timestamp;
	}
	public void setEnd_timestamp(String end_timestamp) {
		this.end_timestamp = end_timestamp;
	}
	public String getFixed_term() {
		return fixed_term;
	}
	public void setFixed_term(String fixed_term) {
		this.fixed_term = fixed_term;
	}
	public String getFixed_begin_term() {
		return fixed_begin_term;
	}
	public void setFixed_begin_term(String fixed_begin_term) {
		this.fixed_begin_term = fixed_begin_term;
	}


	
}
