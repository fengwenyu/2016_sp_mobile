package com.shangpin.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.CMSservice;
import com.shangpin.product.service.intf.cbwfs.IspCMSService;

@Service
public class CMSserviceImpl implements CMSservice {
	@Autowired
	private IspCMSService ispCMSService;

	@Override
	public Object getSearchKeyword() throws Exception {
		return ispCMSService.searchKeyword();
	}

}
