package com.shangpin.wireless.api.service;

import com.shangpin.wireless.api.domain.AolaiActivity;




public interface AolaiActivityService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AolaiActivityServiceImpl";

	AolaiActivity findAolaiActivity();

}
