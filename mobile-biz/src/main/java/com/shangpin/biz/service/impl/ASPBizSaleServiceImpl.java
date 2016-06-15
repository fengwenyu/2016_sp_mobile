package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjMapList;
import com.shangpin.biz.bo.Operation;
import com.shangpin.biz.bo.Sale;
import com.shangpin.biz.service.ASPBizOperationService;
import com.shangpin.biz.service.ASPBizSaleService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.utils.JSONUtils;

@Service
public class ASPBizSaleServiceImpl implements ASPBizSaleService {
    private static final Logger logger = LoggerFactory.getLogger(ASPBizSaleServiceImpl.class);
	
	@Autowired
	ShangPinService shangPinService;
	
	@Autowired
	ASPBizOperationService aspBizOperationService;
	
	private ResultObjMapList<Sale> doBase(String type, String pageIndex, String pageSize) throws Exception {
		String baseData = shangPinService.findMoreSale(type, pageIndex, pageSize);
		ResultObjMapList<Sale> obj = new ResultObjMapList<Sale>();
		if (StringUtils.isNotEmpty(baseData)) {
			obj =JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class,Sale.class);
		}
		return obj;
	}

    @Override
	public List<Sale> queryHomeSale(){
		List<Sale> saleList = new ArrayList<Sale>();
		try {
            String json=shangPinService.findHomeSale();
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                if (StringUtils.isNotEmpty(json)) {
                    ResultObjMapList<Sale> obj=JSONUtils.toGenericsCollection(json, ResultObjMapList.class,Sale.class);
                    if(obj!=null&&obj.getList("sale")!=null){
                        saleList=obj.getList("sale");
                        return saleList;
                    }
                   
                }
                
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public String getMoreSale(String type, String pageIndex, String pageSize) throws Exception {
		String systime = Long.toString(System.currentTimeMillis());
		Map<String, Object> map = new HashMap<String, Object>();
		ResultObjMapList<Sale> obj = doBase(type, pageIndex, pageSize);
		if ("1".equals(type)) {
			List<Operation> operation = aspBizOperationService.doOperation("", pageIndex, pageSize);
			map.put("operation", operation);
		}else if("5".equals(type)){
			List<Operation> operation = aspBizOperationService.doOperation(type, pageIndex, pageSize);
			map.put("operation", operation);
		}
		if (!obj.getContent().isEmpty()) {
			map.putAll(obj.getContent());
		}
		
		map.put("sysTime", systime);
		
		return ApiBizData.spliceData(map, obj.getCode(), obj.getMsg());
		
	}
	
	public List<Sale> getHotSale(String type, String pageIndex, String pageSize) throws Exception {
		ResultObjMapList<Sale> obj = doBase(type, pageIndex, pageSize);
		  if(obj!=null&&obj.getList("sale")!=null){
			  return  obj.getList("sale");
          }
		return null;
		
	}
	
}
