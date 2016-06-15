package com.shangpin.biz.bo.base;

import java.util.Map;

/**
 * 
 * 结果vo content是Map<String,T> 例如:Map<String,T>为
 * Map<String,User> { code:0, message:xx,
 * （注意：Map中的的key(String类型)代表"user"这个字符串） content:{ user: 
 * {userid:"xx1",gender:"xx1","priceindex":"xx1","name":"xx1"} } }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 */

public class ResultObjMap<T> extends ResultBase {
    private static final long serialVersionUID = -622976415994662853L;
    // 内容数据
	private Map<String, T> content;

	public Map<String, T> getContent() {
		return content;
	}

	public void setContent(Map<String, T> content) {
		this.content = content;
	}

	/**
	 * 得到是返回成功的Map
	 * 
	 * @return
	 */
	public Map<String, T> getMap() {
		if (!isSuccess()) {
			return null;
		}
		Map<String, T> content = this.getContent();
		return content;
	}

	/**
	 * 
	 * 取得content下对象
	 * 
	 * @param key
	 *            content下 单个字段对应的key字符串 比如“user” “promotion”
	 * @return
	 * @author zhanghongwei
	 */
	public T getObj(String key) {
		Map<String, T> content = getMap();
		if (content == null) {
			return null;
		}
		return content.get(key);
	}
}
