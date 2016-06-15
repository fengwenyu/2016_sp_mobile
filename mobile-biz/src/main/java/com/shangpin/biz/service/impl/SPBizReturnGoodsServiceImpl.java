package com.shangpin.biz.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.ReturnContent;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.service.SPBizReturnGoodsService;
import com.shangpin.biz.service.abstraction.AbstractBizReturnGoodsService;

@Service
public class SPBizReturnGoodsServiceImpl extends AbstractBizReturnGoodsService implements SPBizReturnGoodsService {
    /**
     * 返回实体类ResultBaseNew
     */
    @Override
    public ResultBaseNew returnDetailPojo(String userId,String returnId) {
        String date = this.returnDetail(userId, returnId);
        if (StringUtils.isBlank(date)) {
            return null;
        }
        ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(date,ReturnContent.class);
        return resultBaseNew;
    }
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
     */
    @Override
    public String returnSubmit(String userId, String orderNo, String productNo,
            String orderDetailNo, String skuNo, String returnReason,
            String bankInfo, String imageList, String returnReasonDetail,
            String refundType, String remark) {
        // TODO Auto-generated method stub
        return null;
    }
}
