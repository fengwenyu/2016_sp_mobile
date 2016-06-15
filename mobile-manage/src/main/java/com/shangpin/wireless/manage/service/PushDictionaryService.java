package com.shangpin.wireless.manage.service;

import java.util.List;

import com.shangpin.wireless.domain.PushDictionary;

public interface PushDictionaryService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.service.impl.PushDictionaryServiceImpl";
	List<PushDictionary> findList() throws Exception;
}
