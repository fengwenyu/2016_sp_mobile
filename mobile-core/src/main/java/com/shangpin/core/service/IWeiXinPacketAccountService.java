package com.shangpin.core.service;

import com.shangpin.core.entity.WeiXinPacketAccount;

/**
 * @author qinyingchun
 *微信红包账户业务逻辑处理接口
 */
public interface IWeiXinPacketAccountService {
	
	public WeiXinPacketAccount save(WeiXinPacketAccount account);
	
	public WeiXinPacketAccount findById(long id);
	
	public WeiXinPacketAccount findByLoginName(String loginName);
	
	public WeiXinPacketAccount modify(WeiXinPacketAccount account);
	
	public void delete(long id);
	
	public WeiXinPacketAccount findByOpenId(String openId);
	

}
