package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IPushDataDao;
import com.shangpin.core.entity.PushData;
import com.shangpin.core.service.IPushDataService;

@Service
@Transactional
public class PushDataServiceImpl implements IPushDataService {
	
	@Autowired
	private IPushDataDao pushDao;

	@Override
	public List<PushData> findByToken(String token) {
		return pushDao.findByTokenAndStatus(token, "0");
	}
	
}
