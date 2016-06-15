package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.CollectProductList;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.ASPBizCollectService;
import com.shangpin.biz.service.abstraction.AbstractBizCollectService;
@Service
public class ASPBizCollectServiceImpl extends AbstractBizCollectService implements ASPBizCollectService {

	@Override
	public ResultObjMapList<CollectProductList> doCollectProductList(String userId, String shopType, String pageIndex, String pageSize, String postArea) {
		ResultObjMapList<CollectProductList> obj=this.beCollectProductList(userId, shopType, pageIndex, pageSize, postArea);
		if(obj!=null&&obj.getContent()!=null&&obj.getContent().get("list")!=null){
			 List<CollectProductList> collectProductList=obj.getContent().get("list");
			 List<CollectProductList> newCPL=new ArrayList<CollectProductList>();
			 for(CollectProductList cpl:collectProductList){
				 if(("1").equals(cpl.getType())){
					 cpl.setBrandNameCN("电子卡");
					 cpl.setBrandNameEN("电子卡");
				 }
				 if("2".equals(cpl.getType())){
					 cpl.setBrandNameCN("实物卡");
					 cpl.setBrandNameEN("实物卡");
				 }
				 newCPL.add(cpl);
			 }
			 obj.getContent().put("list", newCPL);
		}
		 return obj;
	}

}
