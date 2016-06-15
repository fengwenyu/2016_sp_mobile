package com.shangpin.biz.service;

import java.util.Set;

public interface SPBizCommonHanderService {
	
	/**
	 * 获取可以合并支付的平台版本
	 * @return
	 */
	public Set<String> useCombinePay();
	
	/**
	 * 增加一个平台版本
	 * @param member
	 */
	public void add(String member);
	
	/**
	 * 删除一个成员
	 * @param member
	 */
	public void remove(String member);

}
