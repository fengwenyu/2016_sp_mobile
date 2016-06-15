package com.shangpin.api.controller;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizReturnGoodsService;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.utils.JsonUtil;
/**
 * 退货功能接口
 * @author Administrator
 *
 */
@Controller
public class ReturnGoodsController  extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsController.class);
	@Autowired
	private ASPBizReturnGoodsService returnGoodsService;
	@Autowired
	private ASPBizUserService aSPBizUserService;
	/**
	 * 退货列表查询
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnGoodList", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnGoodList(HttpServletRequest request,String pageIndex,String pageSize){
		String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String data = returnGoodsService.returnGoodList(userId, pageIndex, pageSize);
		return data;
	}
	
    /**
     * 支持多件退，退货列表
     * @param request
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/returnProList")
    public String returnProList(HttpServletRequest request,String pageIndex,String pageSize){
        String userId = request.getHeader("userid");
        if(userId==null||"".equals(userId)){
            userId = request.getParameter("userId");
        }
        logger.info("userId:"+userId+"---------returnProList");
        String data = returnGoodsService.returnProList(userId, pageIndex, pageSize);
        return data;
    }
	/**
	 * 退货初始化
	 * @param request
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnGoodInit", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnGoodInit(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, String skuNo){
		String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	logger.info("userId:"+userId, "orderNo:"+orderNo, "productNo:"+productNo, "orderDetailNo:"+orderDetailNo, "skuNo:"+skuNo);
    	String data = returnGoodsService.returnGoodInit(userId, orderNo, productNo, orderDetailNo, skuNo);
        if (!StringUtils.isEmpty(data)) {
            ResultObjOne<Object> result = JsonUtil.fromJson(data, new TypeToken<ResultObjOne<Object>>() {});
            if (result.getCode().equals("0")) {
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject content = obj.getJSONObject("content"); 
                JSONObject refundAmount = content.getJSONObject("refundAmount");
                if (!refundAmount.equals("null")) {
                  String domain=aSPBizUserService.getShangpinDomain();
                  refundAmount.put("ruleUrl", domain+"returnGoods/clRules" );
                  refundAmount.put("isClose", "0" );
                }
                return obj.toString();
            }
        }
	    return data;
	}
	
	/**
     * 根据件数获取退款金额信息
     * @param request
     * @param orderNo
     * @param productNo
     * @param orderDetailNo
     * @param skuNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/refundAmountDetails")
    public String refundAmountDetails(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, String skuNo, String count){
        String userId = request.getHeader("userid");
        if(userId==null||"".equals(userId)){
            userId = request.getParameter("userId");
        }
        String data = returnGoodsService.refundAmountDetails(userId, orderNo, productNo, orderDetailNo, skuNo,count);
        if (!StringUtils.isEmpty(data)) {
            ResultObjOne<Object> result = JsonUtil.fromJson(data, new TypeToken<ResultObjOne<Object>>() {});
            if (result.getCode().equals("0")) {
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject content = obj.getJSONObject("content"); 
                if (!content.equals("null")) {
                  String domain=aSPBizUserService.getShangpinDomain();
                  content.put("ruleUrl", domain+"returnGoods/clRules" );
                }
                return obj.toString();
            }
        }
        return data;
    }
	/**
	 * 退货申请提交
	 * @param request
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @param returnReason
	 * @param bankInfo
	 * @param imageList
	 * @param returnReasonDetail
	 * @param refundType
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnSubmit", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnSubmit(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, 
			String skuNo,String returnReason,String bankInfo,String imageList,String returnReasonDetail,String refundType,String remark,String supplierOrderNo, String count){
		String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String data = returnGoodsService.returnSubmit(userId, orderNo, productNo, orderDetailNo, skuNo, returnReason, bankInfo, imageList,returnReasonDetail,refundType,remark,supplierOrderNo,count,"3");
		return data;
	}
	/**
     * 新多件退货申请提交
     * @param request
     * @param orderNo
     * @param productNo
     * @param orderDetailNo
     * @param skuNo
     * @param returnReason
     * @param bankInfo
     * @param imageList
     * @param returnReasonDetail
     * @param refundType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/returnNewSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public String returnNewSubmit(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, 
            String skuNo,String returnReason,String bankInfo,String imageList,String returnReasonDetail,String refundType,String remark,String supplierOrderNo, String count,String consignee,String phone){
        String userId = request.getHeader("userid");
        if(userId==null||"".equals(userId)){
            userId = request.getParameter("userId");
        }
        logger.info("userId:"+userId, "orderNo:"+orderNo, "productNo:"+productNo, "orderDetailNo:"+orderDetailNo, "skuNo:"+skuNo, "returnReason:"+returnReason, "bankInfo:"+bankInfo, "imageList:"+imageList,"returnReasonDetail:"+returnReasonDetail,"refundType:"+refundType,"remark:"+remark,"supplierOrderNo:"+supplierOrderNo,"count:"+count,"3","consignee:"+consignee,"phone:"+phone);
        String data = returnGoodsService.returnNewSubmit(userId, orderNo, productNo, orderDetailNo, skuNo, returnReason, bankInfo, imageList,returnReasonDetail,refundType,remark,supplierOrderNo,count,"3",consignee,phone);
        if (!StringUtils.isEmpty(data)) {
            ResultObjOne<Object> result = JsonUtil.fromJson(data, new TypeToken<ResultObjOne<Object>>() {});
            if (result.getCode().equals("0")) {
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject content = obj.getJSONObject("content"); 
                String domain=aSPBizUserService.getShangpinDomain();
                content.put("url", domain+"/help/app_return_process.html" );
                content.put("webTitle", "查看完整退货流程" );
                return obj.toString();
            }
        }
        return data;
    }
    
   /**取消申请
    * @param request
    * @param returnId
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/cancelReturns")
    public String cancelRtCheck(HttpServletRequest request,String returnId){
        String userId = request.getHeader("userid");
        if(userId==null||"".equals(userId)){
            userId = request.getParameter("userId");
        }
        logger.info("userId:"+userId+"returnId:"+returnId);
        String data = returnGoodsService.cancelRtCheck(userId,returnId);
        logger.info("return:----------"+data);
        return data;
    }
	/**
	 * 退货进度详情
	 * @param request
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnDetail(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, String skuNo, String returnId){
		String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String data = returnGoodsService.returnDetail(userId, returnId);
		return data;
	}
	/**
	 * 退款去向
	 * @param request
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/returnDestination", method = { RequestMethod.POST, RequestMethod.GET })
	public String returnDestination(HttpServletRequest request,String orderNo, String productNo, String orderDetailNo, String skuNo, String returnId){
		String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String data = returnGoodsService.returnDestination(userId, orderNo, productNo, orderDetailNo, skuNo, returnId);
		return data;
	}
}
