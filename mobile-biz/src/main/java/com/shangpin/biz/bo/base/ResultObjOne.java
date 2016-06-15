package com.shangpin.biz.bo.base;

import com.shangpin.utils.JsonUtil;

/**
 * 结果vo content是一个实体时 例如:T实体代表User对象 { code:0, message:xx, content: {
 * userid:"xx",gender:"xx","priceindex":"xx","name":"xx" } }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 */

public class ResultObjOne<T> extends ResultBase {
	private static final long serialVersionUID = 2109376009813111642L;
	// 内容数据
	private T content;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	/**
	 * 取得content下对应实体
	 * 
	 * @return
	 * @author zhanghongwei
	 */
	public T getObj() {
		if (!this.isSuccess()) {
			return null;
		}
		T content = this.getContent();
		if (content == null) {
			return null;
		}
		return content;
	}

	/**
	 * 如果content对象为空则显示 "content":{}
	 * 
	 * @return
	 * @author zghw
	 */
	public String toJsonNullable() {
		if (this.getCode() == null && this.getContent() == null) {
			return "{\"code\":\"1\",\"msg\":\"error\",\"content\":{}}";
		}
		if (this.getContent() == null) {
			return "{\"code\":\"" + this.getCode() + "\",\"msg\":\"" + this.getMsg() + "\",\"content\":{}}";
		}
		String result = JsonUtil.toJson(this);
		return result;
	}
}
