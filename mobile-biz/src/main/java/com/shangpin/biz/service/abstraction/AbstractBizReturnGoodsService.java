package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ReturnGoodsService;
import com.shangpin.biz.bo.ReturnContent;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
/**
 * 退货功能接口
 * @author Administrator
 *
 */
public abstract class AbstractBizReturnGoodsService {

    @Autowired
    private ReturnGoodsService returnGoodsService;
    

    public String returnGoodList(String userId, String pageIndex,
            String pageSize) {
        String data = returnGoodsService.returnGoodList(userId, pageIndex, pageSize);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    public String returnProList(String userId, String pageIndex,
            String pageSize) {
        String data = returnGoodsService.returnProList(userId, pageIndex, pageSize);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }

    public String returnGoodInit(String userId, String orderNo,
            String productNo, String orderDetailNo, String skuNo) {
        String data = returnGoodsService.returnGoodInit(userId, orderNo, productNo, orderDetailNo, skuNo);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }


    public String returnSubmit(String userId, String orderNo, String productNo,
            String orderDetailNo, String skuNo, String returnReason,
            String bankInfo, String imageList,String returnReasonDetail,String refundType,String remark,String supplierOrderNo, String count, String orgin) {
        String data = returnGoodsService.returnSubmit(userId, orderNo, productNo, orderDetailNo, skuNo, returnReason, bankInfo, imageList,returnReasonDetail,refundType,remark,supplierOrderNo, count,orgin);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public String returnNewSubmit(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String returnReason, String bankInfo, String imageList,
            String returnReasonDetail, String refundType, String remark, String supplierOrderNo, String count, String orgin, String consignee, String phone) {
        String data = returnGoodsService.returnNewSubmit(userId, orderNo, productNo, orderDetailNo, skuNo, returnReason, bankInfo, imageList,returnReasonDetail,refundType,remark,supplierOrderNo, count,orgin,consignee,phone);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    public String refundAmountDetails(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String count) {
        String data = returnGoodsService.refundAmountDetails(userId,orderNo,productNo,orderDetailNo,skuNo,count);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    public String returnDetail(String userId, String returnId) {
        String data = returnGoodsService.returnDetail(userId, returnId);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public ReturnContent returnDetailObj(String userId, String returnId){
    	String json = this.returnDetail(userId, returnId);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	ResultObjOne<ReturnContent> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ReturnContent>>(){});
    	if(null != obj && Constants.SUCCESS.equals(obj.getCode())){
    		return obj.getObj();
    	}
    	return null;
    }


    public String returnDestination(String userId, String orderNo,
            String productNo, String orderDetailNo, String skuNo, String returnId) {
        String data = returnGoodsService.returnDestination(userId, orderNo, productNo, orderDetailNo, skuNo, returnId);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    
    public String cancelRtCheck(String userId, String returnId) {
        String data = returnGoodsService.cancelRtCheck(userId, returnId);
        if(StringUtils.isEmpty(data)){
            return null;
        }
        return data;
    }
    public String logisticsSubmit(String userId, String applyNo, String logisticsNo, String logisticsCompany){
    	String json = returnGoodsService.logisticsSubmit(userId, applyNo, logisticsNo, logisticsCompany);
    	return json;
    }
    
    public ResultBase logisticsSubmitObj(String userId, String applyNo, String logisticsNo, String logisticsCompany){
    	String json = this.logisticsSubmit(userId, applyNo, logisticsNo, logisticsCompany);
    	if(StringUtils.isEmpty(json)){
    		return null;
    	}
    	return JsonUtil.fromJson(json, ResultBase.class);
    }
}
