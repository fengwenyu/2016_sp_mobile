package com.shangpin.biz.bo.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shangpin.biz.bo.CodeMsgEnum;

import java.io.Serializable;

/**
 * 包装content内容建造器 主要用于构建返回json对应的对象 使用不同的构建方式，可以构建不同的错误信息对象及成功错误对象
 * 
 * @author zghw
 *
 * @param <T>
 *            构建content内容中的对象
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContentBuilder<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	private T content;

	public ContentBuilder() {
	}

	public ContentBuilder(String code, String msg, T content) {
		this.code = code;
		this.msg = msg;
		this.content = content;
	}

	/**
	 * 包装内容对象 生成json对象格式如：{ "code":"10002", "msg":"sendok", "content":{
	 * "userid":"xx", "gender":"xx", "name":"xx" } }
	 * 
	 * @param code
	 *            消息代码
	 * @param msg
	 *            消息内容
	 * @param content
	 *            内容对象
	 * @return
	 */
	public ContentBuilder<T> buildContent(String code, String msg, T content) {
		return new ContentBuilder<T>(code, msg, content);
	}

	/**
	 * 创建默认的成功信息内容对象 生成json对象格式如：{ "code":"0", "msg":"success", "content":{
	 * "userid":"xx", "gender":"xx", "name":"xx" } }
	 * 
	 * @param msg
	 *            定义消息内容
	 * @param content
	 *            内容对象
	 * @return
	 */
	public ContentBuilder<T> buildDefSuccess(String msg, T content) {
		return buildContent(CodeMsgEnum.CODE_SUCCESS.getInfo(), msg, content);
	}

	/**
	 * 创建默认的成功信息内容对象 生成json对象格式如：{ "code":"0", "msg":"", "content":{
	 * "userid":"xx", "gender":"xx", "name":"xx" } }
	 * 
	 * @param content
	 *            内容对象
	 * @return
	 */
	public ContentBuilder<T> buildDefSuccess(T content) {
		return buildContent(CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo(), content);
	}

	/**
	 * 创建默认的成功信息内容对象 生成json对象格式如：{ "code":"0", "msg":"", "content":null }
	 * 其中content为空
	 * 
	 * @return
	 */
	public ContentBuilder<T> buildDefSuccess() {
		return buildDefSuccess(null);
	}

	/**
	 * 创建默认的错误信息内容对象 生成json对象格式如：{ "code":"1", "msg":"error", "content":{
	 * "userid":"xx", "gender":"xx", "name":"xx" } }
	 * 
	 * @param msg
	 *            定义消息内容
	 * @param content
	 *            内容对象
	 * @return
	 */
	public ContentBuilder<T> buildDefError(String msg, T content) {
		return buildContent(CodeMsgEnum.CODE_ERROR.getInfo(), msg, content);
	}

	/**
	 * 创建默认的错误信息内容对象 生成json对象格式如：{ "code":"1", "msg":"", "content":{
	 * "userid":"xx", "gender":"xx", "name":"xx" } }
	 * 
	 * @param content
	 *            内容对象
	 * @return
	 */
	public ContentBuilder<T> buildDefError(T content) {
		return buildContent(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo(), content);
	}

	/**
	 * 创建默认的失败信息内容对象 生成json对象格式如：{ "code":"1", "msg":"", "content":null }
	 * 其中content为空
	 * 
	 * @return
	 */
	public ContentBuilder<T> buildDefError() {
		return buildDefError(null);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
