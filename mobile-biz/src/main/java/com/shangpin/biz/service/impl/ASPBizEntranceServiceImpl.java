package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Entrance;
import com.shangpin.biz.service.ASPBizEntranceService;
import com.shangpin.biz.service.abstraction.AbstractBizEntranceService;

@Service
public class ASPBizEntranceServiceImpl extends AbstractBizEntranceService implements ASPBizEntranceService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ASPBizEntranceServiceImpl.class);

	@Override
	public List<Entrance> queryEntranceList(String type, String pageIndex, String pageSize) {
		List<Entrance> entranceList = new ArrayList<Entrance>();
		if (entranceList != null) {
			entranceList = queryEntrance(type, pageIndex, pageSize);
		}
		return entranceList;
	}
}
