package com.shangpin.biz.service.impl;

import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.base.vo.SubmitOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.OrderItem;
import com.shangpin.biz.bo.base.ResultObjOneResult;
import com.shangpin.biz.service.ASPBizOrderService;
import com.shangpin.biz.service.abstraction.AbstractBizOrderService;

import java.util.Map;

@Service
public class ASPBizOrderServiceImpl extends AbstractBizOrderService implements ASPBizOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ASPBizOrderServiceImpl.class);

	@Override
	public OrderItem findOrderDetail(String userId, String mainOrderNum) {
		try {
			ResultObjOneResult<OrderItem> obj = beOrderDetail(userId, mainOrderNum);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("调用主站查询订单简单信息接口返回数据错误!" + e);
		}
		return null;
	}

	/**
	 * 2.9.12 订单提交(尚品)
	 *
	 * @param map
	 * @return
	 */
	@Override
	public ResultObjOne<SubmitOrder> submitOrderV2(Map<String, String> map) {
		return null;
	}
}
