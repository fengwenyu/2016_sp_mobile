package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.service.ASPBizCategoryOperationService;

@Service
public class ASPBizCategoryOperationServiceImpl implements ASPBizCategoryOperationService {
    private static final Logger logger = LoggerFactory.getLogger(ASPBizCategoryOperationServiceImpl.class);
	@Autowired
	ShangPinService shangPinService;
	
	@Override
    public String queryCategoryOperationToResult(String id, String userId, String mark) {
        try {
            String json = shangPinService.queryCategoryOperation(id, userId, mark);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                return json;
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String queryCategoryOperationToResult(String id,String userId) {
        try {
            String json = shangPinService.queryCategoryOperation(id, userId);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                return json;
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public String getCategoryOperations(String id, String userId) throws Exception {
		return shangPinService.queryCategoryOperations(id, userId);
	}
}
