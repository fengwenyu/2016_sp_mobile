package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.DailyCouponsService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.product.model.cbwfs.PromotionTicket;
import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.product.service.intf.cbwfs.IspPromotionTicketService;
import com.shangpin.utils.HttpClientUtil;

@Service
public class DailyCouponsServiceImpl implements DailyCouponsService{
	
	@Autowired
	private IspPromotionTicketService promotionTicketService;

	/** 天天抢券判断用户是否领取该券*/
	private StringBuilder dailyCouponsGetURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/isReceiveCoupons");
	
	@Override
	public String dailyCouponsGet(String activeCode,String userId){
		Map<String, String> params=new HashMap<String,String>();
		params.put("activeCode", activeCode);
		params.put("userId", userId);
		return HttpClientUtil.doGet(dailyCouponsGetURL.toString(), params);
	}

	@Override
	public ContentBuilder<Map<String, List<PromotionTicket>>> promotionTickets(String order, String type, String pageIndex, String pageSize) {
		ContentBuilder<Map<String, List<PromotionTicket>>> contentBuilder = promotionTicketService.findByCondition(pageIndex, pageSize, order, type);
		return contentBuilder;
	}

}
