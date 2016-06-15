package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.ReturnGoodsService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.utils.HttpClientUtil;
@Service
public class ReturnGoodsServiceImpl implements ReturnGoodsService {

	private StringBuilder returnGoodListRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnGoodList");
	private StringBuilder returnGoodInitRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnGoodInit");
	private StringBuilder returnSubmitRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnSubmit");
	private StringBuilder returnNewSubmitRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnNewSubmit");
	private StringBuilder returnDetailRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnDetail");
	private StringBuilder returnDestinationRUL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/returnDestination");
	private StringBuilder returnlogisticsSubmitURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/returnlogisticsSubmit");
	private StringBuilder cancelRtCheckURL =new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/cancelReturns");
	private StringBuilder returnProListRUL =new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/returnProList");
	private StringBuilder refundAmountDetailsRUL=new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("account/refundAmountDetails");
	@Override
	public String returnGoodList(String userId, String pageIndex,
			String pageSize) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return HttpClientUtil.doGet(returnGoodListRUL.toString(), params);
	}
	@Override
    public String returnProList(String userId, String pageIndex,
            String pageSize) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return HttpClientUtil.doGet(returnProListRUL.toString(), params);
    }
	@Override
	public String returnGoodInit(String userId, String orderNo,
			String productNo, String orderDetailNo, String skuNo) {
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("orderNo", orderNo);
		params.put("productNo", productNo);
		params.put("userId", userId);
		params.put("orderDetailNo", orderDetailNo);
		params.put("skuNo", skuNo);
		if(returnGoodInitRUL.indexOf("http://www20.tradeapiliantiao.com/")>-1){
			params.put("returnDescUrl", "http://192.168.20.77/mshangpin/returnGoods/returnExplain");
		}else{
			params.put("returnDescUrl", "http://m.shangpin.com/returnGoods/returnExplain");
		}		
		return HttpClientUtil.doGet(returnGoodInitRUL.toString(), params);
	}

	@Override
	public String returnSubmit(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String returnReason,
			String bankInfo, String imageList,String returnReasonDetail,String refundType,String remark,String supplierOrderNo, String count, String orgin) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderNo", orderNo);
		params.put("productNo", productNo);
		params.put("userId", userId);
		params.put("orderDetailNo", orderDetailNo);
		params.put("skuNo", skuNo);
		params.put("returnReason", returnReason);
		params.put("bankInfo", bankInfo);
		params.put("imageList", imageList);
		params.put("returnReasonDetail", returnReasonDetail);
		params.put("refundType", refundType);
		params.put("remark", remark);
		params.put("supplierOrderNo", supplierOrderNo);
		params.put("count", count);
		params.put("orgin", orgin);
		return HttpClientUtil.doGet(returnSubmitRUL.toString(), params);
	}
	
	 @Override
	    public String returnNewSubmit(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String returnReason, String bankInfo, String imageList,
	            String returnReasonDetail, String refundType, String remark, String supplierOrderNo, String count, String orgin, String consignee, String phone) {
	     HashMap<String, String> params = new HashMap<String, String>();
	        params.put("orderNo", orderNo);
	        params.put("productNo", productNo);
	        params.put("userId", userId);
	        params.put("orderDetailNo", orderDetailNo);
	        params.put("skuNo", skuNo);
	        params.put("returnReason", returnReason);
	        params.put("bankInfo", bankInfo);
	        params.put("imageList", imageList);
	        params.put("returnReasonDetail", returnReasonDetail);
	        params.put("refundType", refundType);
	        params.put("remark", remark);
	        params.put("supplierOrderNo", supplierOrderNo);
	        params.put("count", count);
	        params.put("orgin", orgin);
	        params.put("consignee", consignee);
            params.put("phone", phone);
	        return HttpClientUtil.doGet(returnNewSubmitRUL.toString(), params);
	    }

	@Override
	public String returnDetail(String userId, String returnId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("returnId", returnId);
		return HttpClientUtil.doGet(returnDetailRUL.toString(), params);
	}

	@Override
	public String returnDestination(String userId, String orderNo,
			String productNo, String orderDetailNo, String skuNo, String returnId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderNo", orderNo);
		params.put("productNo", productNo);
		params.put("userId", userId);
		params.put("orderDetailNo", orderDetailNo);
		params.put("skuNo", skuNo);
		params.put("returnId", returnId);
		return HttpClientUtil.doGet(returnDestinationRUL.toString(), params);
	}

	@Override
	public String logisticsSubmit(String userId, String applyNo, String logisticsNo, String logisticsCompany) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("applyNo", applyNo);
		params.put("logisticsNo", logisticsNo);
		params.put("logisticsCompany", logisticsCompany);
		return HttpClientUtil.doGet(returnlogisticsSubmitURL.toString(), params);
	}

    @Override
    public String cancelRtCheck(String userId, String returnId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("returnId", returnId);
        return HttpClientUtil.doGet(cancelRtCheckURL.toString(), params);
    }
    @Override
    public String refundAmountDetails(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String count) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("productNo", productNo);
        params.put("userId", userId);
        params.put("orderDetailNo", orderDetailNo);
        params.put("skuNo", skuNo);
        params.put("count", count);
        return HttpClientUtil.doGet(refundAmountDetailsRUL.toString(), params);
    }


}
