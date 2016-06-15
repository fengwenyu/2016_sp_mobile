package com.shangpin.mobileShangpin.platform.dao;

import java.util.List;

import com.shangpin.mobileShangpin.base.model.AccountWeixinBind;
import com.shangpin.mobileShangpin.common.persistence.GenericDAO;

public interface WeixinBindDAO extends GenericDAO<AccountWeixinBind, Long> {
	/**
	 * 通过微信id获取是否绑定帐号
	 * 
	 * @param weixinid
	 *            微信id
	 * 
	 * @return 大于0表示绑定
	 */
	public long findWXBindBywxid(String weixinid) throws Exception;

	/**
	 * 通过微信id获取绑定帐号信息列表（未解绑）
	 * 
	 * @param weixinid
	 *            微信id
	 * 
	 * @return 绑定帐号信息列表
	 */
	public List<AccountWeixinBind> findWXBind(String weixinid) throws Exception;
}
