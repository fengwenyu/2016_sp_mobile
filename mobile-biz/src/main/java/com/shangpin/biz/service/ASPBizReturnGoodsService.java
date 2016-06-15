package com.shangpin.biz.service;
/**
 * 退货
 * @author Administrator
 *
 */
public interface ASPBizReturnGoodsService {
	/**
	 * 可退货列表查询
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String returnGoodList(String userId,String pageIndex,String pageSize);
	
	/**
     * 支持多件退，退货列表
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String returnProList(String userId,String pageIndex,String pageSize);
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
	 * @param count 商品数量
	 * @param orgin 来源，1表示APP，0表示M站
	 * @return
	 */
	public String returnSubmit(String userId,String orderNo, String productNo, String orderDetailNo, String skuNo,String returnReason,String bankInfo,String imageList,String returnReasonDetail,String refundType,String remark,String supplierOrderNo, String count, String orgin);
	/**
	 * 退货进度详情
	 * @param userId
	 * @param returnId
	 * @return
	 */
	public String returnDetail(String userId, String returnId);
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
	 * 退货申请提交添加联系人
	 * @param userId
	 * @param orderNo
	 * @param productNo
	 * @param orderDetailNo
	 * @param skuNo
	 * @param returnReason
	 * @param bankInfo
	 * @param imageList
	 * @param returnReasonDetail
	 * @param refundType
	 * @param remark
	 * @param supplierOrderNo
	 * @param count
	 * @param string
	 * @param consignee
	 * @param phone
	 * @return
	 */
    public String returnNewSubmit(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String returnReason, String bankInfo, String imageList,
            String returnReasonDetail, String refundType, String remark, String supplierOrderNo, String count, String string, String consignee, String phone);
    /**
     * 取消订单审核
     * @param userId
     * @param returnId
     * @return
     */
    public String cancelRtCheck(String userId, String returnId);
    /**
     * 根据商品数量获取退款金额信息
     * @param userId
     * @param orderNo
     * @param productNo
     * @param orderDetailNo
     * @param skuNo
     * @param count
     * @return
     */
    public String refundAmountDetails(String userId, String orderNo, String productNo, String orderDetailNo, String skuNo, String count);
}
