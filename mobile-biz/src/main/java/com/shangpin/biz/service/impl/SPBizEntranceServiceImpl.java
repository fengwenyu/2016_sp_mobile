package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Entrance;
import com.shangpin.biz.service.SPBizEntranceService;
import com.shangpin.biz.service.abstraction.AbstractBizEntranceService;

@Service
public class SPBizEntranceServiceImpl extends AbstractBizEntranceService implements SPBizEntranceService {

	@Override
	public List<Entrance> queryEntranceList(String type, String pageIndex, String pageSize) {
		List<Entrance> entranceList = new ArrayList<Entrance>();
		entranceList = queryEntrance(type, pageIndex, pageSize);
		if (entranceList == null) {
			return null;
		}
		return entranceList;
	}

}
