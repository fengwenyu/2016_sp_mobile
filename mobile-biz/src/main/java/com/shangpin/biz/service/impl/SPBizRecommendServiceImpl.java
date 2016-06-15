package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.Recommend;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.service.SPBizRecommendService;
import com.shangpin.biz.service.abstraction.AbstractBizRecommendService;
import com.shangpin.biz.utils.ApiBizData;

@Service
public class SPBizRecommendServiceImpl extends AbstractBizRecommendService implements SPBizRecommendService {

	@Override
	public List<Recommend> doRecommendList(String userId) throws Exception {
		return doRecommends(userId);
	}

	@Override
	public String doRecProduct(String type, String userId, String imei, String coord, String ip, String pageIndex, String pageSize) throws Exception {
		String data = null;
		RecProductFor rec = doBaseRecProduct(type, userId, imei, coord, ip, pageIndex, pageSize);
		if (rec != null) {
			rec.setSystime(String.valueOf(System.currentTimeMillis()));
		}
		if (null == rec) {
			data = ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} else {
			data = ApiBizData.spliceData(rec, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		return data;
	}

	@Override
	public WorthTitle doRecProduct(String type, String userId, String pageIndex, String pageSize) throws Exception {
		WorthTitle worthTitle = doBaseRecWorth(type, userId, pageIndex, pageSize);
		return worthTitle;
	}
	@Override
    public List<Product> doRecProduct(String type, String userId, String shopType,String productId,String pageIndex, String pageSize) throws Exception {
        List<Product> products = new ArrayList<Product>();
        products=doBase(type, userId, shopType,productId,pageIndex, pageSize);
        return products;
    }

}

