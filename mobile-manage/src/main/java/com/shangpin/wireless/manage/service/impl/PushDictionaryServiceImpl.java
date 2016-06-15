package com.shangpin.wireless.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.PushDictionaryDao;
import com.shangpin.wireless.domain.PushDictionary;
import com.shangpin.wireless.manage.service.PushDictionaryService;
@Service(PushDictionaryService.SERVICE_NAME)
public class PushDictionaryServiceImpl implements PushDictionaryService {
	
	@Resource(name = PushDictionaryDao.DAO_NAME)
	private PushDictionaryDao pushDictionaryDao;
	
	@Override
	public List<PushDictionary> findList() throws Exception {
		return pushDictionaryDao.findAll();
	}

}
