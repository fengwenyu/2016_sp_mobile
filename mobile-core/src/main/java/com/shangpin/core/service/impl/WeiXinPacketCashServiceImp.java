package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeiXinPacketCashDao;
import com.shangpin.core.entity.WeiXinPacketCash;
import com.shangpin.core.service.IWeiXinPacketCashService;

@Service
@Transactional
public class WeiXinPacketCashServiceImp implements IWeiXinPacketCashService{

	@Autowired
	private IWeiXinPacketCashDao WeiXinPacketCashDao;

	@Override
	public List<WeiXinPacketCash> findCash() {
		
		return WeiXinPacketCashDao.findAll();
	}

}
