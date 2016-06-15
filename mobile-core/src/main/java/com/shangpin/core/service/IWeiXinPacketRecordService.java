package com.shangpin.core.service;


import java.util.List;

import com.shangpin.core.entity.WeiXinPacketRecord;

/**
 * @author qinyingchun
 *微信红包记录业务逻辑处理接口
 */
public interface IWeiXinPacketRecordService {
	
	public WeiXinPacketRecord save(WeiXinPacketRecord record);
	
	public WeiXinPacketRecord findById(long id);
	
	public WeiXinPacketRecord modify(WeiXinPacketRecord record);
	
	public void delete(long id);
	
	/**
	 * 返回送出红包的列表信息
	 * @param sendLoginName 送红包账户
	 * @return
	 */
	public List<WeiXinPacketRecord> sendPacket(String sendLoginName);
	
	public List<WeiXinPacketRecord> sendPacketO(String openId);
	
	/**
	 * 收到红包列表信息
	 * @param receiveLoginName 收红包账户
	 * @return
	 */
	public List<WeiXinPacketRecord> receivePacket(String receiveLoginName);
	
	
	
	/**
	 * 判断两个两个好友是否已经相互识别了二维码
	 * @param sendOpenId
	 * @param receiveOpenId
	 * @return
	 */
	public boolean isDiscern(String sendOpenId, String receiveOpenId);
	
	public List<WeiXinPacketRecord> findByReceiveOpenId(String receiveOpenId);
}
