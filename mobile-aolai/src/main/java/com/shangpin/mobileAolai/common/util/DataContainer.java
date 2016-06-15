package com.shangpin.mobileAolai.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Map封装数据容器，保存初始化数据，减少与数据库的交互，提高项目效率(非线程安全)。
 * 
 * @author yumeng
 * @date: 2012-11-1
 */
public class DataContainer {
	private static Map<Object, Object> map = new HashMap<Object, Object>();
	private String name;

	public DataContainer(String name) {
		this.name = name;
	}

	/**
	 * 根据key保存、覆盖容器中的对象
	 * 
	 * @param strKey
	 *            键
	 * @param msg
	 *            值
	 */
	public void putOrReplace(String strKey, Object msg) {
		if (null == msg) {
			return;
		}
		map.put(strKey, msg);
	}

	/**
	 * 根据键获取并移除容器中的对象
	 * 
	 * @param strKey
	 *            键
	 * @return 值
	 */
	public Object getAndRemove(String strKey) {
		if (this.isEmpty()) {
			return null;
		}

		return map.remove(strKey);
	}

	/**
	 * 根据键获取容器中得对象
	 * 
	 * @param strKey
	 *            键
	 * @return 值
	 */
	public Object get(String strKey) {
		if (this.isEmpty()) {
			return null;
		}
		return map.get(strKey);
	}

	/**
	 * 获取容器中所有对象
	 * 
	 * @return 所有对象
	 */
	public Map<Object, Object> getAll() {
		if (this.isEmpty()) {
			return null;
		}

		return map;
	}

	/**
	 * 判断容器是否为空，true为空
	 * 
	 * @return 是否为空
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * 清空容器
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 获取容器大小
	 * 
	 * @return 容器大小
	 */
	public int getQueueSize() {
		return map.size();
	}

	/**
	 * 获取此容器的名称
	 * 
	 * @return 容器名称
	 */
	public String getName() {
		return name;
	}
}
