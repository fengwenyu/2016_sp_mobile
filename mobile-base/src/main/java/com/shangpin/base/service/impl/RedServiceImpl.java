package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.RedService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;
@Service
public class RedServiceImpl implements RedService {

	//主站红包检验URL
    private StringBuffer sendRedURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/returnRedCommand");
    
    
	public String red(String keywords) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keywords", keywords);
		String result=HttpClientUtil.doGet(sendRedURL.toString(), map);
        return result;		
	}

}
