package com.shangpin.biz.bo.base;

import java.util.List;
import java.util.Map;

import com.shangpin.utils.JsonUtil;

/**
 * 
 * 结果vo content是Map<String,List<T>> 例如:Map<String,List<T>>为
 * Map<String,List<User>> { code:0, message:xx,
 * （==注意：Map中的的key(String类型)代表"list"这个字符串==） content:{ list: [
 * {userid:"xx1",gender:"xx1","priceindex":"xx1","name":"xx1"},
 * {userid:"xx2",gender:"xx2","priceindex":"xx2","name":"xx2"},
 * {userid:"xxn",gender:"xxn","priceindex":"xxn","name":"xxn"} ] } }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 */

public class ResultObjMapList<T> extends ResultBase {
	private static final long serialVersionUID = -1795280591337260731L;
	// 内容数据
	private Map<String, List<T>> content;

	public Map<String, List<T>> getContent() {
		return content;
	}

	public void setContent(Map<String, List<T>> content) {
		this.content = content;
	}

	/**
	 * 得到是返回成功的Map
	 * 
	 * @return
	 */
	public Map<String, List<T>> getMap() {
		if (!isSuccess()) {
			return null;
		}
		Map<String, List<T>> content = this.getContent();
		return content;
	}

	/**
	 * 
	 * 取得content下list对应集合
	 * 
	 * @param key
	 *            content下 单个字段对应的key字符串 比如“list” “promotion”
	 * @return
	 * @author zhanghongwei
	 */
	public List<T> getList(String key) {
		Map<String, List<T>> content = getMap();
		if (content == null) {
			return null;
		}
		List<T> list = content.get(key);
		if (list == null || list.size() <= 0) {
			return null;
		}
		return list;
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
