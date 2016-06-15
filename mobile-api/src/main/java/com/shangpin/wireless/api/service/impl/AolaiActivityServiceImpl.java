package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AolaiActivityDao;
import com.shangpin.wireless.api.domain.AolaiActivity;
import com.shangpin.wireless.api.service.AolaiActivityService;


@Service(AolaiActivityService.SERVICE_NAME)
public class AolaiActivityServiceImpl  implements AolaiActivityService{
	@Resource(name = AolaiActivityDao.DAO_NAME)
	private AolaiActivityDao aolaiActivityDao;

	@Override
	public AolaiActivity findAolaiActivity() {
		 List<AolaiActivity> list=aolaiActivityDao.findAolaiActivity();
		 if(list.size()>0){
			 return list.get(0); 
		 }
		return null;
	}

}
