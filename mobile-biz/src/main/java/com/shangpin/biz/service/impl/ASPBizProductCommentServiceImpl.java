package com.shangpin.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.ProductComment;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizProductCommentlService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizProductCommentServiceImpl implements ASPBizProductCommentlService {
    private static final Logger logger = LoggerFactory.getLogger(ASPBizProductCommentServiceImpl.class);
	@Autowired
	ShangPinService shangPinService;
	


    @Override
    public ProductComment queryProductComment(String productId, String pageIndex, String pageSize) {
        try {
            String json = shangPinService.findCommentList(productId, pageIndex, pageSize,"");
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {                
                ResultObjOne<ProductComment> productCommentObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductComment>>(){});
                if(Constants.SUCCESS.equals(productCommentObj.getCode())){
                    return productCommentObj.getObj();
                }
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public String queryProductCommentToResult(String productId, String pageIndex, String pageSize,String tagId) {
        try {
            String json = shangPinService.findCommentList(productId, pageIndex, pageSize,tagId);
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
}
