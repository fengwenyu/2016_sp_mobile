package com.shangpin.biz.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import com.shangpin.biz.service.ASPBizCartService;
import com.shangpin.biz.service.abstraction.AbstractBizCartService;

import java.util.Map;

@Service
public class ASPBizCartServiceImpl extends AbstractBizCartService implements ASPBizCartService {

	@Override
	public String doShowCart(String userId, String isChecked) {
		return doBaseShowCart(userId, isChecked);
	}

	@Override
	public String doModifyCart(String userId, String cartItem, String isChecked,String region) {
		return doBaseModifyCart(userId, cartItem, isChecked,region);
	}

	@Override
	public String doDeleteCart(String userId, String cartDetailId, String isChecked) {
		return doBaseDeleteCart(userId, cartDetailId, isChecked);
	}

	@Override
	public String doShowCartV2(String userId, String isChecked){
		return doBaseShowCartV2(userId, isChecked);
	}
	@Override
	public String doModifyCartV2(String userId, String cartItem, String isChecked){
		return doBaseModifyCartV2(userId, cartItem, isChecked, null, null);
	}

	@Override
	public String doDeleteCartV2(String userId, String cartDetailId, String isChecked){
		return doBaseDeleteCartV2(userId, cartDetailId, isChecked);
	}

}
