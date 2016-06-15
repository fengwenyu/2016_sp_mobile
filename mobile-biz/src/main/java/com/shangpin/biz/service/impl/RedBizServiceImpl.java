package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.RedService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.biz.bo.Red;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.RedBizService;
import com.shangpin.biz.service.abstraction.AbstractBizCouponService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

@Service
public class RedBizServiceImpl extends AbstractBizCouponService implements RedBizService{

	private static final Logger logger = LoggerFactory.getLogger(RedBizServiceImpl.class);
	@Autowired
	private RedService redService;
	//主站红包检验URL
     private StringBuffer sendRedURL = new StringBuffer(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/returnRedCommand");
	@Override
	public Boolean checkisred(String keywords) {
		logger.debug("红包校验URl>>>>>>>>>>>>>>>>>>>"+sendRedURL);
        String result=redService.red(keywords);
        ResultObjOne<Red> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<Red>>(){});
        if(null!=obj){
        	if("1".equals(obj.getContent().getType())){
        		return true;
        	}
        }
		return false;
	}
	@Override
	public Red findRedList(String keywords) {
		 String result=redService.red(keywords);
		 Red red =new Red();
	     ResultObjOne<Red> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<Red>>(){});
	     if(null!=obj){
	        	if("1".equals(obj.getContent().getType())){
	    	red=obj.getContent();
			}
	        	}
		return red;
	}

	
}
 