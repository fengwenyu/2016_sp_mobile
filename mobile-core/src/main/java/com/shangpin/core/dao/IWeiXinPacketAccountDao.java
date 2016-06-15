package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeiXinPacketAccount;

/**
 * @author qinyingchun
 * 微信红包账户数据处理接口，访问数据库数据
 *
 */
public interface IWeiXinPacketAccountDao extends JpaRepository<WeiXinPacketAccount, Long>, JpaSpecificationExecutor<WeiXinPacketAccount> {
	
	public WeiXinPacketAccount findByLoginName(String loginName);
	
	public WeiXinPacketAccount findByOpenId(String openId);

}
