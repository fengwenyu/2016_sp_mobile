package com.shangpin.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.PointBuyService;
import com.shangpin.biz.bo.PointBuyProduct;
import com.shangpin.biz.bo.PointBuyTimesList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizPointBuyService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
@Service
public class ASPBizPointBuyServiceImpl implements ASPBizPointBuyService {
	@Autowired
	private PointBuyService pointBuyService;
	@Override
	public ResultObjOne<PointBuyTimesList> findTimesList() {
		String json = pointBuyService.pointBuyTimesList();
		if (!StringUtils.isEmpty(json)) {                
            ResultObjOne<PointBuyTimesList> pointBuyTimesObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<PointBuyTimesList>>(){});
            if(Constants.SUCCESS.equals(pointBuyTimesObj.getCode())){
                return pointBuyTimesObj;
            }
        }
		return null;
	}
	@Override
	public ResultObjOne<PointBuyProduct> findProductList(String pharseId) {
		String json = pointBuyService.showProductList(pharseId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<PointBuyProduct> pointBuyProductObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<PointBuyProduct>>(){});
            if(Constants.SUCCESS.equals(pointBuyProductObj.getCode())){
                return pointBuyProductObj;
            }
		}
		return null;
	}

}
