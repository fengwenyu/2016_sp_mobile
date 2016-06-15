package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeiXinPacketRecordDao;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.core.service.IWeiXinPacketRecordService;

/**
 * @author qinyingchun
 *微信红包记录业务逻辑处理接口实现类
 */
@Service
@Transactional
public class WeiXinPacketRecordServiceImpl implements IWeiXinPacketRecordService{
	
	@Autowired
	private IWeiXinPacketRecordDao weiXinPacketRecordDao;

	@Override
	public WeiXinPacketRecord save(WeiXinPacketRecord record) {
		return weiXinPacketRecordDao.save(record);
	}

	@Override
	public WeiXinPacketRecord findById(long id) {
		return weiXinPacketRecordDao.findOne(id);
	}

	@Override
	public WeiXinPacketRecord modify(WeiXinPacketRecord record) {
		return weiXinPacketRecordDao.save(record);
	}

	@Override
	public void delete(long id) {
		weiXinPacketRecordDao.delete(id);
	}

	@Override
	public List<WeiXinPacketRecord> sendPacket(String sendLoginName) {
		return weiXinPacketRecordDao.findBySendLoginName(sendLoginName);
	}

	@Override
	public List<WeiXinPacketRecord> receivePacket(String receiveLoginName) {
		return weiXinPacketRecordDao.findByReceiveLoginName(receiveLoginName);
	}

	@Override
	public boolean isDiscern(String sendOpenId, String receiveOpenId) {
		WeiXinPacketRecord record = weiXinPacketRecordDao.findBySendOpenIdAndReceiveOpenId(sendOpenId, receiveOpenId);
		if(null != record){
			return true;
		}
		return false;
	}

	@Override
	public List<WeiXinPacketRecord> findByReceiveOpenId(String receiveOpenId) {
		return weiXinPacketRecordDao.findByReceiveOpenIdOrderByCreateTimeDesc(receiveOpenId);
	}

	@Override
	public List<WeiXinPacketRecord> sendPacketO(String openId) {
		return weiXinPacketRecordDao.findBySendOpenIdOrderByCreateTimeDesc(openId);
	}

	
}
