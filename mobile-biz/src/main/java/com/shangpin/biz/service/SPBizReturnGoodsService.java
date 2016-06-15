package com.shangpin.biz.service;

import com.shangpin.biz.bo.ReturnContent;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultBaseNew;

public interface SPBizReturnGoodsService {
	/**
	 * 可退货列表查询
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String returnGoodList(String userId,String pageIndex,String pageSize);
	/**
	 * 退货初始化
	 * @param userId
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @return
	 */
	public String returnGoodInit(String userId,String orderNo, String productNo, String orderDetailNo, String skuNo);
	/**
	 * 退货申请提交
	 * @param userId
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @param returnReason
	 * @param bankInfo
	 * @param imageList
	 * @return
	 */
	public String returnSubmit(String userId,String orderNo, String productNo, String orderDetailNo, String skuNo,String returnReason,String bankInfo,String imageList,String returnReasonDetail,String refundType,String remark);
	/**
	 * 退货进度详情
	 * @param userId
	 * @param returnId
	 * @return
	 */
	public String returnDetail(String userId, String returnId);
	/**
	 * 退货进度详情返回实体类
	 * @param userId
	 * @param returnId
	 * @return
	 */
	public ResultBaseNew returnDetailPojo(String userId, String returnId);
	/**
	 * 退款去向
	 * @param userId
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @return
	 */
	public String returnDestination(String userId,String orderNo, String productNo, String orderDetailNo, String skuNo, String returnId);
	
	/**
	 * 退货详情
	 * @param userId
	 * @param returnId
	 * @return
	 */
	public ReturnContent returnDetailObj(String userId,String returnId);
	
	/**
	 * @author qinyingchun
	 * 提交退货物流信息
	 * @param userId 用户编号
	 * @param applyNo 申请单号
	 * @param logisticsNo 物流单号
	 * @param logisticsCompany 物流公司
	 * @return
	 */
	public String logisticsSubmit(String userId, String applyNo, String logisticsNo, String logisticsCompany);
    	
	/**
	 * @author qinyingchun
	 * 提交退货物流信息
	 * @param userId 用户编号
	 * @param applyNo 申请单号
	 * @param logisticsNo 物流单号
	 * @param logisticsCompany 物流公司
	 * @return
	 */
    public ResultBase logisticsSubmitObj(String userId, String applyNo, String logisticsNo, String logisticsCompany);

}
