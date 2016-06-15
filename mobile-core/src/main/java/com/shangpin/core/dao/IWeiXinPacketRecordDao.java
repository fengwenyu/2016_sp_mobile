package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeiXinPacketRecord;
/**
 * @author qinyingchun
 * 微信红包记录数据处理接口，访问数据库数据
 *
 */
public interface IWeiXinPacketRecordDao extends JpaRepository<WeiXinPacketRecord, Long>, JpaSpecificationExecutor<WeiXinPacketRecord> {
	
	

	public List<WeiXinPacketRecord> findBySendLoginName(String sendLoginName);
	
	public List<WeiXinPacketRecord> findBySendOpenIdOrderByCreateTimeDesc(String openId);
	
	public List<WeiXinPacketRecord> findByReceiveLoginName(String receiveLoginName);
	
	public List<WeiXinPacketRecord> findByReceiveOpenIdOrderByCreateTimeDesc(String openId);
												 
	public WeiXinPacketRecord findBySendOpenIdAndReceiveOpenId(String sendOpenId, String receiveOpenId);
	

}
