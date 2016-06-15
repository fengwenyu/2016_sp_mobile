package com.shangpin.biz.service.impl;

import com.shangpin.base.vo.Address;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;
import com.shangpin.biz.service.ASPBizSettlementService;
import com.shangpin.biz.service.abstraction.AbstractBizSettlementService;

import org.springframework.stereotype.Service;

/**
 * <br/>
 * Date: 2016/4/22<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@Service
public class ASPBizSettlementServiceImpl extends AbstractBizSettlementService implements ASPBizSettlementService {
    /**
     * 购物车结算
     *
     * @param userid             用户id
     * @param buyId              购物车id
     * @param isDefaultUseCoupon 1使用优惠券，0 不使用
     * @return
     */
    @Override
    public ResultObjOne<SettlementUnion> cartSettlement(String userid, String buyId, String isDefaultUseCoupon) {
        return this.doCartSettlement(userid, buyId, isDefaultUseCoupon);
    }

    /**
     * 立即购买结算
     *
     * @param buyNowDo BuyNowDo
     * @return
     */
    @Override
    public ResultObjOne<SettlementUnion> buyNowSettlement(BuyNowDo buyNowDo) {
        return this.doBuyNowSettlement(buyNowDo);
    }

    /**
     * 刷新结算页
     *
     * @param refreshDo@return
     */
    @Override
    public ResultObjOne<SettlementUnion> refreshSettlement(RefreshDo refreshDo) {
        return this.doRefreshSettlement(refreshDo);
    }

	@Override
	public String addresslist(String userid,String buyId,String receiveId,String ordersourceId,String buyType) {
		return this.doaddresslist(userid, buyId, receiveId,ordersourceId,buyType);
	}
}
