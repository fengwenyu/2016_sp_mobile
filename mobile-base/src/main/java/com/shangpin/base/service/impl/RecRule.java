package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shangpin.base.service.RecRuleService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.Product;
import com.shangpin.utils.HttpClientUtil;
@Service
public class RecRule implements RecRuleService {

	//推荐规则URL
     private StringBuffer sendRedURL =new StringBuffer(GlobalConstants.BASE_URL_REPCONMONED);
    //搜索商品地址
    private StringBuilder subjectProductListURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("Product");
	public String doRecRuleProduct(String userId, String imei,String offset,String num)throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("imei", imei);
		map.put("offset",offset );//开始查询的条数
		if(null!=num&&!"".equals(num)){
			map.put("num", num);
		}else{
			map.put("num", "0");
		}
		String result=HttpClientUtil.doGet(sendRedURL.toString(), map);
        return result;
	}

	@Override
	public String findByProductNos(List<String> productNos) {
		if(CollectionUtils.isEmpty(productNos)){
			return null;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < productNos.size(); i++) {
			String str=	productNos.get(i).toString();
			builder.append(str).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize",String.valueOf(productNos.size()));
		params.put("productNo", builder.toString());
		String result=HttpClientUtil.doGet(subjectProductListURL.toString(), params);
		
		return result;
	}

}
